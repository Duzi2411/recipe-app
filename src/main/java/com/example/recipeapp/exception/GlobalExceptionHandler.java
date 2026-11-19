package com.example.recipeapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
    public ModelAndView handleRecipeNotFound(RecipeNotFoundException exception) {
        return createErrorPage(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ModelAndView handleCategoryNotFound(CategoryNotFoundException exception) {
        return createErrorPage(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    public ModelAndView handleIngredientNotFound(IngredientNotFoundException exception) {
        return createErrorPage(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MealPlanNotFoundException.class)
    public ModelAndView handleMealPlanNotFound(MealPlanNotFoundException exception) {
        return createErrorPage(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ModelAndView handleCategoryAlreadyExists(CategoryAlreadyExistsException exception) {
        return createErrorPage(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IngredientAlreadyExistsException.class)
    public ModelAndView handleIngredientAlreadyExists(IngredientAlreadyExistsException exception) {
        return createErrorPage(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIllegalArgument(IllegalArgumentException exception) {
        return createErrorPage(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ModelAndView createErrorPage(String message, HttpStatus status) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", message);
        modelAndView.setStatus(status);
        return modelAndView;
    }
}