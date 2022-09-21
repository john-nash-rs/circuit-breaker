package com.company;

public class Main {

    static DemoCircuitBreaker demoCircuitBreaker = new DemoCircuitBreaker();
    static DemoFailureCircuitBreaker demoFailureCircuitBreaker = new DemoFailureCircuitBreaker();

    public static void main(String[] args) throws Exception {
	// write your code here
        System.out.println("Hello World");
         demoCircuitBreaker.demo();
         //demoFailureCircuitBreaker.demo();
    }


}
