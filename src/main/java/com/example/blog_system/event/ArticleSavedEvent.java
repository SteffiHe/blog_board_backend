package com.example.blog_system.event;

import com.example.blog_system.entity.Article;

/**
 * Event that is published when an article is saved
 * The getter article() is automatically generated
 */
public record ArticleSavedEvent(Article article) { }