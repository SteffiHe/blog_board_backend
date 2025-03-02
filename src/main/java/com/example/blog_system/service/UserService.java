package com.example.blog_system.service;

import com.example.blog_system.dto.UserDTO;
import com.example.blog_system.entity.User;
import com.example.blog_system.vo.UserVO;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    List<UserVO> getAllUsersVO();

    User getUserByUsername(String username);
    UserVO getUserByUsernameVO(String username);

    boolean deleteUserByUsername(String username);
    boolean insertUser(UserDTO userDTO);
    boolean updateUser(UserDTO userDTO);
}
