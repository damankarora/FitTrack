package com.activitytracker.foodlogger.Service;

import com.activitytracker.foodlogger.Model.Meal;
import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MealService {

    private final MealRepository mealRepository;

    private final UserService userService;

    public MealService(MealRepository mealRepository, UserService userService) {
        this.userService = userService;
        this.mealRepository = mealRepository;
    }

    public void addNewMeal(Meal mealToAdd, String userId) throws NoSuchElementException {

        // Check if user exists.

        User userFound = userService.getUserDetails(Integer.valueOf(userId));

        if (userFound == null){
            throw new NoSuchElementException("User not found");
        }

        mealToAdd.setUser(userFound);
        mealRepository.save(mealToAdd);
    }


    public void updateMeal(Meal mealToUpdate){
        mealRepository.save(mealToUpdate);
    }

    public List<Meal> getAllMealsOfUser(String userId) throws NoSuchElementException{

        User userFound = userService.getUserDetails(Integer.valueOf(userId));

        if (userFound == null){
            throw new NoSuchElementException("User not found");
        }

        return mealRepository.findAllByUserId(Integer.valueOf(userId));
    }

    public void deleteMeal(String mealId){

        Meal foundMeal = mealRepository.findById(Integer.valueOf(mealId)).orElse(null);
        if (foundMeal == null){
            throw new NoSuchElementException("Meal not found");
        }
        mealRepository.delete(foundMeal);
    }

    public Meal getMealById(String mealId){
        Meal foundMeal = mealRepository.findById(Integer.valueOf(mealId)).orElse(null);
        if (foundMeal == null){
            throw new NoSuchElementException("Meal not found");
        }


        return foundMeal;

    }
}
