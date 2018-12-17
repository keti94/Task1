package com.katarina.database;

import com.katarina.domain.Product;

import java.util.LinkedList;
import java.util.List;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class MySqlCon {

    private Connection connection;
    private static MySqlCon instance;

    public MySqlCon() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/task1", "root", "");
//            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MySqlCon getInstance() {
        if (instance == null) {
            instance = new MySqlCon();
        }
        return instance;
    }

    public List<Product> getAllProducts() {
        List<Product> products = new LinkedList<>();
        String query = "SELECT * FROM products";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                int price = resultSet.getInt(2);
                String name = resultSet.getString(3);
                Product product = new Product(id, price, name);

                products.add(product);
            }
            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return products;
    }

    public void saveProduct(Product product) {
        try {
            String query = "INSERT INTO products(price, name) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getPrice());
            preparedStatement.setString(2, product.getName());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException ex) {

        }
    }
}
