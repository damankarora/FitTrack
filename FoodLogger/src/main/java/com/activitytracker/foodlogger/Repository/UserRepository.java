package com.activitytracker.foodlogger.Repository;

import com.activitytracker.foodlogger.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
