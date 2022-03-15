package com.example.exerciselogger.Service;

import com.example.exerciselogger.ExternalService.Nutrionix.ExerciseDetails;
import com.example.exerciselogger.ExternalService.Nutrionix.FetchActivityDetails;
import com.example.exerciselogger.ExternalService.Nutrionix.FetchActivityDetailsResponse;
import com.example.exerciselogger.Model.Activity;
import com.example.exerciselogger.Payloads.AddActivityPayload;
import com.example.exerciselogger.Payloads.UpdateActivityPayload;
import com.example.exerciselogger.Repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final FetchActivityDetails fetchActivityDetails;

    public ActivityService(ActivityRepository activityRepository, FetchActivityDetails fetchActivityDetails) {
        this.activityRepository = activityRepository;
        this.fetchActivityDetails = fetchActivityDetails;
    }

    public List<Activity> addNewActivity(AddActivityPayload activityPayload){

        List<Activity> activityListToAdd = new ArrayList<>();

        for (String activityTitle : activityPayload.getQuery()){
            Activity activityToAdd = new Activity();
            activityToAdd.setActivityTitle(activityTitle);
            activityToAdd.setUserId(activityPayload.getUserId());
            activityToAdd.setCaloriesBurnt(-1);
            activityToAdd.setActivityDuration(-1);
            activityListToAdd.add(activityToAdd);
        }

        List<Activity> addedActivities = (List<Activity>) activityRepository.saveAll(activityListToAdd);

        Thread updateThread = new Thread(() -> {updateCalorieData(activityPayload.getRequestBody(), addedActivities);
            System.out.println("Updated");});

        updateThread.start();


        return addedActivities;

    }

    public void deleteActivityById(Integer id){
        activityRepository.deleteById(id);
    }

    public Activity updateActivity(UpdateActivityPayload payload, String activityId){
        Activity foundActivity = activityRepository.findById(Integer.valueOf(activityId)).orElse(null);

        if (foundActivity == null){
            throw new NoSuchElementException("Invalid activity Id");
        }

        foundActivity.setDate(payload.getDate());
        foundActivity.setUserId(payload.getUserId());

        foundActivity.setCaloriesBurnt(-1);
        foundActivity.setActivityDuration(-1);

        Activity savedActivity = activityRepository.save(foundActivity);

        Thread updateCalorieThread = new Thread(() -> updateCalorieData(payload.getRequestBody(), Collections.singletonList(savedActivity)));

        updateCalorieThread.start();

        return savedActivity;

    }

    public Activity getActivityById(Integer id){
        return activityRepository.findById(id).orElse(null);
    }

    public List<Activity> getAllActivitiesOfUser(Integer userId){
        return activityRepository.findFirst10ByUserIdOrderByActivityIdDesc(userId);
    }

    public void updateCalorieData(Map<String, Object> requestBody, List<Activity> activityList){
        FetchActivityDetailsResponse response = fetchActivityDetails.fetchData(requestBody);

        List<ExerciseDetails> exerciseDetailsList = response.getExercises();

        for (int i = 0; i < exerciseDetailsList.size(); i ++){
            ExerciseDetails exerciseDetails = exerciseDetailsList.get(i);
            Activity activityToAdd = activityList.get(i);

            activityToAdd.setActivityTitle(exerciseDetails.getUser_input());
            activityToAdd.setActivityDuration(exerciseDetails.getDuration_min());
            activityToAdd.setCaloriesBurnt(exerciseDetails.getNf_calories());
        }

        activityRepository.saveAll(activityList);
    }
}
