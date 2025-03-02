package com.example.blog_system.exception;

import com.example.blog_system.result.Result;
import com.example.blog_system.result.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * handle all exceptions
     *
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> Result <T> exceptionHandler(Exception e) {
        return Result.failure( "An unexpected error occurred: " + e.getMessage());
    }
}
