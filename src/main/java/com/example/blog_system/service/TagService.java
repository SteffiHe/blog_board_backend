package com.example.blog_system.service;

import com.example.blog_system.entity.Tag;
import java.util.List;
import java.util.Optional;

public interface TagService {

    List<Tag> getAllTags();

    Optional<Tag> getTagByName(String name);

    Tag insertTag(Tag tag);

    Tag updateTagByName(Tag tag);

    void deleteTagByName(String name);

}