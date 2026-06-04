package com.foodhunt.food_service.serviceImpl;

import com.foodhunt.food_service.dto.FoodSpotRequest;
import com.foodhunt.food_service.dto.FoodSpotResponse;
import com.foodhunt.food_service.dto.ReviewSummaryResponse;
import com.foodhunt.food_service.entity.FoodSpot;
import com.foodhunt.food_service.exception.ResourceNotFoundException;
import com.foodhunt.food_service.openFeign.ReviewClient;
import com.foodhunt.food_service.repository.FoodSpotRepository;
import com.foodhunt.food_service.service.FoodSpotService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodSpotServiceImpl implements FoodSpotService {

    private final FoodSpotRepository foodSpotRepository;
    private final ReviewClient reviewClient;

    @Override
    public FoodSpotResponse createFoodSpot(FoodSpotRequest request,String createdBy) {

        FoodSpot foodSpot=new FoodSpot();
        foodSpot.setName(request.getName());
        foodSpot.setDescription(request.getDescription());
        foodSpot.setAddress(request.getAddress());
        foodSpot.setCity(request.getCity());
        foodSpot.setLatitude(request.getLatitude());
        foodSpot.setLongitude(request.getLongitude());

        foodSpot.setCreatedBy(createdBy);
        FoodSpot savedFoodSpot=foodSpotRepository.save(foodSpot);

        return FoodSpotResponse.builder()
                .id(savedFoodSpot.getId())
                .name(savedFoodSpot.getName())
                .address(savedFoodSpot.getAddress())
                .city(savedFoodSpot.getCity())
                .description(savedFoodSpot.getDescription())
                .latitude(savedFoodSpot.getLatitude())
                .longitude(savedFoodSpot.getLongitude())
                .build();

    }

    @Override
    public Page<FoodSpotResponse> getAllFoodSpots(int page,int size,String sortBy,String direction) {

        Sort sort=direction.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();


        Pageable pageable= PageRequest.of(page,size,sort);
        Page<FoodSpot>foodSpots = foodSpotRepository.findAll(pageable);

        return foodSpots
                .map(f->FoodSpotResponse.builder()
                        .id(f.getId())
                        .name(f.getName())
                        .address(f.getAddress())
                        .description(f.getDescription())
                        .address(f.getAddress())
                        .city(f.getCity())
                        .latitude(f.getLatitude())
                        .longitude(f.getLongitude())
                        .createdAt(f.getCreatedAt())
                        .createdBy(f.getCreatedBy())
                        .build()
                );
    }

    @Override
    @Retry(name = "reviewService")
    @CircuitBreaker(name="reviewService",fallbackMethod = "reviewServiceFallback")
    public FoodSpotResponse getFoodSpotById(Long id) {
        System.out.println("Calling Review Service...");
     FoodSpot foodSpot=foodSpotRepository.findById(id)
             .orElseThrow(()->new ResourceNotFoundException("Food spot not found"));

        ReviewSummaryResponse summary =
                reviewClient.getReviewSummary(id);

        return buildFoodSpotResponse(foodSpot, summary);
    }

public FoodSpotResponse reviewServiceFallback(Long id,Throwable ex){

    System.out.println("Circuit Breaker Fallback Executed");
        FoodSpot foodSpot=foodSpotRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Food spot not found"));
        ReviewSummaryResponse summary=new ReviewSummaryResponse();
        summary.setAverageRating(0.0);
        summary.setTotalReviews(0L);
         return buildFoodSpotResponse(foodSpot, summary);
}
    private FoodSpotResponse buildFoodSpotResponse(
            FoodSpot foodSpot,
            ReviewSummaryResponse summary
    ) {
        return FoodSpotResponse.builder()
                .id(foodSpot.getId())
                .name(foodSpot.getName())
                .description(foodSpot.getDescription())
                .address(foodSpot.getAddress())
                .city(foodSpot.getCity())
                .latitude(foodSpot.getLatitude())
                .longitude(foodSpot.getLongitude())
                .createdAt(foodSpot.getCreatedAt())
                .createdBy(foodSpot.getCreatedBy())
                .averageRating(summary.getAverageRating())
                .totalReviews(summary.getTotalReviews())
                .build();
    }
    @Override
    public FoodSpotResponse updateFoodSpot(Long id, FoodSpotRequest request) {
        FoodSpot foodSpot=foodSpotRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Food spot not found"));

       foodSpot.setName(request.getName());
       foodSpot.setAddress(request.getAddress());
       foodSpot.setCity(request.getCity());
       foodSpot.setDescription(request.getDescription());
       foodSpot.setLatitude(request.getLatitude());
       foodSpot.setLongitude(request.getLongitude());
       FoodSpot updatedFoodSpot=foodSpotRepository.save(foodSpot);

       return FoodSpotResponse.builder()
               .id(updatedFoodSpot.getId())
               .name(updatedFoodSpot.getName())
               .address(updatedFoodSpot.getAddress())
               .city(updatedFoodSpot.getCity())
               .description(updatedFoodSpot.getDescription())
               .latitude(updatedFoodSpot.getLatitude())
               .longitude(updatedFoodSpot.getLongitude())
               .createdAt(updatedFoodSpot.getCreatedAt())
               .createdBy(updatedFoodSpot.getCreatedBy())
               .build();
    }

    @Override
    public void deleteFoodSpot(Long id) {
        FoodSpot foodSpot = foodSpotRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Food spot not found"));

        foodSpotRepository.delete(foodSpot);
    }

    @Override
    public Page<FoodSpotResponse> searchByCity(String city, int page, int size) {
        Pageable pageable=PageRequest.of(page,size);

        Page<FoodSpot> foodSpots=foodSpotRepository.findByCityIgnoreCase(city,pageable);
      return foodSpots.map(f->
              FoodSpotResponse.builder()
                      .id(f.getId())
                      .name(f.getName())
                      .address(f.getAddress())
                      .city(f.getCity())
                      .description(f.getDescription())
                      .latitude(f.getLatitude())
                      .longitude(f.getLongitude())
                      .createdBy(f.getCreatedBy())
                      .createdAt(f.getCreatedAt())
                      .build());


    }
}
