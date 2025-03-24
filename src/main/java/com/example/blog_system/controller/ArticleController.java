package com.example.blog_system.controller;

import com.example.blog_system.dto.ArticleDTO;
import com.example.blog_system.entity.Article;
import com.example.blog_system.entity.Category;
import com.example.blog_system.result.Result;
import com.example.blog_system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * Retrieves all articles
     *
     * @return a list of all articles
     */
    @RequestMapping(value = "/getAllArticles", method = RequestMethod.GET)
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }


    @RequestMapping(value = "/getArticleById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getArticleById(@PathVariable String id) {
        Optional<Article> articleOptional = articleService.getArticleById(id);

        if (articleOptional.isPresent()) {
            return ResponseEntity.ok(articleOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article with id " + id + " not found.");
        }

    }

    /**
     * Retrieves all articles with authorname DTO
     *
     * @return a list of all articles authorname DTO
     */
    @GetMapping("/getAllArticlesWithAuthornameDTO")
    public ResponseEntity<List<ArticleDTO>> getAllArticlesWithAuthornameDTO() {
        List<ArticleDTO> articles = articleService.getAllArticlesWithAuthornameDTO();
        return ResponseEntity.ok(articles);
    }

    /**
     * Retrieves all articles with optional sorting
     *
     * @param sortBy sorting criteria (title, createTime, author)
     * @return a list of sorted articles
     */
    @RequestMapping(value = "/getAllArticlesSorted", method = RequestMethod.GET)
    public ResponseEntity<List<Article>> getAllArticlesSorted(@RequestParam(defaultValue = "createTime") String sortBy) {
        List<Article> sortedArticles = articleService.getAllArticlesSorted(sortBy);
        return ResponseEntity.ok(sortedArticles);
    }


    /**
     * Retrieves articles containing a specified keyword
     * in their title, content or author
     *
     * @param keyword keyword to search for
     * @return a list of matching articles
     */
    @RequestMapping(value = "/getArticleByKeyword/{keyword}", method = RequestMethod.GET)
    public ResponseEntity<?> getArticleByKeyword(@PathVariable String keyword) {
        List<Article> existingArticles = articleService.getArticleByKeyword(keyword);

        if (existingArticles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article with keyword " + keyword + " not found.");
        } else {
            return ResponseEntity.ok(existingArticles);
        }
    }

    /**
     * Inserts a new article
     *
     * @param article article to insert
     * @return inserted article
     */
    @RequestMapping(value = "/insertArticle", method = RequestMethod.POST)
    public ResponseEntity<Article> insertArticle(@RequestBody Article article) {
        Article savedArticle = articleService.insertArticle(article);
        return ResponseEntity.ok(savedArticle);
    }

    @RequestMapping(value = "/updateArticle/{articleId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateArticleCategory(
            @PathVariable String articleId,
            @RequestBody Article article) {

        Article existingArticle = articleService.getArticleById(articleId).orElse(null);

        if(existingArticle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article with id " + articleId + " not found.");
        } else {
            Article updatedArticle = articleService.updateArticle(articleId, article);
            return ResponseEntity.ok(updatedArticle);
        }

    }

    /**
     * Updates the category of an existing article.
     *
     * @param articleId the ID of the article to update
     * @param category  the new category
     * @return ResponseEntity with the updated article
     */
    @RequestMapping(value = "/updateCategory/{articleId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateArticle(
            @PathVariable String articleId,
            @RequestBody Category category) {

        Article existingArticle = articleService.getArticleById(articleId).orElse(null);

        if(existingArticle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article with id " + articleId + " not found.");
        } else {
            Article updatedArticle = articleService.updateArticleCategory(articleId, category);
            return ResponseEntity.ok(updatedArticle);
        }

    }

    /**
     * Deletes an article by its ID
     *
     * @param id id of the article to delete
     */
    @RequestMapping(path = "/deleteArticle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteArticle(@PathVariable String id) {

        Article existingArticle = articleService.getArticleById(id).orElse(null);

        if (existingArticle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article with id " + id + " not found.");
        } else {
            articleService.deleteArticle(id);
            return ResponseEntity.ok("Article with id " + id + " deleted.");
        }

    }


}
