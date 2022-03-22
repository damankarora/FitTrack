package com.example.exerciselogger.Controller;

import com.example.exerciselogger.Model.Activity;
import com.example.exerciselogger.Payloads.AddActivityPayload;
import com.example.exerciselogger.Payloads.UpdateActivityPayload;
import com.example.exerciselogger.Service.ActivityService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping("/")
    public String home(){
        System.out.println("Hit the endpoint");
        return "Hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addActivity")
    public List<Activity> addNewActivity(@RequestBody AddActivityPayload payload){
        return activityService.addNewActivity(payload);
    }

    @RequestMapping(method=RequestMethod.GET, value = "/activity/{activityId}")
    public Activity getActivityById(@PathVariable String activityId){
        Activity foundActivity = activityService.getActivityById(Integer.valueOf(activityId));
        if (foundActivity == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Activity found");
        }
        return foundActivity;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/activity/recent/{userId}")
    public List<Activity> getActivitiesByUserId(@PathVariable String userId){
        List<Activity> foundActivities = activityService.getAllActivitiesOfUser(Integer.valueOf(userId));

        if (foundActivities == null || foundActivities.size() < 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No activities found with given user id");
        }
        return foundActivities;
    }



    @RequestMapping(method = RequestMethod.DELETE, value = "/activity/{activityId}")
    public void deleteActivityById(@PathVariable String activityId){
        try{
            activityService.deleteActivityById(Integer.valueOf(activityId));
        }catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No activity found with given Id");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/activity/{activityId}")
    public Activity updateActivity(@PathVariable String activityId, @RequestBody UpdateActivityPayload payload){
        try{
            return activityService.updateActivity(payload, activityId);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No activity found with given Id");
        }
    }

}

