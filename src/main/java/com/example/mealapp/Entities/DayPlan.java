package com.example.mealapp.Entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.key.InstantKeyDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
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
    @Column(name = "time")
    @MapKeyJoinColumn(name = "meal_id")
    @JsonDeserialize(keyUsing = InstantKeyDeserializer.class)
    private Map<Meal, LocalTime> meals = new HashMap<>();


    public DayPlan(String name, Day weekDay, Map<Meal, LocalTime> meals) {
        this.name = name;
        this.weekDay = weekDay;
        this.meals = meals;
    }



    public DayPlan(String name) {
        this.name = name;

    }

    public Map<Meal, LocalTime> getMeals() {
        return new HashMap<>(meals) ;

    }

    public void addMeal(LocalTime time, Meal meal) {

        meals.put(meal, time);

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

            this.kcal += meal.getKcal();
            this.carbs += meal.getCarbs();
            this.fat += meal.getFat();
            this.protein += meal.getProtein();

        }

    }
}
