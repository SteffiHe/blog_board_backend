package com.example.blog_system.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

/**
 * Class to represent a tag
 */
@Data
@Document(collection = "tag")
public class Tag {
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String id; //unique id

    @Indexed(unique = true)  // Indexierung für schnellere Abfragen
    private String name; //name of the tag

    @CreatedDate
    @Field(targetType = FieldType.DATE_TIME, name = "create_time")
    private Date createTime;
}
