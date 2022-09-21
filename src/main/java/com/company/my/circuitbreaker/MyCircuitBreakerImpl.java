package com.company.my.circuitbreaker;

public abstract class MyCircuitBreakerImpl<T, K>  {
    private int total;

    private int failure;

    private double threshold;

    private int WINDOW_SIZE = 10;

    private boolean isCircuitOpen = false;

    MyCircuitBreakerImpl(double threshold){
        this.threshold = threshold;
    }

    K run(T request){
        isCircuitOpen();

        if(isCircuitOpen){
            System.out.println("Circuit Open. So Returning default response");
            return null;
        }

        try {
            K response = start(request);
            return response;
        } catch (Exception e){
            System.out.println("Exception occured. Increasing failure count");
            failure = failure + 1;
        }
        return null;
    }

    private void isCircuitOpen() {
        total = total + 1;
        if(total == 10){
            double failurePercent = failure / total * 100;
            if(failurePercent > threshold){
                System.out.println("Circuit Open");
                isCircuitOpen = true;
            }
            total = 0;
            failure = 0;
        }
    }

    abstract K start(T request);


}