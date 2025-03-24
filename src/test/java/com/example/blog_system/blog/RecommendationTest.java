package com.example.blog_system.blog;

import com.example.blog_system.entity.Recommendation;
import com.example.blog_system.repository.RecommendationRepository;
import com.example.blog_system.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional  // Ensures rollback after each test
@Slf4j
public class RecommendationTest {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private RecommendationRepository recommendationRepository;

    private Recommendation recommendation;

    @BeforeEach
    void setUp() {
        recommendation = new Recommendation();
        recommendation.setName("Recommendation test");
        recommendation = recommendationRepository.save(recommendation);
    }

    @AfterEach
    public void tearDown() {
        // Cleanup test data after each test
        recommendationService.deleteRecommendationByName("Recommendation test");
    }

    // Test to get all recommendations
    @Test
    void testGetAllRecommendations() {
        List<Recommendation> recommendations = recommendationService.getAllRecommendations();
        assertFalse(recommendations.isEmpty());
    }

    // Test to get recommendation by name
    @Test
    void testGetRecommendationByName() {
        Optional<Recommendation> result  = recommendationService.getRecommendationByName("Recommendation test");
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals("Recommendation test", result.get().getName());
    }
}
