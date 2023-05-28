package com.example.mealapp.Configuration;

import com.example.mealapp.Ingredient.Ingredient;
import com.example.mealapp.Ingredient.IngredientService;
import com.example.mealapp.Meal.MealService;
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
            Ingredient twarogChudy = new Ingredient(null, "twarog chudy", 86, 3.5, 0, 18);
            Ingredient skyr = new Ingredient(null, "skyr", 64, 4.1, 0, 12);
            Ingredient wiorki = new Ingredient(null, "wiorki kokosowe", 657, 5.9, 67, 5.9);
            Ingredient olejkokosowy = new Ingredient(null, "olej kokosowy", 892, 0, 100, 0);
            Ingredient cukier = new Ingredient(null, "cukier", 393, 100, 0, 0);
            Ingredient platkiOwsiane = new Ingredient(null, "platki owsiane", 363, 60, 5.7, 13);
            Ingredient kakao = new Ingredient(null, "kakao", 309, 13, 11, 24);
            Ingredient serekWiejskiLekki = new Ingredient(null, "serek wiejski lekki", 81, 2.4, 3, 11);
            Ingredient masloOrzechowe = new Ingredient(null, "maslo orzechowe", 637, 9.6, 52, 29);
            Ingredient kurczak = new Ingredient(null, "piers z kurczaka", 98, 0, 3.7, 21.8);
            Ingredient maka = new Ingredient(null, "maka", 35, 74, 16, 10);
            Ingredient mozzarellaLight = new Ingredient(null, "mozzarella light", 165, 2, 9, 19);
            Ingredient bulkaTarta = new Ingredient(null, "bulka tarta", 395, 72, 5, 13);


            ingredientService.addIngredient(mleko);
            ingredientService.addIngredient(jajko);
            ingredientService.addIngredient(odzywkaBialkowa);
            ingredientService.addIngredient(banan);
            ingredientService.addIngredient(twarogChudy);
            ingredientService.addIngredient(skyr);
            ingredientService.addIngredient(wiorki);
            ingredientService.addIngredient(olejkokosowy);
            ingredientService.addIngredient(cukier);
            ingredientService.addIngredient(platkiOwsiane);
            ingredientService.addIngredient(kakao);
            ingredientService.addIngredient(serekWiejskiLekki);
            ingredientService.addIngredient(masloOrzechowe);
            ingredientService.addIngredient(kurczak);
            ingredientService.addIngredient(maka);
            ingredientService.addIngredient(mozzarellaLight);
            ingredientService.addIngredient(bulkaTarta);

        };

    }

}
