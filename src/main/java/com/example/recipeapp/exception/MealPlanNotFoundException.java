package com.example.recipeapp.exception;

public class MealPlanNotFoundException extends RuntimeException {

    public MealPlanNotFoundException(String message) {
        super(message);
    }
}