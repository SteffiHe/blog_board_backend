package com.example.blog_system.service;

import com.example.blog_system.entity.Category;
import com.example.blog_system.repo.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock private CategoryRepo categoryRepo;
    @InjectMocks private CategoryService categoryService;

    @Test
    void saveCategory_ShouldSaveNewCategory() {
        Category category = new Category("Programming");
        when(categoryRepo.save(category)).thenReturn(category);

        Category result = categoryService.saveOrUpdateCategory(category);

        assertNotNull(result);
        assertEquals("Programming", result.getName());
        verify(categoryRepo).save(category);
    }

    @Test
    void findCategoryById_ShouldReturnCategory() {
        Category category = new Category("1");
        when(categoryRepo.findById("1")).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.findCategoryById("1");

        assertTrue(result.isPresent());
        assertEquals("Programming", result.get().getName());
    }

    @Test
    void deleteCategory_ShouldDeleteCategoryById() {
        doNothing().when(categoryRepo).deleteById("1");

        categoryService.deleteCategory("1");

        verify(categoryRepo).deleteById("1");
    }
}