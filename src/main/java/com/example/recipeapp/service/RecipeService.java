package com.example.recipeapp.service;

import com.example.recipeapp.entity.Recipe;
import com.example.recipeapp.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe findById(UUID id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public Recipe create(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe update(UUID id, Recipe updatedRecipe) {
        Recipe recipe = findById(id);

        recipe.setTitle(updatedRecipe.getTitle());
        recipe.setDescription(updatedRecipe.getDescription());
        recipe.setInstructions(updatedRecipe.getInstructions());
        recipe.setPreparationTime(updatedRecipe.getPreparationTime());
        recipe.setServings(updatedRecipe.getServings());
        recipe.setCategory(updatedRecipe.getCategory());

        return recipeRepository.save(recipe);
    }

    public void delete(UUID id) {
        Recipe recipe = findById(id);
        recipeRepository.delete(recipe);
    }
}