package com.example.todo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static final String URL = "jdbc:mysql://localhost:3306/todo";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    /*
    méthode de connexion a la base de donnée
    return un objet de type Connection
     */
    public static Connection connexionDB(){
        Connection connexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(DB.URL,DB.USER,DB.PASSWORD);

        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
        return connexion;
    }

}