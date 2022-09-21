package com.company;
public class ExternalCallFailureServiceImpl implements  ExternalCallService{

    @Override
    public String play()  {
        try {
            Thread.sleep(1000);
            System.out.println(" *****  I am failed method. I am called here. ********");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Ooops! Something went wrong");
    }
}