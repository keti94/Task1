package com.katarina.start;

import com.katarina.controller.Controller;
import com.katarina.database.MySqlCon;
import com.katarina.domain.Product;

enum Operation {add, list, minmax, sum, end};

public class TaskExecutor {

    Operation operation;
    static Product prod;



    public TaskExecutor(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public static void execute(Operation operation) {

        switch (operation) {
            case add:

                Controller.getInstance().saveProduct(prod);
//                //Controller.getInstance().getSubrstring();
//                Controller.getInstance().saveProduct(product);
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
