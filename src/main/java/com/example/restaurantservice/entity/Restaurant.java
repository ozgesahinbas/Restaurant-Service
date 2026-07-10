package com.example.restaurantservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document

public class Restaurant {
    @Id
    private String id;
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private RestaurantStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
