package com.example.blog_system.strategy;

import com.example.blog_system.entity.Article;

import java.util.List;

public interface ArticleSortStrategy {
    List<Article> sort(List<Article> articles);
}