package com.example.blog_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.blog_system.entity.Tag;

import java.util.Optional;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {

    /**
     * Finds a tag by its name
     * @param name name of the tag
     * @return optional containing the found tag or empty if not found
     */
    Optional<Tag> findByNameIgnoreCase(String name);

    /**
     * Deletes a tag by its name
     * @param name name of the tag to delete
     */
    void deleteByNameIgnoreCase(String name);
}
