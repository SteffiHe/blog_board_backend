package com.example.blog_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.service.TagService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controller/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * Find all tags
     * @return list of tags
     */
    @GetMapping
    public List<Tag> findAllTags() {
        return tagService.findAllTags();
    }

    /**
     * Find tag by id
     * @param id id of the tag to be found
     * @return tag
     */
    @GetMapping("/byId/{id}")
    public Optional<Tag> getTagById(@PathVariable String id) {
        return tagService.findTagById(id);
    }

    /**
     * Create a new tag
     * @param tag tag of a blog
     * @return created tag
     */
    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.saveOrUpdateTag(tag);
    }

    /**
     * Update a tag by id
     * @param id id of the tag
     * @param tag tag of a blog
     * @return updated tag
     */
    @PatchMapping("/byId/{id}")
    public Tag updateTag(@PathVariable String id, @RequestBody Tag tag) {
        //tag.setId(id);
        return tagService.saveOrUpdateTag(tag);
    }

    /**
     * Delete a tag by id
     * @param id id of the tag to be deleted
     */
    @DeleteMapping("/byId/{id}")
    public void deleteTag(@PathVariable String id) {
        tagService.deleteTag(id);
    }
}
