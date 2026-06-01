package com.foodhunt.favorite_service.controller;

import com.foodhunt.favorite_service.dto.FavoriteResponse;
import com.foodhunt.favorite_service.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{foodSpotId}")
    public FavoriteResponse addFavorite(
            @PathVariable Long foodSpotId,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getSubject();
        String userEmail = jwt.getClaimAsString("email");

        return favoriteService.addFavorite(foodSpotId, userId, userEmail);
    }

    @GetMapping("/my")
    public List<FavoriteResponse> getMyFavorites(
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getSubject();

        return favoriteService.getMyFavorites(userId);
    }

    @DeleteMapping("/{foodSpotId}")
    public String removeFavorite(
            @PathVariable Long foodSpotId,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getSubject();

        favoriteService.removeFavorite(foodSpotId, userId);

        return "Favorite removed successfully";
    }
}