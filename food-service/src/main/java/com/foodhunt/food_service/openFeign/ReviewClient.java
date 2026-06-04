package com.foodhunt.food_service.openFeign;

import com.foodhunt.food_service.dto.BatchReviewSummaryResponse;
import com.foodhunt.food_service.dto.ReviewSummaryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="REVIEW-SERVICE")
public interface ReviewClient {


    @GetMapping("/reviews/food/{foodSpotId}/summary")
    ReviewSummaryResponse getReviewSummary(
            @PathVariable Long foodSpotId
    );

    @GetMapping("/reviews/summary/batch")
    List<BatchReviewSummaryResponse> getBatchReviewSummary(
            @RequestParam List<Long> foodSpotIds
    );
}