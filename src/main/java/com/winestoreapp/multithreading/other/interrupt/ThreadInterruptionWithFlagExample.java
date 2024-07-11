package com.winestoreapp.multithreading.other.interrupt;

import lombok.SneakyThrows;

public class ThreadInterruptionWithFlagExample {

    private static volatile boolean isInterrupted = false;

    public static void main(String[] args) throws InterruptedException {
        // Створення потоку
        Thread mainThread = new Thread(() -> {
            try {
                System.out.println("1 Потік починає роботу");
                // Імітація роботи протягом 10 секунд
                Thread.sleep(10_000);
                System.out.println("2 Потік завершив роботу");
            } catch (InterruptedException e) {
                if (isInterrupted) {
                    System.out.println("3 Потік mainThread      був перерваний через встановлений прапор");
                } else {
                    System.out.println("4 mainThread Потік був перерваний під час сну");
                }
            }
        });

        // Створення потоку з лічильником
        Thread counterThread = new Thread(() -> {
            int counter = 0;
            while (!isInterrupted) {
                System.out.println("     >>> Лічильник: " + counter++);
                try {
                    Thread.sleep(250); // Імітація роботи з перевіркою прапора кожні пів секунди
//                    Thread.currentThread().interrupt();
                } catch (InterruptedException e) {
                    if (isInterrupted) {
                        System.out.println("3 Потік counterThread   був перерваний через встановлений прапор");
                    } else {
                        System.out.println("4 counterThread Потік був перерваний під час сну");
                    }
                }
            }
            System.out.println("     >>> Лічильник був зупинений");
        });


        // Запуск потоку

        mainThread.start();
        counterThread.start();

        // Дамо потоку попрацювати 1 секунду
        Thread.sleep(1000);

        // Встановлюємо прапор переривання
        System.out.println("5 Встановлюємо прапор переривання через 1 секунду");
        isInterrupted = true;
        mainThread.interrupt(); // Перериваємо потік
        counterThread.interrupt(); // Перериваємо потік з лічильником

        // Чекаємо завершення роботи потоку
        mainThread.join();
        counterThread.join();

        System.out.println("6 Потік зупинено");
    }

}