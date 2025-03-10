package com.example.blog_system.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Listener for ArticleSavedEvent
 */
@Component
public class ArticleEventListener {

    @EventListener
    public void handleArticleSavedEvent(ArticleSavedEvent event) {
        System.out.println("Article saved: " + event.article().getTitle());
    }
}