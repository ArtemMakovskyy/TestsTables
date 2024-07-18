package com.winestoreapp.multithreading;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinRecursiveActionExample {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        PrintTask task = new PrintTask(array, 0, array.length);
        pool.invoke(task);
    }
}

class PrintTask extends RecursiveAction {
    private static final int THRESHOLD = 10;
    private int[] array;
    private int start;
    private int end;

    public PrintTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + " - Value: " + array[i]);
            }
        } else {
            int mid = (start + end) / 2;
            PrintTask leftTask = new PrintTask(array, start, mid);
            PrintTask rightTask = new PrintTask(array, mid, end);

            leftTask.fork();
            rightTask.fork();

            leftTask.join();
            rightTask.join();
        }
    }
}
