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
        List<UserVO> userVOList = userService.getAllUsersVO();
        return userVOList != null ? Result.success(userVOList) : Result.notFound();
    }

    @RequestMapping(value = "/getUserByUsername", method = RequestMethod.GET)
    public Result getUserByUsername(@RequestParam String username) {
        UserVO userVO = userService.getUserByUsernameVO(username);
        return userVO != null ? Result.success(userVO) : Result.notFound();
    }

    @RequestMapping(value = "/deleteUserByUsername", method = RequestMethod.DELETE)
    public Result deleteUserByUsername(@RequestParam String username) {
        boolean deleted = userService.deleteUserByUsername(username);
        return deleted ? Result.success("User deleted successfully") : Result.notFound();
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public Result insertUser(@RequestBody UserDTO userDTO) {
        boolean isAdded = userService.insertUser(userDTO);  // Assuming the service handles the creation of the user
        return isAdded ? Result.success("User added successfully") : Result.BAD_REQUEST();
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public Result updateUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getBirthday());
        boolean updated = userService.updateUser(userDTO);
        return updated ? Result.success("User added successfully") : Result.BAD_REQUEST();

    }
}
