package com.example.blog_system.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.blog_system.entity.Tag;

@Repository
public interface TagRepo extends MongoRepository<Tag, String> {
    //Standard methods like findAll() or save() are provided by MongoRepository
}
