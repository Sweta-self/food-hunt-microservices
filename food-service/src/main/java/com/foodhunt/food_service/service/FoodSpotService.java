package com.foodhunt.food_service.service;

import com.foodhunt.food_service.dto.FoodSpotRequest;
import com.foodhunt.food_service.dto.FoodSpotResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FoodSpotService {

    FoodSpotResponse createFoodSpot(FoodSpotRequest request,String createdBy);
    Page<FoodSpotResponse> getAllFoodSpots(int page,int size,String sortBy,String direction);
    FoodSpotResponse getFoodSpotById(Long id);
    FoodSpotResponse updateFoodSpot(Long id,FoodSpotRequest request);
    void deleteFoodSpot(Long id);
    Page<FoodSpotResponse>searchByCity(String city,int page,int size);

}
