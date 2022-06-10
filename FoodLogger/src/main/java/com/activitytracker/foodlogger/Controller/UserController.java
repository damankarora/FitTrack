package com.activitytracker.foodlogger.Controller;

import com.activitytracker.foodlogger.Commons.AuthenticatedUserContainer;
import com.activitytracker.foodlogger.Commons.ExceptionContainer;
import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Model.UserCred;
import com.activitytracker.foodlogger.Payloads.CreateUserPayload;
import com.activitytracker.foodlogger.Service.UserCredService;
import com.activitytracker.foodlogger.Service.UserService;
import com.activitytracker.foodlogger.Utils.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@ApiResponse(responseCode = "200", description = "Success")
public class UserController {


    private final UserService userService;
    private final UserCredService userCredService;

    public UserController(UserService userService, UserCredService userCredService) {
        this.userService = userService;
        this.userCredService = userCredService;
    }

    @RequestMapping("/")
    @Operation(description = "Home page")
    public String home(){
        return AuthenticatedUserContainer.getAuthenticatedUsername();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    @Operation(description = "Create a new User")
    public String createUser(@RequestBody CreateUserPayload user, HttpServletResponse response){

        try{
            if(validateCreatePayload(user)){
                UserCred addedUserCred = userCredService.registerUser(user.getUsername(), user.getPassword(), "ROLE_USER");
                User userDetails = userService.addNewUser(new User(
                        user.getName(), user.getUsername(), user.getGender(),
                        user.getHeight(), user.getHeightUnit(), user.getWeight(),
                        user.getWeightUnit(), user.getAge()
                ));
                addedUserCred.setUserDetails(userDetails);
                userCredService.updateUser(addedUserCred);
                return "Success";
            }
            throw new ExceptionContainer.InvalidPayloadException();


        } catch (ExceptionContainer.InvalidPayloadException err){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required Fields Missing");
        } catch (ExceptionContainer.UserAlreadyExistsException err){
            //Redirecting to login page.
            response.addHeader("Location", "/login");
            throw new ResponseStatusException(HttpStatus.valueOf(302));
        }
        catch (Exception err) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong");
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/all")
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
    @RequestMapping(method = RequestMethod.GET, value = "/user/current")
    public User getCurrentUser(){
        UserCred loggedUser = userCredService.findByUsername(AuthenticatedUserContainer.getAuthenticatedUsername());
        return loggedUser.getUserDetails();
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
        //TODO: Add validation on email update (because it has to be unique).
        try {
            UserCred loggedUser = userCredService.findByUsername(AuthenticatedUserContainer.getAuthenticatedUsername());
            updatedUser.setId(loggedUser.getUserDetails().getId());

            // To prevent null insertion in non-nullable fields.
            if (StringUtils.isEmpty(updatedUser.getName())){
                updatedUser.setName(loggedUser.getUserDetails().getName());
            }
            if (StringUtils.isEmpty(updatedUser.getEmail())){
                updatedUser.setEmail(loggedUser.getUserDetails().getEmail());
            }
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
    public String deleteUser(@PathVariable Integer userId){
        try{
            UserCred foundCred = userCredService.findCredByDetailId(userId);
            userCredService.deleteUser(foundCred.getId());
            return "SUCCESS";
        }
        catch (EmptyResultDataAccessException e){
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid userId");
        }
    }

    private static boolean validateCreatePayload(CreateUserPayload userPayload){
        return !StringUtils.isEmptyMultiple(Arrays.asList(
                userPayload.getUsername(), userPayload.getName(), userPayload.getPassword()
        ));
    }
}
