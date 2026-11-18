package com.example.recipeapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "meal_plans")
public class MealPlan {

    @Id
    @UuidGenerator
    private UUID id;

    @NotBlank(message = "Името е задължително.")
    @Size(max = 100, message = "Името не може да надвишава 100 символа.")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "Избери дата.")
    @Column(nullable = false)
    private LocalDate plannedDate;

    @Size(max = 500, message = "Бележките не могат да надвишават 500 символа.")
    private String notes;

    @NotNull(message = "Избери рецепта.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    public MealPlan() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(LocalDate plannedDate) {
        this.plannedDate = plannedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}