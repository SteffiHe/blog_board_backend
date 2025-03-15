package com.example.blog_system.blog;

import com.example.blog_system.entity.Article;
import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.event.ArticleEventListener;
import com.example.blog_system.event.ArticleSavedEvent;
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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // Ensures rollback after each test
public class ArticleTest {

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

    @Autowired
    private ArticleEventListener articleEventListener;

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
        article1.setAuthor("Alice");
        article1.setCategory(category);
        article1.setTags(Arrays.asList(tag1, tag2));
        articleService.insertArticle(article1);
    }

    @AfterEach
    void tearDown() {
        articleService.deleteArticle(article1.getId());
        categoryService.deleteCategoryByName(category.getName());
        tagService.deleteTagByName(tag1.getName());
        tagService.deleteTagByName(tag2.getName());
    }

    /**
     * Tests if the ArticleSavedEvent is correctly triggered
     */
    @Test
    void testArticleSavedEventIsTriggered() {
        articleService.insertArticle(article1);
        // The event should be received
        assertNotNull(articleEventListener.getReceivedEvent(), "Das Event sollte nicht null sein!");
        // Ensure the event contains the correct article
        assertEquals("Why MySQL?", articleEventListener.getReceivedEvent().article().getTitle());
    }

    @Test
    void testGetArticleByKeyword() {
        List<Article> articles = articleService.getArticleByKeyword("Alice");
        assertEquals(1, articles.size());
        assertEquals("Alice", articles.get(0).getAuthor());
    }

    @Test
    void testSortArticlesByTitle() {
        Article article2 = new Article();
        article2.setTitle("A Guide to SQL");
        article2.setContent("SQL is essential for databases...");
        article2.setAuthor("Bob");
        article2.setCategory(category);
        article2.setTags(Arrays.asList(tag1));
        articleService.insertArticle(article2);

        List<Article> sortedArticles = articleService.getAllArticlesSorted("title");

        assertEquals("A Guide to SQL", sortedArticles.get(0).getTitle());
        assertEquals("Why MySQL?", sortedArticles.get(1).getTitle());
    }

    @Test
    void testSortArticlesByCreateTime() {
        Article article2 = new Article();
        article2.setTitle("Advanced SQL Techniques");
        article2.setContent("Deep dive into SQL...");
        article2.setAuthor("Charlie");
        article2.setCategory(category);
        article2.setTags(Arrays.asList(tag2));
        article2 = articleService.insertArticle(article2);

        List<Article> sortedArticles = articleService.getAllArticlesSorted("createtime");

        assertEquals(article1.getId(), sortedArticles.get(0).getId());
        assertEquals(article2.getId(), sortedArticles.get(1).getId());
    }

    @Test
    void testSortArticlesByAuthor() {
        Article article2 = new Article();
        article2.setTitle("Database Indexing");
        article2.setContent("Indexing improves query speed...");
        article2.setAuthor("Aaron");
        article2.setCategory(category);
        article2.setTags(Arrays.asList(tag1, tag2));
        articleService.insertArticle(article2);

        List<Article> sortedArticles = articleService.getAllArticlesSorted("author");

        assertEquals("Aaron", sortedArticles.get(0).getAuthor());
        assertEquals("Alice", sortedArticles.get(1).getAuthor());
    }

}