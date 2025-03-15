package com.example.blog_system.decorator;

public class PremiumArticle extends ArticleDecorator {

    public PremiumArticle(Article decoratedArticle) {
        super(decoratedArticle);
    }

    @Override
    public String getContent() {
        return "[PREMIUM] " + super.getContent();
    }
}