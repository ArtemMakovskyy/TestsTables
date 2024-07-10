package com.winestoreapp.multithreading.other.interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // Виконуємо завдання
                System.out.println("Потік працює");
                try {
                    Thread.sleep(100); // Імітація роботи
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread.sleep(500); // Дамо потоку попрацювати 5 секунд
        executor.shutdownNow(); // Запитуємо зупинку потоку
        executor.awaitTermination(5, TimeUnit.SECONDS); // Чекаємо завершення
        System.out.println("Потік зупинено");
    }
}