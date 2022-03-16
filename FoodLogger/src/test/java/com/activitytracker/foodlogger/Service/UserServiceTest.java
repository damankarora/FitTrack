package com.activitytracker.foodlogger.Service;

import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Test
    @DisplayName("Should return a user by Id")
    public void shouldReturnuserById(){
        UserService userService = new UserService(userRepository);

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
        UserService userService = new UserService(userRepository);

        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());
        User foundUser = userService.getUserDetails(1);

        assertNull(foundUser);
    }

    @Test
    @DisplayName("Should add user successfully")
    public void shouldAddUser(){
        String testEmail = "dummyemail@gmail.com";
        String testName = "Daman";

        UserService userService = new UserService(userRepository);

        User userToAdd = new User();
        userToAdd.setName(testName);
        userToAdd.setEmail(testEmail);

        assertDoesNotThrow(() -> userService.addNewUser(userToAdd));

    }

    @Test
    @DisplayName("Should not add user when mandatory fields are missing")
    public void shouldNotAddUser(){

        UserService userService = new UserService(userRepository);

        User userToAdd = new User();

        Mockito.when(userRepository.save(userToAdd)).thenThrow(new RuntimeException("Mandatory fields cannot be empty"));

        assertThrows(RuntimeException.class, () -> userService.addNewUser(userToAdd));
    }
}