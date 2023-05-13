package com.example.swa7_app;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    public Connection databaselink;
    public Connection getConnection(){
        String databaseName = "hms_db" ;
        String databaseUser = "root";
        String databasePassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databaseName ;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaselink = DriverManager.getConnection(url,databaseUser,databasePassword);
            //databaselink = DriverManager.getConnection("jdbc:mysql://localhost:3306/swa7_db","root","");
            System.out.println("CONNECT");
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaselink;
    }
}
