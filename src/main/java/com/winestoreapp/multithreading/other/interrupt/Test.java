package com.winestoreapp.multithreading.other.interrupt;

public class Test {
    private static volatile boolean isWorking = true;

    public static void main(String[] args) throws InterruptedException {
        Thread parentThread = new Thread(() -> {
            try {
                Thread.sleep(3500);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Thread childThread1 = new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            Thread childThread2 = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            try {
                childThread1.setName("1");
                childThread2.setName("2");
                childThread1.start();
                childThread2.start();
                childThread1.join();
                childThread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        parentThread.setName("parent");
        parentThread.start();
        parentThread.join();
    }

    private static void chaildTheerd1() {

    }
}
