package com.example.exerciselogger.Repository;

import com.example.exerciselogger.Model.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Integer> {
    public List<Activity> findFirst10ByUserIdOrderByActivityIdDesc(Integer userId);
}
