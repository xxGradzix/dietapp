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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private MealRepository mealRepository;

    private IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        ingredientService = new IngredientService(ingredientRepository, mealRepository);
    }

    @Test
    void getAllIngredients() {

        // given

        Ingredient ingredient = new Ingredient("ingredient");

        // when

        ingredientService.getAllIngredients();

        // then

        verify(ingredientRepository).findAll();

    }

    @Test
    void addIngredient() {

        // given

        Ingredient ingredient = new Ingredient("ingredient");

        // when

        ArgumentCaptor<Ingredient> argumentCaptor = ArgumentCaptor.forClass(Ingredient.class);

        ingredientService.addIngredient(ingredient);

        // then

        verify(ingredientRepository).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(ingredient);

    }

    @Test
    void deleteIngredient() {
        // given

        Ingredient ingredient = new Ingredient("ingredient");
        Meal meal = new Meal("meal");
        meal.addIngredient(ingredient, 100);

        // when

        when(ingredientRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));

        when(mealRepository.findAll()).thenReturn(List.of(meal));

        ArgumentCaptor<Ingredient> argumentCaptorIngredient = ArgumentCaptor.forClass(Ingredient.class);
        ArgumentCaptor<Meal> argumentCaptorMeal = ArgumentCaptor.forClass(Meal.class);

        ingredientService.deleteIngredient(ingredient.getId());

        // then

        verify(ingredientRepository).delete(argumentCaptorIngredient.capture());
        assertThat(argumentCaptorIngredient.getValue()).isEqualTo(ingredient);

        verify(mealRepository).save(argumentCaptorMeal.capture());
        assertThat(argumentCaptorMeal.getValue()).isEqualTo(meal);
    }
}