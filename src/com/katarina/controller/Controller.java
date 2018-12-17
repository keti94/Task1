package com.katarina.controller;

import com.katarina.database.MySqlCon;
import com.katarina.domain.Product;
import com.katarina.start.TaskExecutor;

import javax.print.DocFlavor;
import java.util.List;


public class Controller {
    MySqlCon mySqlCon;
    private static Controller instance;
    public static Product prod;


    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Controller() {
        mySqlCon = new MySqlCon();

    }

//    public boolean exists(String addProcuct){
//            String name = addProcuct.split(" ")[2];
//            int price = 0;
//            try {
//                price = Integer.valueOf(addProcuct.split(" ")[1]);
//            } catch (NumberFormatException e) {
//                System.out.println("Niste dovfdhvjk");
//            }
//            boolean exists = false;
//            for (Product pr : MySqlCon.getInstance().getAllProducts()) {
//                if (pr.getName().equals(name)) {
//                    exists = true;
//                }
//            }
//            if (!exists) {
//                Controller.getInstance().saveProduct(name, price);
//            }
//    return exists;
//    }

    public boolean exists(Product product) {
        boolean exists = false;
        for (Product p : MySqlCon.getInstance().getAllProducts()) {
            if (p.getName().equals(product.getName())) exists = true;
        }
        return exists;
    }
    public boolean validation(String price){
        boolean valid=false;
        int priceOfProduct =0;
        try {
            priceOfProduct = Integer.valueOf(price);
            valid=true;
        }catch (NumberFormatException ex){
            System.out.println("Invalid price!");
        }
        return valid;

    }

    public void saveProduct(Product product) {
//        Product p = new Product();
//        p.setName(product.getName());
//        p.setPrice(product.getPrice());
        try {
            prod.equals(product);
            MySqlCon.getInstance().saveProduct(prod);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getAllProducts() {

        try {
            for (Product p : MySqlCon.getInstance().getAllProducts()) {
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int sumPrices() {
        int sum = 0;
        for (Product p : MySqlCon.getInstance().getAllProducts()) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void minMax() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Product p : MySqlCon.getInstance().getAllProducts()) {
            if (p.getPrice() < min) min = p.getPrice();
            if (p.getPrice() > max) max = p.getPrice();
        }
        System.out.println("MIN = " + min + ", MAX = " + max);
    }

    public void endProgram() {
        MySqlCon.getInstance().closeConnection();
    }

    public void getSubrstring(String string) {
        String name = string.split(" ")[2];
        int price = 0;
        try {
            price = Integer.valueOf(string.split("")[1]);
        } catch (NumberFormatException ex) {
            System.out.println("Niste dobro uneli cenu!");
        }

    }
}

