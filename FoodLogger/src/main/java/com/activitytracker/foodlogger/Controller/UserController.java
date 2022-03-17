package com.activitytracker.foodlogger.Controller;

import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Service.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public void createUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public User getSingleUser(@PathVariable String userId){
        return userService.getUserDetails(Integer.valueOf(userId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user")
    public void updateUser(@RequestBody User updatedUser){

        try {
            userService.updateUser(updatedUser);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{userId}")
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
