package paf.personal_blog_system.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import paf.personal_blog_system.entity.Blog;
import paf.personal_blog_system.entity.Category;
import paf.personal_blog_system.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) throws Exception {
        //Blog blog = new Blog("Test-Titel", "Steffi", "Das ist ein Test!");
        Blog blog = new Blog("Titel", "Autor", "Content", new Category("Tech"), new ArrayList<Tag>());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(blog);
        System.out.println(json);
    }
}