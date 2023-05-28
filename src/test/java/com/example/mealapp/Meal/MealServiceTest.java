package com.example.mealapp.Meal;

import com.example.mealapp.DayPlan.DayPlan;
import com.example.mealapp.Ingredient.Ingredient;
import com.example.mealapp.Meal.Meal;
import com.example.mealapp.Meal.MealService;
import com.example.mealapp.DayPlan.DayPlanRepository;
import com.example.mealapp.Ingredient.IngredientRepository;
import com.example.mealapp.Meal.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MealServiceTest {

    @Mock
    private MealRepository mealRepository;
    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private DayPlanRepository dayPlanRepository;

    private MealService mealService;

    @BeforeEach
    void setUp() {
        mealService = new MealService(mealRepository, ingredientRepository, dayPlanRepository);
    }

    @Test
    void testAddIngredientToMeal() {

        // given
        Meal meal = new Meal("testMeal");
        Ingredient ingredient = new Ingredient("testIngredient");

        List<DayPlan> dayPlans = Arrays.asList(
                new DayPlan("plan1"),
                new DayPlan("plan2")
        );



        // when
        when(dayPlanRepository.findAll()).thenReturn(dayPlans);
        when(mealRepository.findById(meal.getId())).thenReturn(Optional.of(meal));
        when(ingredientRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));

        // then
        Meal result = mealService.addIngredientToMeal(meal.getId(), ingredient.getId(), 3);

        assertEquals(meal, result);
        verify(dayPlanRepository).findAll();

//        for (DayPlan plan : dayPlans) {
//            assertThat();
//        }

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

        List<DayPlan> dayPlans = Arrays.asList(
                new DayPlan("plan1"),
                new DayPlan("plan2")
        );

        // when
        when(dayPlanRepository.findAll()).thenReturn(dayPlans);
        when(mealRepository.findById(meal.getId())).thenReturn(Optional.of(meal));
        when(ingredientRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));

        // then
        Meal result = mealService.deleteIngredientFromMeal(meal.getId(), ingredient.getId(), 100);

        assertEquals(meal, result);

        verify(dayPlanRepository).findAll();


    }

    @Test
    void deleteMeal() {
        // given
        Meal meal = new Meal("meal");

        List<DayPlan> dayPlans = Arrays.asList(
                new DayPlan("plan1"),
                new DayPlan("plan2")
        );

        // when
        when(dayPlanRepository.findAll()).thenReturn(dayPlans);
        when(mealRepository.findById(meal.getId())).thenReturn(Optional.of(meal));
        mealService.deleteMeal(meal.getId());

        // then
        ArgumentCaptor<Meal> argumentCaptor = ArgumentCaptor.forClass(Meal.class);

        verify(mealRepository).delete(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue()).isEqualTo(meal);
        verify(dayPlanRepository).findAll();


    }
}