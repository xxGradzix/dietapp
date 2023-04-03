package com.example.mealapp.Services;

import com.example.mealapp.Entities.Ingredient;
import com.example.mealapp.Entities.Meal;
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

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> addIngredients(List<Ingredient> ingredients) {
        return ingredientRepository.saveAll(ingredients);
    }
    
    @Transactional
    public void deleteIngredient(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow(() -> new RuntimeException("there is no ingredient with that id"));
        List<Meal> meals = mealRepository.findAll();
        for (Meal meal : meals) {
            meal.deleteIngredient(ingredient, Integer.MAX_VALUE);
            mealRepository.save(meal);
        }
        ingredientRepository.delete(ingredient);
    }
}
