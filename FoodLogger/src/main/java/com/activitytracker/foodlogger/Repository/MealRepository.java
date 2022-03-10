package com.activitytracker.foodlogger.Repository;

import com.activitytracker.foodlogger.Model.Meal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Integer> {

    List<Meal> findAllByUserId(Integer userId);

}
