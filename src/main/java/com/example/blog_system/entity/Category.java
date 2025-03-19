package com.example.blog_system.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

/**
 * Class to represent a category
 */
@Data
@Document(collection = "category")
public class Category {
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String id; //unique id

    @Indexed(unique = true)
    private String name; //name of the category

    @CreatedDate
    @Field(targetType = FieldType.DATE_TIME, name = "create_time")
    private Date createTime;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

}