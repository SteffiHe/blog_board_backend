package com.example.blog_system.strategy;

import com.example.blog_system.entity.Article;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;

@Component("articleSortByTag")
public class ArticleSortByTag implements ArticleSortStrategy {
    @Override
    public List<Article> sort(List<Article> articles) {
        return articles.stream()
                .sorted(Comparator.comparing(article ->
                        article.getTags() != null && !article.getTags().isEmpty() ?
                                article.getTags().get(0).getName() : ""))
                .toList();
    }
}