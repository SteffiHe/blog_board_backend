package com.example.blog_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.blog_system.entity.Blog;
import com.example.blog_system.service.BlogService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/controller/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * Find all blogs
     * @return list of blogs
     */
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.findAllBlogs();
    }

    /**
     * Find a blog by id
     * @param id id of the blog to be found
     * @return blog
     */
    @GetMapping(path = "/byId/{id}")
    public Optional<Blog> getBlogById(@PathVariable String id){
        return blogService.findBlogById(id);
    }

    /**
     * Save or update a blog (update und insert = upsert)
     * @param blog blog to be saved (create oder update)
     * @return savedBlog
     */
    @PatchMapping(path = "/upsert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Blog saveOrUpdateBlog(@RequestBody Blog blog) {
        return blogService.saveOrUpdateBlog(blog);
    }

    /**
     * Delete a blog by id
     * @param id id of the blog to be deleted
     */
    @DeleteMapping(path = "/byId//{id}")
    public void deleteBlog(@PathVariable String id) {
        blogService.deleteBlog(id);
    }

}
