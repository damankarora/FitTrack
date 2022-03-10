package com.activitytracker.foodlogger.Model;

import com.activitytracker.foodlogger.View.MealView;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Meal {

    @JsonView(MealView.Overview.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mealId;

    @ManyToOne
    @JsonView(MealView.Extended.class)
    private User user;

    @JsonView(MealView.Overview.class)
    private String mealTitle;

    @JsonView(MealView.Overview.class)
    private Date mealDate;

    @JsonView(MealView.Extended.class)
    @OneToMany(mappedBy = "meal", cascade = CascadeType.REMOVE)
    private List<MealLog> mealLogs;



    public Meal(String mealTitle) {
        this.mealTitle = mealTitle;

    }

    public Meal() {

    }


    public List<MealLog> getMealLogs() {
        return mealLogs;
    }

    public void setMealLogs(List<MealLog> mealLogs) {
        this.mealLogs = mealLogs;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public Meal(Integer mealId) {
        this.mealId = mealId;
    }

    public Meal( User user) {

        this.user = user;
    }

    public Integer getMealId() {
        return mealId;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

