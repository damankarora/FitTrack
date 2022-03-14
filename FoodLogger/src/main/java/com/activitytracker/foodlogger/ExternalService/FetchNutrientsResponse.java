package com.activitytracker.foodlogger.ExternalService;

import java.util.List;

public class FetchNutrientsResponse {
    private List<FoodNutrition> foods;

    public List<FoodNutrition> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodNutrition> foods) {
        this.foods = foods;
    }
}

