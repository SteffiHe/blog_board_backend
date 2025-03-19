package com.example.blog_system.controller;

import com.example.blog_system.dto.UserDTO;
import com.example.blog_system.entity.User;
import com.example.blog_system.result.Result;
import com.example.blog_system.service.UserService;
import com.example.blog_system.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    public Result getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return userList != null ? Result.success(userList) : Result.notFound();
    }


    /**
     * Retrieves a list of all users
     * @return Success or failure result containing the user details
     */
    @RequestMapping(value = "/getAllUsersVO",method = RequestMethod.GET)
    public Result getAllUsersVO(){
        List<UserVO> userVOList = userService.getAllUsersVO();
        return userVOList != null ? Result.success(userVOList) : Result.notFound();
    }

    /**
     * Retrieves a user by username
     * @param username username of the user to be found
     * @return Success or failure result containing the user details
     */
    @RequestMapping(value = "/getUserByUsernameVO", method = RequestMethod.GET)
    public Result getUserByUsernameVO(@RequestParam String username) {
        UserVO userVO = userService.getUserByUsernameVO(username);
        return userVO != null ? Result.success(userVO) : Result.notFound();
    }

    /**
     * Deletes a user by username
     * @param username username of the user to be deleted
     * @return Success or failure result confirming the deletion
     */
    @RequestMapping(value = "/deleteUserByUsername", method = RequestMethod.DELETE)
    public Result deleteUserByUsername(@RequestParam String username) {
        boolean deleted = userService.deleteUserByUsername(username);
        return deleted ? Result.success("User deleted successfully") : Result.notFound();
    }

    /**
     * Creates a new user based on the provided data
     * @param userDTO user data transfer object containing user details
     * @return Success or failure result confirming the creation
     */
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public Result insertUser(@RequestBody UserDTO userDTO) {
        boolean isAdded = userService.insertUser(userDTO);  // Assuming the service handles the creation of the user
        return isAdded ? Result.success("User added successfully") : Result.BAD_REQUEST();
    }

    /**
     * Updates an existing user's information
     * @param userDTO user data transfer object with updated user details
     * @return Success or failure result confirming the update
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public Result updateUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getBirthday());
        boolean updated = userService.updateUser(userDTO);
        return updated ? Result.success("User added successfully") : Result.BAD_REQUEST();
    }
}
