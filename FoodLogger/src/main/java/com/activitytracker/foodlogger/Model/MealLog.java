package com.activitytracker.foodlogger.Model;


import com.activitytracker.foodlogger.ExternalService.DetailedNutrient;
import com.activitytracker.foodlogger.ExternalService.FoodNutrition;
import com.activitytracker.foodlogger.View.MealView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonView(MealView.Extended.class)
public class MealLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String foodItem;

    private float calories;

    // Focused
    private float fiber;
    private float totalCarbs;
    private float protein;
    private float totalFat;

    // Extended (<attb_Id>).
    private float servingQuantity;
    private String servingUnit;
    private float servingWeight;
    private float calciumMg; //(301)
    private float ironMg; //(303)
    private float saturatedFat;
    private float cholesterolMg;
    private float sodiumMg;
    private float lactose; //(213)
    private float zincMg; //(309)

    private float sugars;
    private float potassiumMg;

    @ManyToOne
    @JsonIgnore
    private Meal meal;

    public MealLog() {
    }

    public MealLog(String foodItem, Meal meal) {
        this.foodItem = foodItem;
        this.meal = meal;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }



    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public float getServingQuantity() {
        return servingQuantity;
    }

    public void setServingQuantity(float servingQuantity) {
        this.servingQuantity = servingQuantity;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public float getServingWeight() {
        return servingWeight;
    }

    public void setServingWeight(float servingWeight) {
        this.servingWeight = servingWeight;
    }

    public float getCalories() {
        return calories;
    }



    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getFiber() {
        return fiber;
    }

    public void setFiber(float fiber) {
        this.fiber = fiber;
    }

    public float getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(float totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(float totalFat) {
        this.totalFat = totalFat;
    }

    public float getCalciumMg() {
        return calciumMg;
    }

    public void setCalciumMg(float calciumMg) {
        this.calciumMg = calciumMg;
    }

    public float getIronMg() {
        return ironMg;
    }

    public void setIronMg(float ironMg) {
        this.ironMg = ironMg;
    }

    public float getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(float saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public float getCholesterolMg() {
        return cholesterolMg;
    }

    public void setCholesterolMg(float cholesterolMg) {
        this.cholesterolMg = cholesterolMg;
    }

    public float getSodiumMg() {
        return sodiumMg;
    }

    public void setSodiumMg(float sodiumMg) {
        this.sodiumMg = sodiumMg;
    }

    public float getLactose() {
        return lactose;
    }

    public void setLactose(float lactose) {
        this.lactose = lactose;
    }

    public float getZincMg() {
        return zincMg;
    }

    public void setZincMg(float zincMg) {
        this.zincMg = zincMg;
    }

    public float getSugars() {
        return sugars;
    }

    public void setSugars(float sugars) {
        this.sugars = sugars;
    }

    public float getPotassiumMg() {
        return potassiumMg;
    }

    public void setPotassiumMg(float potassiumMg) {
        this.potassiumMg = potassiumMg;
    }

    public void parseData(FoodNutrition foodNutrition){
        this.setServingQuantity(foodNutrition.getServing_qty());
        this.setServingUnit(foodNutrition.getServing_unit());
        this.setServingWeight(foodNutrition.getServing_weight_grams());

        this.setCalories(foodNutrition.getNf_calories());
        this.setTotalFat(foodNutrition.getNf_total_fat());
        this.setSaturatedFat(foodNutrition.getNf_saturated_fat());
        this.setCholesterolMg(foodNutrition.getNf_cholesterol());
        this.setSodiumMg(foodNutrition.getNf_sodium());
        this.setTotalCarbs(foodNutrition.getNf_total_carbohydrate());
        this.setFiber(foodNutrition.getNf_dietary_fiber());
        this.setSugars(foodNutrition.getNf_sugars());
        this.setProtein(foodNutrition.getNf_protein());
        this.setPotassiumMg(foodNutrition.getNf_potassium());

        List<DetailedNutrient> detailedNutrients = foodNutrition.getFull_nutrients();

        for (DetailedNutrient nutrient : detailedNutrients){
            int attribId = nutrient.getAttr_id();

            switch (attribId){
                case 213: this.setLactose(nutrient.getValue());
                            break;
                case 301: this.setCalciumMg(nutrient.getValue());
                            break;
                case 303: this.setIronMg(nutrient.getValue());
                            break;
                case 309: this.setZincMg(nutrient.getValue());
                            break;
            }
        }


    }
}
