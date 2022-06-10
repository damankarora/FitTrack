package com.activitytracker.foodlogger.Service;

import com.activitytracker.foodlogger.Commons.ExceptionContainer;
import com.activitytracker.foodlogger.Model.UserCred;
import com.activitytracker.foodlogger.Repository.UserCredRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCredService {

    private final UserCredRepository userCredRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCredService(UserCredRepository userCredRepository, PasswordEncoder passwordEncoder) {
        this.userCredRepository = userCredRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserCred registerUser(String username, String password, String role) throws ExceptionContainer.UserAlreadyExistsException {
        UserCred foundUser = findByUsername(username);
        if (foundUser != null){
            throw new ExceptionContainer.UserAlreadyExistsException();
        }
        UserCred credToAdd = new UserCred(username, passwordEncoder.encode(password), role);
        userCredRepository.save(credToAdd);
        return credToAdd;
    }

    public UserCred findByUsername(String username){
        return userCredRepository.findUserCredByUsername(username);
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
