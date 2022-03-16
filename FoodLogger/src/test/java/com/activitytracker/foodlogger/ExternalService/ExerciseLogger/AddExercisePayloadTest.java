package com.activitytracker.foodlogger.ExternalService.ExerciseLogger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AddExercisePayloadTest {

    @Test
    @DisplayName("Request body should be empty when no value is set.")
    public void requestBodyShouldBeEmpty(){
        AddExercisePayload payload = new AddExercisePayload();

        payload.setQuery(Collections.singletonList("ran 2 miles"));
        payload.setUserId(1);
        Map<String, Object> objectMap = payload.getRequestBody();

        assertEquals(2, objectMap.size());
    }

    @Test
    @DisplayName("Should throw error when mandatory data is not set")
    public void requestBodyShouldThrow(){
        AddExercisePayload payload = new AddExercisePayload();

        RuntimeException thrownException = assertThrows(RuntimeException.class, payload::getRequestBody);

        assertEquals("Query is a must to make a request", thrownException.getMessage());

        AddExercisePayload payload1 = new AddExercisePayload();

        payload1.setQuery(Collections.singletonList("Ran 2 miles"));

        RuntimeException thrownException1 = assertThrows(RuntimeException.class, payload1::getRequestBody);
        assertEquals("User id can't be empty", thrownException1.getMessage());
    }

    @Test
    @DisplayName("When all the data is given")
    public void requestBodyShouldBeComplete(){
        AddExercisePayload payload = new AddExercisePayload();
        payload.setQuery(Collections.singletonList("ran 2 miles"));
        payload.setUserId(1);
        payload.setAge((short) 21);
        payload.setGender("Male");
        payload.setWeightKg(65);
        payload.setHeightCm(170);

        Map<String, Object> objectMap = payload.getRequestBody();

        assertEquals(objectMap.size(), 6);
        //noinspection unchecked
        assertEquals("ran 2 miles", ((List<String>)objectMap.get("query")).get(0));
        assertEquals(1, objectMap.get("userId"));
        assertEquals("Male", objectMap.get("gender"));
        assertEquals(170.0, objectMap.get("heightCm"));
        assertEquals(65.0, objectMap.get("weightKg"));
        assertEquals((short) 21, objectMap.get("age"));
    }
}