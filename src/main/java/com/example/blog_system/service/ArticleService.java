package com.example.blog_system.service;

import com.example.blog_system.entity.Article;
import com.example.blog_system.entity.Category;
<<<<<<< HEAD
import com.example.blog_system.strategy.ArticleSortStrategy;
=======
>>>>>>> 506d713aa5861831559aabb53630b32f5152a1d6

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    List<Article> getArticleByKeyword(String keyword);

    Article getArticleById(String id);

<<<<<<< HEAD
    // Strategy pattern implementation
    List<Article> getAllArticlesSorted(String sortBy);

=======
>>>>>>> 506d713aa5861831559aabb53630b32f5152a1d6
    Article insertArticle(Article article);

    Article deleteArticle(String id);

    Article updateArticleCategory(String articleId, Category category);
<<<<<<< HEAD

=======
>>>>>>> 506d713aa5861831559aabb53630b32f5152a1d6
}
