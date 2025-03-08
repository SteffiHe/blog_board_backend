package com.example.blog_system.dao;

import org.apache.ibatis.annotations.*;
import com.example.blog_system.entity.User;

import java.util.List;

//@DS("dataSourcePostgreSql")
@Mapper
public interface UserMapper {

   List<User> getAllUsers();
   User getUserByUsername(@Param("username") String username);
   int deleteUserByUsername(@Param("username") String username);
   int insertUser(User user);
   int updateUser(User user);
}
