package paf.personal_blog_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paf.personal_blog_system.entity.Category;
import paf.personal_blog_system.repo.CategoryRepo;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(String id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }
}
