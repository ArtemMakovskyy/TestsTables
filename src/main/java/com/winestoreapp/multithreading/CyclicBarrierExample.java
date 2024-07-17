package com.winestoreapp.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class CyclicBarrierExample {
    /*     If NUM_PARTIES % CYCLES != 0 not all task will be com completed     */
    private static final int NUM_PARTIES = 3;
    private static final int CYCLES = 10;
    private static final CyclicBarrier barrier
            = new CyclicBarrier(NUM_PARTIES, new BarrierAction());

    public static void main(String[] args) {
        for (int i = 0; i < CYCLES; i++) {
            new Thread(new Task(i)).start();
        }
    }

    static class Task implements Runnable {
        private int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(">>> >>> >>> Task " + taskId + " is starting");
            try {
                // Симуляція виконання завдання
                Thread.sleep(10);
                System.out.println("    YYY >>> Task " + taskId + " is finished and waiting at the barrier");
//                barrier.await(); // Чекання інших потоків на бар'єрі
                barrier.await(500, TimeUnit.MILLISECONDS);
                System.out.println("        !!! Task Task " + taskId + " is released from the barrier");
            } catch (InterruptedException | BrokenBarrierException e) {
                System.out.println("        !X! Task " + taskId + " was released due to timeout or interruption");
                e.printStackTrace();
            }
        }
    }

    static class BarrierAction implements Runnable {
        @Override
        public void run() {
            System.out.println("<<< BarrierAction >>>  All parties have arrived at the barrier, performing the barrier action");
        }
    }
}
