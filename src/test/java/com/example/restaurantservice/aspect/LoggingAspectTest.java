package com.example.restaurantservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoggingAspectTest {
    private LoggingAspect loggingAspect;
    private JoinPoint joinPoint;
    private ProceedingJoinPoint proceedingJoinPoint;
    private Signature signature;

    @BeforeEach
    void setUp(){
        loggingAspect = new LoggingAspect();
        joinPoint = mock(JoinPoint.class);
        proceedingJoinPoint = mock(ProceedingJoinPoint.class);
        signature = mock(Signature.class);

    }
    @Test
    void shouldLogRequestSuccessfully() {

        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("testMethod");
        when(joinPoint.getArgs()).thenReturn(new Object[]{"testArgument"});

        loggingAspect.logRequest(joinPoint);

        verify(joinPoint).getSignature();
        verify(joinPoint).getArgs();
        verify(signature).getName();
    }
    @Test
    void shouldLogExecutionTimeSuccessfully() throws Throwable {

        when(proceedingJoinPoint.getArgs())
                .thenReturn(new Object[]{"testArgument"});

        when(proceedingJoinPoint.proceed(any(Object[].class)))
                .thenReturn("testResult");

        when(proceedingJoinPoint.getSignature())
                .thenReturn(signature);

        when(signature.getName())
                .thenReturn("testMethod");

        Object result =
                loggingAspect.logExecutionTime(proceedingJoinPoint);

        assertEquals("testResult", result);

        verify(proceedingJoinPoint)
                .proceed(any(Object[].class));

        verify(proceedingJoinPoint)
                .getSignature();

        verify(signature)
                .getName();
    }
    @Test
    void shouldLogExecutionTimeWhenArgsAreNull() throws Throwable {

        when(proceedingJoinPoint.getArgs())
                .thenReturn(null);

        when(proceedingJoinPoint.proceed())
                .thenReturn("testResult");

        when(proceedingJoinPoint.getSignature())
                .thenReturn(signature);

        when(signature.getName())
                .thenReturn("testMethod");

        Object result =
                loggingAspect.logExecutionTime(proceedingJoinPoint);

        assertEquals("testResult", result);

        verify(proceedingJoinPoint).proceed();
        verify(proceedingJoinPoint).getSignature();
        verify(signature).getName();
    }
    @Test
    void shouldLogExceptionSuccessfully() {

        RuntimeException exception =
                new RuntimeException("Test exception");

        when(joinPoint.getSignature())
                .thenReturn(signature);

        when(signature.getName())
                .thenReturn("testMethod");

        loggingAspect.logException(joinPoint, exception);

        verify(joinPoint).getSignature();
        verify(signature).getName();
    }
}
