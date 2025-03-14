package com.example.blog_system.strategy;

import com.example.blog_system.entity.Article;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component ("articleSortByCreateTime")
public class ArticleSortByCreateTime implements ArticleSortStrategy {

    @Override
    public List<Article> sort(List<Article> articles) {
        return articles.stream()
                .sorted(Comparator.comparing(Article::getCreateTime))
                .toList();
    }
}