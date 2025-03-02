package com.example.blog_system.service;

import com.example.blog_system.entity.Blog;
import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.repo.BlogRepo;
import com.example.blog_system.repo.CategoryRepo;
import com.example.blog_system.repo.TagRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @Mock private BlogRepo blogRepo;
    @Mock private TagRepo tagRepo;
    @Mock private CategoryRepo categoryRepo;
    @InjectMocks private BlogService blogService;

    @Test
    void saveOrUpdateBlog_ShouldSaveNewTagAndCategory() {
        Blog blog = new Blog();
        Tag tag = new Tag("Tech");
        Category category = new Category("Programming");
        blog.setTags(List.of(tag));
        blog.setCategory(category);

        when(tagRepo.findById("1")).thenReturn(Optional.empty());
        when(categoryRepo.findById("1")).thenReturn(Optional.empty());
        when(tagRepo.save(tag)).thenReturn(tag);
        when(categoryRepo.save(category)).thenReturn(category);
        when(blogRepo.save(blog)).thenReturn(blog);

        Blog result = blogService.saveOrUpdateBlog(blog);

        assertNotNull(result);
        assertEquals("Tech", result.getTags().get(0).getName());
        assertEquals("Programming", result.getCategory().getName());

        verify(tagRepo).save(tag);
        verify(categoryRepo).save(category);
        verify(blogRepo).save(blog);
    }

    @Test
    void deleteBlog_ShouldDeleteBlogById() {
        doNothing().when(blogRepo).deleteById("1");

        blogService.deleteBlog("1");

        verify(blogRepo).deleteById("1");
    }
}