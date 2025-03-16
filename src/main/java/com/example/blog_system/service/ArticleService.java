package com.example.blog_system.service;

import com.example.blog_system.entity.Article;
import com.example.blog_system.entity.Category;

import com.example.blog_system.strategy.ArticleSortStrategy;


import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    List<Article> getArticleByKeyword(String keyword);

    Article getArticleById(String id);

    // Strategy pattern implementation
    List<Article> getAllArticlesSorted(String sortBy);


    Article insertArticle(Article article);

    Article deleteArticle(String id);

    Article updateArticleCategory(String articleId, Category category);

}
