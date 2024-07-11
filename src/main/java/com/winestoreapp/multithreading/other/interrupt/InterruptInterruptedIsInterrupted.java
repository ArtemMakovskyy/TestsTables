package com.winestoreapp.multithreading.other.interrupt;

import lombok.SneakyThrows;

public class InterruptInterruptedIsInterrupted {
    public static void main(String[] args) throws InterruptedException {
//        interrupt();
//        total();
//        isInterrupted();
        ex();
    }


    private static void ex() throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " working 1 sec");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
//        while (!Thread.currentThread().isInterrupted()){
//            System.out.println((Thread.currentThread().isInterrupted()));
//        }
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        while (!Thread.currentThread().isInterrupted()){
            System.out.println((Thread.currentThread().isInterrupted()));
        }
        thread.join();
    }

    private static void interrupt() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000); // Потік B заснув
                System.out.println("0 Потік B заснув"); // Потік B заснув
            } catch (InterruptedException e) {
                System.out.println("1 Потік B був перерваний"); // Потік B прокинеться і виконає цей код
            }
        });

        thread.start();

        thread.interrupt(); // Потік A перериває потік B
    }

    @SneakyThrows
    private static void total(){
        // Створення потоку
        Thread thread = new Thread(() -> {
            while (true) {
                // Перевірка статусу переривання
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("2 Потік був перерваний, вихід з циклу");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("\n3 >>>Імітація роботи");
                } catch (InterruptedException e) {
                    System.out.println("4 " + Thread.currentThread().getName() + " Потік прокинувся від сну через переривання");
                    // Повторне встановлення статусу переривання
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Запуск потоку
        thread.start();

        // Дамо потоку попрацювати 3 секунди
        Thread.sleep(3000);

        // Перериваємо потік
        System.out.println("5 Перериваємо потік " + Thread.currentThread().getName());
        thread.interrupt();

        // Чекаємо завершення роботи потоку
        thread.join();

        // Демонстрація роботи Thread.interrupted()
        System.out.println("6 Демонстрація роботи Thread.interrupted() на основному потоці:");
        Thread.currentThread().interrupt(); // Встановлюємо статус переривання для основного потоку
        System.out.println("7 Thread.interrupted(): " + Thread.interrupted()); // Перевіряємо і очищаємо статус переривання
        System.out.println("8 Thread.interrupted() після очищення: " + Thread.interrupted()); // Перевіряємо ще раз (має бути false)

        // Демонстрація роботи isInterrupted() на новому потоці
        Thread newThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("9 Новий потік працює");
                try {
                    Thread.sleep(1000); // Імітація роботи
                } catch (InterruptedException e) {
                    System.out.println("10 Новий потік прокинувся від сну через переривання");
                    Thread.currentThread().interrupt(); // Повторне встановлення статусу переривання
                }
            }
        });

        newThread.start();
        Thread.sleep(3000); // Дамо потоку попрацювати 3 секунди
        newThread.interrupt(); // Перериваємо новий потік
        newThread.join(); // Чекаємо завершення роботи нового потоку
    }

    private static void isInterrupted(){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100); // Потік заснув
                System.out.println("11 Потік заснув " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("12 Потік був перерваний"); // Потік прокинеться і виконає цей код
            }
        });

        thread.start();
        thread.interrupt(); // Перериваємо потік

        boolean wasInterrupted = thread.isInterrupted(); // Перевіряємо статус переривання
        System.out.println("13 wasInterrupted " + wasInterrupted); // Виведе true
        System.out.println("14 thread.isInterrupted() " + wasInterrupted); // Виведе true знову, оскільки статус не був очищений
    }
}
