package com.example.blog_system.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi articleApi() {
        return GroupedOpenApi.builder()
                .group("article")
                .pathsToMatch("/api/article/**")
                .build();
    }

    @Bean
    public GroupedOpenApi categoryApi() {
        return GroupedOpenApi.builder()
                .group("category")
                .pathsToMatch("/api/category/**")
                .build();
    }

    @Bean
    public GroupedOpenApi rateApi() {
        return GroupedOpenApi.builder()
                .group("rate")
                .pathsToMatch("/api/rate/**")
                .build();
    }

    @Bean
    public GroupedOpenApi recommendationApi() {
        return GroupedOpenApi.builder()
                .group("recommendation")
                .pathsToMatch("/api/recommendation/**")
                .build();
    }

    @Bean
    public GroupedOpenApi tagApi() {
        return GroupedOpenApi.builder()
                .group("tag")
                .pathsToMatch("/api/tag/**")
                .build();
    }
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/api/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/**")
                .build();
    }

}

