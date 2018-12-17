package com.katarina.start;

import com.katarina.controller.Controller;

enum Operation {add, list, minmax, sum, end};

public class TaskExecutor {

    Operation operation;


    public TaskExecutor(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public static void execute(Operation operation) {

        switch (operation) {
            case add:
                Controller.getInstance().saveProduct("", 1);
                break;
            case list:
                Controller.getInstance().getAllProducts();
                break;
            case minmax:
                Controller.getInstance().minMax();
                break;
            case sum:
                System.out.println("Ukupan zbir je: " + Controller.getInstance().sumPrices());
                break;
            case end:
                Controller.getInstance().endProgram();
                break;
        }
    }
}
