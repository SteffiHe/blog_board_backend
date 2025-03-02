package com.example.blog_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.blog_system.entity.Category;
import com.example.blog_system.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controller/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Find all categories
     * @return list of categories
     */
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.findAllCategories();
    }

    /**
     * Find a category by id
     * @param id id of a category
     * @return category
     */
    @GetMapping("/byId/{id}")
    public Optional<Category> getCategoryById(@PathVariable String id) {
        return categoryService.findCategoryById(id);
    }

    /**
     * Create a new category
     * @param category category of a blog
     * @return created category
     */
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveOrUpdateCategory(category);
    }

    /**
     * Update a category by id
     * @param id id of a category
     * @param category category of a blog
     * @return geupdated category
     */
    @PatchMapping("/byId/{id}")
    public Category updateCategory(@PathVariable String id, @RequestBody Category category) {
        category.setId(id);
        return categoryService.saveOrUpdateCategory(category);
    }

    /**
     * Delete a category by Id
     * @param id id of the category to be deleted
     */
    @DeleteMapping("/byId/{id}")
    public void deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
    }
}
