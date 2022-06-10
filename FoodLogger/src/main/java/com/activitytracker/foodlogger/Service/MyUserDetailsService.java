package com.activitytracker.foodlogger.Service;

import com.activitytracker.foodlogger.dataobjects.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    UserCredService userCredService;

    public MyUserDetailsService(UserCredService userCredService) {
        this.userCredService = userCredService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(userCredService.findByUsername(username));
    }
}
