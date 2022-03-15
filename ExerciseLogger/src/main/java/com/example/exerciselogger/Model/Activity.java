package com.example.exerciselogger.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer activityId;

    private Integer userId;

    private String activityTitle;
    private double activityDuration;
    private double caloriesBurnt;
    private Date date;

    public Activity() {
    }

    public Activity(Integer activityId, Integer userId, String activityTitle, double activityDuration, double caloriesBurnt) {
        this.activityId = activityId;
        this.userId = userId;
        this.activityTitle = activityTitle;
        this.activityDuration = activityDuration;
        this.caloriesBurnt = caloriesBurnt;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public double getActivityDuration() {
        return activityDuration;
    }

    public void setActivityDuration(double activityDuration) {
        this.activityDuration = activityDuration;
    }

    public double getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(double caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
