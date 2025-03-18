package com.example.blog_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Class to represent an article
 */
@Data
@Document(collection = "article")
public class Article {

    @Id
    private String id; //unique id

    private String title; //title of the article
    private String content; //content of the article

    @Field(targetType = FieldType.STRING, name = "author")
    private String author; //author of the article

    private Long authorId;
    private String authorName;

    @DBRef
    @NotNull(message = "Category cannot be empty")
    private Category category; //article has one category (cannot be null)

    @DBRef
    private List<Tag> tags; //article has many tags (can be null)

    @DBRef
    private Rate rate; //article has one rate (can be null)

    @CreatedDate
    @Field(targetType = FieldType.DATE_TIME, name = "create_time")
    private Date createTime;


}


// Ensure createTime is set automatically before saving the document
@Component
class ArticleEntityListener implements BeforeConvertCallback<Article> {
    @Override
    public Article onBeforeConvert(Article article, String collection) {
        if (article.getCreateTime() == null) {
            article.setCreateTime(new Date());
            //article.setCreateTime(new Date(System.currentTimeMillis() - TimeZone.getDefault().getRawOffset()));
        }
        return article;
    }

}
