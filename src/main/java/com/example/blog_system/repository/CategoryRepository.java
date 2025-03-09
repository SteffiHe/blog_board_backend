package com.example.blog_system.repository;

import com.example.blog_system.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.blog_system.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByNameIgnoreCase(String name);

    void deleteByNameIgnoreCase(String name);
}
