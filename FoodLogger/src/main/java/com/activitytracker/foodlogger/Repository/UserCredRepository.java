package com.activitytracker.foodlogger.Repository;

import com.activitytracker.foodlogger.Model.UserCred;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredRepository extends CrudRepository<UserCred, Integer> {
    public UserCred findUserCredByUserDetails_Id(Integer userDetails_id);
}
