package com.example.blog_system.designpattern;

import com.example.blog_system.entity.Article;
import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.repository.ArticleRepository;
import com.example.blog_system.repository.CategoryRepository;
import com.example.blog_system.repository.TagRepository;
import com.example.blog_system.service.ArticleService;
import com.example.blog_system.service.CategoryService;
import com.example.blog_system.service.TagService;
import com.example.blog_system.strategy.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class StrategyTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    private Category categoryBackend, categoryDesignPatterns, categoryAPI;
    private Tag tagJava, tagOOP, tagRest;
    private Article article1, article2, article3;

    @BeforeEach
    void setUp() {
        // Clear database
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
        tagRepository.deleteAll();

        // save category
        categoryBackend = new Category();
        categoryBackend.setName("Backend");
        categoryBackend = categoryRepository.save(categoryBackend);

        categoryDesignPatterns = new Category();
        categoryDesignPatterns.setName("Design Patterns");
        categoryDesignPatterns = categoryRepository.save(categoryDesignPatterns);

        categoryAPI = new Category();
        categoryAPI.setName("API Development");
        categoryAPI = categoryRepository.save(categoryAPI);

        // save tags
        tagJava = new Tag();
        tagJava.setName("Java");
        tagJava = tagRepository.save(tagJava);

        tagOOP = new Tag();
        tagOOP.setName("OOP");
        tagOOP = tagRepository.save(tagOOP);

        tagRest = new Tag();
        tagRest.setName("REST");
        tagRest = tagRepository.save(tagRest);

        // save article
        article1 = new Article();
        article1.setTitle("Spring Boot Guide");
        article1.setAuthor("Alice");
        article1.setCategory(categoryBackend);
        article1.setTags(List.of(tagJava));
        article1 = articleRepository.save(article1);

        article2 = new Article();
        article2.setTitle("Java Observer Pattern");
        article2.setAuthor("Bob");
        article2.setCategory(categoryDesignPatterns);
        article2.setTags(List.of(tagOOP));
        article2 = articleRepository.save(article2);

        article3 = new Article();
        article3.setTitle("REST API Best Practices");
        article3.setAuthor("Charlie");
        article3.setCategory(categoryAPI);
        article3.setTags(List.of(tagRest));
        article3 = articleRepository.save(article3);
    }

    @AfterEach
    void tearDown() {
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    void testSortByTitle() {
        ArticleSortStrategy strategy = new ArticleSortByTitle();
        List<Article> sortedArticles = strategy.sort(List.of(article1, article2, article3));

        sortedArticles.forEach(a -> System.out.println(a.getTitle()));

        assertEquals("Java Observer Pattern", sortedArticles.get(0).getTitle());
        assertEquals("REST API Best Practices", sortedArticles.get(1).getTitle());
        assertEquals("Spring Boot Guide", sortedArticles.get(2).getTitle());

        System.out.println("Test erfolgreich!\n");
    }

    @Test
    void testSortByAuthor() {
        ArticleSortStrategy strategy = new ArticleSortByAuthor();
        List<Article> sortedArticles = strategy.sort(List.of(article1, article2, article3));

        sortedArticles.forEach(a -> System.out.println(a.getAuthor()));

        assertEquals("Alice", sortedArticles.get(0).getAuthor());
        assertEquals("Bob", sortedArticles.get(1).getAuthor());
        assertEquals("Charlie", sortedArticles.get(2).getAuthor());

        System.out.println("Test erfolgreich!\n");
    }

    @Test
    void testSortByCreateTime() {
        ArticleSortStrategy strategy = new ArticleSortByCreateTime();
        List<Article> sortedArticles = strategy.sort(List.of(article1, article2, article3));

        sortedArticles.forEach(a -> System.out.println(a.getCreateTime()));

        System.out.println("Test erfolgreich!\n");
    }

    @Test
    void testSortByTag() {
        ArticleSortStrategy strategy = new ArticleSortByTag();
        List<Article> sortedArticles = strategy.sort(List.of(article1, article2, article3));

        sortedArticles.forEach(a -> System.out.println(a.getTags().get(0).getName()));

        assertEquals("Java", sortedArticles.get(0).getTags().get(0).getName());
        assertEquals("OOP", sortedArticles.get(1).getTags().get(0).getName());
        assertEquals("REST", sortedArticles.get(2).getTags().get(0).getName());

        System.out.println("Test erfolgreich!\n");
    }

    @Test
    void testSortByCategory() {
        ArticleSortStrategy strategy = new ArticleSortByCategory();
        List<Article> sortedArticles = strategy.sort(List.of(article1, article2, article3));

        sortedArticles.forEach(a -> System.out.println(a.getCategory().getName()));

        assertEquals("API Development", sortedArticles.get(0).getCategory().getName());
        assertEquals("Backend", sortedArticles.get(1).getCategory().getName());
        assertEquals("Design Patterns", sortedArticles.get(2).getCategory().getName());

        System.out.println("Test erfolgreich!\n");
    }

}