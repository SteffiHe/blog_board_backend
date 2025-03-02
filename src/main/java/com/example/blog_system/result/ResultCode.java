package com.example.blog_system.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {
    SUCCESS("200", "success"),
    BAD_REQUEST("400", "bad request"),
    UNAUTHORIZED("401", "unauthorized"),
    NOT_FOUND("404", "not found"),
    FORBIDDEN("403", "forbidden"),
    FAILED("500", "internal server error");

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
