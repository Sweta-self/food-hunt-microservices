package com.foodhunt.review_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ReviewSummaryResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double averageRating;
    private Long totalReviews;

}
