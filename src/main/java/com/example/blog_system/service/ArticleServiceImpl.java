package com.example.blog_system.service;

import com.example.blog_system.dao.UserMapper;
import com.example.blog_system.dto.ArticleDTO;
import com.example.blog_system.entity.Article;
import com.example.blog_system.event.ArticleSavedEvent;
import com.example.blog_system.repository.ArticleRepository;
import com.example.blog_system.repository.CategoryRepository;
import com.example.blog_system.repository.TagRepository;
import com.example.blog_system.strategy.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ArticleSortStrategy articleSortStrategy;
    private final UserMapper userMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, TagRepository tagRepository,
                              CategoryRepository categoryRepository, ApplicationEventPublisher eventPublisher,
                              @Qualifier("articleSortByCreateTime") ArticleSortStrategy articleSortStrategy,
                              UserMapper userMapper) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
        this.eventPublisher = eventPublisher;
        this.articleSortStrategy = articleSortStrategy;
        this.userMapper = userMapper;
    }

    /**
     * Retrieves all articles from the database
     * @return a list of all articles
     */
    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    /**
     * Retrieves all articles from the database with authorname
     * @return a list of all articles with authorname
     */
    @Override
    public List<Article> getAllArticlesWithAuthorname() {
        List<Article> articles = articleRepository.findAll();

        for (Article article : articles) {
            System.out.println("AuthorId: " + article.getAuthorId());
            if (article.getAuthorId() != null) {
                String username = userMapper.getUsernameById(article.getAuthorId());
                System.out.println("Username: " + username);
                if (username != null) {
                    article.setAuthorName(username);
                }
            }
        }
        return articles;
    }

    /**
     * Retrieves all articles from the database with authorname DTO
     * @return a list of all articles with authorname DTO
     */
    @Override
    public List<ArticleDTO> getAllArticlesWithAuthornameDTO() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDTO> articleDTOList = new ArrayList<>();

        for (Article article : articles) {
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article, articleDTO); // Kopiert alle gleichnamigen Felder
            articleDTO.setAuthorName(userMapper.getUsernameById(article.getAuthorId())); // Setzt den Autorennamen
            articleDTOList.add(articleDTO);
        }

        return articleDTOList;
    }

    /**
     * Retrieves all articles from the database and sorts them based on the specified criteria
     * This method implements the Strategy Pattern to allow dynamic sorting by different attributes
     * @param sortBy the field to sort the articles by (e.g., "title", "createtime", "author", "id")
     * @return list of sorted articles
     */
    @Override
    public List<Article> getAllArticlesSorted(String sortBy) {
        List<Article> articles = articleRepository.findAll();

        // Strategy pattern implementation
        ArticleSortStrategy strategy = switch (sortBy.toLowerCase()) {
            case "title" -> new ArticleSortByTitle();
            case "createtime" -> new ArticleSortByCreateTime();
            case "author" -> new ArticleSortByAuthor();
            case "id" -> new ArticleSortById();
            default -> articleSortStrategy;  // Default strategy, can be "createTime" or any default
        };

        return strategy.sort(articles);
    }

    /**
     * Retrieves a list of articles that contain a specified keyword
     * in their title, content or author's name
     * @param keyword keyword to search for in an article
     * @return a list of matching articles
     */
    public List<Article> getArticleByKeyword(String keyword) {
        // Search by title and content
        List<Article> articles = articleRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
        // Search by authorId, if keyword is authorname
        Long authorId = userMapper.getUserIdByUsername(keyword); // Hole die authorId aus dem Benutzernamen
        if (authorId != null) {
            articles.addAll(articleRepository.findByAuthorId(authorId));
        }
        // Remove duplicates as there might be overlaps
        return articles.stream().distinct().toList();

    }

    /**
     * Saves a new article or updates an existing article
     * If the article contains tags or a category, they are also saved or updated
     * @param article article to save or update
     * @return saved or updated article
     */
    public Article insertArticle(Article article) {
        // save or update tags
        if (article.getTags() != null) {
            article.setTags(article.getTags().stream()
                    .map(tag -> tagRepository.findByNameIgnoreCase(tag.getName())
                            .stream().findFirst().orElseGet(() -> tagRepository.save(tag)))
                    .toList());
        }

        // save or update category
        if (article.getCategory() != null) {
            article.setCategory(
                    categoryRepository.findByNameIgnoreCase(article.getCategory().getName())
                    .orElseGet(() -> categoryRepository.save( article.getCategory()))
            );
        }

        // save article
        Article savedArticle = articleRepository.save(article);

        // publish event
        eventPublisher.publishEvent(new ArticleSavedEvent(savedArticle));

        // save or update article
        return savedArticle;
    }

    /**
     * Deletes an article by its ID
     * @param id id of an article to delete
     */
    @Override
    public void deleteArticle(String id) {
        articleRepository.deleteById(id);
    }


}
