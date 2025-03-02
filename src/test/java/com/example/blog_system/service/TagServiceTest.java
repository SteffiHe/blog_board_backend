package com.example.blog_system.service;

import com.example.blog_system.entity.Tag;
import com.example.blog_system.repo.TagRepo;
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

    @Mock private TagRepo tagRepo;
    @InjectMocks private TagService tagService;

    @Test
    void saveTag_ShouldSaveOrUpdateNewTag() {
        Tag tag = new Tag("Tech");
        when(tagRepo.save(tag)).thenReturn(tag);

        Tag result = tagService.saveOrUpdateTag(tag);

        assertNotNull(result);
        assertEquals("Tech", result.getName());
        verify(tagRepo).save(tag);
    }

    @Test
    void findTagById_ShouldReturnTag() {
        Tag tag = new Tag("1");
        when(tagRepo.findById("1")).thenReturn(Optional.of(tag));

        Optional<Tag> result = tagService.findTagById("1");

        assertTrue(result.isPresent());
        assertEquals("Tech", result.get().getName());
    }

    @Test
    void deleteTag_ShouldDeleteTagById() {
        doNothing().when(tagRepo).deleteById("1");

        tagService.deleteTag("1");

        verify(tagRepo).deleteById("1");
    }
}