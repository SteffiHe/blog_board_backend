package com.example.blog_system.service;

import com.example.blog_system.dao.UserMapper;
import com.example.blog_system.dto.UserDTO;
import com.example.blog_system.entity.User;
import com.example.blog_system.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * Retrieve a list of all users from the database
     * @return a list of User objects representing all users
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> userList = userMapper.getAllUsers();
        return userList;
    }

    /**
     * Retrieves a list of all users and converts them to UserVO objects
     * @return a list of UserVO objects representing all users
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserVO> getAllUsersVO() {

        List<UserVO> userVOList = new ArrayList<UserVO>();
        List<User> userList = this.getAllUsers();
        for (User user : userList) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user,userVO);
            userVOList.add(userVO);
        }

        return userVOList;
    }

    /**
     * Retrieves a user from the database by username
     * @param username username of a user to retrieve
     * @return user object if found, otherwise null
     */
    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        return user;
    }

    /**
     * Retrieves a user by username and returns it as a UserVO
     * @param username username of a user to retrieve
     * @return userVO object containing the user's information
     */
    @Override
    @Transactional(readOnly = true)
    public UserVO getUserByUsernameVO(String username) {
        User user = this.getUserByUsername(username);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    /**
     * Deletes a user from the database by username
     * @param username username of the user to be deleted
     * @return true if the user was successfully deleted, otherwise false
     */
    @Override
    @Transactional
    public boolean deleteUserByUsername(String username) {
        int rowsAffected = userMapper.deleteUserByUsername(username);
        return rowsAffected > 0;
    }

    /**
     * Insert a new user into the database
     * @param userDTO DTO object containing user data
     * @return true if the user was successfully inserted, otherwise false
     */
    @Override
    @Transactional
    public boolean insertUser(UserDTO userDTO) {
        // Logic to convert UserDTO to User entity and save it to DB
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setBirthday(userDTO.getBirthday());
        user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));

        int rowsAffected = userMapper.insertUser(user);  // Assuming insertUser() is implemented in the UserMapper
        return rowsAffected > 0 ;
    }


    @Override
    @Transactional
    public boolean updateUser(UserDTO userDTO) {
        User user = userMapper.getUserByUsername(userDTO.getUsername());

        if (user != null) {
            // Update the user's details with the data from DTO
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            user.setBirthday(userDTO.getBirthday());
            user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            System.out.println("user.getCreateTime() = " + user.getCreateTime());

            // Save the updated user back to the database
            return userMapper.updateUser(user) > 0;
        }

        return false;  // User not found, return false
    }
}