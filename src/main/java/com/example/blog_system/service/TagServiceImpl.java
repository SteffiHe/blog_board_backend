package com.example.blog_system.service;

import com.example.blog_system.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.blog_system.entity.Tag;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByNameIgnoreCase(name);
    }

    public Tag insertTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag updateTagByName(Tag tag) {
        return tagRepository.save(tag);
    }

    public void deleteTagByName(String name) {
        tagRepository.deleteByNameIgnoreCase(name);
    }
}
