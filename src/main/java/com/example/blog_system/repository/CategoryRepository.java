package com.example.blog_system.repository;

import com.example.blog_system.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.blog_system.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    /**
     * Finds a category by its name
     *
     * @param name name of the category
     * @return optional containing the found category or empty if not found
     */
    Optional<Category> findByNameIgnoreCase(String name);

    /**
     * Deletes a category by its name
     *
     * @param name name of the category to delete
     */
    void deleteByNameIgnoreCase(String name);
}
