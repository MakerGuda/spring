package com.spring.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {

    /**
     * 分布式锁的key
     */
    String key();

    /**
     * 等待锁超时时间
     */
    long waitTime() default 1;

    /**
     * 锁过期时间
     */
    long leaseTime() default 30;

    /**
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

}
