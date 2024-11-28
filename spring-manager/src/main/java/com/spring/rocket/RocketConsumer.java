package com.spring.rocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "${rocketmq.consumer.topic}", consumerGroup = "consumer_i")
public class RocketConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("message:{}", message);
    }

}
