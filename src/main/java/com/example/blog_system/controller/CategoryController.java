package com.example.blog_system.controller;

import com.example.blog_system.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.blog_system.entity.Category;
import com.example.blog_system.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Find all categories
     * @return list of categories
     */
    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }


    @GetMapping("/getCategoryByName/{name}")
    public Optional<Category> getCategoryById(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }


    @PostMapping("/insertCategory")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {

        Optional<Category> existingCategory = categoryService.getCategoryByName(category.getName());

        if (existingCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Category with name '" + category.getName() + "' already exists.");
        }

        Category savedCategory = categoryService.insertCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }


    @PatchMapping("/updateCategoryByName/{name}")
    public ResponseEntity<?> updateCategory(@PathVariable String name, @RequestBody Category category) {
        Optional<Category> existingCategoryOptional = categoryService.getCategoryByName(name);

        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();

            // Update only the fields provided in the request
            if (category.getName() != null) {
                existingCategory.setName(category.getName());
            }

            Category updatedCategory = categoryService.updateCategoryByName(existingCategory);
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with Name " + name + " not found.");
        }
    }

    @DeleteMapping("/deleteCategoryByName/{name}")
    public ResponseEntity<?> deleteCategory(@PathVariable String name) {
        Optional<Category> existingCategoryOptional = categoryService.getCategoryByName(name);

        if (existingCategoryOptional.isPresent()) {
            categoryService.deleteCategoryByName(name);

            return ResponseEntity.ok("Category with Name " + name + " has been deleted.");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category with Name " + name + " not found.");
        }
    }
}
