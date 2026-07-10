package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.RestaurantCreateRequest;
import com.example.restaurantservice.dto.RestaurantResponse;
import com.example.restaurantservice.dto.RestaurantUpdateRequest;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse createRestaurant(RestaurantCreateRequest request);
    RestaurantResponse getRestaurantById(String id);
    List <RestaurantResponse> getAllRestaurants();
    RestaurantResponse updateRestaurant(String id, RestaurantUpdateRequest request);
    void deleteRestaurant(String id);
}
