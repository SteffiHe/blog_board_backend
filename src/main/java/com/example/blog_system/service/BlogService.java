package com.example.blog_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.blog_system.entity.Blog;
import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.repo.BlogRepo;
import com.example.blog_system.repo.CategoryRepo;
import com.example.blog_system.repo.TagRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private final BlogRepo blogRepo;
    private final TagRepo tagRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public BlogService(BlogRepo blogRepo, TagRepo tagRepo, CategoryRepo categoryRepo) {
        this.blogRepo = blogRepo;
        this.tagRepo = tagRepo;
        this.categoryRepo = categoryRepo;
    }

    /**
     * Find all blogs
     * @return all blogs
     */
    public List<Blog> findAllBlogs() {
        return blogRepo.findAll();
    }

    /**
     * Find a blog by id
     * @param id id of a blog
     * @return founded id
     */
    public Optional<Blog> findBlogById(String id) {
        return blogRepo.findById(id);
    }

    /**
     * Save or update a blog
     * @param blog blog to be saved (create oder update)
     * @return saved Blog
     */
    public Blog saveOrUpdateBlog(Blog blog) {
        // save Tag
        if (blog.getTags() != null) {
            List<Tag> savedTags = new ArrayList<>();
            for (Tag tag : blog.getTags()) {
                savedTags.add(tagRepo.findById(tag.getId()).orElseGet(() -> tagRepo.save(tag)));
            }
            blog.setTags(savedTags);
        }

        // save Category
        if (blog.getCategory() != null) {
            blog.setCategory(categoryRepo.findById(blog.getCategory().getId())
                    .orElseGet(() -> categoryRepo.save(blog.getCategory())));
        }

        // save Blog
        return blogRepo.save(blog);
    }

    /**
     * Delete a blog by id
     * @param id id of the blog to be deleted
     */
    public void deleteBlog(String id) {
        blogRepo.deleteById(id);
    }

}
