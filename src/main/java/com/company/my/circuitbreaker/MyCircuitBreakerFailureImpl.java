package com.company.my.circuitbreaker;

public class MyCircuitBreakerFailureImpl extends MyCircuitBreakerImpl<Integer, String> {


    MyCircuitBreakerFailureImpl(double threshold) {
        super(threshold);
    }

    @Override
    String start(Integer request) {
        if(request < 10) {
            System.out.println("****** I am success request ******");
            return "pong";
        }
        try {
            Thread.sleep(1000);
            System.out.println(" *****  I am failed method. I am called here. ********");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Ooops! Something went wrong");
    }
}