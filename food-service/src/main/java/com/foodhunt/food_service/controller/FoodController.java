package com.foodhunt.food_service.controller;

import com.foodhunt.food_service.dto.FoodSpotRequest;
import com.foodhunt.food_service.dto.FoodSpotResponse;
import com.foodhunt.food_service.service.FoodSpotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodSpotService foodSpotService;
    @GetMapping("/test")
    public String test(){
        return "Food Service Working";
    }

    @PostMapping("/createfoodspot")
    public FoodSpotResponse createFoodSpotController( @Valid @RequestBody FoodSpotRequest request){
        return foodSpotService.createFoodSpot(request);
    }

    @GetMapping("/getAll")
    public List<FoodSpotResponse>getAllFoodSpotsController(){
        return foodSpotService.getAllFoodSpots();
    }

    @GetMapping("/{id}")
    public FoodSpotResponse getFoodSpotByIdController(@PathVariable Long id){
        return foodSpotService.getFoodSpotById(id);
    }

}
