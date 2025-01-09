package CRUD;

import java.sql.*;

public class DbConfig {

    private static final String URL = "jdbc:sqlite:dbprojetjava";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            throw new SQLException("Driver SQLlite non trouvé", e);
        }
    }

    }









