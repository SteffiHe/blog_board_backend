package com.example.blog_system.service;

import com.example.blog_system.entity.Category;
import com.example.blog_system.entity.Rate;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public List<Rate> getAllRates() {
        return rateRepository.findAll();
    }

    @Override
    public Optional<Rate> getRateByName(String name){
        return rateRepository.findByNameIgnoreCase(name);
    }
}
