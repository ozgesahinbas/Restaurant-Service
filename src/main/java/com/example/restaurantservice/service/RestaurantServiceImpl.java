package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.RestaurantCreateRequest;
import com.example.restaurantservice.dto.RestaurantResponse;
import com.example.restaurantservice.dto.RestaurantUpdateRequest;
import com.example.restaurantservice.entity.Restaurant;
import com.example.restaurantservice.exception.RestaurantNotFoundException;
import com.example.restaurantservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Override
    public RestaurantResponse createRestaurant(RestaurantCreateRequest request) {
        return request.convertEntityAsOpt()
                .map(repository::save)
                .map(RestaurantResponse::new)
                .orElseThrow(() -> new RuntimeException("Service is faced exception while creating restaurant."));
    }

    @Override
    public RestaurantResponse getRestaurantById(String id) {
        return repository.findById(id).map(RestaurantResponse::new)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {

        return repository.findAll().stream().map(RestaurantResponse::new).toList();
    }

    @Override
    public RestaurantResponse updateRestaurant(String id, RestaurantUpdateRequest request) {
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        Restaurant updatedRestaurant = request.updateEntity(restaurant);
        updatedRestaurant = repository.save(updatedRestaurant);

        return new RestaurantResponse(updatedRestaurant);
    }

    @Override
    public void deleteRestaurant(String id) {
        if (!repository.existsById(id)) {
            throw new RestaurantNotFoundException(id);
        }
        repository.deleteById(id);
    }
}