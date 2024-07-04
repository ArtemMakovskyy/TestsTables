package com.winestoreapp.multithreading;

import java.util.concurrent.RecursiveTask;

class MyRecursiveTask extends RecursiveTask<Integer> {
    private int workload;

    public MyRecursiveTask(int workload) {
        this.workload = workload;
    }

    @Override
    protected Integer compute() {
        if (workload > 1) {
            MyRecursiveTask subtask1 = new MyRecursiveTask(workload / 2);
            MyRecursiveTask subtask2 = new MyRecursiveTask(workload / 2);
            subtask1.fork();
            subtask2.fork();
            return subtask1.join() + subtask2.join();
        } else {
            return workload;
        }
    }
}