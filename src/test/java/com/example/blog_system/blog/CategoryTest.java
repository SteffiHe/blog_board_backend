package com.example.blog_system.blog;

import com.example.blog_system.entity.Category;
import com.example.blog_system.repository.CategoryRepository;
import com.example.blog_system.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // Ensures rollback after each test
@Slf4j
public class CategoryTest {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {

        category = new Category();
        category.setName("NoSQL_Test");
        category = categoryRepository.save(category); // Save first category
    }

    @AfterEach
    public void tearDown() {
        // Cleanup test data after each test
        categoryService.deleteCategoryByName("NoSQL_Test");
    }

    @Test
    void testGetCategoryByName() {
        Optional<Category> result = categoryService.getCategoryByName("NoSQL_Test");
        assertTrue(result.isPresent());
        assertEquals("NoSQL_Test", result.get().getName());
    }


    @Test
    void testInsertCategory() {
        Category newCategory = new Category();
        newCategory.setName("Cloud");

        Category savedCategory = categoryService.insertCategory(newCategory);
        assertNotNull(savedCategory.getId());
        assertEquals("Cloud", savedCategory.getName());

        categoryService.deleteCategoryByName("Cloud");
        assertFalse(categoryService.getCategoryByName("Cloud").isPresent());
    }

    @Test
    void testUpdateCategoryByName() {
        category.setName("NoSQL_Test Updated");
        Category updatedCategory = categoryService.updateCategoryByName(category);

        assertEquals("NoSQL_Test Updated", updatedCategory.getName());

        categoryService.deleteCategoryByName("NoSQL_Test Updated");
        assertFalse(categoryService.getCategoryByName("NoSQL_Test Updated").isPresent());
    }

}
