package com.example.blog_system.service;

import com.example.blog_system.entity.Article;
import com.example.blog_system.event.ArticleSavedEvent;
import com.example.blog_system.repository.ArticleRepository;
import com.example.blog_system.repository.CategoryRepository;
import com.example.blog_system.repository.TagRepository;
import com.example.blog_system.strategy.ArticleSortByAuthor;
import com.example.blog_system.strategy.ArticleSortByCreateTime;
import com.example.blog_system.strategy.ArticleSortByTitle;
import com.example.blog_system.strategy.ArticleSortStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private TagRepository tagRepository;
    private CategoryRepository categoryRepository;
    private ApplicationEventPublisher eventPublisher;
    private ArticleSortStrategy articleSortStrategy;

    @Autowired
    public void ArticleService(ArticleRepository articleRepository, TagRepository tagRepository,
                               CategoryRepository categoryRepository, ApplicationEventPublisher eventPublisher,
                               @Qualifier("articleSortByCreateTime") ArticleSortStrategy articleSortStrategy) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
        this.eventPublisher = eventPublisher;
        this.articleSortStrategy = articleSortStrategy;
    }

    /**
     * Retrieves all articles from the database
     * @return a list of all articles
     */
    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    /**
     * Retrieves a list of articles that contain a specified keyword
     * in their title, content or author's name
     * @param keyword keyword to search for in an article
     * @return a list of matching articles
     */
    public List<Article> getArticleByKeyword(String keyword) {
        return articleRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                keyword, keyword, keyword);
    }

    /**
     * Saves a new article or updates an existing article
     * If the article contains tags or a category, they are also saved or updated
     * @param article article to save or update
     * @return saved or updated article
     */
    public Article insertArticle(Article article) {
        // save or update tags
        if (article.getTags() != null) {
            article.setTags(article.getTags().stream()
                    .map(tag -> tagRepository.findByNameIgnoreCase(tag.getName())
                            .stream().findFirst().orElseGet(() -> tagRepository.save(tag)))
                    .toList());
        }

        // save or update category
        if (article.getCategory() != null) {
            article.setCategory(
                    categoryRepository.findByNameIgnoreCase(article.getCategory().getName())
                    .orElseGet(() -> categoryRepository.save( article.getCategory()))
            );
        }

        // save article
        Article savedArticle = articleRepository.save(article);

        // publish event
        eventPublisher.publishEvent(new ArticleSavedEvent(savedArticle));

        // save or update article
        return savedArticle;
    }

    /**
     * Deletes an article by its ID
     * @param id id of an article to delete
     */
    @Override
    public void deleteArticle(String id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> getAllArticlesSorted(String sortBy) {
        List<Article> articles = articleRepository.findAll();

        // Strategy pattern implementation
        ArticleSortStrategy strategy = switch (sortBy.toLowerCase()) {
            case "title" -> new ArticleSortByTitle();
            case "createtime" -> new ArticleSortByCreateTime();
            case "author" -> new ArticleSortByAuthor();
            default -> articleSortStrategy;  // Default strategy, can be "createTime" or any default
        };

        return strategy.sort(articles);
    }
}
