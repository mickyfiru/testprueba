/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.profemariostomas.classicmodels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luk0s
 */
public class MySQLConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/universidad";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, USUARIO, PASSWORD);
        return connection;
    }
}
