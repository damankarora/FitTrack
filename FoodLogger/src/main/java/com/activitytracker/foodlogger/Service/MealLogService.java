package com.activitytracker.foodlogger.Service;

import com.activitytracker.foodlogger.Model.Meal;
import com.activitytracker.foodlogger.Model.MealLog;
import com.activitytracker.foodlogger.Repository.MealLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealLogService {

    private final MealLogRepository mealLogRepository;
    private final MealService mealService;

    public MealLogService(MealLogRepository mealLogRepository, MealService mealService) {
        this.mealLogRepository = mealLogRepository;
        this.mealService = mealService;
    }

    public void addMealLog(MealLog mealLogToAdd){
        mealLogRepository.save(mealLogToAdd);
    }

    public List<MealLog> getAllMealLogsByMealId(Integer mealId){
        return mealLogRepository.findAllByMealMealId(mealId);
    }

    public void addMultipleMealLogs(List<String> foodItems, Meal parentMeal){
        for (String foodItem : foodItems){
            MealLog mealLogToAdd = new MealLog(foodItem, parentMeal);

            mealLogRepository.save(mealLogToAdd);
        }
    }

    public void deleteMealLog(Integer mealLogId){
        mealLogRepository.deleteById(mealLogId);
    }

    public List<MealLog> findAllLogsOfUser(String userId){
        List<Meal> mealsOfUser = mealService.getAllMealsOfUser(userId);

        return mealLogRepository.findMealLogsByMealInOrderById(mealsOfUser);
    }
}
