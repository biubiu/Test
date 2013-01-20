package com.shawn.object;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.base.Objects.ToStringHelper;

public class ObjectBuilder {

    public static void main(String[] args) {

    }
}

class NutritionFacts {
    private  int servingSize;
    private int servings;
    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrate;


    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }


    public void setServings(int servings) {
        this.servings = servings;
    }


    public void setCalories(int calories) {
        this.calories = calories;
    }


    public void setFat(int fat) {
        this.fat = fat;
    }


    public void setSodium(int sodium) {
        this.sodium = sodium;
    }


    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }


    public int getServingSize() {
        return servingSize;
    }


    public int getServings() {
        return servings;
    }


    public int getCalories() {
        return calories;
    }


    public int getFat() {
        return fat;
    }


    public int getSodium() {
        return sodium;
    }


    public int getCarbohydrate() {
        return carbohydrate;
    }


    public static class NutritionFactBuilder {
        NutritionFacts nutritionFacts;
        public NutritionFactBuilder(NutritionFacts nutritionFacts) {
            this.nutritionFacts = nutritionFacts;
        }

        public NutritionFacts servingSize(int servingSize){
            nutritionFacts.setServingSize(servingSize);
            return nutritionFacts;
        }

        public NutritionFacts servings(int servings){
            nutritionFacts.setServings(servings);
            return nutritionFacts;
        }

    }
}
