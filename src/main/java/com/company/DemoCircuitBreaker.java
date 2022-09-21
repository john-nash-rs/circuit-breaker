package com.company;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.util.function.Function;
import java.util.function.Supplier;

public class DemoCircuitBreaker {

    private static ExternalCallService externalCallService = new ExternalCallServiceImpl();

    public static void demo() throws Exception{
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();
        CircuitBreaker circuitBreaker = registry.circuitBreaker("example-success-circuit-breaker");
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        Supplier<String> decorated = circuitBreaker.decorateSupplier(externalCallService::play);
        for(int i = 0; i < 100; i++) {
            try {
                String response = decorated.get();
                System.out.println(i + ". Response: " + response);
            } catch (Exception e){
                System.out.println(i + ". Exception occured");
            }
        }
        System.out.println(" Buffered Call:: "+metrics.getNumberOfBufferedCalls());
        System.out.println(" Failed Call:: "+metrics.getNumberOfFailedCalls());
    }
}