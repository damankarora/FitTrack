package com.activitytracker.foodlogger.Controller;

import com.activitytracker.foodlogger.Asynchronous.UpdateNutrients;
import com.activitytracker.foodlogger.Configuration.DataSourceConfig;
import com.activitytracker.foodlogger.ExternalService.FetchNutrients;
import com.activitytracker.foodlogger.ExternalService.FetchNutrientsResponse;
import com.activitytracker.foodlogger.Model.Meal;
import com.activitytracker.foodlogger.Model.MealLog;
import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Service.MealLogService;
import com.activitytracker.foodlogger.Service.MealService;
import com.activitytracker.foodlogger.Service.UserService;
import com.activitytracker.foodlogger.View.MealView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class MealController {

    private final MealService mealService;
    private final MealLogService mealLogService;
    private final UserService userService;
    private final FetchNutrients fetchNutrientsService;
    private final UpdateNutrients updateNutrients;
    private final DataSourceConfig dataSourceConfig;

    public MealController(MealService mealService, MealLogService mealLogService, UserService userService, FetchNutrients fetchNutrientsService, UpdateNutrients updateNutrients, DataSourceConfig dataSourceConfig) {
        this.mealService = mealService;
        this.mealLogService = mealLogService;
        this.userService = userService;
        this.fetchNutrientsService = fetchNutrientsService;
        this.updateNutrients = updateNutrients;
        this.dataSourceConfig = dataSourceConfig;
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/{userId}/meals")
    @JsonView(MealView.Overview.class)
    public List<Meal> getAllMeals(@PathVariable String userId){

        try {
            dataSourceConfig.readValues();
        }
        catch (IOException e){
            System.out.println("could not read file");
            e.printStackTrace();
        }

        try{
            return mealService.getAllMealsOfUser(userId);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/{userId}/meals")
    @JsonView(MealView.Extended.class)
    public Meal addNewMeal(@PathVariable String userId, @RequestBody MealPayload mealPayload){
        try{
            Meal mealToAdd = new Meal(mealPayload.getMealTitle());
            mealToAdd.setMealDate(mealPayload.getDate());
            mealService.addNewMeal(mealToAdd, userId);
            List<MealLog> mealLogList = mealLogService.addMultipleMealLogs(mealPayload.getFoodItems(), mealToAdd);


            updateNutrients.setFood(mealLogList);
            updateNutrients.start();


            return mealToAdd;


        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}/meals/{mealId}")
    @JsonView(MealView.Extended.class)
    public Meal getMealById(@PathVariable String userId, @PathVariable String mealId){
        try{
            return mealService.getMealById(mealId);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}/meals/logs")
    public List<MealLog> getAllMealLogsOfUser(@PathVariable String userId){
        try {
            return mealLogService.findAllLogsOfUser(userId);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{userId}/meals/{mealId}")
    public void deleteMeal(@PathVariable String userId, @PathVariable String mealId){
        try{
            validateUserId(userId);
            mealService.deleteMeal(mealId);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

    @RequestMapping("/test")
    public FetchNutrientsResponse testEndpoint(@RequestParam String food){
        return fetchNutrientsService.fetchData(food);
    }

    private void validateUserId(String userId){
        User foundUser = userService.getUserDetails(Integer.valueOf(userId));
        if (foundUser == null){
            throw new NoSuchElementException("User not found");
        }
    }

}

class MealPayload{
    private final List<String> foodItems;
    private final String mealTitle;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date date;

    public MealPayload(List<String> foodItems, String mealTitle, Date date) {
        this.foodItems = foodItems;
        this.mealTitle = mealTitle;
        this.date = date;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public List<String> getFoodItems() {
        return foodItems;
    }

    public Date getDate() {
        return date;
    }
}


