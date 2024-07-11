package com.winestoreapp.multithreading.other.interrupt;

public class ThreadInterruptionExample {
    private static final int WORK_TIME = 10000;

    public static void main(String[] args) throws InterruptedException {

        // Створення потоку
        Thread workThread = new Thread(() -> {
            try {
                final String currentNameThread = Thread.currentThread().getName();
                System.out.println(currentNameThread + " потік починає роботу на " + WORK_TIME + " секунди");
                Thread.sleep(WORK_TIME); // Імітація роботи протягом 2 секунд
                System.out.println(currentNameThread + " потік завершив роботу після " + WORK_TIME + " секунд");
            } catch (InterruptedException e) {
                System.out.println("Потік був перерваний");
            }
        });
        final String currentNameThread2 = Thread.currentThread().getName();
        // Запуск потоку
        workThread.start();

        // Дамо потоку попрацювати 1 секунду
        Thread.sleep(1000);

        // Перериваємо потік
        System.out.println("Перериваємо внутрішній потік через 1 секунду");
        workThread.interrupt();

        // Чекаємо завершення роботи потоку
        workThread.join();

        System.out.println(currentNameThread2 + " потік зупинено");
    }
}
