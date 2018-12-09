package com.katarina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;


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

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        String blabla = null;
        do {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("What is your request?");

            try {
                blabla = (new BufferedReader(new InputStreamReader(System.in))).readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (blabla.equals("list")) {
                try {
                    MySqlCon.getInstance().getAllProducts();
                    for (Product p : MySqlCon.getInstance().getAllProducts()) {
                        System.out.println(p);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (blabla.equals("minmax")) {
                for (Product p:MySqlCon.getInstance().getAllProducts()) {
                    if (p.getPrice()<min) min=p.getPrice();
                    if (p.getPrice()>max) max=p.getPrice();
                }
                System.out.println("MIN = "+min+", MAX = "+max);
            }

            if (blabla.equals("sum prices")) {
                int sum = 0;
                for (Product p : MySqlCon.getInstance().getAllProducts()) {
                    sum += p.getPrice();
                }
                System.out.println("Ukupan zbir cena je: " + sum);
            }

            if (blabla.substring(0, 3).equals("add")) {

                String name = blabla.split(" ")[2];
                int price = 0;
                try {
                    price = Integer.valueOf(blabla.split(" ")[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Niste dovfdhvjk");
                    continue;
                }
                boolean exists = false;
                for (Product pr : MySqlCon.getInstance().getAllProducts()) {
                    if (pr.getName().equals(name)) {
                        exists = true;
                    }
                }
                if (!exists) {
                    Product p = new Product();
                    p.setName(name);
                    p.setPrice(price);
                    try {
                        MySqlCon.getInstance().saveProduct(p);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        } while (!blabla.equals("end"));
        MySqlCon.getInstance().closeConnection();

    }
}



