package com.example.konstantin.gateopener;

/**
 * Created by Konstantin on 17.10.2015.
 */
public class Timer {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
