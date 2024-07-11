package com.winestoreapp.multithreading.other.interrupt;

public class Test {
    private static volatile boolean isWorking = true;

    public static void main(String[] args) throws InterruptedException {
        Thread workThread = new Thread(() -> {
            while (isWorking) {
                try {
                    System.out.println("workThread have to work 5 sec");
                    Thread.sleep(200);
                    System.out.println("workThread stop work after 5 sec");
                } catch (InterruptedException e) {
                    System.out.println("В кетч. Встановлення статусу переривання, якщо потік пробуджується від сну\n");
                    Thread.currentThread().interrupt();
                }
            }
        });
        workThread.start();
        Thread.sleep(1000);
        isWorking = false;

//        workThread.interrupt();

        workThread.join();
        System.out.println("Stop main thread after 1 sec ");
    }
}
