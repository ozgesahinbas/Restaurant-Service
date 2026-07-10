package com.example.restaurantservice.dto;

import com.example.restaurantservice.entity.Restaurant;
import com.example.restaurantservice.entity.RestaurantStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class RestaurantUpdateRequest {

    @NotBlank(message = "Restaurant name can't be empty")
    private String name;

    private String description;

    @NotBlank(message = "Address can't be empty")
    private String address;

    @NotBlank(message = "Phone number can't be empty")
    private String phoneNumber;

    @Email(message = "Invalid email format")
    private String email;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private RestaurantStatus status;

    public Restaurant updateEntity(Restaurant restaurant) {

        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setAddress(address);
        restaurant.setPhoneNumber(phoneNumber);
        restaurant.setEmail(email);
        restaurant.setOpeningTime(openingTime);
        restaurant.setClosingTime(closingTime);
        restaurant.setStatus(status);
        restaurant.setUpdatedAt(LocalDateTime.now());

        return restaurant;
    }
}