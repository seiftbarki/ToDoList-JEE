package com.example.todo.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public int save(Connection connexion){
        String sql = " INSERT INTO users(Username,Password,Email) VALUES (? ,? ,? ) ";

        int resultat=0;
        try {
            PreparedStatement statement=connexion.prepareStatement(sql);
            statement.setString(1,this.username);
            statement.setString(3,this.email);
            statement.setString(2,this.password);
            resultat=statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultat;
    }

    public static User getById(int id,Connection connexion){
        String sql ="SELECT * FROM users WHERE id = ?";
        User user=null;
        try {
            PreparedStatement statement=connexion.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultat=statement.executeQuery();
            if(resultat.next()){
                user=new User();
                user.id=resultat.getInt("UserID");
                user.username=resultat.getString("Username");
                user.email=resultat.getString("Email");
                user.password=resultat.getString("Password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

}
