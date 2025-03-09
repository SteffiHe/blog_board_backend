package com.example.blog_system.service;

import com.example.blog_system.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    List<Article> getArticleByKeyword(String keyword);

    Article insertArticle(Article article);

    void deleteArticle(String id);
}
