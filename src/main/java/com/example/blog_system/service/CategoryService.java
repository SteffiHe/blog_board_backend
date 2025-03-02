package com.example.blog_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.blog_system.entity.Category;
import com.example.blog_system.repo.CategoryRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    /**
     * Find all categories
     * @return all categories
     */
    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    /**
     * Find a category by id
     * @param id id of a category
     * @return founded id
     */
    public Optional<Category> findCategoryById(String id) {
        return categoryRepo.findById(id);
    }

    /**
     * Save or update a category
     * @param category category of a blog
     * @return saved or updated category
     */
    public Category saveOrUpdateCategory(Category category) {
        return categoryRepo.save(category);
    }

    /**
     * Delete a category by id
     * @param id id of the category to be deleted
     */
    public void deleteCategory(String id) {
        categoryRepo.deleteById(id);
    }
}
