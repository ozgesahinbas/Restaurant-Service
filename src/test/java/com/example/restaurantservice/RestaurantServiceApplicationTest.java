package com.example.restaurantservice;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.mockito.Mockito.mockStatic;

public class RestaurantServiceApplicationTest {
    @Test
    void shouldRunApplication(){
        try (MockedStatic<SpringApplication> mockedSpringApplication =
                mockStatic(SpringApplication.class)){
            String[] args = {};
            RestaurantServiceApplication.main(args);

            mockedSpringApplication.verify(() ->
                    SpringApplication.run(
                            RestaurantServiceApplication.class, args
                    )
            );
        }
    }
}
