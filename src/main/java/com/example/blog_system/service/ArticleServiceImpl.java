package com.example.blog_system.service;

import com.example.blog_system.dao.UserMapper;
import com.example.blog_system.dto.ArticleDTO;
import com.example.blog_system.entity.Article;
import com.example.blog_system.event.ArticleSavedEvent;
import com.example.blog_system.repository.ArticleRepository;
import com.example.blog_system.repository.CategoryRepository;
import com.example.blog_system.repository.RateRepository;
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


    private ArticleRepository articleRepository;


    private TagRepository tagRepository;


    private CategoryRepository categoryRepository;


    private RateRepository rateRepository;

    private ApplicationEventPublisher eventPublisher;
    private ArticleSortStrategy articleSortStrategy;


    @Autowired
    public void ArticleService(ArticleRepository articleRepository, TagRepository tagRepository,
                               CategoryRepository categoryRepository, RateRepository rateRepository ,ApplicationEventPublisher eventPublisher,
                               @Qualifier("articleSortByCreateTime") ArticleSortStrategy articleSortStrategy) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
        this.rateRepository = rateRepository;
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
        // Get the highest existing ID
        List<String> ids = articleRepository.findAllArticleIds();

        int maxId = ids.stream()
                .map(id -> new JSONObject(id).getString("_id")) // Extract the _id field from the JSON string
                .map(id -> id.substring(1)) // Remove the "a" prefix
                .mapToInt(Integer::parseInt) // Convert to integer
                .max()
                .orElse(1); // Default to 0 if empty

        // Create new ID
        String newId = "a" + (maxId + 1);
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
    public Article deleteArticle(String id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + id));

        articleRepository.deleteById(id);

        return article;

    }

    /**
     * Updates an existing article with new data
     * @param id the ID of the article to update
     * @param article the new article data
     * @return the updated article
     */
    public Article updateArticle(String id, Article article) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Article not found with ID: " + id));

        existingArticle.setTitle(article.getTitle());
        existingArticle.setContent(article.getContent());
        existingArticle.setAuthor(article.getAuthor());
        existingArticle.setCreateTime(existingArticle.getCreateTime());

        // Aktualisiere oder speichere Tags
        existingArticle.setTags(article.getTags().stream()
                .map(tag -> tagRepository.findByNameIgnoreCase(tag.getName())
                        .stream().findFirst().orElseGet(() -> tagRepository.save(tag)))
                .toList());

        // Aktualisiere oder speichere Kategorie
        existingArticle.setCategory(
                categoryRepository.findByNameIgnoreCase(article.getCategory().getName())
                        .orElseGet(() -> categoryRepository.save(existingArticle.getCategory()))
        );

        // Aktualisiere oder speichere Rate
        existingArticle.setRate(
                rateRepository.findByNameIgnoreCase(article.getRate().getName())
                        .orElseGet(() -> rateRepository.save(existingArticle.getRate()))
        );

        Article savedArticle = articleRepository.save(existingArticle);
        return savedArticle;

    }

    /**
     * Updates the category of an existing article.
     * If the category does not exist, it will be created and assigned to the article.
     * @param articleId the ID of the article to update
     * @param category the new category
     * @return the updated article
     */
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
