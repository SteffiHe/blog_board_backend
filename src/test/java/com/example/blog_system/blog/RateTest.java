package com.example.blog_system.blog;

import com.example.blog_system.entity.Rate;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.repository.RateRepository;
import com.example.blog_system.service.RateService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional  // Ensures rollback after each test
@Slf4j
public class RateTest {

    @Autowired
    private RateService rateService;

    @Autowired
    private RateRepository rateRepository;

    private Rate rate;

    @BeforeEach
    void setUp() {
        rate = new Rate();
        rate.setName("Rate test");
        rate = rateRepository.save(rate);
    }

    @AfterEach
    public void tearDown() {
        // Cleanup test data after each test
        rateService.deleteRateByName("Rate test");
    }

    // Test to get all rates
    @Test
    void testGetAllRates() {
        List<Rate> rates = rateService.getAllRates();
        assertFalse(rates.isEmpty());
    }

    // Test to get rate by name
    @Test
    void testGetRateByName() {
        Optional<Rate> result  = rateService.getRateByName("Rate test");
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals("Rate test", result.get().getName());
    }
}
