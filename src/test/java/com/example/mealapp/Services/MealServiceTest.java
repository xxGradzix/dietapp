package com.example.mealapp.Services;

import com.example.mealapp.Entities.Ingredient;
import com.example.mealapp.Entities.Meal;
import com.example.mealapp.Repositories.IngredientRepository;
import com.example.mealapp.Repositories.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MealServiceTest {

    @Mock
    private MealRepository mealRepository;
    @Mock
    private IngredientRepository ingredientRepository;

    private MealService mealService;

    @BeforeEach
    void setUp() {
        mealService = new MealService(mealRepository, ingredientRepository);
    }

    @Test
    void testAddIngredientToMeal() {

        // given
        Meal meal = new Meal("testMeal");
        Ingredient ingredient = new Ingredient("testIngredient");

        // when
        when(mealRepository.findById(meal.getId())).thenReturn(Optional.of(meal));
        when(ingredientRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));

        // then
        Meal result = mealService.addIngredientToMeal(meal.getId(), ingredient.getId(), 3);

        assertEquals(meal, result);

    }

    @Test
    void testAddMeal() {

        // given

        Meal meal = new Meal("test1");

        // when

        mealService.addMeal(meal);

        // then

        ArgumentCaptor<Meal> argumentCaptor = ArgumentCaptor.forClass(Meal.class);

        verify(mealRepository).save(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue()).isEqualTo(meal);

    }

    @Test
    void getMeals() {
        // given

        Meal meal1 = new Meal("meal1");
        Meal meal2 = new Meal("meal2");

        // when

        mealService.getMeals();

        // then

        verify(mealRepository).findAll();
    }

    @Test
    void deleteIngredientFromMeal() {
        // given
        Meal meal = new Meal("testMeal");
        Ingredient ingredient = new Ingredient("testIngredient");
        meal.addIngredient(ingredient, 200);

        // when
        when(mealRepository.findById(meal.getId())).thenReturn(Optional.of(meal));
        when(ingredientRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));

        // then
        Meal result = mealService.deleteIngredientFromMeal(meal.getId(), ingredient.getId(), 100);

        assertEquals(meal, result);

    }

    @Test
    void deleteMeal() {
        // given
        Meal meal = new Meal("meal");

        // when
        when(mealRepository.findById(meal.getId())).thenReturn(Optional.of(meal));
        mealService.deleteMeal(meal.getId());

        // then
        ArgumentCaptor<Meal> argumentCaptor = ArgumentCaptor.forClass(Meal.class);

        verify(mealRepository).delete(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue()).isEqualTo(meal);

    }
}