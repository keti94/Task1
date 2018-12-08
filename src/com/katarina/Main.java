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
                    MySqlCon.getInstance();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (blabla.substring(0, 2).equals("add")) {

                String name = blabla.split(" ")[2];
                int price = Integer.valueOf(blabla.split(" ")[1]);

//                for (Product pr: listOfProducts
//                     ) {
//                    if (!pr.name.equals(name)){
                Product p = new Product();
                p.name = name;
                p.price = price;
//                        listOfProducts.add(p);
                try {
                    Controller.getInstance().saveProduct(p);
                    System.out.println("sacuvano");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//                }
//            }

        }
        while (!blabla.equals("end"));

    }
}

