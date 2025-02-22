package paf.personal_blog_system.api;

import org.springframework.web.bind.annotation.PathVariable;
import paf.personal_blog_system.entity.Blog;
import paf.personal_blog_system.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // CRUD
    /**
     * Find all blogs or a blog by id
     * @return list of blogs
     */
    @GetMapping
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = blogService.findBlogById(null);
        System.out.println("Daten aus MongoDB: " + blogs);
        return blogs;
    }

    /**
     * Find all blogs or a blog by id
     * @param id id of the blog to be found
     * @return list of blogs
     */
    @GetMapping(path = "/byId/{id}")
    public List<Blog> getBlogById(@PathVariable("id") final String id){
        List<Blog> blogList = blogService.findBlogById(id);
        return blogList;
    }

    /**
     * Save or update a blog (update und insert = upsert)
     * @param blog blog to be saved (create oder update)
     * @return savedBlog
     */
    @PatchMapping(path = "/upsert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Blog upsertBlog(@RequestBody final Blog blog) {
        Blog savedBlog = blogService.upsertBlog(blog);
        return savedBlog;
    }

    /**
     * Delete a blog by id
     * @param id id of the blog to be deleted
     */
    @DeleteMapping(path = "/{id}")
    public Integer deletePic(@RequestParam("id") final Integer id) {
        return 0;
    }

}
