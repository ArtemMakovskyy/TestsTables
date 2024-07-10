package com.winestoreapp.multithreading.other.interrupt;

public class InterruptibleTask implements Runnable {
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // Виконуємо завдання
            System.out.println("Потік працює");
            try {
                Thread.sleep(100); // Імітація роботи
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Повторне встановлення статусу переривання
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptibleTask task = new InterruptibleTask();
        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(500); // Дамо потоку попрацювати 5 секунд
        thread.interrupt(); // Перериваємо потік
        thread.join(); // Чекаємо завершення потоку
        System.out.println("Потік зупинено");
    }
}