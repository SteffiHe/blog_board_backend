package com.example.blog_system.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> failure() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAILED.getCode());
        result.setMessage(ResultCode.FAILED.getMessage());
        return result;
    }

    public static <T> Result<T> failure(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.FAILED.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> notFound() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.NOT_FOUND.getCode());
        result.setMessage(ResultCode.NOT_FOUND.getMessage());
        return result;
    }

    public static <T> Result<T> BAD_REQUEST() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.BAD_REQUEST.getCode());
        result.setMessage(ResultCode.BAD_REQUEST.getMessage());
        return result;
    }
}
