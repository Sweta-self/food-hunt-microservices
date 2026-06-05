package com.foodhunt.review_service.serviceImpl;

import com.foodhunt.review_service.dto.BatchReviewSummaryResponse;
import com.foodhunt.review_service.dto.CreateReviewRequest;
import com.foodhunt.review_service.dto.ReviewResponse;
import com.foodhunt.review_service.dto.ReviewSummaryResponse;
import com.foodhunt.review_service.entity.Review;
import com.foodhunt.review_service.repository.ReviewRepository;
import com.foodhunt.review_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    @Override
    public ReviewResponse createReview(CreateReviewRequest request, String userId, String email) {
      Review review= new Review();
      review.setFoodSpotId(request.getFoodSpotId());
      review.setRating(request.getRating());
      review.setComment(request.getComment());
      review.setUserId(userId);
      review.setUserEmail(email);
      Review savedReview= reviewRepository.save(review);
      return ReviewResponse.builder()
              .id(savedReview.getId())
              .foodSpotId(savedReview.getFoodSpotId())
              .rating(savedReview.getRating())
              .comment(savedReview.getComment())
              .userId(savedReview.getUserId())
              .userEmail(savedReview.getUserEmail())
              .createdAt(savedReview.getCreatedAt())
              .build();
    }

        @Override
        public List<ReviewResponse> getReviewsByFoodSpotId(Long foodSpotId) {

            List<Review> reviews = reviewRepository.findByFoodSpotId(foodSpotId);

            return reviews.stream()
                    .map(review -> ReviewResponse.builder()
                            .id(review.getId())
                            .foodSpotId(review.getFoodSpotId())
                            .userId(review.getUserId())
                            .userEmail(review.getUserEmail())
                            .rating(review.getRating())
                            .comment(review.getComment())
                            .createdAt(review.getCreatedAt())
                            .build())
                    .toList();

       }

    @Override
    public Double getAverageRatingByFoodSpotId(Long foodSpotId) {
        List<Review> reviews = reviewRepository.findByFoodSpotId(foodSpotId);

        if (reviews.isEmpty()) {
            return 0.0;
        }
        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    public ReviewSummaryResponse getReviewSummary(Long foodSpotId) {
        List<Review> reviews = reviewRepository.findByFoodSpotId(foodSpotId);

        if (reviews.isEmpty()) {
            return new ReviewSummaryResponse(0.0, 0L);
        }

        double averageRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

       return  new ReviewSummaryResponse(
                averageRating,
                (long) reviews.size());

    }

    @Override
    public List<BatchReviewSummaryResponse> getBatchReviewSummary(List<Long> foodSpotIds) {

        List<Review> reviews =
                reviewRepository.findByFoodSpotIdIn(foodSpotIds);

        Map<Long,List<Review>> reviewMap=reviews.stream()
                .collect(Collectors.groupingBy(
                        Review::getFoodSpotId
                ));



        return foodSpotIds.stream()
                .map(
                id->{
                   List<Review>foodSpotReviews=reviewMap.getOrDefault(
                           id, Collections.emptyList()
                   );
                    if(foodSpotReviews.isEmpty()){
                        return new BatchReviewSummaryResponse(
                                id,
                                0.0,
                                0L
                        );
                    }
                    double averageRating=foodSpotReviews.stream()
                            .mapToInt(Review::getRating)
                            .average()
                            .orElse(0.0);

                    return new BatchReviewSummaryResponse(
                            id,
                            averageRating,
                            (long)foodSpotReviews.size()
                    );
                }).toList();

    }

}
