package com.example.todo.servlets;

import com.example.todo.config.DB;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value = "/LoginServlet",name = "login")
public class LoginServlet extends HttpServlet {
    private Connection connexion;
    //attribut qui représente la requéte
    private Statement statement;

    @Override
    public void init(){
        this.connexion= DB.connexionDB();
        try {
            this.statement=this.connexion.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            String sql = "SELECT * FROM users WHERE Username = ? AND Password = ?";

            // Utilisez une PreparedStatement pour éviter les attaques par injection SQL
            PreparedStatement preparedStatement = this.connexion.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("UserID");


                // Stocker l'ID de l'utilisateur dans la session
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);


                // Utilisateur trouvé, authentification réussie
                RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                session.setAttribute("username", username);
                dispatcher.include(request, response);

            } else {
                // Aucun utilisateur trouvé, authentification échouée
                response.sendRedirect("login.jsp");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //detruite la servlet / libérer les ressources
    public void destroy() {
        try {
            this.connexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

