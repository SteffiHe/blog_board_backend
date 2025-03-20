package com.example.blog_system.observer;

import com.example.blog_system.entity.Article;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ArticlePublisher {
    private final ApplicationEventPublisher eventPublisher;

    public ArticlePublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishArticleEvent(String action, Article article) {
        ArticleEvent event = new ArticleEvent(this, action, article);
        eventPublisher.publishEvent(event);
    }
}