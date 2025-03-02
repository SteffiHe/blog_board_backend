package com.example.blog_system.controller;

import com.example.blog_system.result.Result;
import org.springframework.web.bind.annotation.GetMapping;

public class ExceptionController {
    @GetMapping("/exception")
    public Result<String> triggerGenericException() {
        int x = 1 / 0; // Causes division by zero error
        return Result.success("This will never be reached");
    }
}
