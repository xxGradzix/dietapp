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

    @GetMapping
    public ResponseEntity<List<Ingredient>> getIngredients() {
        return new ResponseEntity<>(ingredientService.getAllIngredients(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<List<Ingredient>> addIngredient(@RequestBody List<Ingredient> ingredients) {
        return new ResponseEntity<>(ingredientService.addIngredients(ingredients), HttpStatus.CREATED);
    }
    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<?> deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
        ingredientService.deleteIngredient(ingredientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
