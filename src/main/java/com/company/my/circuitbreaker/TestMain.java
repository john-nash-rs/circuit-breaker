package com.company.my.circuitbreaker;
public class TestMain {
    public static double THRESHOLD = 10;
    public static void main(String[] args){
        MyCircuitBreakerFailureImpl myCircuitBreakerFailure = new MyCircuitBreakerFailureImpl(THRESHOLD);
        for(int i = 1; i < 100; i++){
            myCircuitBreakerFailure.run(i);
        }

    }
}

