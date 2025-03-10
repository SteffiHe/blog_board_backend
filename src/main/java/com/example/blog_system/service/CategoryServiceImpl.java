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
     * Retrieves all categories from the database
     * @return a list of all categories
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieves a category by its name
     * @param name name of a category
     * @return optional containing the found category or empty if not found
     */
    public Optional<Category> getCategoryByName(String name){
        return categoryRepository.findByNameIgnoreCase(name);
    }

    /**
     * Creates and saves a new category in the database
     * @param category category of an article to be created
     * @return saved category
     */
    public Category insertCategory(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Updates an existing category in the database by name
     * @param category category of an article
     * @return updated category
     */
    public Category updateCategoryByName(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Deletes a category from the database by its name
     * @param name name of the category to be deleted
     */
    public void deleteCategoryByName(String name) {
        categoryRepository.deleteByNameIgnoreCase(name);
    }
}
