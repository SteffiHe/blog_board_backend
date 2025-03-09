package com.example.blog_system.blog;


import com.example.blog_system.entity.Article;
import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Tag;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;

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


    @BeforeEach
    void setUp() {

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

    @Test
    void testGetArticleByKeyword() {

        List<Article> articles = articleService.getArticleByKeyword("Alice");
        assertEquals(1, articles.size());
        assertEquals("Alice", articles.get(0).getAuthor());
    }

}
