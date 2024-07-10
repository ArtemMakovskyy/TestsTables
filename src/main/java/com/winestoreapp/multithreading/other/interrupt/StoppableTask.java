package com.winestoreapp.multithreading.other.interrupt;

public class StoppableTask implements Runnable {
    private volatile boolean running = true;

    public void run() {
        while (running) {
            // Виконуємо завдання
            System.out.println("Потік працює");
            try {
                Thread.sleep(100); // Імітація роботи
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void stop() {
        running = false;
    }

    public static void main(String[] args) throws InterruptedException {
        StoppableTask task = new StoppableTask();
        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(500); // Дамо потоку попрацювати 4 секунд
        task.stop(); // Попросимо зупинитися
        thread.join(); // Чекаємо завершення потоку
        System.out.println("Потік зупинено");
    }
}