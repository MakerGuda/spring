package com.spring.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult<T> {

    /**
     * 状态码
     */
    private Long code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据封装
     */
    private T data;

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

}
