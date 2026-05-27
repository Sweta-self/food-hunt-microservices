package com.foodhunt.food_service.controller;

import com.foodhunt.food_service.dto.FoodSpotRequest;
import com.foodhunt.food_service.dto.FoodSpotResponse;
import com.foodhunt.food_service.service.FoodSpotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<FoodSpotResponse> getAllFoodSpotsController(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc")
            String direction
    ){
        return foodSpotService.getAllFoodSpots(page,size,sortBy,direction);
    }

    @GetMapping("/{id}")
    public FoodSpotResponse getFoodSpotByIdController(@PathVariable Long id){
        return foodSpotService.getFoodSpotById(id);
    }

    @PutMapping("/{id}")
    public FoodSpotResponse updatedFoodSpot(@PathVariable Long id,
                                            @Valid @RequestBody FoodSpotRequest request){
        return foodSpotService.updateFoodSpot(id,request);
    }
    @DeleteMapping("/{id}")
    public String deleteFoodSpot(@PathVariable Long id) {

        foodSpotService.deleteFoodSpot(id);

        return "Food spot deleted successfully";
    }
    @GetMapping("/search")
    public Page<FoodSpotResponse> searchByCity(
            @RequestParam String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        return foodSpotService.searchByCity(
                city,
                page,
                size
        );
    }

}
