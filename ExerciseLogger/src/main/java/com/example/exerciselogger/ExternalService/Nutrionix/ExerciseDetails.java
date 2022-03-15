package com.example.exerciselogger.ExternalService.Nutrionix;

public class ExerciseDetails {
    private String user_input;
    private double duration_min;
    private double nf_calories;

    public ExerciseDetails(String user_input, double duration_min, double nf_calories) {
        this.user_input = user_input;
        this.duration_min = duration_min;
        this.nf_calories = nf_calories;
    }

    public String getUser_input() {
        return user_input;
    }

    public void setUser_input(String user_input) {
        this.user_input = user_input;
    }

    public double getDuration_min() {
        return duration_min;
    }

    public void setDuration_min(double duration_min) {
        this.duration_min = duration_min;
    }

    public double getNf_calories() {
        return nf_calories;
    }

    public void setNf_calories(double nf_calories) {
        this.nf_calories = nf_calories;
    }
}
