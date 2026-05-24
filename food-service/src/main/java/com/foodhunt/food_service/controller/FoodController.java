package com.foodhunt.food_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
public class FoodController {

    @GetMapping("/test")
    public String test(){
        return "Food Service Working";
    }
}
