package com.example.blog_system.controller;

import com.example.blog_system.entity.Rate;
import com.example.blog_system.entity.Recommendation;
import com.example.blog_system.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/recommendation")
@RestController
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @RequestMapping(value = "/getAllRecommendations",method = RequestMethod.GET)
    public List<Recommendation> getAllRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    @RequestMapping(value = "/getRecommendationByName/{name}",method = RequestMethod.GET)
    public Recommendation getRecommendationByName(@PathVariable String name) {
        return recommendationService.getRecommendationByName(name).orElse(null);
    }

    @RequestMapping(value = "/deleteRecommendationByName/{name}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRecommendationByName(@PathVariable String name) {
        Optional<Recommendation> existingRecommendationOptional = recommendationService.getRecommendationByName(name);

        if (existingRecommendationOptional.isPresent()) {
            recommendationService.deleteRecommendationByName(name);

            return ResponseEntity.ok("Recommendation with Name " + name + " has been deleted.");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recommendation with Name " + name + " not found.");
        }
    }
}
