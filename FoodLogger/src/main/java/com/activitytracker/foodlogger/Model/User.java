package com.activitytracker.foodlogger.Model;

import com.activitytracker.foodlogger.View.MealView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@JsonView(MealView.Extended.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;
    private String gender;

    private double height;
    private String heightUnit;

    private double weight;
    private String weightUnit;

    private short age;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }



    public User(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


    public User() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }
}
