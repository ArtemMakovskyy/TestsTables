package com.winestoreapp.multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    private static final int PERMITS = 3;
    private static final Semaphore semaphore = new Semaphore(PERMITS,false);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i)).start();
        }
    }

    static class Task implements Runnable {
        private int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            try {
                System.out.println("<<<<<<<<<  Task " + taskId + " is waiting for a permit");
                semaphore.acquire();
                System.out.println("   <<<<<<" + " Task " + taskId );

                // Симуляція виконання завдання
                Thread.sleep(200);

                System.out.println("!     <<< Task " + taskId + " released a permit");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
