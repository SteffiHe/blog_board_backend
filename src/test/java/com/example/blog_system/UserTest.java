package com.example.blog_system;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.blog_system.dao.UserMapper;
import com.example.blog_system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setId(UUID.randomUUID());
        testUser.setUsername("usertest");
        testUser.setPassword("123");
    }

    @Test
    void testGetAllUsers() {
        List<User> users = userMapper.getAllUsers();
        log.info(users.toString());
        assertFalse(users.isEmpty());
    }




}
