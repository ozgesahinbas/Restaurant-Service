package com.example.restaurantservice.repository;

import com.example.restaurantservice.entity.Restaurant;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends CouchbaseRepository<Restaurant, String> {

}