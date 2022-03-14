package com.activitytracker.foodlogger.ExternalService.ExerciseLogger;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrackExercise {

    private final RestTemplate restTemplate;

    public TrackExercise(RestTemplateBuilder restTemplateBuilder) {

            this.restTemplate = restTemplateBuilder.build();

    }

    public String addActivity(){
        return restTemplate.getForObject("http://localhost:8081/", String.class);

    }

}
