package com.example.mealapp.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Ingredient extends Food {

    @Id
    @SequenceGenerator(
            name = "ingredient_sequence",
            sequenceName = "ingredient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ingredient_sequence"
    )
    private Long id;

    private String name;

    private int kcal = 0;
    private double carbs = 0;
    private double fat = 0;
    private double protein = 0;

    public Ingredient(String ingredient) {
        this.name = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
