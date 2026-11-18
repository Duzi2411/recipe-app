package com.example.recipeapp.service;

import com.example.recipeapp.entity.Category;
import com.example.recipeapp.exception.CategoryNotFoundException;
import com.example.recipeapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Категорията с ID " + id + " не е намерена."
                        ));
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(UUID id, Category updatedCategory) {
        Category category = findById(id);

        category.setName(updatedCategory.getName());
        category.setDescription(updatedCategory.getDescription());

        return categoryRepository.save(category);
    }

    public void delete(UUID id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}