package com.activitytracker.foodlogger.Asynchronous;

import com.activitytracker.foodlogger.ExternalService.FetchNutrients;
import com.activitytracker.foodlogger.ExternalService.FetchNutrientsResponse;
import com.activitytracker.foodlogger.ExternalService.FoodNutrition;
import com.activitytracker.foodlogger.Model.MealLog;
import com.activitytracker.foodlogger.Service.MealLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UpdateNutrients extends Thread{

    @Autowired
    private FetchNutrients fetchNutrients;

    @Autowired
    private MealLogService mealLogService;




    private String food = "";

    public List<MealLog> mealLogs;

    public UpdateNutrients() {

    }

    public void setFood(List<MealLog> mealLogsToUpdate){
        this.mealLogs = mealLogsToUpdate;

        List<String> foodItems = mealLogsToUpdate.stream().map((MealLog::getFoodItem)).collect(Collectors.toList());
        food = String.join(", ", foodItems);
    }

    @Override
    public void run() {



         FetchNutrientsResponse response = fetchNutrients.fetchData(food);

         if (response == null){
             throw new RuntimeException("Food details not found");
         }

         List<FoodNutrition> nutrientsList = response.getFoods();

         for(int i = 0; i < nutrientsList.size(); i ++){

             MealLog logToUpdate = this.mealLogs.get(i);
             logToUpdate.parseData(nutrientsList.get(i));
             mealLogService.updateMealLog(logToUpdate);

         }




    }
}
