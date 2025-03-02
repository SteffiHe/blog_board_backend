package com.example.blog_system.controller;

import com.example.blog_system.result.Result;
import com.example.blog_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    public Result getAllUsers(){

        System.out.println(userService.getAllUsers());
        return Result.success(userService.getAllUsers());
    }

}
