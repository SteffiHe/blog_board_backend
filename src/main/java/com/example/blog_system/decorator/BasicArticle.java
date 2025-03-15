package com.example.blog_system.decorator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BasicArticle implements Article {
    private final String content;

    @Override
    public String getContent() {
        return content;
    }
}