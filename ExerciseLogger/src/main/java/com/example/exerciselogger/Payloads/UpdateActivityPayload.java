package com.example.exerciselogger.Payloads;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateActivityPayload{
    private String query;
    private Integer userId;
    private String gender;
    private float weightKg;
    private float heightCm;
    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date date;

    public UpdateActivityPayload(String query, Integer userId, String gender, float weightKg, float heightCm, int age, Date date) {
        this.query = query;
        this.userId = userId;
        this.gender = gender;
        this.weightKg = weightKg;
        this.heightCm = heightCm;
        this.age = age;
        this.date = date;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(float weightKg) {
        this.weightKg = weightKg;
    }

    public float getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(float heightCm) {
        this.heightCm = heightCm;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }



    public Map<String, Object> getRequestBody(){
        Map<String, Object> requestBodyMap = new HashMap<>();

        requestBodyMap.put("query", this.query);
        if (!this.gender.isEmpty()){
            requestBodyMap.put("gender", this.gender);
        }

        if (this.heightCm > 0){
            requestBodyMap.put("height_cm", this.heightCm);
        }

        if (this.weightKg > 0){
            requestBodyMap.put("weight_kg", this.weightKg);
        }

        if (this.age > 0){
            requestBodyMap.put("age", this.age);
        }

        return requestBodyMap;
    }
}
