package com.example.blog_system.service;

import com.example.blog_system.dto.ArticleDTO;
import com.example.blog_system.entity.Article;
import com.example.blog_system.strategy.ArticleSortStrategy;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    List<Article> getAllArticlesWithAuthorname();

    List<ArticleDTO> getAllArticlesWithAuthornameDTO();

    List<Article> getArticleByKeyword(String keyword);

    // Strategy pattern implementation
    List<Article> getAllArticlesSorted(String sortBy);

    Article insertArticle(Article article);

    void deleteArticle(String id);


}
