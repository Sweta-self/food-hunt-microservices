package com.foodhunt.food_service.serviceImpl;

import com.foodhunt.food_service.dto.FoodSpotRequest;
import com.foodhunt.food_service.dto.FoodSpotResponse;
import com.foodhunt.food_service.entity.FoodSpot;
import com.foodhunt.food_service.exception.ResourceNotFoundException;
import com.foodhunt.food_service.repository.FoodSpotRepository;
import com.foodhunt.food_service.service.FoodSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodSpotServiceImpl implements FoodSpotService {

    private final FoodSpotRepository foodSpotRepository;
    @Override
    public FoodSpotResponse createFoodSpot(FoodSpotRequest request) {

        FoodSpot foodSpot=new FoodSpot();
        foodSpot.setName(request.getName());
        foodSpot.setDescription(request.getDescription());
        foodSpot.setAddress(request.getAddress());
        foodSpot.setCity(request.getCity());
        foodSpot.setLatitude(request.getLatitude());
        foodSpot.setLongitude(request.getLongitude());

        foodSpot.setCreatedBy("Sweta");
        FoodSpot savedFoodSpot=foodSpotRepository.save(foodSpot);

        return FoodSpotResponse.builder()
                .id(savedFoodSpot.getId())
                .name(savedFoodSpot.getName())
                .address(savedFoodSpot.getAddress())
                .city(savedFoodSpot.getCity())
                .description(savedFoodSpot.getDescription())
                .latitude(savedFoodSpot.getLatitude())
                .longitude(savedFoodSpot.getLongitude())
                .build();

    }

    @Override
    public List<FoodSpotResponse> getAllFoodSpots() {
        List<FoodSpot>foodSpotList = foodSpotRepository.findAll();

        return foodSpotList.stream()
                .map(f->FoodSpotResponse.builder()
                        .id(f.getId())
                        .name(f.getName())
                        .address(f.getAddress())
                        .description(f.getDescription())
                        .address(f.getAddress())
                        .city(f.getCity())
                        .latitude(f.getLatitude())
                        .longitude(f.getLongitude())
                        .createdAt(f.getCreatedAt())
                        .createdBy(f.getCreatedBy())
                        .build()).toList();
    }

    @Override
    public FoodSpotResponse getFoodSpotById(Long id) {
     FoodSpot foodSpot=foodSpotRepository.findById(id)
             .orElseThrow(()->new ResourceNotFoundException("Food spot not found"));
     return FoodSpotResponse.builder()
             .id(foodSpot.getId())
             .name(foodSpot.getName())
             .description(foodSpot.getDescription())
             .address(foodSpot.getAddress())
             .city(foodSpot.getCity())
             .longitude(foodSpot.getLongitude())
             .latitude(foodSpot.getLatitude())
             .createdBy(foodSpot.getCreatedBy())
             .createdAt(foodSpot.getCreatedAt())
             .build();
    }
}
