package com.example.blog_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.service.TagService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * Retrieves all tags
     * @return a list of all tags
     */
    @GetMapping("/getAllTags")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    /**
     * Retrieves a tag by its name
     * @param name name of the tag to search for
     * @return tag if found, otherwise response
     */
    @GetMapping("/getTagByName/{name}")
    public ResponseEntity<?> getTagById(@PathVariable String name) {

        Optional<Tag> existingTagOptional = tagService.getTagByName(name);

        if (existingTagOptional.isPresent()) {
            return ResponseEntity.ok(existingTagOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag with Name " + name + " not found.");
        }
    }

    /**
     * Creates a new tag if it does not already exist
     * @param tag tag to create
     * @return created tag or a conflict response if it already exists
     */
    @PostMapping("/insertTag")
    public ResponseEntity<?> insertTag(@RequestBody Tag tag) {

        Optional<Tag> existingTag = tagService.getTagByName(tag.getName());

        if (existingTag.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Tag with name '" + tag.getName() + "' already exists.");
        }

        Tag savedTag = tagService.insertTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTag);
    }

    /**
     * Updates a tag by name
     * @param name name of the tag to update
     * @param tag new tag data
     * @return updated tag if found or a conflict response
     */
    @PatchMapping("/updateTagByName/{name}")
    public ResponseEntity<?> updateTag(@PathVariable String name, @RequestBody Tag tag) {
        Optional<Tag> existingTagOptional = tagService.getTagByName(name);

        if (existingTagOptional.isPresent()) {
            Tag existingTag = existingTagOptional.get();

            // Update only the fields provided in the request
            if (tag.getName() != null) {
                existingTag.setName(tag.getName());
            }

            Tag updatedTag = tagService.updateTagByName(existingTag);
            return ResponseEntity.ok(updatedTag);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag with Name " + name + " not found.");
        }
    }

    /**
     * Deletes a tag by name
     * @param name name of the tag to delete
     * @return success message if found, otherwise response
     */
    @DeleteMapping("/deleteTagByName/{name}")
    public ResponseEntity<?> deleteTag(@PathVariable String name) {
        Optional<Tag> existingTagOptional = tagService.getTagByName(name);

        if (existingTagOptional.isPresent()) {
            tagService.deleteTagByName(name);

            return ResponseEntity.ok("Tag with Name " + name + " has been deleted.");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag with Name " + name + " not found.");
        }
    }
}
