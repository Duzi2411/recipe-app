package com.example.recipeapp.service;

import com.example.recipeapp.entity.MealPlan;
import com.example.recipeapp.repository.MealPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MealPlanService {

    private final MealPlanRepository mealPlanRepository;

    public MealPlanService(MealPlanRepository mealPlanRepository) {
        this.mealPlanRepository = mealPlanRepository;
    }

    public List<MealPlan> findAll() {
        return mealPlanRepository.findAll();
    }

    public MealPlan findById(UUID id) {
        return mealPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Планираното хранене не е намерено."
                ));
    }

    public MealPlan create(MealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    public MealPlan update(UUID id, MealPlan updatedMealPlan) {
        MealPlan mealPlan = findById(id);

        mealPlan.setTitle(updatedMealPlan.getTitle());
        mealPlan.setPlannedDate(updatedMealPlan.getPlannedDate());
        mealPlan.setNotes(updatedMealPlan.getNotes());
        mealPlan.setRecipe(updatedMealPlan.getRecipe());

        return mealPlanRepository.save(mealPlan);
    }

    public void delete(UUID id) {
        MealPlan mealPlan = findById(id);
        mealPlanRepository.delete(mealPlan);
    }
}