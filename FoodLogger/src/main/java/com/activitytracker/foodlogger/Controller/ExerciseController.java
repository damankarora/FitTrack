package com.activitytracker.foodlogger.Controller;

import com.activitytracker.foodlogger.ExternalService.ExerciseLogger.Activity;
import com.activitytracker.foodlogger.ExternalService.ExerciseLogger.TrackExercise;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "400", description = "Bad request, user id not exist",
                content = {@Content(examples = @ExampleObject("{\n" +
        "    \"timestamp\": \"2022-03-23T06:58:14.107+00:00\",\n" +
        "    \"status\": 400,\n" +
        "    \"error\": \"Bad Request\",\n" +
        "    \"path\": \"/user/100/meals\"\n" +
        "}"))}),
        @ApiResponse(responseCode = "503", description = "Exercise logger microservice not working",
                content = {@Content(examples = @ExampleObject("{\n" +
        "    \"timestamp\": \"2022-03-23T07:08:38.507+00:00\",\n" +
        "    \"status\": 503,\n" +
        "    \"error\": \"Service Unavailable\",\n" +
        "    \"path\": \"/activity/158\"\n" +
        "}"))})})
public class ExerciseController {

    private final TrackExercise trackExercise;

    public ExerciseController(TrackExercise trackExercise) {
        this.trackExercise = trackExercise;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/activity")
    @Operation(description = "Add a new activity")
    public List<Activity> addNewActivity(@RequestBody AddActivityPayload payload){
        return trackExercise.addNewActivity(payload.getQuery(), payload.getUserId(), payload.getDate());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/activity/{activityId}")
    @Operation(description = "Fetch an activity using ActivityId")
    public Activity fetchActivityById(@PathVariable Integer activityId){
        return trackExercise.getActivity(activityId, null).get(0);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/activity/recent/{userId}")
    @Operation(description = "Get recent activities of a user by UserId")
    public List<Activity> fetchRecentActivities(@Parameter(description = "Valid id of user") @PathVariable Integer userId){
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
