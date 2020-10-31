package com.akropoliprishtine.services.book;

import com.akropoliprishtine.entities.book.Author;
import com.akropoliprishtine.entities.book.Category;
import com.akropoliprishtine.repositories.book.AuthorRepository;
import com.akropoliprishtine.repositories.book.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<Category> saveCategories(List<Category> categories) {
        return this.categoryRepository.saveAll(categories);
    }

    public Category saveCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }
}
