package com.example.blog_system.decorator;

public class ReadTimeArticle extends ArticleDecorator {

    public ReadTimeArticle(Article decoratedArticle) {
        super(decoratedArticle);
    }

    @Override
    public String getContent() {
        int wordCount = super.getContent().split("\\s+").length;
        int readTime = Math.max(1, wordCount / 200); // 200 words / minute
        return super.getContent() + " (Geschätzte Lesezeit: " + readTime + " min)";
    }
}