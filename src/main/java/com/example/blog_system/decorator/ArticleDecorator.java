package com.example.blog_system.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ArticleDecorator implements Article {
    protected final Article decoratedArticle;

    @Override
    public String getContent() {
        return decoratedArticle.getContent();
    }
}