package com.example.blog_system.observer;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @EventListener
    public void handleArticleEvent(ArticleEvent event) {
        System.out.println("Benachrichtigung: Artikel '" + event.getArticle().getTitle() +
                "' wurde " + event.getAction());
        // email or push-up notification
    }
}