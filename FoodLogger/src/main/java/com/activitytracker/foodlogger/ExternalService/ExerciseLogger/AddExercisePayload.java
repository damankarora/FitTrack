package com.activitytracker.foodlogger.ExternalService.ExerciseLogger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddExercisePayload {
    private List<String> query;
    private Integer userId;
    private String gender;
    private double weightKg;
    private double heightCm;
    private short age;

    public List<String> getQuery() {
        return query;
    }

    public void setQuery(List<String> query) {
        this.query = query;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public double getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(double heightCm) {
        this.heightCm = heightCm;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public Map<String, Object> getRequestBody(){
        Map<String, Object> objectMap = new HashMap<>();
        if (this.query != null && this.query.size() > 0){
            objectMap.put("query", this.query);
        }else{
//            If no query is given, request cannot me made.
            throw new RuntimeException("Query is a must to make a request");
        }

        if (this.userId != null){
            objectMap.put("userId", this.userId);
        }else{
            throw new RuntimeException("User id can't be empty");
        }

        if (this.gender != null && !this.gender.isEmpty()){
            objectMap.put("gender", this.gender);
        }

        if (this.heightCm > 0){
            objectMap.put("heightCm", this.heightCm);
        }

        if (this.weightKg > 0){
            objectMap.put("weightKg", this.weightKg);
        }

        if (this.age > 0){
            objectMap.put("age", this.age);
        }


        return objectMap;
    }
}
