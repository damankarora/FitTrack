package com.activitytracker.foodlogger.Service;

import com.activitytracker.foodlogger.Asynchronous.UpdateNutrients;
import com.activitytracker.foodlogger.Model.Meal;
import com.activitytracker.foodlogger.Model.MealLog;
import com.activitytracker.foodlogger.Repository.MealLogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealLogService {

    private final MealLogRepository mealLogRepository;
    private final MealService mealService;


    public MealLogService(MealLogRepository mealLogRepository, MealService mealService) {
        this.mealLogRepository = mealLogRepository;
        this.mealService = mealService;

    }

    public void updateMealLogs(List<MealLog> mealLogToAdd){
        mealLogRepository.saveAll(mealLogToAdd);
    }

    public List<MealLog> getAllMealLogsByMealId(Integer mealId){
        return mealLogRepository.findAllByMealMealId(mealId);
    }

    public List<MealLog> addMultipleMealLogs(List<String> foodItems, Meal parentMeal){

        List<MealLog> addedLog = new ArrayList<>();

        for (String foodItem : foodItems){
            MealLog mealLogToAdd = new MealLog(foodItem, parentMeal);
            mealLogRepository.save(mealLogToAdd);

            addedLog.add(mealLogToAdd);
        }


        return addedLog;
    }

    public void deleteMealLog(Integer mealLogId){
        mealLogRepository.deleteById(mealLogId);
    }

    public List<MealLog> findAllLogsOfUser(String userId){
        List<Meal> mealsOfUser = mealService.getAllMealsOfUser(userId);

        return mealLogRepository.findMealLogsByMealInOrderById(mealsOfUser);
    }
}
