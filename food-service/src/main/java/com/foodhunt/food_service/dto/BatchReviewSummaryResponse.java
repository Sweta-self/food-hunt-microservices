package com.foodhunt.food_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchReviewSummaryResponse {


    private Long foodSpotId;

    private Double averageRating;

    private Long totalReviews;
}
