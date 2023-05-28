package com.example.mealapp.DayPlan;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/dayplan")
@AllArgsConstructor
public class DayPlanController {

    private DayPlanService dayPlanService;

    @GetMapping("/all")
    public ResponseEntity<List<DayPlan>> getDayPlans() {
        return new ResponseEntity<>(dayPlanService.getDayPlans(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DayPlan> getDayPlan(@PathVariable("id") Long id) {
        return new ResponseEntity<>(dayPlanService.getDayPlan(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DayPlan> addDayPlan(@RequestBody DayPlan dayPlan) {
        return new ResponseEntity<>(dayPlanService.addDayPlan(dayPlan), HttpStatus.CREATED);
    }

    @PatchMapping("/{dayPlanId}/add/{mealId}")
    public ResponseEntity<DayPlan> addMealToDayPlan(@PathVariable("dayPlanId") Long dayPlanId,
                                                    @PathVariable("mealId") Long mealId,
                                                    @RequestBody Double part){
//        LocalTime time = LocalTime.of(dayPlanMealTime.getHours(), dayPlanMealTime.getMinutes());
        return new ResponseEntity<>(dayPlanService.addMealToPlan(dayPlanId, mealId, part), HttpStatus.OK);
    }
    @PatchMapping("/{dayPlanId}/delete/{mealId}")
    public ResponseEntity<DayPlan> deleteMealFromDayPlan(@PathVariable("dayPlanId") Long dayPlanId,
                                                    @PathVariable("mealId") Long mealId){

        return new ResponseEntity<>(dayPlanService.deleteMealFromPlan(dayPlanId, mealId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<DayPlan> updateDayPlan(@RequestBody DayPlan dayPlan){
        DayPlan updateDayPlan = dayPlanService.updateDayPlan(dayPlan);
        return new ResponseEntity<>(updateDayPlan, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{dayPlanId}")
    public ResponseEntity<?> deleteDayPlan(@PathVariable("dayPlanId") Long dayPlanId){
        dayPlanService.deleteDayPlan(dayPlanId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
