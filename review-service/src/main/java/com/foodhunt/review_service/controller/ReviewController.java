package com.foodhunt.review_service.controller;

import com.foodhunt.review_service.dto.BatchReviewSummaryResponse;
import com.foodhunt.review_service.dto.CreateReviewRequest;
import com.foodhunt.review_service.dto.ReviewResponse;
import com.foodhunt.review_service.dto.ReviewSummaryResponse;
import com.foodhunt.review_service.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    @Autowired
    private CacheManager cacheManager;

    private final StringRedisTemplate redisTemplate;

    @GetMapping("/debug/redis-set")
    public String setRedisKey() {
        System.out.println("DEBUG: redis-set endpoint hit");
        redisTemplate.opsForValue().set("testKey", "hello");
        System.out.println("DEBUG: redis set done");
        return "saved";
    }
    @GetMapping("/cache/type")
    public String cacheType() {
        return cacheManager.getClass().getName();
    }
    @PostMapping
    public ReviewResponse createReviews(
            @Valid @RequestBody CreateReviewRequest request,
            @AuthenticationPrincipal Jwt jwt
            ){
        String userId=jwt.getSubject();
        String email=jwt.getClaimAsString("email");

        return reviewService.createReview(request,userId,email);
    }
    @GetMapping("/food/{foodSpotId}")
    public List<ReviewResponse> getReviewsByFoodSpotId(
            @PathVariable Long foodSpotId
    ) {
        return reviewService.getReviewsByFoodSpotId(foodSpotId);
    }
    @GetMapping("/food/{foodSpotId}/average-rating")
    public Double getAverageRatingByFoodSpotId(
            @PathVariable Long foodSpotId
    ) {
        return reviewService.getAverageRatingByFoodSpotId(foodSpotId);
    }

    @GetMapping("/food/{foodSpotId}/summary")
    public ReviewSummaryResponse getReviewSummary(
            @PathVariable long foodSpotId
    ){
        return reviewService.getReviewSummary(foodSpotId);
    }
    @GetMapping("/summary/batch")
    public List<BatchReviewSummaryResponse> getBatchReviewSummary(
            @RequestParam List<Long> foodSpotIds
    ) {
        return reviewService.getBatchReviewSummary(foodSpotIds);
    }
}
