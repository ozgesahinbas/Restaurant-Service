package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.RestaurantCreateRequest;
import com.example.restaurantservice.dto.RestaurantResponse;
import com.example.restaurantservice.dto.RestaurantUpdateRequest;
import com.example.restaurantservice.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(
            @Valid @RequestBody RestaurantCreateRequest request) {

        RestaurantResponse response = restaurantService.createRestaurant(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(
            @PathVariable String id) {

        RestaurantResponse response = restaurantService.getRestaurantById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants() {

        List<RestaurantResponse> responses = restaurantService.getAllRestaurants();

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(
            @PathVariable String id,
            @Valid @RequestBody RestaurantUpdateRequest request) {

        RestaurantResponse response = restaurantService.updateRestaurant(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(
            @PathVariable String id) {

        restaurantService.deleteRestaurant(id);

        return ResponseEntity.noContent().build();
    }
}
