package com.katarina.start;

import com.katarina.controller.Controller;
import com.katarina.database.MySqlCon;
import com.katarina.domain.Product;
import com.katarina.start.TaskExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {


    public static void main(String[] args) {
        // Konzonla aplikacija
        //
        //konekcija sa bazom
        //Baza ima list proizvoda
        //Definisane komande
        //List - izlistava sve proizvode u konzoli
        //Add  {naziv} {cena} - dodaje u bazu
        //Prices - prikazuje zbir svih cena proizvoda
        //ExpensiveAndNot - prikazuje najskuplji i najeftiniji proizvod
        //Gasi konkciju sa bazoms
        //Kada se pokrene gasenje aplikacije mora konekcija sa bazom da se prekine
        //

        String blabla = null;
        boolean aaa = true;
        while (aaa) {
            System.out.println("What is your request?");
            try {

                blabla = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                String komanda = blabla.split(" ")[0];
                if (komanda.equals("add")){
                    String name = blabla.split(" ")[2];
                    String price = blabla.split(" ")[1];
                    if (Controller.getInstance().validation(price)) {
                        int priceInt = Integer.valueOf(price);
                        Product p = new Product(priceInt, name);
                        if (!Controller.getInstance().exists(p)) Controller.getInstance().saveProduct(p);
                    }
                }else{

                TaskExecutor taskExecutor = new TaskExecutor(Operation.valueOf(komanda));

                taskExecutor.execute(Operation.valueOf(komanda));}

//                if (komanda.equals("add")){
//
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }

//            if (blabla.substring(0, 3).equals("add")) {
//
//                String name = blabla.split(" ")[2];
//                String price = blabla.split(" ")[1];
////                int price = 0;
////                try {
////                    price = Integer.valueOf(blabla.split(" ")[1]);
////                } catch (NumberFormatException e) {
////                    System.out.println("Niste dobro uneli broj");
////                    continue;
////                }
//                if (Controller.getInstance().validation(price)) {
//                    int priceInt = Integer.valueOf(price);
//                    Product p = new Product(priceInt, name);
//                    if (!Controller.getInstance().exists(p)) Controller.getInstance().saveProduct(p);
//                }
//                if (!Controller.getInstance().exists(p)) Controller.getInstance().saveProduct(p);
//                boolean exists = false;
//                for (Product pr : MySqlCon.getInstance().getAllProducts()) {
//                    if (pr.getName().equals(name)) {
//                        exists = true;
//                    }
//                }
//                if (!exists) {
//                    Controller.getInstance().saveProduct(p);
//                }

            if (Operation.valueOf(blabla).equals(Operation.end))
                aaa = false;
        }}
    }




