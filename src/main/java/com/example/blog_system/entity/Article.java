package com.example.blog_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

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

    @Field(targetType = FieldType.STRING, name = "author")
    private String author; //author of the blog

    @DBRef
    @NotNull(message = "Category cannot be empty")
    private Category category; //blog has one category (cannot be null)

    @DBRef(lazy = true)
    private List<Tag> tags; //blog has many tags (can be null)

    @CreatedDate
    @Field(targetType = FieldType.DATE_TIME, name = "create_time")
    private Date createTime;

<<<<<<< HEAD
}


// Ensure createTime is set automatically before saving the document
@Component
class ArticleEntityListener implements BeforeConvertCallback<Article> {
    @Override
    public Article onBeforeConvert(Article article, String collection) {
        if (article.getCreateTime() == null) {
            article.setCreateTime(new Date());
        }
        return article;
    }
=======
>>>>>>> 506d713aa5861831559aabb53630b32f5152a1d6
}
