package com.winestoreapp.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Multithreading {
    public static void main(String[] args) {
//      1 Реалізація інтерфейсу `Runnable`
        Thread runnableClass = new Thread(new RunnableClass());
        runnableClass.start();

//      2 Розширення класу `Thread`
        ThreadClass threadClass = new ThreadClass();
        threadClass.start();

//      3 Використання анонімного класу:
        Thread anonimThreadClass = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Використання анонімного класу");
            }
        });
        anonimThreadClass.start();

//      4 Використання лямбда-виразів
        final Thread lambda = new Thread(() -> System.out.println("Використання лямбда-виразів"));
        lambda.start();

//      5 Використання інтерфейсу `Callable` і `Future`
        callableAndFutureWithReturn();

        forkJoinPool_RecursiveTask();
        forkJoinPool_RecursiveAction();

//      6  Використання ExecutorService:
        ExecutorService executorService
                = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            System.out.println("Використання ExecutorService Потік запущено");
        });
        executorService.shutdown();

//      7  Використання CompletableFuture
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("CompletableFuture Потік запущено");
        });

        future.thenRun(() -> System.out.println("CompletableFuture Потік завершено"));

//      8 ScheduledExecutorService
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.schedule(() -> {
            System.out.println("ScheduledExecutorService Потік запущено через 3 секунди");
        }, 3, TimeUnit.SECONDS);

        scheduler.shutdown();

    }

    private static void callableAndFutureWithReturn() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<Integer> callable = () -> {
            System.out.println("Потік запущено callableAndFutureWithReturn");
            return 123;
        };
        Future<Integer> future = executorService.submit(callable);

        try {
            Integer result = future.get();
            System.out.println("Результат callableAndFuture with return: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    private static void forkJoinPool_RecursiveTask() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyRecursiveTask task = new MyRecursiveTask(100);
        Integer result = forkJoinPool.invoke(task);
        System.out.println("Результат forkJoinPool_RecursiveTask: " + result);
    }

    private static void forkJoinPool_RecursiveAction() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyRecursiveAction action = new MyRecursiveAction(100);
        forkJoinPool.invoke(action);
        System.out.println("Задача завершена forkJoinPool_RecursiveAction");
    }
}
