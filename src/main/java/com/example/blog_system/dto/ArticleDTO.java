package com.example.blog_system.dto;

import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

    private String id;
    private String title;
    private String content;
    private String authorName;
    private Category category;
    private List<Tag> tags;
    private String createTime;

}