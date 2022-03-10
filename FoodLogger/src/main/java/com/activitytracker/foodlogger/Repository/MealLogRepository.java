package com.activitytracker.foodlogger.Repository;

import com.activitytracker.foodlogger.Model.Meal;
import com.activitytracker.foodlogger.Model.MealLog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MealLogRepository extends CrudRepository<MealLog, Integer> {
    List<MealLog> findAllByMealMealId(Integer meal_mealId);

    List<MealLog> findMealLogsByMealInOrderById(List<Meal> meals);
}
