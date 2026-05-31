package com.foodhunt.review_service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ReviewResponse {

    private Long id;
    private Long foodSpotId;
    private String userId;
    private String userEmail;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

}
