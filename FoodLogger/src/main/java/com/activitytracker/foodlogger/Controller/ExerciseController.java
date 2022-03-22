package com.activitytracker.foodlogger.Controller;

import com.activitytracker.foodlogger.ExternalService.ExerciseLogger.Activity;
import com.activitytracker.foodlogger.ExternalService.ExerciseLogger.TrackExercise;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ExerciseController {

    private final TrackExercise trackExercise;

    public ExerciseController(TrackExercise trackExercise) {
        this.trackExercise = trackExercise;
    }

    @RequestMapping("/exercise")
    public String callExercise(){
        return trackExercise.callExerciseLogger();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/activity")
    public List<Activity> addNewActivity(@RequestBody AddActivityPayload payload){
        return trackExercise.addNewActivity(payload.getQuery(), payload.getUserId(), payload.getDate());
    }

    @RequestMapping("/activity/{activityId}")
    public Activity fetchActivityById(@PathVariable Integer activityId){
        return trackExercise.getActivity(activityId, null).get(0);
    }

    @RequestMapping("/activity/recent/{userId}")
    public List<Activity> fetchRecentActivities(@PathVariable Integer userId){
        return trackExercise.getActivity(null, userId);
    }

}

class AddActivityPayload{
    private final List<String> query;
    private final Integer userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date date;

    public AddActivityPayload(List<String> query, Integer userId, Date date) {
        this.query = query;
        this.userId = userId;
        this.date = date;
    }

    public List<String> getQuery() {
        return query;
    }

    public Integer getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }
}
