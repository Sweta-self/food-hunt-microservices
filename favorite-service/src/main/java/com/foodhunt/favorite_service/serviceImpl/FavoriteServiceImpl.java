package com.foodhunt.favorite_service.serviceImpl;

import com.foodhunt.favorite_service.dto.FavoriteResponse;
import com.foodhunt.favorite_service.entity.Favorite;
import com.foodhunt.favorite_service.exception.FavoriteAlreadyExistsException;
import com.foodhunt.favorite_service.exception.ResourceNotFoundException;
import com.foodhunt.favorite_service.repository.FavoriteRepository;
import com.foodhunt.favorite_service.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Override
    public FavoriteResponse addFavorite(
            Long foodSpotId,
            String userId,
            String userEmail
    ) {

        favoriteRepository.findByUserIdAndFoodSpotId(userId, foodSpotId)
                .ifPresent(favorite -> {
                    throw new FavoriteAlreadyExistsException("Food spot already added to favorites");
                });

        Favorite favorite = new Favorite();
        favorite.setFoodSpotId(foodSpotId);
        favorite.setUserId(userId);
        favorite.setUserEmail(userEmail);

        Favorite savedFavorite = favoriteRepository.save(favorite);

        return mapToResponse(savedFavorite);
    }

    @Override
    public List<FavoriteResponse> getMyFavorites(String userId) {

        return favoriteRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void removeFavorite(Long foodSpotId, String userId) {

        Favorite favorite = favoriteRepository
                .findByUserIdAndFoodSpotId(userId, foodSpotId)
                .orElseThrow(() ->  new ResourceNotFoundException("Favorite not found"));

        favoriteRepository.delete(favorite);
    }

    private FavoriteResponse mapToResponse(Favorite favorite) {

        return FavoriteResponse.builder()
                .id(favorite.getId())
                .foodSpotId(favorite.getFoodSpotId())
                .userId(favorite.getUserId())
                .userEmail(favorite.getUserEmail())
                .createdAt(favorite.getCreatedAt())
                .build();
    }
}