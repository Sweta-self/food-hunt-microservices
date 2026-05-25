package com.foodhunt.food_service.service;

import com.foodhunt.food_service.dto.FoodSpotRequest;
import com.foodhunt.food_service.dto.FoodSpotResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FoodSpotService {

    FoodSpotResponse createFoodSpot(FoodSpotRequest request);
    List<FoodSpotResponse> getAllFoodSpots();
    FoodSpotResponse getFoodSpotById(Long id);
}
