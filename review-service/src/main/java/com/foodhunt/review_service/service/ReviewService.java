package com.foodhunt.review_service.service;

import com.foodhunt.review_service.dto.CreateReviewRequest;
import com.foodhunt.review_service.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {

    ReviewResponse createReview(CreateReviewRequest request,
                                String userId,
                                String email);
    List<ReviewResponse> getReviewsByFoodSpotId(Long foodSpotId);
    Double getAverageRatingByFoodSpotId(Long foodSpotId);
}
