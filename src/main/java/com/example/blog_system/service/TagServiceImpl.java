package com.example.blog_system.service;

import com.example.blog_system.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.blog_system.entity.Tag;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    /**
     * Retrieves all tags from the database
     * @return a list of all tags
     */
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    /**
     * Retrieves a tag by its name
     * @param name name of the tag
     * @return optional containing the found tag or empty if not found
     */
    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByNameIgnoreCase(name);
    }

    /**
     * Creates and saves a new tag in the database
     * @param tag tag to be created
     * @return created tag
     */
    public Tag insertTag(Tag tag) {
        if (tagRepository.findByNameIgnoreCase(tag.getName()).isPresent()) {
            throw new IllegalArgumentException("Tag '" + tag.getName() + "' existiert bereits!");
        }
        return tagRepository.save(tag);
    }

    /**
     * Updates an existing tag in the database by name
     * @param tag tag of an article
     * @return updated tag
     */
    public Tag updateTagByName(Tag tag) {
        return tagRepository.save(tag);
    }

    /**
     * Deletes a tag from the database by its name
     * @param name name of the tag to be deleted
     */
    public void deleteTagByName(String name) {
        tagRepository.deleteByNameIgnoreCase(name);
    }

}
