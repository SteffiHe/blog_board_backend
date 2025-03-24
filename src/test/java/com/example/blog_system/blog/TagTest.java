package com.example.blog_system.blog;

import com.example.blog_system.entity.Tag;
import com.example.blog_system.repository.TagRepository;
import com.example.blog_system.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // Ensures rollback after each test
@Slf4j
public class TagTest {
    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    private Tag tag;

    @BeforeEach
    void setUp() {

        tag = new Tag();
        tag.setName("MySQL");
        tag = tagRepository.save(tag); // Save second tag
    }

    @AfterEach
    public void tearDown() {
        // Cleanup test data after each test
        tagService.deleteTagByName("MySQL");
    }


    @Test
    void testGetAllTags() {
        List<Tag> tags = tagService.getAllTags();
        assertFalse(tags.isEmpty());
    }

    @Test
    void testGetTagByName() {
        Optional<Tag> result = tagService.getTagByName("MySQL");
        assertTrue(result.isPresent());
        assertEquals("MySQL", result.get().getName());
    }

    @Test
    void testInsertTag() {
        Tag newTag = new Tag();
        newTag.setName("Redis");

        Tag savedTag = tagService.insertTag(newTag);
        assertNotNull(savedTag.getId());
        assertEquals("Redis", savedTag.getName());

        //delete
        tagService.deleteTagByName("Redis");
        assertFalse(tagService.getTagByName("Redis").isPresent());
    }

    @Test
    void testUpdateTagByName() {
        tag.setName("MySQL Updated");
        Tag updatedTag = tagService.updateTagByName(tag);

        assertEquals("MySQL Updated", updatedTag.getName());

        tagService.deleteTagByName("MySQL Updated");
        assertFalse(tagService.getTagByName("MySQL").isPresent());
    }

}
