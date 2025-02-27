package com.example.blog_system.service;

import com.example.blog_system.dao.UserMapper;
import com.example.blog_system.entity.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
