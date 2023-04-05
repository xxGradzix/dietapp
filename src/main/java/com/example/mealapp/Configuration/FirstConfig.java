package com.example.mealapp.Configuration;

import com.example.mealapp.Entities.Ingredient;
import com.example.mealapp.Entities.Meal;
import com.example.mealapp.Services.IngredientService;
import com.example.mealapp.Services.MealService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstConfig {
    @Bean
    CommandLineRunner commandLineRunner(IngredientService ingredientService, MealService mealService) {

        return args -> {
            Ingredient mleko = new Ingredient(null, "mleko", 32, 4.7, 3.2, 3.2);
            Ingredient jajko = new Ingredient(null, "jajko", 140, 0.6, 9.7, 12.5);
            Ingredient odzywkaBialkowa = new Ingredient(null, "odzywka bialkowa", 418, 15, 8, 72);
            Ingredient banan = new Ingredient(null, "banan", 94, 22, 0.2, 1.2);
            Ingredient twarogChudy = new Ingredient(null, "twaróg chudy", 86, 3.5, 0, 18);
            Ingredient skyr = new Ingredient(null, "skyr", 64, 4.1, 0, 12);
            Ingredient wiorki = new Ingredient(null, "wiórki kokosowe", 657, 5.9, 67, 5.9);
            Ingredient olejkokosowy = new Ingredient(null, "olej kokosowy", 892, 0, 100, 0);
            Ingredient cukier = new Ingredient(null, "cukier", 393, 100, 0, 0);


            ingredientService.addIngredient(mleko);
            ingredientService.addIngredient(jajko);
            ingredientService.addIngredient(odzywkaBialkowa);
            ingredientService.addIngredient(banan);
            ingredientService.addIngredient(twarogChudy);
            ingredientService.addIngredient(skyr);
            ingredientService.addIngredient(wiorki);
            ingredientService.addIngredient(olejkokosowy);
            ingredientService.addIngredient(cukier);

            Meal meal = new Meal("Twaróg kokosowy");
            meal.addIngredient(twarogChudy, 250);
            meal.addIngredient(skyr, 150);
            meal.addIngredient(olejkokosowy, 20);
            meal.addIngredient(cukier, 20);
            meal.addIngredient(wiorki, 40);

            mealService.addMeal(meal);
        };

    }

}
