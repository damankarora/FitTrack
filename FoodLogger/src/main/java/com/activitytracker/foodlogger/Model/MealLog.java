package com.activitytracker.foodlogger.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class MealLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String foodItem;

    private Date mealDate;

    public MealLog() {
    }

    public MealLog(String foodItem) {

        this.foodItem = foodItem;
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
}
