package com.example.blog_system.service;

import com.example.blog_system.entity.Tag;
import com.example.blog_system.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

//    @Mock private TagRepository tagRepository;
//    @InjectMocks private TagService tagService;
//
//    @Test
//    void saveTag_ShouldSaveOrUpdateNewTag() {
//        Tag tag = new Tag("Tech");
//        when(tagRepository.save(tag)).thenReturn(tag);
//
//        Tag result = tagService.saveOrUpdateTag(tag);
//
//        assertNotNull(result);
//        assertEquals("Tech", result.getName());
//        verify(tagRepository).save(tag);
//    }
//
//    @Test
//    void findTagById_ShouldReturnTag() {
//        Tag tag = new Tag("1");
//        when(tagRepository.findById("1")).thenReturn(Optional.of(tag));
//
//        Optional<Tag> result = tagService.findTagById("1");
//
//        assertTrue(result.isPresent());
//        assertEquals("Tech", result.get().getName());
//    }
//
//    @Test
//    void deleteTag_ShouldDeleteTagById() {
//        doNothing().when(tagRepository).deleteById("1");
//
//        tagService.deleteTag("1");
//
//        verify(tagRepository).deleteById("1");
//    }
}