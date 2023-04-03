package com.example.mealapp.Repositories;

import com.example.mealapp.Entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
//    @Query("SELECT m.*\n" +
//            "FROM meal m\n" +
//            "INNER JOIN meal_ingredients mi ON m.id = mi.meal_id\n" +
//            "INNER JOIN ingredient i ON mi.ingredient_id = i.id\n" +
//            "WHERE i.id = ?1")
//    List<Meal> findByIngredient(Ingredient ingredient);
}
