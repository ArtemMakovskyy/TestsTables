package com.winestoreapp.multithreading.other.interrupt;

public class InterruptExample {

    public static void main(String[] args) {
//        case1interrupt();
//        case2interrupt();
        d();
    }

    static void d() {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Потік починає спати");
                Thread.sleep(5000); // Потік спить на 5 секунд
                System.out.println("Потік прокинувся");
            } catch (InterruptedException e) {
                System.out.println("Потік був перерваний під час сну");
            }
        });

        thread.start(); // Запускаємо потік

        // Чекаємо 3 секунди, а потім перериваємо потік
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt(); // Перериваємо потік

    }


    static void case1interrupt() {
//        Переривання потоку під час сплячого режиму
        Thread thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Потік працює");
                    Thread.sleep(1000); // Імітація роботи
                }
            } catch (InterruptedException e) {
                System.out.println("Потік був перерваний під час сну");
            }
        });

        thread.start(); // Запускаємо потік

        // Очікуємо 3 секунди, а потім перериваємо потік
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt(); // Перериваємо потік
    }

    static void case2interrupt() {
//        Обробка переривання поза сплячим режимом:

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Потік працює");
            }
            System.out.println("Потік був перерваний");
        });

        thread.start(); // Запускаємо потік

        // Очікуємо 3 секунди, а потім перериваємо потік
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt(); // Перериваємо потік
    }

    static void case1interruptED() { Thread thread = new Thread(() -> {
        while (!Thread.interrupted()) {
            System.out.println("Потік працює");
        }
        System.out.println("Потік був перерваний");
    });

        thread.start(); // Запускаємо потік

        // Очікуємо 3 секунди, а потім перериваємо потік
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt(); // Перериваємо потік
    }
    static void case2interruptED() {   Thread thread = new Thread(() -> {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Потік працює");
        }
        System.out.println("Потік був перерваний");
    });

        thread.start(); // Запускаємо потік

        // Очікуємо 3 секунди, а потім перериваємо потік
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt(); // Перериваємо потік
    }
}


