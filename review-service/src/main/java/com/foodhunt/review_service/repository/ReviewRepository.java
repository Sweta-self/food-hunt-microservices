package com.foodhunt.review_service.repository;

import com.foodhunt.review_service.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review>findByFoodSpotId(Long foodSpotId);
}
