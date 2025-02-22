package paf.personal_blog_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

/**
 * Class to represent a blog
 */
@Document(collection = "blogs")
@NoArgsConstructor
@Getter
@Setter
public class Blog {
    @Id
    @JsonProperty
    private String id; //unique id
    @JsonProperty
    private String title; //title of the blog
    @JsonProperty
    private String author; //author of the blog
    @JsonProperty
    private String content; //content of the blog
    @DBRef
    private Category category; //blog has one category
    @DBRef
    private List<Tag> tags; //blog has many tags

    /**
     * Constructor without id (MongoDB will auto-generate it)
     * @param title title of the blog
     * @param author author of the blog
     * @param content content of the blog
     * @param category category of the blog
     * @param tags tags of the blog
     */
    public Blog(String title, String author, String content, Category category, List<Tag> tags) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.category = category;
        this.tags = tags;
    }

        public static void main(String[] args) {
            Blog blog = new Blog();
            blog.setCategory(new Category("Tech"));
            blog.setTags(List.of(new Tag("Java"), new Tag("Spring")));

            System.out.println("Category: " + blog.getCategory().getName());
            System.out.println("Tags: " + blog.getTags().stream().map(Tag::getName).toList());
    }
}
