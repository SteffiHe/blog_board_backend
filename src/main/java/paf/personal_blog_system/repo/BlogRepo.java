package paf.personal_blog_system.repo;

import org.springframework.stereotype.Repository;
import paf.personal_blog_system.entity.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface BlogRepo extends MongoRepository<Blog, String> {
}
