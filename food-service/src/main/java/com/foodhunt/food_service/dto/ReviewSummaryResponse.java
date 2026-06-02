package com.foodhunt.food_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSummaryResponse {


    private Double averageRating;

    private Long totalReviews;
}
