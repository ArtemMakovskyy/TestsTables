package com.winestoreapp.multithreading.returnvolue;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainReturnValue {
    public static void main(String[] args) {
        callableReturnVoid();
        callableReturnInteger();
        callableGetThenReturnInteger(25);
        completableFuture(2, 3);
        completableFutureAsync(10, 3);

    }

    private static void callableReturnVoid() {
        Callable<Void> callable = () -> {
            System.out.println("Цей callable виконує деякі дії, але не повертає значення.\n");
            return null; // Використання null, оскільки Callable вимагає повернення значення
        };

        try {
            callable.call(); // Виклик callable
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void callableReturnInteger() {
        ExecutorService executorService
                = Executors.newSingleThreadExecutor();
        Callable<Integer> callable = () -> {
            return 42;
        };
        Future<Integer> future = executorService.submit(callable);
        try {
            Integer result = future.get();
            System.out.println("Результат з callable: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static void callableGetThenReturnInteger(int number) {
        MyIntegerCallable myIntegerCallable
                = new MyIntegerCallable(number);
        try {
            final Integer call = myIntegerCallable.call();
            System.out.println(number + " x 2 = " + call);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void completableFuture(int number, int multiple) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // Симулюємо деяку роботу потоку
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return number * multiple;
        });
        try {
            Integer result = future.get();
            System.out.println(number + " X " + multiple + " = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void completableFutureAsync(int number, int multiple) {
        // Виконання асинхронного завдання з використанням CompletableFuture.runAsync
        if (false) {
            //довгий запис
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                int result = number * multiple;
                System.out.println("Обчислення результату в асинхронному потоці: " + result);
                return result;
            });

            // Обробка результату
            future.thenAccept(result -> {
                System.out.println("Результат обчислення: " + result);
            });

            // Очікування завершення для демонстрації
            try {
                System.out.println("Кінцевий результат: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        if (true) {
            //короткий запис
            CompletableFuture.supplyAsync(() -> {
                        int result = number * multiple;
                        System.out.println("\nОбчислення результату в асинхронному потоці: " + result);
                        return result;
//                    })
//                    .thenAccept(result -> {
//                        System.out.println("Результат обчислення: " + result);
                    })
                    .exceptionally(ex -> {
                        ex.printStackTrace();
                        return null;
                    });

            // У реальних додатках зазвичай не викликають get() для CompletableFuture,
            // але для демонстрації можна почекати на завершення
            try {
                Thread.sleep(1000); // Дати час на завершення асинхронного завдання
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class MyIntegerCallable implements Callable<Integer> {
    private int value;

    public MyIntegerCallable(int value) {
        this.value = value;
    }

    @Override
    public Integer call() throws Exception {
        // Симулюємо деяку роботу потоку
        Thread.sleep(10);
        return value * 2;
    }
}
