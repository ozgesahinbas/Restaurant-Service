package com.example.restaurantservice.dto;

import com.example.restaurantservice.entity.Restaurant;
import com.example.restaurantservice.entity.RestaurantStatus;
import com.example.restaurantservice.exception.RestaurantNotFoundException;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class RestaurantResponse {
    private String id;
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private RestaurantStatus status;

    public RestaurantResponse(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.description = restaurant.getDescription();
        this.address = restaurant.getAddress();
        this.phoneNumber = restaurant.getPhoneNumber();
        this.email = restaurant.getEmail();
        this.openingTime = restaurant.getOpeningTime();
        this.closingTime = restaurant.getClosingTime();
        this.status = restaurant.getStatus();
    }

}