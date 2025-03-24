package com.example.blog_system.service;

import com.example.blog_system.dao.UserMapper;
import com.example.blog_system.dto.ArticleDTO;
import com.example.blog_system.entity.Article;
import com.example.blog_system.entity.Category;
import com.example.blog_system.observer.ArticleObservable;
import com.example.blog_system.repository.*;
import jakarta.persistence.EntityNotFoundException;

import com.example.blog_system.strategy.ArticleSortByAuthor;
import com.example.blog_system.strategy.ArticleSortByCreateTime;
import com.example.blog_system.strategy.ArticleSortByTitle;
import com.example.blog_system.strategy.ArticleSortStrategy;

import com.example.blog_system.strategy.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import org.json.JSONObject; // You can use org.json or any other JSON library.
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {


    private ArticleRepository articleRepository;
    private ArticleObservable articleObservable;
    private TagRepository tagRepository;
    private CategoryRepository categoryRepository;
    private RateRepository rateRepository;
    private RecommendationRepository recommendationRepository;
    private ArticleSortStrategy articleSortStrategy;
    private UserMapper userMapper;


    @Autowired
    public void ArticleService(ArticleRepository articleRepository, TagRepository tagRepository,
                               CategoryRepository categoryRepository, RateRepository rateRepository , RecommendationRepository recommendationRepository, UserMapper userMapper,
                               @Qualifier("articleSortByCreateTime") ArticleSortStrategy articleSortStrategy) {
        this.articleRepository = articleRepository;
        this.articleObservable = new ArticleObservable();
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
        this.rateRepository = rateRepository;
        this.recommendationRepository = recommendationRepository;
        this.articleSortStrategy = articleSortStrategy;
        this.userMapper = userMapper;
    }

    /**
     * Retrieves all articles from the database
     * @return a list of all articles
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Article> getArticleById( String id) {
        return Optional.ofNullable(articleRepository.findById(id).orElse(null));
    }


    /**
     * Retrieves all articles from the database with authorname DTO
     * @return a list of all articles with authorname DTO
     */
    @Override
    @Transactional(readOnly = true)
    public List<ArticleDTO> getAllArticlesWithAuthornameDTO() {
        List<Article> articles = articleRepository.findAll();
        List<ArticleDTO> articleDTOList = new ArrayList<>();

        for (Article article : articles) {
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article, articleDTO); // Kopiert alle gleichnamigen Felder
            articleDTO.setAuthor(userMapper.getUsernameById(Long.valueOf(article.getAuthor()))); // Setzt den Autorennamen
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
    @Transactional(readOnly = true)
    public List<Article> getAllArticlesSorted(String sortBy) {
        List<Article> articles = articleRepository.findAll();

        // Strategy pattern implementation
        ArticleSortStrategy strategy = switch (sortBy.toLowerCase()) {
            case "title" -> new ArticleSortByTitle();
            case "createtime" -> new ArticleSortByCreateTime();
            case "author" -> new ArticleSortByAuthor();
            case "id" -> new ArticleSortById();
            case "category" -> new ArticleSortByCategory();
            case "tag" -> new ArticleSortByTag();
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
    @Override
    @Transactional(readOnly = true)
    public List<Article> getArticleByKeyword(String keyword) {
        // Search by title and content
        List<Article> articles = articleRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
        // Search by authorId, if keyword is authorname
        Long authorId = userMapper.getUserIdByUsername(keyword); // Hole die authorId aus dem Benutzernamen
        if (authorId != null) {
            articles.addAll(articleRepository.findByAuthor(authorId.toString()));
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
    @Override
    @Transactional
    public Article insertArticle(Article article) {
        // Get the highest existing ID
        List<String> ids = articleRepository.findAllArticleIds();

        System.out.println("ids");
        System.out.println(ids);

        int maxId = ids.stream()
                .map(id -> new JSONObject(id).getString("_id")) // Extract the _id field from the JSON string
                //.map(id -> id.substring(1)) // Remove the "a" prefix
                .mapToInt(Integer::parseInt) // Convert to integer
                .max()
                .orElse(1); // Default to 0 if empty

        // Create new ID
        String newId = String.valueOf(maxId + 1);
        article.setId(newId);

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

        //System.out.println(rateRepository.findByNameIgnoreCase(article.getRate().getName()));

        // save or update rate
        if (article.getRate() != null) {
            article.setRate(
                    rateRepository.findByNameIgnoreCase(article.getRate().getName())
                    .orElseGet(() -> rateRepository.save( article.getRate()))
            );
        }

        // save or update recommendation
        if (article.getRecommendation() != null) {
            article.setRecommendation(
                    recommendationRepository.findByNameIgnoreCase(article.getRecommendation().getName())
                    .orElseGet(() -> recommendationRepository.save( article.getRecommendation()))
            );
        }

        // save article
        Article savedArticle = articleRepository.save(article);

        // Notify Observer - article was created
        articleObservable.notifyObservers("created", savedArticle);

        // save or update article
        return savedArticle;
    }

    /**
     * Deletes an article by its ID
     * @param id id of an article to delete
     */
    @Override
    @Transactional
    public Article deleteArticle(String id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + id));

        articleRepository.deleteById(id);

        // Notify Observer article was deleted
        articleObservable.notifyObservers("deleted", article);

        return article;

    }

    /**
     * Updates an existing article with new data
     * @param id the ID of the article to update
     * @param article the new article data
     * @return the updated article
     */
    @Override
    @Transactional
    public Article updateArticle(String id, Article article) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + id));

        // Update basic article properties
        existingArticle.setTitle(article.getTitle());
        existingArticle.setContent(article.getContent());
        existingArticle.setAuthor(article.getAuthor());
        existingArticle.setCreateTime(existingArticle.getCreateTime());

        // Update or save tags
        existingArticle.setTags(article.getTags().stream()
                .map(tag -> tagRepository.findByNameIgnoreCase(tag.getName())
                        .stream().findFirst().orElseGet(() -> tagRepository.save(tag)))
                .toList());

        // Update or save category
        existingArticle.setCategory(
                categoryRepository.findByNameIgnoreCase(article.getCategory().getName())
                        .orElseGet(() -> categoryRepository.save(existingArticle.getCategory()))
        );

        // Update or save rating
        existingArticle.setRate(
                rateRepository.findByNameIgnoreCase(article.getRate().getName())
                        .orElseGet(() -> rateRepository.save(existingArticle.getRate()))
        );

        // Update or save recommendation
        existingArticle.setRecommendation(
                recommendationRepository.findByNameIgnoreCase(article.getRecommendation().getName())
                        .orElseGet(() -> recommendationRepository.save(existingArticle.getRecommendation()))
        );

        // Save the updated article to the repository
        Article savedArticle = articleRepository.save(existingArticle);

        // Notify Observer - article was updated
        articleObservable.notifyObservers("updated", savedArticle);

        return savedArticle;
    }

    /**
     * Updates the category of an existing article.
     * If the category does not exist, it will be created and assigned to the article.
     * @param articleId the ID of the article to update
     * @param category the new category
     * @return the updated article
     */
    @Transactional
    public Article updateArticleCategory(String articleId, Category category) {
        // Find the article by ID
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + articleId));

        // Find or create the category
        Category existingCategory = categoryRepository.findByNameIgnoreCase(category.getName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(category.getName());
                    return categoryRepository.save(newCategory);
                });

        // Update the article's category
        article.setCategory(existingCategory);

        // Save the updated article
        return articleRepository.save(article);

    }

}
