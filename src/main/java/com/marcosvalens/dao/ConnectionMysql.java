package com.marcosvalens.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMysql{
    private Connection connection = null;
    private static ConnectionMysql connectionMysql;

    private ConnectionMysql(){
        String url = "jdbc:mysql://localhost:3306/examen_dwes";
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "root";

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnection(){
        if (connectionMysql == null) {
            connectionMysql = new ConnectionMysql();
        }
        return connectionMysql.connection;
    }

}
