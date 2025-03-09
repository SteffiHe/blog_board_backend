package com.example.blog_system.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

//    @Mock private BlogRepository blogRepository;
//    @Mock private TagRepository tagRepository;
//    @Mock private CategoryRepository categoryRepository;
//    @InjectMocks private BlogService blogService;
//
//    @Test
//    void saveOrUpdateBlog_ShouldSaveNewTagAndCategory() {
//        Blog blog = new Blog();
//        Tag tag = new Tag("Tech");
//        Category category = new Category("Programming");
//        blog.setTags(List.of(tag));
//        blog.setCategory(category);
//
//        when(tagRepository.findById("1")).thenReturn(Optional.empty());
//        when(categoryRepository.findById("1")).thenReturn(Optional.empty());
//        when(tagRepository.save(tag)).thenReturn(tag);
//        when(categoryRepository.save(category)).thenReturn(category);
//        when(blogRepository.save(blog)).thenReturn(blog);
//
//        Blog result = blogService.saveOrUpdateBlog(blog);
//
//        assertNotNull(result);
//        assertEquals("Tech", result.getTags().get(0).getName());
//        assertEquals("Programming", result.getCategory().getName());
//
//        verify(tagRepository).save(tag);
//        verify(categoryRepository).save(category);
//        verify(blogRepository).save(blog);
//    }
//
//    @Test
//    void deleteBlog_ShouldDeleteBlogById() {
//        doNothing().when(blogRepository).deleteById("1");
//
//        blogService.deleteBlog("1");
//
//        verify(blogRepository).deleteById("1");
//    }
}