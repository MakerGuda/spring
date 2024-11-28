package com.spring.controller;

import com.spring.api.CommonResult;
import com.spring.entity.User;
import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rocket")
public class RocketController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.consumer.topic}")
    private String topic;

    @GetMapping("send")
    public CommonResult<Void> send() {
        User user = new User();
        user.setId(1L);
        user.setName("spring");
        Message<User> message = MessageBuilder.withPayload(user).build();
        rocketMQTemplate.convertAndSend(topic, message);
        return CommonResult.success(null);
    }

}
