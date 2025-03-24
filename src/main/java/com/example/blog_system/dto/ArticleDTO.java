package com.example.blog_system.dto;

import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Rate;
import com.example.blog_system.entity.Recommendation;
import com.example.blog_system.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
public class ArticleDTO {

    private String id;
    private String title;
    private String content;
    private String author;
    private Category category;
    private List<Tag> tags;
    private Rate rate;
    private Recommendation recommendation;
    private Date createTime;

}