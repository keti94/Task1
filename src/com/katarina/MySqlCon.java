package com.katarina;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class MySqlCon {

    private Connection connection;
    private static MySqlCon instance;

    private MySqlCon() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/task1", "root", "");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products");
            System.out.println("Connection OK");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(3) + "  " + rs.getInt(2));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static MySqlCon getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySqlCon();
        }
        return instance;
    }
    public List<Product> getAllProducts() throws Exception {
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
            throw new Exception(ex.getMessage(), ex);
        }
        return products;
    }

    public void saveProduct(Product product) throws Exception {
        try {
            String query = "INSERT INTO products(id, price, name) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, product.getId());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setString(3, product.getName().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            throw new Exception("Save product exception! " + ex.getMessage(), ex);
        }
    }
}
