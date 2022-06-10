package com.activitytracker.foodlogger.Service;

import com.activitytracker.foodlogger.Model.User;
import com.activitytracker.foodlogger.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        List<User> foundUsers = new ArrayList<>();
        userRepository.findAll().forEach(foundUsers::add);
        return foundUsers;
    }

    public User getUserDetails(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public void updateUser(User user) throws NoSuchElementException{

        User foundUser = userRepository.findById(user.getId()).orElse(null);

        if (foundUser == null){
            throw new NoSuchElementException("User not found");
        }
        userRepository.save(user);
    }
}


