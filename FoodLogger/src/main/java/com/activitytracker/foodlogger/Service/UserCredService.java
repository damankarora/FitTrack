package com.activitytracker.foodlogger.Service;

import com.activitytracker.foodlogger.Model.UserCred;
import com.activitytracker.foodlogger.Repository.UserCredRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCredService {

    private final UserCredRepository userCredRepository;

    public UserCredService(UserCredRepository userCredRepository) {
        this.userCredRepository = userCredRepository;
    }

    public UserCred registerUser(String username, String password, String role){
        UserCred credToAdd = new UserCred(username, password, role);
        userCredRepository.save(credToAdd);
        return credToAdd;
    }

    public UserCred updateUser(UserCred updatedUserCred){
        return userCredRepository.save(updatedUserCred);
    }

    public UserCred findById(Integer credId){
        return userCredRepository.findById(credId).orElse(null);
    }

    public UserCred findCredByDetailId(Integer detailId){
        return userCredRepository.findUserCredByUserDetails_Id(detailId);
    }

    public void deleteUser(Integer credId){
        userCredRepository.deleteById(credId);
    }
}
