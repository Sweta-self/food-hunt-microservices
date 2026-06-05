package com.foodhunt.food_service.serviceImpl;

import com.foodhunt.food_service.dto.BatchReviewSummaryResponse;
import com.foodhunt.food_service.openFeign.ReviewClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewSummaryCacheService {

    private final ReviewClient reviewClient;

    @Cacheable(value = "reviewSummary", key = "#foodSpotIds")
    public List<BatchReviewSummaryResponse>getBatchReviewSummary(List<Long> foodSpotIds){
        System.out.println("Calling Review Service batch API...");
        return reviewClient.getBatchReviewSummary(foodSpotIds);
    }
}
