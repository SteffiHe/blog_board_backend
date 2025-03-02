package com.example.blog_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(
    name = "user",
    schema = "public"
)
@Data
public class User implements Serializable {
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false)
    private UUID id;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private int id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @Temporal(TemporalType.DATE)
    private LocalDate birthday;
    private String email;

    @CreationTimestamp  // Automatically sets creation timestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false, nullable = false)
    private Date createTime;

}
