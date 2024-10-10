package com.spring;

import com.spring.api.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("test")
public class TestController {

    @Value("${server.url}")
    private String url;

    @GetMapping("nacos")
    public CommonResult<String> nacos() {
        return CommonResult.success(url);
    }

}
