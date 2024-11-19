package com.spring.aspect;

import com.spring.annotation.RedisLock;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁的切面
 */
@Slf4j
@Aspect
@Component
public class RedisLockAspect {

    @Resource
    private RedissonClient redissonClient;

    @Around("@annotation(redisLock)")
    public Object aroundRedisLock(ProceedingJoinPoint pjp, RedisLock redisLock) throws Throwable {
        log.info("=====请求排队尝试获取锁=====");
        String redisKey = redisLock.key();
        Assert.hasText(redisKey, "@RedisLock key不能为空!!!");
        long waitTime = redisLock.waitTime();
        long leaseTime = redisLock.leaseTime();
        TimeUnit timeUnit = redisLock.timeUnit();
        RLock lock = redissonClient.getLock(redisKey);
        boolean locked = lock.tryLock(waitTime, leaseTime, timeUnit);
        //没有获取到锁
        if (!locked) {
            return null;
        }
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            log.error("方法执行失败:{}", e.getMessage(), e);
            throw e;
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

}
