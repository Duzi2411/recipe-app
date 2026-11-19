package com.example.recipeapp.controller;

import com.example.recipeapp.entity.MealPlan;
import com.example.recipeapp.service.MealPlanService;
import com.example.recipeapp.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/meal-plans")
public class MealPlanController {

    private final MealPlanService mealPlanService;
    private final RecipeService recipeService;

    public MealPlanController(
            MealPlanService mealPlanService,
            RecipeService recipeService) {
        this.mealPlanService = mealPlanService;
        this.recipeService = recipeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("mealPlans", mealPlanService.findAll());
        return "meal-plans";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("mealPlan", new MealPlan());
        model.addAttribute("recipes", recipeService.findAll());
        return "add-meal-plan";
    }

    @PostMapping("/add")
    public String create(
            @Valid @ModelAttribute("mealPlan") MealPlan mealPlan,
            BindingResult bindingResult,
            @RequestParam(required = false) UUID recipeId,
            Model model) {

        if (recipeId == null) {
            bindingResult.rejectValue("recipe", "NotNull",
                    "Избери рецепта.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("recipes", recipeService.findAll());
            return "add-meal-plan";
        }

        mealPlan.setRecipe(recipeService.findById(recipeId));
        mealPlanService.create(mealPlan);

        return "redirect:/meal-plans";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        model.addAttribute("mealPlan", mealPlanService.findById(id));
        model.addAttribute("recipes", recipeService.findAll());
        return "edit-meal-plan";
    }

    @PostMapping("/{id}/edit")
    public String update(
            @PathVariable UUID id,
            @Valid @ModelAttribute("mealPlan") MealPlan updatedMealPlan,
            BindingResult bindingResult,
            @RequestParam(required = false) UUID recipeId,
            Model model) {

        if (recipeId == null) {
            bindingResult.rejectValue("recipe", "NotNull",
                    "Избери рецепта.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("recipes", recipeService.findAll());
            return "edit-meal-plan";
        }

        updatedMealPlan.setRecipe(recipeService.findById(recipeId));
        mealPlanService.update(id, updatedMealPlan);

        return "redirect:/meal-plans";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id) {
        mealPlanService.delete(id);
        return "redirect:/meal-plans";
    }
}