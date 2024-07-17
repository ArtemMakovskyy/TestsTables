package com.winestoreapp.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.*;

public class ThreadPoolExecutorExample2 {
    public static void main(String[] args) {
        int corePoolSize = 2;       // Мінімальна кількість потоків
        int maxPoolSize = 4;        // Максимальна кількість потоків
        long keepAliveTime = 10;    // Тривалість утримання зайвих потоків у пулі (секунди)
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue);

        for (int i = 1; i <= 6; i++) {
            Callable<String> worker = new WorkerCallable("Task " + i);
            Future<String> result = executor.submit(worker);
            try {
                System.out.println("Result of " + worker.toString() + ": " + result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    static class WorkerCallable implements Callable<String> {
        private String taskName;

        public WorkerCallable(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + " is executing " + taskName);
            Thread.sleep(250); // Імітація виконання завдання
            return taskName + " completed";
        }

        @Override
        public String toString() {
            return this.taskName;
        }
    }
}
