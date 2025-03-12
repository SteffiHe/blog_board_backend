package com.example.blog_system.event;

import lombok.Getter;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Listener for ArticleSavedEvent
 */
@Getter
@Component
public class ArticleEventListener {
    private ArticleSavedEvent receivedEvent;

    @EventListener
    public void onArticleSavedEvent(ArticleSavedEvent event) {
        this.receivedEvent = event;
        System.out.println("Article saved: " + event.article().getTitle());
    }
}