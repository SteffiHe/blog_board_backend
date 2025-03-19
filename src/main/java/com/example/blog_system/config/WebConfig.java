package com.example.blog_system.config;

import com.example.blog_system.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer  {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/register");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");
    }

    @Override
    // https://stackoverflow.com/questions/49228615/is-there-any-way-we-make-pathvariable-name-case-insensitive-in-spring
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:9999") // specify the frontend's origin
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }
}
