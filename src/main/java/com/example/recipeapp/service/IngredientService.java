package com.example.recipeapp.service;

import com.example.recipeapp.entity.Ingredient;
import com.example.recipeapp.exception.IngredientAlreadyExistsException;
import com.example.recipeapp.exception.IngredientNotFoundException;
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
                .orElseThrow(() ->
                        new IngredientNotFoundException("Съставката не е намерена."));
    }

    public Ingredient create(Ingredient ingredient) {
        if (ingredientRepository.existsByNameIgnoreCase(ingredient.getName())) {
            throw new IngredientAlreadyExistsException(
                    "Съставка с това име вече съществува.");
        }

        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(UUID id, Ingredient updatedIngredient) {
        Ingredient ingredient = findById(id);

        boolean nameAlreadyExists = ingredientRepository
                .existsByNameIgnoreCase(updatedIngredient.getName());

        if (nameAlreadyExists
                && !ingredient.getName().equalsIgnoreCase(updatedIngredient.getName())) {
            throw new IngredientAlreadyExistsException(
                    "Съставка с това име вече съществува.");
        }

        ingredient.setName(updatedIngredient.getName());
        ingredient.setDescription(updatedIngredient.getDescription());

        return ingredientRepository.save(ingredient);
    }

    public void delete(UUID id) {
        Ingredient ingredient = findById(id);
        ingredientRepository.delete(ingredient);
    }
}