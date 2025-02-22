package paf.personal_blog_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paf.personal_blog_system.entity.Tag;
import paf.personal_blog_system.repo.TagRepo;
import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepo tagRepo;

    public List<Tag> getAllTags() {
        return tagRepo.findAll();
    }

    public Tag getTagById(String id) {
        return tagRepo.findById(id).orElse(null);
    }

    public Tag saveTag(Tag tag) {
        return tagRepo.save(tag);
    }
}
