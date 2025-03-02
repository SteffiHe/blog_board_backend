package com.example.blog_system.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.blog_system.entity.Category;

@Repository
public interface CategoryRepo extends MongoRepository<Category, String> {
    //Standard methods like findAll() or save() are provided by MongoRepository
}
