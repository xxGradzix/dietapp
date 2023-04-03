package com.example.mealapp.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
    private int carbs = 0;
    private int fat = 0;
    private int protein = 0;

}
