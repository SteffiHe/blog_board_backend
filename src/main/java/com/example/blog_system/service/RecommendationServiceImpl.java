package com.example.blog_system.service;

import com.example.blog_system.entity.Recommendation;
import com.example.blog_system.repository.RecommendationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Override
    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    @Override
    public Optional<Recommendation> getRecommendationByName(String name){
        return recommendationRepository.findByNameIgnoreCase(name);
    }

    @Override
    public void deleteRecommendationByName(String name){
        recommendationRepository.deleteByNameIgnoreCase(name);
    }
}
