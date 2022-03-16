package com.activitytracker.foodlogger.ExternalService.ExerciseLogger;

import java.util.Date;

//    {
//        "activityId": 50,
//            "userId": 2,
//            "activityTitle": "run 1 mile",
//            "activityDuration": 0.0,
//            "caloriesBurnt": 0.0,
//            "date": null
//    }
public class Activity {

    private Integer activityId;
    private Integer userId;
    private String activityTitle;
    private float activityDuration;
    private float caloriesBurnt;
    private Date date;

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

    public float getActivityDuration() {
        return activityDuration;
    }

    public void setActivityDuration(float activityDuration) {
        this.activityDuration = activityDuration;
    }

    public float getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(float caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
