package com.example.blog_system.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Data
@Document(collection = "recommendation")
public class Recommendation {
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String id; //unique id

    @Indexed(unique = true)
    private String name; //name of the tag

    @CreatedDate
    @Field(targetType = FieldType.DATE_TIME, name = "create_time")
    private Date createTime;

    public Recommendation() {
    }

    public Recommendation(String name) {
        this.name = name;
    }
}
