package com.example.blog_system.observer;

import com.example.blog_system.entity.Article;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ArticleObserver implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Article newArticle = (Article) evt.getNewValue();
        String eventType = evt.getPropertyName(); // "created", "updated", "deleted"

        switch (eventType) {
            case "created":
                System.out.printf("Neuer Artikel erstellt: '%s'\n", newArticle.getTitle());
                break;
            case "updated":
                System.out.printf("Artikel aktualisiert: '%s'\n", newArticle.getTitle());
                break;
            case "deleted":
                System.out.printf("Artikel gelöscht: '%s'\n", newArticle.getTitle());
                break;
            default:
                System.out.printf("Unbekannte Aktion für Artikel: '%s'\n", newArticle.getTitle());
        }
    }
}