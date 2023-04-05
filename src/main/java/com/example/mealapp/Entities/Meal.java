package com.example.mealapp.Entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.key.InstantKeyDeserializer;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Meal extends Food {

    @Id
    @SequenceGenerator(
            name = "meal_sequence",
            sequenceName = "meal_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "meal_sequence"
    )
    private Long id;

    private String name;

    private int kcal = 0;
    private double carbs = 0;
    private double fat = 0;
    private double protein = 0;

    public Meal(String name) {
        this.name = name;
    }




    @ElementCollection
    @CollectionTable(name = "meal_ingredients", joinColumns = @JoinColumn(name = "meal_id"))
    @Column(name = "amount")
    @MapKeyJoinColumn(name = "ingredient_id")
    @JsonDeserialize(keyUsing = InstantKeyDeserializer.class)
    private Map<Ingredient, Integer> ingredients = new HashMap<>();
    
    public Map<Ingredient, Integer> getIngredients() {
        return new HashMap<>(ingredients);
    }
    public void addIngredient(Ingredient ingredient, int amount) {
        if(amount < 0) return;

        ingredients.put(ingredient, ingredients.getOrDefault(ingredient, 0) + amount);

        updateData();

    }

    public void deleteIngredient(Ingredient ingredient, int amount) {
        ingredients.put(ingredient, ingredients.getOrDefault(ingredient, 0) - amount);
        if(ingredients.get(ingredient) <= 0)ingredients.remove(ingredient);
        updateData();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return name.equals(meal.name)
                && kcal == meal.kcal && carbs == meal.carbs && fat == meal.fat && protein == meal.protein
                && ingredients.equals(meal.ingredients)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name
                , kcal, carbs, fat, protein
                , ingredients
        );
    }

    @Override
    public void updateData() {
        this.kcal = 0;
        this.carbs = 0;
        this.fat = 0;
        this.protein = 0;
        for(Ingredient ingredient : ingredients.keySet()) {

            this.kcal += ingredient.getKcal() * ingredients.get(ingredient) / 100;
            this.carbs += ingredient.getCarbs() * ingredients.get(ingredient) / 100;
            this.fat += ingredient.getFat() * ingredients.get(ingredient) / 100;
            this.protein += ingredient.getProtein() * ingredients.get(ingredient) / 100;

        }

    }


}
