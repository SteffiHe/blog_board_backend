package com.example.blog_system.controller;

import com.example.blog_system.entity.Article;
import com.example.blog_system.result.Result;
import com.example.blog_system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping(value = "/getAllArticles",method = RequestMethod.GET)
    public List<Article> getAllArticles(){

        System.out.println(articleService.getAllArticles());
        return articleService.getAllArticles();

    }
}
