package paf.personal_blog_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class to represent a tag
 */
@Setter
@Getter
@Document(collection = "tags")
@NoArgsConstructor
public class Tag {
    @Id
    @JsonProperty
    private String id; //unique id
    @JsonProperty
    private String name; //name of the tag

    /**
     * Constructor
     * @param name name of the tag
     */
    public Tag(String name) {
        this.name = name;
    }
}
