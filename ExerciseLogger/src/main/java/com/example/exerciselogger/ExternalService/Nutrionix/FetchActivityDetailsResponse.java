package com.example.exerciselogger.ExternalService.Nutrionix;

import java.util.List;

public class FetchActivityDetailsResponse {
    List<ExerciseDetails> exercises;

    public List<ExerciseDetails> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDetails> exercises) {
        this.exercises = exercises;
    }
}

