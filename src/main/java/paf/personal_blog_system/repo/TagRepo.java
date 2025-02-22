package paf.personal_blog_system.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import paf.personal_blog_system.entity.Tag;

@Repository
public interface TagRepo extends MongoRepository<Tag, String> {
}
