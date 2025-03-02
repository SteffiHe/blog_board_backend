package com.example.blog_system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVO {
    private String username;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+2")
    private Date birthday;
}
