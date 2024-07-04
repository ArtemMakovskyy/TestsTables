package com.winestoreapp.multithreading;

import java.util.concurrent.RecursiveAction;

class MyRecursiveAction extends RecursiveAction {
    private int workload;

    public MyRecursiveAction(int workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (workload > 1) {
            MyRecursiveAction subtask1 = new MyRecursiveAction(workload / 2);
            MyRecursiveAction subtask2 = new MyRecursiveAction(workload / 2);
            subtask1.fork();
            subtask2.fork();
            subtask1.join();
            subtask2.join();
        } else {
            if (false)
            System.out.println("Виконання задачі з навантаженням MyRecursiveAction: " + workload);
        }
    }
}