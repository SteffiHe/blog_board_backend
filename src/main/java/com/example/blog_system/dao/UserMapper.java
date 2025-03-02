package com.example.blog_system.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.*;
import com.example.blog_system.entity.User;

import java.util.List;

@DS("dataSourcePostgreSql")
@Mapper
public interface UserMapper {

   // @Select("select * from user")
   List<User> getAllUsers();
}
