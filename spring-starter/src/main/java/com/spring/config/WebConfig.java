package com.spring.config;

import com.spring.interceptor.LocaleInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LocaleInterceptor localeInterceptor;

    /**
     * 自定义拦截器相关设置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //所有接口
                .allowedOrigins("*").allowCredentials(true) //是否发送cookie
                .allowedOriginPatterns("*") //支持域
                .allowedMethods("GET", "POST")  //支持方法
                .allowedHeaders("*")    //支持的请求头
                .exposedHeaders("*");
    }

}
