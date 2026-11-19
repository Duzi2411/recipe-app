package com.example.recipeapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @UuidGenerator
    private UUID id;

    @NotBlank(message = "Името на рецептата е задължително.")
    @Size(max = 100, message = "Името не може да бъде по-дълго от 100 символа.")
    @Column(nullable = false)
    private String title;

    @Size(max = 1000, message = "Описанието не може да бъде по-дълго от 1000 символа.")
    @Column(length = 1000)
    private String description;

    @NotBlank(message = "Начинът на приготвяне е задължителен.")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String instructions;

    @NotNull(message = "Времето за приготвяне е задължително.")
    @Positive(message = "Времето трябва да бъде по-голямо от 0.")
    @Column(nullable = false)
    private Integer preparationTime;

    @NotNull(message = "Броят порции е задължителен.")
    @Positive(message = "Броят порции трябва да бъде по-голям от 0.")
    @Column(nullable = false)
    private Integer servings;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Recipe() {
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}