package com.spring.exception;

import com.spring.api.CommonResult;
import com.spring.api.IErrorCode;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Resource
    private ApplicationContext applicationContext;

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult<?> handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult<?> handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public CommonResult<?> handleValidException(IllegalArgumentException e) {
        return CommonResult.validateFailed(e.getMessage());
    }

    /**
     * 处理自定义业务异常
     */
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult<?> handleValidException(ApiException e) {
        String message = e.getMessage();
        IErrorCode errorCode = e.getErrorCode();
        if (errorCode != null && errorCode.getCode() != null) {
            message = applicationContext.getMessage(String.valueOf(errorCode.getCode()), null, message, LocaleContextHolder.getLocale());
        }
        return CommonResult.validateFailed(message);
    }

}
