package com.example.restaurantservice.dto;

import com.example.restaurantservice.entity.Restaurant;
import com.example.restaurantservice.entity.RestaurantStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Data
public class RestaurantCreateRequest {

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

    public Restaurant convertEntity() {
        return Restaurant.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .description(description)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .openingTime(openingTime)
                .closingTime(closingTime)
                .status(status)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public Optional<Restaurant> convertEntityAsOpt() {
        return Optional.of(convertEntity());
    }


}