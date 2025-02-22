package paf.personal_blog_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class to represent a category
 */
@Document(collection = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @JsonProperty
    private String id; //unique id
    @JsonProperty
    private String name; //name of the category

    /**
     * Constructor
     * @param name name of the category
     */
    public Category(String name) {
        this.name = name;
    }
}