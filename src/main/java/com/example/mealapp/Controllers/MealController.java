package com.example.mealapp.Controllers;

import com.example.mealapp.Entities.Meal;
import com.example.mealapp.Services.MealService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/meal")
public class MealController {

    private MealService mealService;

    @GetMapping
    public ResponseEntity<List<Meal>> getMeals() {
        return new ResponseEntity<>(mealService.getMeals(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<List<Meal>> addMeals(@RequestBody List<Meal> meals) {
        return new ResponseEntity<>(mealService.addMeals(meals), HttpStatus.CREATED);
    }

    @PostMapping("/{mealId}/add/{ingredientId}")
    public ResponseEntity<Meal> addFoodIngredient(@PathVariable("mealId") Long mealId, @PathVariable("ingredientId") Long ingredient, @RequestParam(name = "amount") int amount ){
        return new ResponseEntity<>(mealService.addIngredientToMeal(mealId, ingredient, amount), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{mealId}/delete/{ingredientId}")
    public ResponseEntity<Meal> deleteIngredientFromMeal(@PathVariable("mealId") Long mealId, @PathVariable("ingredientId") Long ingredient, @RequestParam(name = "amount") int amount ) {
        return new ResponseEntity<>(mealService.deleteIngredientFromMeal(mealId, ingredient, amount), HttpStatus.OK);
    }
    @DeleteMapping("/{mealId}")
    public ResponseEntity<?> deleteMeal(@PathVariable("mealId") Long mealId) {
        mealService.deleteMeal(mealId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
