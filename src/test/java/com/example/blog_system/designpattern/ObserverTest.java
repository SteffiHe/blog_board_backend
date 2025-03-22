package com.example.blog_system.designpattern;

import com.example.blog_system.dao.UserMapper;
import com.example.blog_system.entity.Article;
import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.observer.ArticleObservable;
import com.example.blog_system.observer.ArticleObserver;
import com.example.blog_system.repository.ArticleRepository;
import com.example.blog_system.repository.CategoryRepository;
import com.example.blog_system.repository.TagRepository;
import com.example.blog_system.service.ArticleService;
import com.example.blog_system.service.CategoryService;
import com.example.blog_system.service.TagService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
@Transactional
public class ObserverTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    private Category category;
    private Tag tag1, tag2;
    private Article article1, article2;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    private ArticleObservable observable;
    private AtomicReference<Article> receivedArticle;

    @BeforeEach
    void setUp() {
        // Clear data to avoid duplicates
        tagRepository.deleteAll();
        categoryRepository.deleteAll();
        articleRepository.deleteAll();

        // Create category
        category = new Category();
        category.setName("Database-Test");
        category = categoryRepository.save(category);

        // Create tags
        tag1 = new Tag();
        tag1.setName("MySQL");
        tag1 = tagRepository.save(tag1);

        tag2 = new Tag();
        tag2.setName("SQL-Test");
        tag2 = tagRepository.save(tag2);

        // Create first article
        article1 = new Article();
        article1.setTitle("Why MySQL?");
        article1.setContent("MySQL is a powerful database...");
        article1.setAuthor("1");
        article1.setCategory(category);
        article1.setTags(Arrays.asList(tag1, tag2));

        articleService.insertArticle(article1);

        // Add Observer
        observable = new ArticleObservable();
        receivedArticle = new AtomicReference<>();
    }

    @AfterEach
    void tearDown() {
        articleService.deleteArticle(article1.getId());
        categoryService.deleteCategoryByName(category.getName());
        tagService.deleteTagByName(tag1.getName());
        tagService.deleteTagByName(tag2.getName());
    }

    @Test
    void testObserverGetsNotified() {
        ArticleObservable observable = new ArticleObservable();
        ArticleObserver observer = new ArticleObserver();

        // add observer
        observable.addObserver(observer);
        System.out.println("Observer wurde hinzugefügt!");

        // Create new article
        Article newArticle = new Article();
        newArticle.setTitle("Spring Boot Observer");
        newArticle.setContent("Testing Observer with real service.");
        newArticle.setAuthor("4");

        // Create article -> Notify observer
        observable.notifyObservers("created", newArticle);

        // Update article -> Notify observer
        newArticle.setContent("Updated Content");
        observable.notifyObservers("updated", newArticle);

        // Delete article -> Notify observer
        observable.notifyObservers("deleted", newArticle);
    }
}
