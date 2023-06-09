package com.example.mealapp.Controllers;

import com.example.mealapp.Entities.Ingredient;
import com.example.mealapp.Services.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/ingredient")
public class IngredientController {

    private IngredientService ingredientService;

    @GetMapping("/all")
    public ResponseEntity<List<Ingredient>> getIngredients() {
        return new ResponseEntity<>(ingredientService.getAllIngredients(), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        return new ResponseEntity<>(ingredientService.addIngredient(ingredient), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{ingredientId}")
    public ResponseEntity<?> deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
