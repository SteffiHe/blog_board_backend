package com.example.blog_system;

import com.example.blog_system.dao.UserMapper;
import com.example.blog_system.dto.UserDTO;
import com.example.blog_system.entity.User;
import com.example.blog_system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.*;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setUsername("usertest");
        testUser.setPassword("password123");
        testUser.setEmail("usertest@example.com");
        testUser.setBirthday(LocalDate.parse("1992-02-02"));
        userMapper.insertUser(testUser);  // Insert test user before each test
    }

    @AfterEach
    public void tearDown() {
        // Cleanup test data after each test
        userMapper.deleteUserByUsername("usertest");
    }

    @Test
    public void testGetUserByUsername() {
        User user = userMapper.getUserByUsername("usertest");
        assertNotNull(user);
        assertEquals("usertest", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = userMapper.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());  // Check that users list is not empty
    }

    @Test
    public void getUserIdByUsername() {
        long userId = userMapper.getUserIdByUsername("usertest");
        assertTrue(userId > 0);
    }

    @Test
    public void testGetUsernameById() {
        Long testId = 1L;
        System.out.println("Übergebene ID: " + testId);

        String username = userMapper.getUsernameById(testId);
        System.out.println("Gefundener Username: " + username);
        assertNotNull(username);
    }


    @Test
    public void deleteUserByUsername() {
        // Delete user and verify deletion
        userMapper.deleteUserByUsername("usertest");
        User user = userMapper.getUserByUsername("usertest");
        assertNull(user);  // Ensure the user no longer exists
    }

    @Test
    public void insertUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("newuser");
        userDTO.setPassword("newpassword");
        userDTO.setEmail("newuser@example.com");
        userDTO.setBirthday(LocalDate.parse("1992-02-02"));

        Boolean rowsAffected = userService.insertUser(userDTO);
        assertTrue(rowsAffected);

        User user = userMapper.getUserByUsername("newuser");
        assertNotNull(user);
        assertEquals("newuser", user.getUsername());

        userMapper.deleteUserByUsername("newuser");
    }

    @Test
    public void updateUser() {
        // First, update the test user's email
        testUser.setEmail("updatedemail@example.com");
        int rowsAffected = userMapper.updateUser(testUser);
        assertEquals(1, rowsAffected);  // Assert that 1 row was updated

        // Verify the user was updated
        User updatedUser = userMapper.getUserByUsername("usertest");
        assertNotNull(updatedUser);
        assertEquals("updatedemail@example.com", updatedUser.getEmail());
    }

}
