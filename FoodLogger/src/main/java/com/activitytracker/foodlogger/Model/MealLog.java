package com.activitytracker.foodlogger.Model;


import com.activitytracker.foodlogger.View.MealView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@JsonView(MealView.Extended.class)
public class MealLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String foodItem;

    @ManyToOne
    @JsonIgnore
    private Meal meal;

    public MealLog() {
    }

    public MealLog(String foodItem, Meal meal) {
        this.foodItem = foodItem;
        this.meal = meal;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }



    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
