package com.winestoreapp.multithreading.other;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionExample {
//    private int counter = 0;
private AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        RaceConditionExample example = new RaceConditionExample();
        example.runTest();
    }

    public void runTest() {
        Thread thread1 = new Thread(this::increment);
        Thread thread2 = new Thread(() -> {
            increment();
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final counter value: " + counter);
    }

//    private synchronized void increment() { //fixed
    private void increment() {
        for (int i = 0; i < 1000000; i++) {
//            counter++;
            counter.incrementAndGet();
        }
    }
}