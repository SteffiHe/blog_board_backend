package com.example.blog_system.repository;

import com.example.blog_system.entity.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
    Optional<Recommendation> findByNameIgnoreCase(String name);
}
