package paf.personal_blog_system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object for a blog
 */
@NoArgsConstructor
@Getter
@Setter
public class BlogDto {
    private String title;
    private String author;
    private String content;

    public BlogDto(String title, String author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
