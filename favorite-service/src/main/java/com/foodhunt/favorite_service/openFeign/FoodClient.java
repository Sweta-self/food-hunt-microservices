package com.foodhunt.favorite_service.openFeign;
import com.foodhunt.favorite_service.dto.FoodResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FOOD-SERVICE")
public interface FoodClient {

    @GetMapping("/foods/{id}")
    FoodResponse getFoodById(
            @PathVariable Long id
    );
}
