package com.example.blog_system.strategy;

import com.example.blog_system.entity.Article;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleSortById implements ArticleSortStrategy {

    @Override
    public List<Article> sort(List<Article> articles) {
        return articles.stream()
                .sorted((a1, a2) -> a1.getId().compareTo(a2.getId()))
                .collect(Collectors.toList());
    }
}