package com.example.blog_system.service;


import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();

    Optional<Category> getCategoryByName(String name);

    Category insertCategory(Category category);

    Category updateCategoryByName(Category category);

    void deleteCategoryByName(String name) ;
}
