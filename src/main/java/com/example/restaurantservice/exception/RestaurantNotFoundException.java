package com.example.restaurantservice.exception;

public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(String id){
        super("Restaurant not found with this id: " + id);
    }
}
