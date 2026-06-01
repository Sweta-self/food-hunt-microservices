package com.foodhunt.favorite_service.service;

import com.foodhunt.favorite_service.dto.FavoriteResponse;

import java.util.List;

public interface FavoriteService {

    FavoriteResponse addFavorite(
            Long foodSpotId,
            String userId,
            String userEmail
    );

    List<FavoriteResponse> getMyFavorites(String userId);

    void removeFavorite(Long foodSpotId, String userId);
}