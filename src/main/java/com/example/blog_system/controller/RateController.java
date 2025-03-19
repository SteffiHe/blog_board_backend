package com.example.blog_system.controller;

import com.example.blog_system.entity.Rate;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rate")
@RestController
public class RateController {

    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/getAllRates",method = RequestMethod.GET)
    public List<Rate> getAllRates() {
        return rateService.getAllRates();
    }

    @RequestMapping(value = "/getRateByName/{name}",method = RequestMethod.GET)
    public Rate getRateByName(@PathVariable String name) {
        return rateService.getRateByName(name).orElse(null);
    }
}
