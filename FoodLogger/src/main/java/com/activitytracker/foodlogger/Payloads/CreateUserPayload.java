package com.activitytracker.foodlogger.Payloads;

public class CreateUserPayload {
    private final String name;
    private final String username;
    private final String password;
    private final String gender;
    private final double height;
    private final String heightUnit;
    private final double weight;
    private final String weightUnit;
    private final short age;



    public CreateUserPayload(String name, String username, String password, String gender, double height, String heightUnit, double weight, String weightUnit, short age) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.height = height;
        this.heightUnit = heightUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public double getHeight() {
        return height;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public double getWeight() {
        return weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public short getAge() {
        return age;
    }
}
