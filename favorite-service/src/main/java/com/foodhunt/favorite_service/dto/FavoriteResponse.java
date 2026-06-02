package com.foodhunt.favorite_service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FavoriteResponse {

    private Long id;

    private Long foodSpotId;

    private String userId;

    private String userEmail;

    private LocalDateTime createdAt;

    private String foodSpotName;

    private String foodSpotCity;

    private String foodSpotAddress;
}