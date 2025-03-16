package com.example.blog_system.repository;

import com.example.blog_system.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

    /**
     * Searches for articles that contain a specified keyword
     * in the title, content or author field
     * @param title keyword to search in the title
     * @param content keyword to search in the content
     * @param authorId keyword to search in the author field
     * @return a list of matching articles
     */
    List<Article> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            String title, String content, String authorId);

    @Query(value = "{ '_id': { $regex: '^a[0-9]+' } }", fields = "{ '_id': 1}")
    List<String> findAllArticleIds();
}
