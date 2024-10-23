package com.spring.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;

/**
 * 国际化多语言拦截器
 */
@Slf4j
@Component
public class LocaleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String value = request.getHeader("lang");
        log.info("国际化多语言拦截器，当前获取到的语言信息为:{}", value);
        if (StringUtils.isNotBlank(value)) {
            LocaleContextHolder.setLocale(Locale.forLanguageTag(value));
        } else {
            LocaleContextHolder.setLocale(Locale.CHINESE);
        }
        return true;
    }

}
