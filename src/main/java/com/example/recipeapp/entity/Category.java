package com.example.recipeapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @UuidGenerator
    private UUID id;

    @NotBlank(message = "Името на категорията е задължително.")
    @Size(max = 100, message = "Името не може да бъде по-дълго от 100 символа.")
    @Column(nullable = false, unique = true)
    private String name;

    @Size(max = 500, message = "Описанието не може да бъде по-дълго от 500 символа.")
    @Column(length = 500)
    private String description;

    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}