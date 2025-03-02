package com.example.blog_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Class to represent a blog
 */
@Document(collection = "blogs")
@NoArgsConstructor
@Getter
@Setter
public class Blog {
    @Id
    private String id; //unique id
    private String title; //title of the blog
    private String authorId; //authorId of the blog (PostgreSQL)
    private String content; //content of the blog
    @DBRef
    private Category category; //blog has one category
    @DBRef
    private List<Tag> tags; //blog has many tags

    /**
     * Constructor without id (MongoDB will auto-generate it)
     * @param title title of the blog
     * @param authorId authorId of the blog
     * @param content content of the blog
     * @param category category of the blog
     * @param tags tags of the blog
     */
    public Blog(String title, String authorId, String content, Category category, List<Tag> tags) {
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.category = category;
        this.tags = tags;
    }
}
