package com.example.blog_system.service;

import com.example.blog_system.dao.UserMapper;
import com.example.blog_system.entity.User;
import com.example.blog_system.vo.UserInfoVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userMapper.getAllUsers();
        return userList;
    }
}
