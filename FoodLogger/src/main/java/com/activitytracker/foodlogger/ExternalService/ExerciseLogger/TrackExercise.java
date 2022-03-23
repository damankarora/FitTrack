package com.activitytracker.foodlogger.ExternalService.ExerciseLogger;


import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class TrackExercise {

    private final RestTemplate restTemplate;
    private final UserService userService;

    @Value("${exercise.logger.url}")
    private String exerciseMSURL;

    public TrackExercise(RestTemplateBuilder restTemplateBuilder, UserService userService) {

        this.restTemplate = restTemplateBuilder.build();
        this.userService = userService;
    }

    public List<Activity> addNewActivity(List<String> query, Integer userId, Date date){
        User foundUser = userService.getUserDetails(userId);

        if (foundUser == null){
            throw new NoSuchElementException("User not found");
        }

        AddExercisePayload addExercisePayload = new AddExercisePayload();
        addExercisePayload.setQuery(query);
        addExercisePayload.setUserId(userId);
        addExercisePayload.setGender(foundUser.getGender());
        addExercisePayload.setAge(foundUser.getAge());
        addExercisePayload.setHeightCm(foundUser.getHeight());
        addExercisePayload.setWeightKg(foundUser.getWeight());
        addExercisePayload.setDate(date);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(addExercisePayload.getRequestBody(), headers);

        ResponseEntity<Activity[]> response = restTemplate.postForEntity(exerciseMSURL+"addActivity", httpEntity, Activity[].class);

        if (response.getStatusCode() == HttpStatus.OK){
            return Arrays.asList(Objects.requireNonNull(response.getBody()));
        }

        throw new ResponseStatusException(response.getStatusCode(), "Error occured in exercise logger");

    }



    public List<Activity> getActivity(Integer activityId, Integer userId){
        if (activityId != null){
            ResponseEntity<Activity> response;
            try {
                response = restTemplate.getForEntity(exerciseMSURL + "activity/" + activityId, Activity.class);
            }catch (ResourceAccessException e){
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error from exercise logger");
            }
            if (response.getStatusCode() == HttpStatus.OK){
                return Collections.singletonList(response.getBody());
            }else {
                throw new ResponseStatusException(response.getStatusCode(), "Error from exercise logger");
            }
        }
        if (userId != null){

            User foundUser = userService.getUserDetails(userId);
            if (foundUser == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userid");
            }

            ResponseEntity<Activity[]> response= restTemplate.getForEntity(exerciseMSURL+"/activity/recent/"+userId, Activity[].class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
                return Arrays.asList(response.getBody());
            }else {
                throw new ResponseStatusException(response.getStatusCode(), "Error from exercise logger");
            }
        }

        throw new RuntimeException("Cannot fetch exercise details.");

    }

    public String callExerciseLogger(){
        return restTemplate.getForObject("http://localhost:8081/", String.class);

    }

}
