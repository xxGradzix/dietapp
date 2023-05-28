package com.example.mealapp.Meal;

import com.example.mealapp.DayPlan.DayPlan;
import com.example.mealapp.Ingredient.Ingredient;
import com.example.mealapp.DayPlan.DayPlanRepository;
import com.example.mealapp.Ingredient.IngredientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MealService {

    private MealRepository mealRepository;
    private IngredientRepository ingredientRepository;
    private DayPlanRepository dayPlanRepository;

    public Meal addMeal(Meal meal) {
        if(mealRepository.findAll().contains(meal)) throw new RuntimeException("This meal already exists");
        return mealRepository.save(meal);
    }

    public List<Meal> getMeals() {
        return mealRepository.findAll();
    }

    @Transactional
    public Meal addIngredientToMeal(Long mealId, Long ingredientId, int amount) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new RuntimeException("there is no meal with that id"));
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("there is no ingredient with that id"));

        meal.addIngredient(ingredient, amount);

        for (DayPlan plan : dayPlanRepository.findAll()) {
            plan.updateData();
        }
        return meal;
    }

    @Transactional
    public Meal deleteIngredientFromMeal(Long mealId, Long ingredientId, int amount) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new RuntimeException("there is no meal with that id"));
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("there is no ingredient with that id"));

        meal.deleteIngredient(ingredient, amount);
        for (DayPlan plan : dayPlanRepository.findAll()) {
            plan.updateData();
        }
        return meal;
    }


    public void deleteMeal(Long mealId) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new RuntimeException("there is no meal with that id"));
        List<DayPlan> dayPlans = dayPlanRepository.findAll();
        for(DayPlan dayPlan : dayPlans) {
            dayPlan.deleteMealByMeal(meal);
        }
        mealRepository.delete(meal);
    }

    public Meal updateMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public Meal getMeal(Long id) {
        return mealRepository.findById(id).orElseThrow(() -> new RuntimeException("there is no meal with that id"));
    }
}
