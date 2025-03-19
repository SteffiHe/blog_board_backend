package com.example.blog_system.service;

import com.example.blog_system.entity.Rate;
import com.example.blog_system.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface RateService {
    List<Rate> getAllRates();
    Optional<Rate> getRateByName(String name);
}
