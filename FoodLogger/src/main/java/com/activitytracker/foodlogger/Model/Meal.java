package com.activitytracker.foodlogger.Model;

import javax.persistence.*;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mealId;

    @ManyToOne
    private User user;

    public Meal() {
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

