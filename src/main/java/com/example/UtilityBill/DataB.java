package com.example.UtilityBill;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataB {
    public Connection databaselink;
    public Connection getConnection() {
        String databaseName = "utility";
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost:3306/utility";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaselink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception e){
            e.printStackTrace();
        }
        return databaselink;
    }
}
