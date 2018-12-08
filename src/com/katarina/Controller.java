package com.katarina;

import java.util.List;

public class Controller {
    private static Controller instance;


    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void saveProduct(Product product) throws Exception {
        MySqlCon.getInstance().saveProduct(product);
    }

    public List<Product> getAllProducts() {
        List<Product> products = null;
        try {
            products = MySqlCon.getInstance().getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}

