package com.foodhunt.review_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewSummaryResponse {

    private Double averageRating;
    private Long totalReviews;

}
