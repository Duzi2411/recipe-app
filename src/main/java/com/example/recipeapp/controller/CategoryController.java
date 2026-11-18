package com.example.recipeapp.controller;

import com.example.recipeapp.entity.Category;
import com.example.recipeapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @PostMapping("/add")
    public String addCategory(
            @Valid @ModelAttribute("category") Category category,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "add-category";
        }

        categoryService.create(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String showEditCategoryForm(@PathVariable UUID id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "edit-category";
    }

    @PostMapping("/{id}/edit")
    public String updateCategory(
            @PathVariable UUID id,
            @Valid @ModelAttribute("category") Category updatedCategory,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit-category";
        }

        categoryService.update(id, updatedCategory);
        return "redirect:/categories";
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable UUID id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}