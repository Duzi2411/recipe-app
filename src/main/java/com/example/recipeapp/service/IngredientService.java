package com.example.recipeapp.service;

import com.example.recipeapp.entity.Ingredient;
import com.example.recipeapp.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(UUID id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(UUID id, Ingredient updatedIngredient) {
        Ingredient ingredient = findById(id);

        ingredient.setName(updatedIngredient.getName());
        ingredient.setDescription(updatedIngredient.getDescription());

        return ingredientRepository.save(ingredient);
    }

    public void delete(UUID id) {
        Ingredient ingredient = findById(id);
        ingredientRepository.delete(ingredient);
    }
}