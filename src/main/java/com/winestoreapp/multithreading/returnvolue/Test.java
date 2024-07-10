package com.winestoreapp.multithreading.returnvolue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.SneakyThrows;

public class Test {
    @SneakyThrows
    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            System.out.println("CompletableFuture.runAsync");
        }).get();

        int i1 = 5;
        int i2 = 25;
        CompletableFuture.supplyAsync(() -> {
            int result = i1 * i2;
            return result;
        }).thenAccept(res -> {
            sleep(1000);
            System.out.println(res);
        });

    }

    @SneakyThrows
    private static void sleep(int time) {
        Thread.sleep(time);
    }
}
