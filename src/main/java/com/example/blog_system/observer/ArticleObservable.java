package com.example.blog_system.observer;

import com.example.blog_system.entity.Article;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ArticleObservable {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeObserver(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void notifyObservers(String eventType, Article article) {
        support.firePropertyChange(eventType, null, article);
    }
}