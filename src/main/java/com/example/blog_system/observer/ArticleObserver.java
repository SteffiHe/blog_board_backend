package com.example.blog_system.observer;

import com.example.blog_system.entity.Article;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ArticleObserver implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Article newArticle = (Article) evt.getNewValue();
        System.out.printf("Neuer Artikelstatus: '%s' wurde geändert!\n", newArticle.getTitle());
    }
}