package com.example.blog_system.service;

import com.example.blog_system.entity.Article;
import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.event.ArticleSavedEvent;
import com.example.blog_system.repository.ArticleRepository;
import com.example.blog_system.repository.CategoryRepository;
import com.example.blog_system.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject; // You can use org.json or any other JSON library.

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private TagRepository tagRepository;
    private CategoryRepository categoryRepository;
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public void ArticleService(ArticleRepository articleRepository, TagRepository tagRepository,
                               CategoryRepository categoryRepository, ApplicationEventPublisher eventPublisher) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Retrieves all articles from the database
     * @return a list of all articles
     */
    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById( String id) {
        return articleRepository.findById(id).orElse(null);
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
        // Get the highest existing ID
        List<String> ids = articleRepository.findAllArticleIds();

        int maxId = ids.stream()
                .map(id -> new JSONObject(id).getString("_id")) // Extract the _id field from the JSON string
                .map(id -> id.substring(1)) // Remove the "a" prefix
                .mapToInt(Integer::parseInt) // Convert to integer
                .max()
                .orElse(1); // Default to 0 if empty

        // Create new ID
        String newId = "a" + (maxId + 1);
        article.setId(newId);


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
    public Article deleteArticle(String id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + id));

        articleRepository.deleteById(id);

        return article;
    }


    /**
     * Updates the category of an existing article.
     * If the category does not exist, it will be created and assigned to the article.
     * @param articleId the ID of the article to update
     * @param category the new category
     * @return the updated article
     */
    public Article updateArticleCategory(String articleId, Category category) {
        // Find the article by ID
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + articleId));

        // Find or create the category
        Category existingCategory = categoryRepository.findByNameIgnoreCase(category.getName())
                .orElseGet(() -> {
                    // 如果不存在，则创建新的 Category
                    Category newCategory = new Category();
                    newCategory.setName(category.getName());
                    return categoryRepository.save(newCategory);
                });

        // Update the article's category
        article.setCategory(existingCategory);

        // Save the updated article
        return articleRepository.save(article);
    }
}
