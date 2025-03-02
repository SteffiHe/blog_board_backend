package com.example.blog_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.repo.TagRepo;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepo tagRepo;

    /**
     * Find all tags
     * @return all tags
     */
    public List<Tag> findAllTags() {
        return tagRepo.findAll();
    }

    /**
     * Find a tag by id
     * @param id id of a tag
     * @return founded tag
     */
    public Optional<Tag> findTagById(String id) {
        return tagRepo.findById(id);
    }

    /**
     * Save or update a tag
     * @param tag tag of a blog
     * @return saved or updated tag
     */
    public Tag saveOrUpdateTag(Tag tag) {
        return tagRepo.save(tag);
    }

    /**
     * Delete a tag by id
     * @param id id of the tag to be deleted
     */
    public void deleteTag(String id) {
        tagRepo.deleteById(id);
    }
}