package com.example.mealapp.DayPlan;

import com.example.mealapp.Meal.Meal;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.key.InstantKeyDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class DayPlan {

    @Id
    @SequenceGenerator(
            name = "dayplan_sequence",
            sequenceName = "dayplan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dayplan_sequence"
    )
    private Long id;

    private String name;
    private Day weekDay;
    private int kcal = 0;
    private double carbs = 0;
    private double fat = 0;
    private double protein = 0;

    @ElementCollection
    @CollectionTable(name = "dayplan_meals", joinColumns = @JoinColumn(name = "dayplan_id"))
    @Column(name = "part")
    @MapKeyJoinColumn(name = "meal_id")
    @JsonDeserialize(keyUsing = InstantKeyDeserializer.class)
    private Map<Meal, Double> meals = new HashMap<>();


    public DayPlan(String name, Day weekDay, Map<Meal, Double> meals) {
        this.name = name;
        this.weekDay = weekDay;
        this.meals = meals;
    }



    public DayPlan(String name) {
        this.name = name;

    }

    public Map<Meal, Double> getMeals() {
        return new HashMap<>(meals) ;

    }

    public void addMeal(Double part, Meal meal) {


        if(part < 0) return;

        meals.put(meal, meals.getOrDefault(meal, 0.0) + part);

        updateData();

    }
    public void deleteMealByMeal(Meal meal) {

        meals.remove(meal);

        updateData();
    }
    public void updateData() {
        this.kcal = 0;
        this.carbs = 0;
        this.fat = 0;
        this.protein = 0;
        for(Meal meal : meals.keySet()) {

            this.kcal += meal.getKcal() * meals.get(meal);
            this.carbs += meal.getCarbs() * meals.get(meal);
            this.fat += meal.getFat() * meals.get(meal);
            this.protein += meal.getProtein() * meals.get(meal);

        }

    }
}
