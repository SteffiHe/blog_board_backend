package com.example.blog_system.observer;

import com.example.blog_system.entity.Article;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ArticleObservable {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Article state;

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeObserver(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void setState(Article newArticle) {
        Article oldArticle = this.state;
        this.state = newArticle;
        support.firePropertyChange("article", oldArticle, newArticle);
    }
}