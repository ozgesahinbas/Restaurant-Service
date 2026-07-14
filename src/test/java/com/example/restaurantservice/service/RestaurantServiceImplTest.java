package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.RestaurantCreateRequest;
import com.example.restaurantservice.dto.RestaurantUpdateRequest;
import com.example.restaurantservice.entity.Restaurant;
import com.example.restaurantservice.entity.RestaurantStatus;
import com.example.restaurantservice.exception.RestaurantNotFoundException;
import com.example.restaurantservice.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.restaurantservice.dto.RestaurantResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.restaurantservice.dto.RestaurantUpdateRequest;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    private Restaurant restaurant;
    private RestaurantCreateRequest createRequest;

    @BeforeEach
    void setUp() {

        restaurant = Restaurant.builder()
                .id("123")
                .name("Test Restaurant")
                .description("Test Description")
                .address("Istanbul")
                .phoneNumber("05551234567")
                .email("test@restaurant.com")
                .openingTime(LocalTime.of(9, 0))
                .closingTime(LocalTime.of(23, 0))
                .status(RestaurantStatus.ACTIVE)
                .build();

        createRequest = new RestaurantCreateRequest();
        createRequest.setName("Test Restaurant");
        createRequest.setDescription("Test Description");
        createRequest.setAddress("Istanbul");
        createRequest.setPhoneNumber("05551234567");
        createRequest.setEmail("test@restaurant.com");
        createRequest.setOpeningTime(LocalTime.of(9, 0));
        createRequest.setClosingTime(LocalTime.of(23, 0));
        createRequest.setStatus(RestaurantStatus.ACTIVE);
    }
    @Test
    void shouldCreateRestaurantSuccessfully() {
        when(repository.save(any(Restaurant.class)))
                .thenReturn(restaurant);
        RestaurantResponse response =
                restaurantService.createRestaurant(createRequest);

        assertEquals("123", response.getId());
        assertEquals("Test Restaurant", response.getName());
        verify(repository).save(any(Restaurant.class));
    }
    @Test
    void shouldGetRestaurantByIdSuccessfully() {
        when(repository.findById("123"))
                .thenReturn(Optional.of(restaurant));

        RestaurantResponse response =
                restaurantService.getRestaurantById("123");

        assertEquals("123", response.getId());
        assertEquals("Test Restaurant", response.getName());

        verify(repository).findById("123");
    }
    @Test
    void shouldThrowExceptionWhenRestaurantNotFoundById() {
        when(repository.findById("123"))
                .thenReturn(Optional.empty());

        assertThrows(
                RestaurantNotFoundException.class,
                () -> restaurantService.getRestaurantById("123")
        );
        verify(repository).findById("123");
    }
    @Test
    void shouldGetAllRestaurantSuccessfully(){
       when(repository.findAll())
               .thenReturn(List.of(restaurant));

       List<RestaurantResponse> responses =
               restaurantService.getAllRestaurants();

       assertEquals(1, responses.size());
       assertEquals("123", responses.get(0).getId());
       assertEquals("Test Restaurant", responses.get(0).getName());
       verify(repository).findAll();
    }
    @Test
    void shouldUpdateRestaurantSuccessfully() {

        RestaurantUpdateRequest updateRequest = new RestaurantUpdateRequest();
        updateRequest.setName("Updated Restaurant");
        updateRequest.setDescription("Updated Description");
        updateRequest.setAddress("Kadikoy, Istanbul");
        updateRequest.setPhoneNumber("05559876543");
        updateRequest.setEmail("updated@restaurant.com");
        updateRequest.setOpeningTime(LocalTime.of(10, 0));
        updateRequest.setClosingTime(LocalTime.of(22, 0));
        updateRequest.setStatus(RestaurantStatus.ACTIVE);

        when(repository.findById("123"))
                .thenReturn(Optional.of(restaurant));

        when(repository.save(any(Restaurant.class)))
                .thenReturn(restaurant);

        RestaurantResponse response =
                restaurantService.updateRestaurant("123", updateRequest);

        assertEquals("Updated Restaurant", response.getName());
        verify(repository).findById("123");
        verify(repository).save(any(Restaurant.class));
    }
    @Test
    void shouldThrowExceptionWhenUpdatingRestaurantNotFound() {
        RestaurantUpdateRequest updateRequest = new RestaurantUpdateRequest();

        when(repository.findById("123"))
                .thenReturn(Optional.empty());

        assertThrows(
                RestaurantNotFoundException.class,
                () -> restaurantService.updateRestaurant("123", updateRequest)
        );
        verify(repository).findById("123");
        verify(repository, never()).save(any(Restaurant.class));
    }
    @Test
    void shouldDeleteRestaurantSuccessfully() {

        when(repository.existsById("123"))
                .thenReturn(true);

        restaurantService.deleteRestaurant("123");

        verify(repository).existsById("123");
        verify(repository).deleteById("123");
    }
    @Test
    void shouldThrowExceptionWhenDeletingRestaurantNotFound() {

        when(repository.existsById("123"))
                .thenReturn(false);

        assertThrows(
                RestaurantNotFoundException.class,
                () -> restaurantService.deleteRestaurant("123")
        );

        verify(repository).existsById("123");
        verify(repository, never()).deleteById("123");
    }
    }
