package com.consultorio.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class DatabaseConnection {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/consultorio"; // Reemplaza 'testdb' con el nombre de tu BD
    private static final String USER = "root"; // Tu usuario de MySQL
    private static final String PASSWORD = ""; // Tu contraseña de MySQL
    public static void main(String[] args) {
        try {
            // Establecer conexión
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a MySQL!");
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}

