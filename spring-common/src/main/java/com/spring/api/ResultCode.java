package com.spring.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode implements IErrorCode {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     */
    FAILED(500, "操作失败"),
    /**
     * 参数校验失败
     */
    VALIDATE_FAILED(404, "参数检验失败"),
    /**
     * 没有相关权限
     */
    FORBIDDEN(403, "没有相关权限");

    private final Long code;

    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
