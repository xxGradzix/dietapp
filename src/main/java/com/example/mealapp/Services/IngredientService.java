package com.example.mealapp.Services;

import com.example.mealapp.Entities.DayPlan;
import com.example.mealapp.Entities.Ingredient;
import com.example.mealapp.Entities.Meal;
import com.example.mealapp.Repositories.DayPlanRepository;
import com.example.mealapp.Repositories.IngredientRepository;
import com.example.mealapp.Repositories.MealRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {

    private IngredientRepository ingredientRepository;
    private MealRepository mealRepository;
    private DayPlanRepository dayPlanRepository;


    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        if(ingredientRepository.findAll().contains(ingredient)) throw new RuntimeException("This ingredient already exists");
        return ingredientRepository.save(ingredient);
    }
    
    @Transactional
    public void deleteIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("there is no ingredient with that id"));
        List<Meal> meals = mealRepository.findAll();

        for (Meal meal : meals) {
            meal.deleteIngredient(ingredient, Integer.MAX_VALUE);
            mealRepository.save(meal);
        }
        for (DayPlan plan : dayPlanRepository.findAll()) {
            plan.updateData();
            dayPlanRepository.save(plan);
        }
        ingredientRepository.delete(ingredient);
    }
}
