package com.foodhunt.favorite_service.repository;

import com.foodhunt.favorite_service.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository
        extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUserId(String userId);

    Optional<Favorite> findByUserIdAndFoodSpotId(
            String userId,
            Long foodSpotId
    );
}