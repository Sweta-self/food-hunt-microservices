package com.foodhunt.food_service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FoodSpotResponse {

    private Long id;

    private String name;

    private String description;

    private String address;

    private String city;

    private Double latitude;

    private Double longitude;

    private LocalDateTime createdAt;

    private String createdBy;
}