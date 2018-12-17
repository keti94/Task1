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

        do {
            System.out.println("What is your request?");

            try {
                blabla = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                TaskExecutor taskExecutor = new TaskExecutor(Operation.valueOf(blabla));
                taskExecutor.execute(Operation.valueOf(blabla));

            } catch (IOException e) {
                e.printStackTrace();
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
                    Controller.getInstance().saveProduct(name, price);
                }

            }
        } while (!Operation.valueOf(blabla).equals(Operation.end.toString()));


    }
}



