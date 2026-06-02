package com.foodhunt.review_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewSummaryResponse {

    private Double averageRating;
    private Long totalReviews;

}
