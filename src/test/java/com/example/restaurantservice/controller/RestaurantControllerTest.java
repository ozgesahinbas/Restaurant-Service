package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.RestaurantUpdateRequest;
import com.example.restaurantservice.entity.Restaurant;
import com.example.restaurantservice.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.restaurantservice.dto.RestaurantCreateRequest;
import com.example.restaurantservice.dto.RestaurantResponse;
import com.example.restaurantservice.entity.RestaurantStatus;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantControllerTest {

    private MockMvc mockMvc;
    private RestaurantService restaurantService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        restaurantService = Mockito.mock(RestaurantService.class);

        RestaurantController restaurantController =
                new RestaurantController(restaurantService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(restaurantController)
                .build();

        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }
    @Test
    void shouldCreateRestaurantSuccessfully() throws Exception {

        RestaurantCreateRequest request = new RestaurantCreateRequest();

        request.setName("Test Restaurant");
        request.setDescription("CRUD test restaurant");
        request.setAddress("Istanbul");
        request.setPhoneNumber("05551234567");
        request.setEmail("test@restaurant.com");
        request.setOpeningTime(LocalTime.of(9, 0));
        request.setClosingTime(LocalTime.of(23, 0));
        request.setStatus(RestaurantStatus.ACTIVE);

        Restaurant restaurant = Restaurant.builder()
                .id("123")
                .name("Test Restaurant")
                .description("CRUD test restaurant")
                .address("Istanbul")
                .phoneNumber("05551234567")
                .email("test@restaurant.com")
                .openingTime(LocalTime.of(9, 0))
                .closingTime(LocalTime.of(23, 0))
                .status(RestaurantStatus.ACTIVE)
                .build();

        RestaurantResponse response = new RestaurantResponse(restaurant);

        when(restaurantService.createRestaurant(any(RestaurantCreateRequest.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/restaurants")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("Test Restaurant"));
    }
    @Test
    void shouldGetRestaurantByIdSuccessfully() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id("123")
                .name("Test Restaurant")
                .description("CRUD test restaurant")
                .address("Istanbul")
                .phoneNumber("05551234567")
                .email("test@restaurant.com")
                .openingTime(LocalTime.of(9, 0))
                .closingTime(LocalTime.of(23, 0))
                .status(RestaurantStatus.ACTIVE)
                .build();

        RestaurantResponse response = new RestaurantResponse(restaurant);

        when(restaurantService.getRestaurantById("123"))
                .thenReturn(response);

        mockMvc.perform(
                        get("/restaurants/123")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("Test Restaurant"));
    }
    @Test
    void shouldGetAllRestaurantsSuccessfully() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id("123")
                .name("Test Restaurant")
                .description("CRUD test restaurant")
                .address("Istanbul")
                .phoneNumber("05551234567")
                .email("test@restaurant.com")
                .openingTime(LocalTime.of(9, 0))
                .closingTime(LocalTime.of(23, 0))
                .status(RestaurantStatus.ACTIVE)
                .build();

        RestaurantResponse response = new RestaurantResponse(restaurant);

        when(restaurantService.getAllRestaurants())
                .thenReturn(List.of(response));

        mockMvc.perform(
                        get("/restaurants")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("123"))
                .andExpect(jsonPath("$[0].name").value("Test Restaurant"));
    }
    @Test
    void shouldUpdateRestaurantSuccessfully() throws Exception {

        RestaurantUpdateRequest request = new RestaurantUpdateRequest();
        request.setName("Updated Restaurant");
        request.setDescription("Updated Description");
        request.setAddress("Kadikoy, Istanbul");
        request.setPhoneNumber("05559876543");
        request.setEmail("updated@restaurant.com");
        request.setOpeningTime(LocalTime.of(10, 0));
        request.setClosingTime(LocalTime.of(22, 0));
        request.setStatus(RestaurantStatus.ACTIVE);

        Restaurant restaurant = Restaurant.builder()
                .id("123")
                .name("Updated Restaurant")
                .description("Updated Description")
                .address("Kadikoy, Istanbul")
                .phoneNumber("05559876543")
                .email("updated@restaurant.com")
                .openingTime(LocalTime.of(10, 0))
                .closingTime(LocalTime.of(22, 0))
                .status(RestaurantStatus.ACTIVE)
                .build();

        RestaurantResponse response = new RestaurantResponse(restaurant);

        when(restaurantService.updateRestaurant(
                any(String.class),
                any(RestaurantUpdateRequest.class)
        )).thenReturn(response);

        mockMvc.perform(
                        put("/restaurants/123")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("Updated Restaurant"));
    }
    @Test
    void shouldDeleteRestaurantSuccessfully() throws Exception {

        mockMvc.perform(
                        delete("/restaurants/123")
                )
                .andExpect(status().isNoContent());

        verify(restaurantService).deleteRestaurant("123");
    }
    @Test
    void shouldReturnBadRequestWhenRestaurantNameIsEmpty() throws Exception {

        RestaurantCreateRequest request = new RestaurantCreateRequest();

        request.setName("");
        request.setDescription("Test Description");
        request.setAddress("Istanbul");
        request.setPhoneNumber("05551234567");
        request.setEmail("test@restaurant.com");
        request.setOpeningTime(LocalTime.of(9, 0));
        request.setClosingTime(LocalTime.of(23, 0));
        request.setStatus(RestaurantStatus.ACTIVE);

        mockMvc.perform(
                        post("/restaurants")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());

        verify(restaurantService, never())
                .createRestaurant(any(RestaurantCreateRequest.class));
    }
    @Test
    void shouldReturnBadRequestWhenEmailIsInvalid() throws Exception {

        RestaurantCreateRequest request = new RestaurantCreateRequest();

        request.setName("Test Restaurant");
        request.setDescription("Test Description");
        request.setAddress("Istanbul");
        request.setPhoneNumber("05551234567");
        request.setEmail("invalid-email");
        request.setOpeningTime(LocalTime.of(9, 0));
        request.setClosingTime(LocalTime.of(23, 0));
        request.setStatus(RestaurantStatus.ACTIVE);

        mockMvc.perform(
                        post("/restaurants")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());

        verify(restaurantService, never())
                .createRestaurant(any(RestaurantCreateRequest.class));
    }
}
