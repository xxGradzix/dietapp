package com.example.mealapp.Services;


import com.example.mealapp.Entities.DayPlan;
import com.example.mealapp.Entities.Meal;
import com.example.mealapp.Repositories.DayPlanRepository;
import com.example.mealapp.Repositories.MealRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DayPlanService {

    private DayPlanRepository dayPlanRepository;
    private MealRepository mealRepository;
    public List<DayPlan> getDayPlans() {

        return dayPlanRepository.findAll();
    }

    public DayPlan addDayPlan(DayPlan dayPlan) {
        if(dayPlanRepository.findAll().contains(dayPlan)) throw new RuntimeException("This dayplan already exists");
        return dayPlanRepository.save(dayPlan);
    }

    @Transactional
    public DayPlan addMealToPlan(Long planId, Long mealId, LocalTime time) {

        DayPlan dayPlan = dayPlanRepository.findById(planId).orElseThrow(() ->
                new RuntimeException("There is no plan with that id"));

        Meal meal = mealRepository.findById(mealId).orElseThrow(() ->
                new RuntimeException("There is no meal with that id"));

        dayPlan.addMeal(time, meal);
        return dayPlan;

    }

    @Transactional
    public DayPlan deleteMealFromPlan(Long planId, Long mealId) {

        DayPlan dayPlan = dayPlanRepository.findById(planId).orElseThrow(() ->
                new RuntimeException("There is no plan with that id"));

        Meal meal = mealRepository.findById(mealId).orElseThrow(() ->
                new RuntimeException("There is no meal with that id"));

        dayPlan.deleteMealByMeal(meal);
        return dayPlan;
    }

    public void deleteDayPlan(Long dayPlanId) {
        dayPlanRepository.findById(dayPlanId).orElseThrow(() -> new RuntimeException("There is no day plan with that id"));
        dayPlanRepository.deleteById(dayPlanId);
    }
}
