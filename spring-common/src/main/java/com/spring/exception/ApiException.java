package com.spring.exception;

import com.spring.api.IErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(IErrorCode errorCode, String message) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
