package com.example.blog_system.service;
import com.example.blog_system.entity.Recommendation;

import java.util.List;
import java.util.Optional;

public interface RecommendationService {
    List<Recommendation> getAllRecommendations();
    Optional<Recommendation> getRecommendationByName(String name);
}
