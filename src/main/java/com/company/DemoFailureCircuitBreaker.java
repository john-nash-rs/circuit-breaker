package com.company;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;

public class DemoFailureCircuitBreaker {

    private static ExternalCallService externalCallService = new ExternalCallFailureServiceImpl();

    public static void demo() throws Exception{
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .slidingWindowSize(5)
                .permittedNumberOfCallsInHalfOpenState(2)
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .build();
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("example-failure-circuit-breaker");
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        Supplier<String> decorated = circuitBreaker.decorateSupplier(externalCallService::play);
        for(int i = 0; i < 9; i++) {
            try {
                String response = decorated.get();
                System.out.println(i + ". Response: " + response);
            } catch (Exception e){
                System.out.println(i + ". Exception occured");
            }
        }
        System.out.println(" Buffered Call:: "+metrics.getNumberOfBufferedCalls());
        System.out.println(" Failed Call:: "+metrics.getNumberOfFailedCalls());
        System.out.println(" Number of not permitted Call:: "+metrics.getNumberOfNotPermittedCalls());
    }
}