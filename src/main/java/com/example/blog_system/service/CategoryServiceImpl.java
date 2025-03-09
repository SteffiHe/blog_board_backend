package com.example.blog_system.service;

import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Find all categories
     * @return all categories
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryByName(String name){

        return categoryRepository.findByNameIgnoreCase(name);
    }

    public Category insertCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategoryByName(Category category) {
        return categoryRepository.save(category);
    }


    public void deleteCategoryByName(String name) {
        categoryRepository.deleteByNameIgnoreCase(name);
    }

    /**
     * Save or update a category
     * @param category category of a blog
     * @return saved or updated category
     */
    public Category saveOrUpdateCategory(Category category) {
        return categoryRepository.save(category);
    }
}
