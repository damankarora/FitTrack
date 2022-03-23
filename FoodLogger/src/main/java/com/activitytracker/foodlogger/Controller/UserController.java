package com.activitytracker.foodlogger.Controller;

import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@ApiResponse(responseCode = "200", description = "Success")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    @Operation(description = "Create a new User")
    public void createUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    @Operation(description = "Get all users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @ApiResponse(responseCode = "400", description = "Invalid userId",
            content = @Content(examples = @ExampleObject(
            "{\n" +
            "    \"timestamp\": \"2022-03-23T06:58:14.107+00:00\",\n" +
            "    \"status\": 400,\n" +
            "    \"error\": \"Bad Request\",\n" +
            "    \"path\": \"/*\"\n" +
            "}")))
    @Operation(description = "Get details of a user by id")
    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public User getSingleUser(@PathVariable String userId){
        return userService.getUserDetails(Integer.valueOf(userId));
    }

    @ApiResponse(responseCode = "400", description = "Invalid userId",
            content = @Content(examples = @ExampleObject(
                    "{\n" +
                            "    \"timestamp\": \"2022-03-23T06:58:14.107+00:00\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Bad Request\",\n" +
                            "    \"path\": \"/*\"\n" +
                            "}")))
    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    @Operation(description = "Update details of a user")
    public void updateUser(@RequestBody User updatedUser){

        try {
            userService.updateUser(updatedUser);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{userId}")
    @ApiResponse(responseCode = "400", description = "Invalid userId",
            content = @Content(examples = @ExampleObject(
                    "{\n" +
                            "    \"timestamp\": \"2022-03-23T06:58:14.107+00:00\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Bad Request\",\n" +
                            "    \"path\": \"/*\"\n" +
                            "}")))
    @Operation(description = "Delete a user by userId")
    public void deleteUser(@PathVariable String userId){
        try{
            userService.deleteUser(Integer.valueOf(userId));
        }
        catch (EmptyResultDataAccessException e){
            System.out.println(e.getExpectedSize());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");
        }
    }


}
