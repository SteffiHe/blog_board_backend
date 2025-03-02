package com.example.blog_system.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {
    SUCCESS("200", "success"),
    FAILED("500", "internal server error"),
    VALIDATE_FAILED("404", "validate failed"),
    UNAUTHORIZED("401", "unauthorized"),
    FORBIDDEN("403", "forbidden");

    private String code;
    private String message;


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
