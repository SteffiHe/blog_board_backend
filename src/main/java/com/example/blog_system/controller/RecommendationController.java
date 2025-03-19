package com.example.blog_system.controller;

import com.example.blog_system.entity.Recommendation;
import com.example.blog_system.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/recommendation")
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
}
