package com.example.mealapp.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    public DayPlan(String name, Day weekDay, Map<LocalTime, Meal> meals) {
        this.name = name;
        this.weekDay = weekDay;
        this.meals = meals;
    }

    @ElementCollection
    @CollectionTable(name = "dayplan_meal", joinColumns = @JoinColumn(name = "dayplan_id"))
    @Column(name = "meal")
    @MapKeyJoinColumn(name = "time")
    @JsonDeserialize(keyUsing = InstantKeyDeserializer.class)
    @JsonFormat(pattern = "HH:mm:ss")
    private Map<LocalTime, Meal> meals = new HashMap<>();

    public Map<LocalTime, Meal> getMeals() {
        return new HashMap<>(meals) ;

    }

    public void addMeal(LocalTime time, Meal meal) {

        meals.put(time, meal);

        updateData();

    }
    public void deleteMealByMeal(Meal meal) {

        for(LocalTime time : meals.keySet()){
            if(meals.get(time) == meal) meals.remove(time);
        }
        updateData();
    }
    public void updateData() {
        this.kcal = 0;
        this.carbs = 0;
        this.fat = 0;
        this.protein = 0;
        for(Meal meal : meals.values()) {

            this.kcal += meal.getKcal();
            this.carbs += meal.getCarbs();
            this.fat += meal.getFat();
            this.protein += meal.getProtein();

        }

    }
}
