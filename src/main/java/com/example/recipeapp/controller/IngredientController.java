package com.example.recipeapp.controller;

import com.example.recipeapp.entity.Ingredient;
import com.example.recipeapp.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String showAllIngredients(Model model) {
        model.addAttribute("ingredients", ingredientService.findAll());
        return "ingredients";
    }

    @GetMapping("/add")
    public String showAddIngredientForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "add-ingredient";
    }

    @PostMapping("/add")
    public String addIngredient(
            @Valid @ModelAttribute("ingredient") Ingredient ingredient,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "add-ingredient";
        }

        ingredientService.create(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/{id}/edit")
    public String showEditIngredientForm(@PathVariable UUID id, Model model) {
        model.addAttribute("ingredient", ingredientService.findById(id));
        return "edit-ingredient";
    }

    @PostMapping("/{id}/edit")
    public String updateIngredient(
            @PathVariable UUID id,
            @Valid @ModelAttribute("ingredient") Ingredient updatedIngredient,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit-ingredient";
        }

        ingredientService.update(id, updatedIngredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/{id}/delete")
    public String deleteIngredient(@PathVariable UUID id) {
        ingredientService.delete(id);
        return "redirect:/ingredients";
    }
}