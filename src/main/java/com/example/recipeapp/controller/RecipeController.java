package com.example.recipeapp.controller;

import com.example.recipeapp.entity.Category;
import com.example.recipeapp.entity.Recipe;
import com.example.recipeapp.service.CategoryService;
import com.example.recipeapp.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService,
                            CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipes";
    }

    @GetMapping("/{id}")
    public String showRecipeDetails(@PathVariable UUID id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe-details";
    }

    @GetMapping("/add")
    public String showAddRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", categoryService.findAll());
        return "add-recipe";
    }

    @PostMapping("/add")
    public String addRecipe(
            @Valid @ModelAttribute("recipe") Recipe recipe,
            BindingResult bindingResult,
            @RequestParam(required = false) UUID categoryId,
            Model model) {

        if (categoryId == null) {
            bindingResult.rejectValue("category", "NotNull",
                    "Избери категория.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "add-recipe";
        }

        Category category = categoryService.findById(categoryId);
        recipe.setCategory(category);

        recipeService.create(recipe);

        return "redirect:/recipes";
    }

    @GetMapping("/{id}/edit")
    public String showEditRecipeForm(@PathVariable UUID id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "edit-recipe";
    }

    @PostMapping("/{id}/edit")
    public String updateRecipe(
            @PathVariable UUID id,
            @Valid @ModelAttribute("recipe") Recipe updatedRecipe,
            BindingResult bindingResult,
            @RequestParam(required = false) UUID categoryId,
            Model model) {

        if (categoryId == null) {
            bindingResult.rejectValue("category", "NotNull",
                    "Избери категория.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "edit-recipe";
        }

        Category category = categoryService.findById(categoryId);
        updatedRecipe.setCategory(category);

        recipeService.update(id, updatedRecipe);

        return "redirect:/recipes/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteRecipe(@PathVariable UUID id) {
        recipeService.delete(id);
        return "redirect:/recipes";
    }
}