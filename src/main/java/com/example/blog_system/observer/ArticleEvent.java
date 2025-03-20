package com.example.blog_system.observer;

import com.example.blog_system.entity.Article;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ArticleEvent extends ApplicationEvent {
    private final String action;
    private final Article article;

    public ArticleEvent(Object source, String action, Article article) {
        super(source);
        this.action = action;
        this.article = article;
    }

}