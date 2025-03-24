package com.example.blog_system.blog;

import com.example.blog_system.dao.UserMapper;
import com.example.blog_system.entity.*;
import com.example.blog_system.observer.ArticleObservable;
import com.example.blog_system.observer.ArticleObserver;
import com.example.blog_system.repository.*;
import com.example.blog_system.service.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private RateService rateService;

    @Autowired
    private RecommendationService recommendationService;


    private Category category;
    private Tag tag1, tag2;
    private Rate rate;
    private Recommendation recommendation;
    private Article article1, article2;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {

        // Create category
        category = new Category();
        category.setName("Database-Test");
        category = categoryRepository.save(category);

        // Create tags
        tag1 = new Tag();
        tag1.setName("MySQL-Test");
        tag1 = tagRepository.save(tag1);

        tag2 = new Tag();
        tag2.setName("SQL-Test");
        tag2 = tagRepository.save(tag2);

        // Create rate
        rate = new Rate();
        rate.setName("Rate test");
        rate = rateRepository.save(rate);

        // Create recommendation
        recommendation = new Recommendation();
        recommendation.setName("Recommendation test");
        recommendation = recommendationRepository.save(recommendation);

        // Create first article
        article1 = new Article();
        article1.setTitle("Why MySQL for Test?");
        article1.setContent("MySQL Test is a powerful database...");
        article1.setAuthor("1");
        article1.setCategory(category);
        article1.setTags(Arrays.asList(tag1, tag2));
        article1.setRate(rate);
        article1.setRecommendation(recommendation);


        articleService.insertArticle(article1);
    }

    @AfterEach
    void tearDown() {
        articleService.deleteArticle(article1.getId());
        categoryService.deleteCategoryByName(category.getName());
        tagService.deleteTagByName(tag1.getName());
        tagService.deleteTagByName(tag2.getName());
        rateService.deleteRateByName(rate.getName());
        recommendationService.deleteRecommendationByName(recommendation.getName());
    }

    @Test
    void testGetAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        assertNotNull(articles);
        assertFalse(articles.isEmpty());
    }



    @Test
    void testGetArticleByKeyword() {
        List<Article> articles = articleService.getArticleByKeyword("MySQL Test");
        assertEquals(1, articles.size());
        //assertEquals("Alice", articles.get(0).getAuthorName());
        Long authorId = Long.valueOf(articles.getFirst().getAuthor());
        assertEquals(1L, authorId.longValue());

        String authorName = userMapper.getUsernameById(authorId);
        assertEquals("Lucy", authorName);
    }

    @Test
    void testUpdateArticleCategory() {
        Category newCategory = new Category();
        newCategory.setName("Database-Test-Update");
        newCategory = categoryRepository.save(newCategory);


        Article updatedArticle = articleService.updateArticleCategory(article1.getId(), newCategory);

        assertEquals("Database-Test-Update", updatedArticle.getCategory().getName());

        categoryService.deleteCategoryByName(newCategory.getName());
    }

}