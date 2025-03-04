package com.example.blog_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Document(collection = "article")
public class Article {

    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String id;

    private String user;  // User Name

    private String titel;
    private String content;

    @Field(targetType = FieldType.DATE_TIME, name = "create_time")
    private Date createTime;



}
