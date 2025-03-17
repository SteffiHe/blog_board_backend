package com.example.blog_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Class to represent an article
 */
@Data
@Document(collection = "article")
public class Article {

    @Id
    private String id; //unique id

    private String title; //title of the blog
    private String content; //content of the blog

    /**@Field(targetType = FieldType.STRING, name = "author")
    private String author; //author of the blog*/

    private Long authorId;
    private String authorName;

    @DBRef
    private Category category; //blog has one category
    @DBRef
    private List<Tag> tags; //blog has many tags

    @CreatedDate
    @Field(targetType = FieldType.DATE_TIME, name = "create_time")
    private Date createTime;


}
