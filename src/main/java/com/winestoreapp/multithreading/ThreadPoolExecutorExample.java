package com.winestoreapp.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {
        int corePoolSize = 2;       // Мінімальна кількість потоків
        int maxPoolSize = 4;        // Максимальна кількість потоків
        long keepAliveTime = 10;    // Тривалість утримання зайвих потоків у пулі (секунди)
        // Черга завдань на виконання
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        // Створення ThreadPoolExecutor
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,              // Мінімальна кількість потоків
                maxPoolSize,               // Максимальна кількість потоків
                keepAliveTime,             // Тривалість утримання зайвих потоків
                TimeUnit.SECONDS,          // Одиниця часу для тривалості утримання
                workQueue);                // Черга завдань
        // Подача завдань на виконання
        for (int i = 1; i <= 6; i++) {
            Runnable worker = new WorkerThread("Task " + i);
            executor.execute(worker);
        }
        // Завершення роботи пула потоків після завершення всіх завдань
        executor.shutdown();
    }
    static class WorkerThread implements Runnable {
        private String taskName;
        public WorkerThread(String taskName) {
            this.taskName = taskName;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is executing " + taskName);
            try {
                Thread.sleep(250); // Імітація виконання завдання
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}