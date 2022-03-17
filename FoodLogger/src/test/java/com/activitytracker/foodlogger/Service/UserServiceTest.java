package com.activitytracker.foodlogger.Service;

import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    @DisplayName("Should return a user by Id")
    public void shouldReturnUserById(){


        String testEmail = "dummyemail@gmail.com";
        Integer testUserId = 1;
        String testName = "Daman";
        short age = 21;
        float heightCm = 170;

        User mockUser = new User(testUserId, testName, testEmail);
        mockUser.setAge(age);
        mockUser.setHeight(heightCm);

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(mockUser));

        mockUser.setId(1);

        User foundUser = userService.getUserDetails(1);

        assertEquals(mockUser.getId(), foundUser.getId());
        assertEquals(mockUser.getName(), foundUser.getName());
        assertEquals(mockUser.getEmail(), foundUser.getEmail());
        assertEquals(mockUser.getAge(), foundUser.getAge());
        assertEquals(mockUser.getHeight(), foundUser.getHeight());
    }

    @Test
    @DisplayName("Should return null if id is invalid")
    public void shouldReturnNullIfInvalid(){


        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());
        User foundUser = userService.getUserDetails(1);

        assertNull(foundUser);
    }

    @Test
    @DisplayName("Should add user successfully")
    public void shouldAddUser(){
        String testEmail = "dummyemail@gmail.com";
        String testName = "Daman";

        User userToAdd = new User();
        userToAdd.setName(testName);
        userToAdd.setEmail(testEmail);

        assertDoesNotThrow(() -> userService.addNewUser(userToAdd));

    }

    @Test
    @DisplayName("Should not add user when mandatory fields are missing")
    public void shouldNotAddUser(){

        User userToAdd = new User();

        Mockito.when(userRepository.save(userToAdd)).thenThrow(new RuntimeException("Mandatory fields cannot be empty"));

        assertThrows(RuntimeException.class, () -> userService.addNewUser(userToAdd));
    }

    @Test@DisplayName("Should update user data when Id is correct")
    public void shouldUpdateUserData(){

        Integer testId = 1;
        String testName = "New name";

        User userToUpdate = new User();
        userToUpdate.setId(testId);
        userToUpdate.setName("old Name");

        Mockito.when(userRepository.findById(testId)).thenReturn(Optional.of(userToUpdate));

        User userWithUpdatedData = new User();
        userWithUpdatedData.setName(testName);
        userWithUpdatedData.setId(testId);

        assertDoesNotThrow(()->userService.updateUser(userWithUpdatedData));
    }

    @Test
    @DisplayName("Should not update when Id is invalid")
    public void shouldNotUpdateInvalidId(){

        Integer testId = 1;
        String testName = "New name";

        Mockito.when(userRepository.findById(testId)).thenReturn(Optional.empty());

        User userWithUpdatedData = new User();
        userWithUpdatedData.setName(testName);
        userWithUpdatedData.setId(testId);

        NoSuchElementException e = assertThrows(NoSuchElementException.class, () -> userService.updateUser(userWithUpdatedData));

        assertEquals("User not found", e.getMessage());
    }

    @Test
    @DisplayName("Should delete a user with correct Id")
    public void shouldDeleteUser(){

        Integer testId = 1;
        assertDoesNotThrow(()->userService.deleteUser(testId));
    }

    @Test
    @DisplayName("Should not delete user with incorrect Id")
    public void shouldNotDeleteUser(){
        Integer testId = 1;
        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(userRepository).deleteById(testId);

        assertThrows(EmptyResultDataAccessException.class, () -> userService.deleteUser(testId));
    }

    @Test
    @DisplayName("Should return all the Users")
    public void shouldReturnAllUsers(){
        List<User> userList = Arrays.asList(
                new User(1, "Daman", "damanarora@gmail.com"),
                new User(2, "Daman2", "damanarora@gmail2.com"),
                new User(3, "Daman3", "damanarora@gmail3.com"));
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        assertEquals(3, userService.getAllUsers().size());

    }

}