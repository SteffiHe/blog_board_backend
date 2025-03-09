package com.example.blog_system.controller;

import com.example.blog_system.entity.Article;
import com.example.blog_system.result.Result;
import com.example.blog_system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/getAllArticles",method = RequestMethod.GET)
    public List<Article> getAllArticles(){

        return articleService.getAllArticles();

    }

    @RequestMapping(value = "/getArticleByKeyword/{keyword}",method = RequestMethod.GET)
    public ResponseEntity<List<Article>> getArticleByKeyword(@PathVariable String keyword) {
        List<Article> articles = articleService.getArticleByKeyword(keyword);
        return ResponseEntity.ok(articles);
    }

    @RequestMapping(value = "/insertArticle",method = RequestMethod.POST)
    public ResponseEntity<Article> insertArticle(@RequestBody Article article) {
        Article savedArticle = articleService.insertArticle(article);
        return ResponseEntity.ok(savedArticle);
    }

    @RequestMapping(path = "/byId/{id}",method = RequestMethod.DELETE)
    public void deleteArticle(@PathVariable String id) {

        articleService.deleteArticle(id);
    }


}
