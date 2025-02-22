package paf.personal_blog_system.service;

import paf.personal_blog_system.entity.Blog;
import paf.personal_blog_system.entity.Category;
import paf.personal_blog_system.repo.TagRepo;
import paf.personal_blog_system.repo.CategoryRepo;
import paf.personal_blog_system.repo.BlogRepo;
import paf.personal_blog_system.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private final BlogRepo blogRepo;
    private final TagRepo tagRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public BlogService(BlogRepo blogRepo, TagRepo tagRepo, CategoryRepo categoryRepo) {
        this.blogRepo = blogRepo;
        this.tagRepo = tagRepo;
        this.categoryRepo = categoryRepo;
    }

    /**
     * Find all blogs
     * @param idFilter id of the blog to be found
     * @return list of blogs
     */
    public List<Blog> findBlogById(String idFilter) {
        List<Blog> blogList = new ArrayList<>();
        if (idFilter == null) {
            blogRepo.findAll().forEach(blogList::add);
        } else {
            blogRepo.findById(idFilter).ifPresent(blogList::add);
        }
        return blogList;
    }

    /**
     * Save or update a blog (update und insert = upsert)
     * @param blog blog to be saved (create oder update)
     * @return saved Blog
     */
    public Blog upsertBlog(final Blog blog) {
        // Save Tags
        if (blog.getTags() != null){
            List<Tag> savedTags = new ArrayList<>();
            for (Tag tag : blog.getTags()) {
                if (!tagRepo.existsById(tag.getId())) {
                    savedTags.add(tagRepo.save(tag));
                } else {
                    savedTags.add(tag);
                }
            }
            blog.setTags(savedTags);
        }
        //Save Category
        if (blog.getCategory() != null){
            Category category = blog.getCategory();
            if (!categoryRepo.existsById(category.getId())) {
                blog.setCategory(categoryRepo.save(category));
            }
        }
        //Save Blog
        Blog savedBlog = blogRepo.save(blog);
        if (savedBlog == null) {
            throw new RuntimeException("Blog could not be saved");
        }
        return savedBlog;
    }

    /**
     * Delete a blog by id
     * @param id id of the blog to be deleted
     */
    public void deleteBlog(String id) {
        blogRepo.deleteById(id);
    }

}
