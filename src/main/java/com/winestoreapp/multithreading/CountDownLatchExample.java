package com.winestoreapp.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {

    /*     If NUM_TASKS >= CYCLES not all task will be com completed     */
    private static final int NUM_TASKS = 7;
    private static final int CYCLES = 5;
    private static final CountDownLatch latch = new CountDownLatch(NUM_TASKS);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < CYCLES; i++) {
            new Thread(new Task(i, latch)).start();
        }

        System.out.println("Main thread waiting for tasks to complete...");
        final boolean completed = latch.await(2, TimeUnit.SECONDS);// Очікування з таймаутом 2 секунди
//        latch.await(); // Очікування, доки лічильник не досягне нуля
        if (completed) {
            System.out.println("VVV All tasks are completed within the time limit. Main thread resumes.");
        } else {
            System.out.println("!!! Time limit reached. Not all tasks completed. Main thread resumes.");
        }
    }

    static class Task implements Runnable {
        private int taskId;
        private CountDownLatch latch;

        public Task(int taskId, CountDownLatch latch) {
            this.taskId = taskId;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("S >   Task " + taskId + " is starting");
            try {
                Thread.sleep(150);
                System.out.println("  > C Task " + taskId + " is completed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }
}
