package com.example.blog_system.decorator;

public class HighlightedArticle extends ArticleDecorator {

    public HighlightedArticle(Article decoratedArticle) {
        super(decoratedArticle);
    }

    @Override
    public String getContent() {
        return "**HIGHLIGHT**: " + super.getContent();
    }
}