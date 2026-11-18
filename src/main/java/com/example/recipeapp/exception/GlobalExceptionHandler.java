package com.example.recipeapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
    public ModelAndView handleRecipeNotFound(RecipeNotFoundException exception) {
        return createErrorPage(exception.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ModelAndView handleCategoryNotFound(CategoryNotFoundException exception) {
        return createErrorPage(exception.getMessage());
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    public ModelAndView handleIngredientNotFound(IngredientNotFoundException exception) {
        return createErrorPage(exception.getMessage());
    }

    private ModelAndView createErrorPage(String message) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", message);
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}