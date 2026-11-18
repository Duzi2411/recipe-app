package com.example.recipeapp.repository;

import com.example.recipeapp.entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MealPlanRepository extends JpaRepository<MealPlan, UUID> {
}