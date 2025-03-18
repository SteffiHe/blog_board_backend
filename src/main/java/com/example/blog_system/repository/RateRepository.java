package com.example.blog_system.repository;

import com.example.blog_system.entity.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepository extends MongoRepository<Rate, String> {

    Optional<Rate> findByNameIgnoreCase(String name);
}
