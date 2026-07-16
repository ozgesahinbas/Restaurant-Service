package com.example.restaurantservice.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void shouldHandleRestaurantNotFoundException() {

        RestaurantNotFoundException exception =
                new RestaurantNotFoundException("123");

        ResponseEntity<String> response =
                globalExceptionHandler.handleRestaurantNotFound(exception);

        assertEquals(404, response.getStatusCode().value());
        assertEquals("Restaurant not found with this id: 123",
                response.getBody()
        );
    }
    @Test
    void shouldHandleGenericException(){
        Exception exception = new Exception("Something went wrong");

        ResponseEntity<String> response = globalExceptionHandler.handleGenericException(exception);

        assertEquals(500, response.getStatusCode().value());
        assertEquals("An unexpected error occurred",
                response.getBody()
        );
    }
    @Test
    void shouldHandleValidationException(){
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);

        BindingResult bindingResult = mock(BindingResult.class);

        FieldError fieldError = new FieldError(
                "restaurantCreateRequest", "name", "Restaurant name can't be empty"
        );
        when(exception.getBindingResult())
                .thenReturn(bindingResult);

        when(bindingResult.getFieldErrors())
                .thenReturn(List.of(fieldError));
        ResponseEntity<Map<String, String>> response =
                globalExceptionHandler.handleValidationException(exception);

        assertEquals(400, response.getStatusCode().value());
        assertEquals(
                "Restaurant name can't be empty",
                response.getBody().get("name"));
    }
}