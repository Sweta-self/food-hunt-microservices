package com.foodhunt.food_service.repository;

import com.foodhunt.food_service.entity.FoodSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodSpotRepository extends JpaRepository<FoodSpot,Long> {

    Page<FoodSpot> findByCityIgnoreCase(String city,  Pageable pageable);
}
