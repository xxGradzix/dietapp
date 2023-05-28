package com.example.mealapp.Meal;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/meal")
public class MealController {

    private MealService mealService;
    @GetMapping("/all")
    public ResponseEntity<List<Meal>> getMeals() {
        return new ResponseEntity<>(mealService.getMeals(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMeal(@PathVariable("id") Long id) {
        return new ResponseEntity<>(mealService.getMeal(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Meal> addMeals(@RequestBody Meal meal) {
        return new ResponseEntity<>(mealService.addMeal(meal), HttpStatus.CREATED);
    }

    @PatchMapping("/{mealId}/add/{ingredientId}")
    public ResponseEntity<Meal> addFoodIngredient(@PathVariable("mealId") Long mealId, @PathVariable("ingredientId") Long ingredient, @RequestBody Map<String, Integer> amount){
        return new ResponseEntity<>(mealService.addIngredientToMeal(mealId, ingredient, amount.get("amount")), HttpStatus.ACCEPTED);
    }
    @PatchMapping("/{mealId}/delete/{ingredientId}")
    public ResponseEntity<Meal> deleteIngredientFromMeal(@PathVariable("mealId") Long mealId, @PathVariable("ingredientId") Long ingredient, @RequestBody Map<String, Integer> amount) {
        return new ResponseEntity<>(mealService.deleteIngredientFromMeal(mealId, ingredient, amount.get("amount")), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Meal> updateMeal (@RequestBody Meal meal) {
        Meal updateMeal = mealService.updateMeal(meal);
        return new ResponseEntity<>(updateMeal, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{mealId}")
    public ResponseEntity<?> deleteMeal(@PathVariable("mealId") Long mealId) {
        mealService.deleteMeal(mealId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
