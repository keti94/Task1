package com.katarina.controller;

import com.katarina.database.MySqlCon;
import com.katarina.domain.Product;
import com.katarina.start.TaskExecutor;

import javax.print.DocFlavor;
import java.util.List;


public class Controller {
    MySqlCon mySqlCon;
    private static Controller instance;


    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
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

    public void saveProduct(Product p) throws Exception {

        p = new Product();
        p.setName(p.getName());
        p.setPrice(p.getPrice());
        try {
            MySqlCon.getInstance().saveProduct(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveProduct(String name, int price){

        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        try {
            MySqlCon.getInstance().saveProduct(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllProducts() {

        try {
            MySqlCon.getInstance().getAllProducts();
            for (Product p : MySqlCon.getInstance().getAllProducts()) {
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public List<Product> getAllProductss(){
//
//    }

    public int sumPrices(){
        int sum=0;
        for (Product p:MySqlCon.getInstance().getAllProducts()) {
            sum+=p.getPrice();
        }
        return sum;
    }

    public void minMax(){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Product p : MySqlCon.getInstance().getAllProducts()) {
            if (p.getPrice() < min) min = p.getPrice();
            if (p.getPrice() > max) max = p.getPrice();
        }
        System.out.println("MIN = " + min + ", MAX = " + max);
    }

    public void endProgram(){
        MySqlCon.getInstance().closeConnection();
    }
}

