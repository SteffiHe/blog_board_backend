package com.example.blog_system.controller;

import com.example.blog_system.entity.User;
import com.example.blog_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/findAllUser",method = RequestMethod.GET)
    public List<User> findAllUser(){

        System.out.println(userService.findAllUser());
        return userService.findAllUser();
    }

}
