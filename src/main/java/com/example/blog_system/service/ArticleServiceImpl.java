package com.example.blog_system.service;

import com.example.blog_system.entity.Article;
import com.example.blog_system.entity.Tag;
import com.example.blog_system.repository.ArticleRepository;
import com.example.blog_system.repository.CategoryRepository;
import com.example.blog_system.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private TagRepository tagRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void ArticleService(ArticleRepository articleRepository, TagRepository tagRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Article> getAllArticles() {

        return articleRepository.findAll();
    }


    public List<Article> getArticleByKeyword(String keyword) {
        return articleRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                keyword, keyword, keyword);
    }

    public Article insertArticle(Article article) {
        // save Tag
        if (article.getTags() != null) {
            List<Tag> savedTags = new ArrayList<>();
            for (Tag tag : article.getTags()) {
                savedTags.add(
                        tagRepository.findByNameIgnoreCase(tag.getName()).orElseGet(() -> tagRepository.save(tag))
                );
            }
            article.setTags(savedTags);
        }

        // save Category
        if (article.getCategory() != null) {
            article.setCategory(
                    categoryRepository.findByNameIgnoreCase(article.getCategory().getName())
                    .orElseGet(() -> categoryRepository.save( article.getCategory()))
            );
        }

        return articleRepository.save(article);
    }


    @Override
    public void deleteArticle(String id) {
        articleRepository.deleteById(id);
    }


}
