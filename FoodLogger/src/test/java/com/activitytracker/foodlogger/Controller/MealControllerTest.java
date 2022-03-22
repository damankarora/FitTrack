package com.activitytracker.foodlogger.Controller;

import com.activitytracker.foodlogger.Model.Meal;
import com.activitytracker.foodlogger.Model.MealLog;
import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Repository.MealRepository;
import com.activitytracker.foodlogger.Repository.UserRepository;
import com.activitytracker.foodlogger.Service.MealLogService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class MealControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    MealRepository mealRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MealLogService mealLogService;

    @Test
    @DisplayName("Should return all the meals of a user")
    public void shouldReturnListOfMealsOfUser(){

        User ourUser = getTestUser();
        userRepository.save(ourUser);

        Integer userId = ourUser.getId();

        Meal dummyMeal = new Meal();
        Date myDate = new Date();
        dummyMeal.setMealTitle("Breakfast");
        dummyMeal.setMealDate(myDate);
        dummyMeal.setUser(ourUser);

        mealRepository.save(dummyMeal);

        List<MealsResponse> responseList = webTestClient.get().uri("/user/"+userId+"/meals").exchange().expectStatus().is2xxSuccessful().expectBodyList(MealsResponse.class).returnResult().getResponseBody();

        assertNotNull(responseList);
        assertEquals(1, responseList.size());

        assertEquals(myDate, responseList.get(0).getMealDate());

        mealRepository.delete(dummyMeal);
        userRepository.delete(ourUser);

    }

    @Test
    @DisplayName("Should return Http 400 when user id is invalid")
    public void shouldReturnBadRequestError(){
        webTestClient.get().uri("/user/10/meals").exchange().expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should add a meal")
    public void shouldAddMeal() {
//        Adding test user.
        User ourUser = getTestUser();

        userRepository.save(ourUser);

        String payload = "";
        try{
           payload  = readJsonFile();
        }
        catch (RuntimeException e){
            userRepository.delete(ourUser);
            e.printStackTrace();
        }

        MealsResponse response = webTestClient.post().uri("user/"+ourUser.getId()+"/meals").contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(payload)).exchange().expectStatus().is2xxSuccessful().expectBody(MealsResponse.class).returnResult().getResponseBody();

        assertNotNull(response);
        assertEquals("Lunch", response.getMealTitle());

        mealRepository.deleteById(response.getMealId());
        userRepository.delete(ourUser);
    }

    @Test
    @DisplayName("Should not add meal when userId is invalid")
    public void shouldNotAddMeal(){

        String payload = "";
        try{
            payload  = readJsonFile();
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }

        webTestClient.post().uri("user/2000/meals").contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(payload)).exchange().expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should delete a Meal with no logs")
    public void shouldDeleteMeal(){
        User ourUser = getTestUser();
        userRepository.save(ourUser);

        Meal dummyMeal = new Meal();
        Date myDate = new Date();
        dummyMeal.setMealTitle("Breakfast");
        dummyMeal.setMealDate(myDate);
        dummyMeal.setUser(ourUser);

        mealRepository.save(dummyMeal);

        webTestClient.delete().uri("user/"+ourUser.getId()+"/meals/"+dummyMeal.getMealId()).exchange().expectStatus().is2xxSuccessful();

        assertNull(mealRepository.findById(dummyMeal.getMealId()).orElse(null));
        userRepository.delete(ourUser);

    }

    @Test
    @DisplayName("Should not delete meal if user id is invalid")
    public void shouldNotDeleteMealUserId(){
        webTestClient.delete().uri("user/90/meals/90").exchange().expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should not delete meal if not exists")
    public void shouldNotDeleteMealNotExists(){
        User ourUser = getTestUser();
        userRepository.save(ourUser);

        webTestClient.delete().uri("user/"+ourUser.getId()+"/meals/90").exchange().expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Should delete a meal with logs when ids are correct")
    public void shouldDeleteAMeal(){
        User ourUser = getTestUser();
        userRepository.save(ourUser);

        Meal dummyMeal = new Meal();
        Date myDate = new Date();
        dummyMeal.setMealTitle("Breakfast");
        dummyMeal.setMealDate(myDate);
        dummyMeal.setUser(ourUser);
        mealRepository.save(dummyMeal);

        webTestClient.delete().uri("user/"+ourUser.getId()+"/meals/"+dummyMeal.getMealId()).exchange().expectStatus().is2xxSuccessful();

        assertEquals(0, mealLogService.getAllMealLogsByMealId(dummyMeal.getMealId()).size());

        userRepository.delete(ourUser);

    }

    @Test
    @DisplayName("Should return all meal logs of a user")
    public void shouldReturnAllLogs(){
        User ourUser = getTestUser();

        userRepository.save(ourUser);

        Meal dummyMeal = new Meal();
        Date myDate = new Date();
        dummyMeal.setMealTitle("Breakfast");
        dummyMeal.setMealDate(myDate);
        dummyMeal.setUser(ourUser);
        mealRepository.save(dummyMeal);

        List<MealLog> logs = mealLogService.addMultipleMealLogs(Arrays.asList("2 roti", "1 cup rice"), dummyMeal);
        Map<Integer, MealLog> logMap = new HashMap<>();
        logs.forEach((mealLog -> logMap.put(mealLog.getId(), mealLog)));

        List<MealLog> returnedLogs = webTestClient.get().uri("user/"+ourUser.getId()+"/meals/logs/").exchange().expectBodyList(MealLog.class).returnResult().getResponseBody();

        assertNotNull(returnedLogs);
        assertEquals(2, returnedLogs.size());

        for (MealLog log : returnedLogs){
            assertTrue(logMap.containsKey(log.getId()));
        }

        mealRepository.deleteById(dummyMeal.getMealId());
        userRepository.delete(ourUser);
    }

    private String readJsonFile() {
        try{
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("addMealResource.json")).toURI());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String payload = reader.readLine();
            reader.close();

            return payload;
        }
        catch (IOException e){
            System.out.println("Unable to open file");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        catch (URISyntaxException e){
            System.out.println("URL syntax exception");
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    private User getTestUser(){
        User ourUser = new User();
        ourUser.setName("Test user");
        ourUser.setEmail("testEmail.sfs@ccom");
        return ourUser;
    }

}

class MealsResponse{
    String mealTitle;
    Integer mealId;
    Date mealDate;

    public MealsResponse(String mealTitle, Integer mealId, Date mealDate) {
        this.mealTitle = mealTitle;
        this.mealId = mealId;
        this.mealDate = mealDate;
    }

    @Override
    public String toString(){
        return mealTitle + " " + mealId + " " + mealDate;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public Integer getMealId() {
        return mealId;
    }

    public Date getMealDate() {
        return mealDate;
    }
}