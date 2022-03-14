package com.activitytracker.foodlogger.Controller;

import com.activitytracker.foodlogger.ExternalService.ExerciseLogger.TrackExercise;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExerciseController {

    private TrackExercise trackExercise;

    public ExerciseController(TrackExercise trackExercise) {
        this.trackExercise = trackExercise;
    }

    @RequestMapping("/exercise")
    public String callExercise(){
        return trackExercise.addActivity();
    }

}
