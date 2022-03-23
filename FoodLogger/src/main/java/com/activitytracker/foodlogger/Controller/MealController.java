package com.activitytracker.foodlogger.Controller;

import com.activitytracker.foodlogger.Asynchronous.UpdateNutrients;
import com.activitytracker.foodlogger.ExternalService.FetchNutrients;
import com.activitytracker.foodlogger.Model.Meal;
import com.activitytracker.foodlogger.Model.MealLog;
import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Service.MealLogService;
import com.activitytracker.foodlogger.Service.MealService;
import com.activitytracker.foodlogger.Service.UserService;
import com.activitytracker.foodlogger.View.MealView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "400", description = "Invalid user id", content = @Content(examples = @ExampleObject("{\n" +
                "    \"timestamp\": \"2022-03-23T06:58:14.107+00:00\",\n" +
                "    \"status\": 400,\n" +
                "    \"error\": \"Bad Request\",\n" +
                "    \"path\": \"/user/100/meals\"\n" +
                "}")))
})
public class MealController {

    private final MealService mealService;
    private final MealLogService mealLogService;
    private final UserService userService;
    private final UpdateNutrients updateNutrients;

    public MealController(MealService mealService, MealLogService mealLogService, UserService userService, FetchNutrients fetchNutrientsService, UpdateNutrients updateNutrients) {
        this.mealService = mealService;
        this.mealLogService = mealLogService;
        this.userService = userService;
        this.updateNutrients = updateNutrients;
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/{userId}/meals")
    @JsonView(MealView.Overview.class)
    @Operation(description = "Get all meals of a user")
    public List<Meal> getAllMeals(@PathVariable String userId){

        try{
            return mealService.getAllMealsOfUser(userId);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/{userId}/meals")
    @JsonView(MealView.Extended.class)
    @Operation(description = "Add a new meal")
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
    @Operation(description = "Get a meal by mealId")
    public Meal getMealById(@PathVariable String userId, @PathVariable String mealId){
        try{
            validateUserId(userId);
            return mealService.getMealById(mealId);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}/meals/logs")
    @Operation(description = "Get all meal logs of a user")
    public List<MealLog> getAllMealLogsOfUser(@PathVariable String userId){
        try {
            return mealLogService.findAllLogsOfUser(userId);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{userId}/meals/{mealId}")
    @Operation(description = "Delete a meal by id")
    public void deleteMeal(@PathVariable String userId, @PathVariable String mealId){
        try{
            validateUserId(userId);
            mealService.deleteMeal(mealId);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
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


