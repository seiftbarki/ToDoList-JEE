package com.example.todo.servlets;

import com.example.todo.config.DB;
import com.example.todo.models.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(value = "/cat",name = "categorie")
public class CategorieServlet extends HttpServlet {
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

        // Récupérer la nouvelle catégorie à partir du formulaire
        String newCategory = request.getParameter("newCategory");

        try {
            // Dans la servlet d'ajout de catégorie


            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("userId");

            String username = (String) session.getAttribute("username");

// Utiliser userId lors de l'insertion de la catégorie
            String sql = "INSERT INTO categories (CategoryName,UserID) VALUES (?, ?)";
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            preparedStatement.setString(1, newCategory);
            preparedStatement.setInt(2, userId);
            // Exécuter la requête d'insertion
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Catégorie ajoutée avec succès
                RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                request.setAttribute("username", username);  // Rétablissez l'attribut username
                dispatcher.include(request, response);

            } else {
                // Échec de l'ajout de catégorie
                response.sendRedirect("error.jsp");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de l'utilisateur à partir de la session
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        // Assurez-vous que userId est de type int

        // Utiliser une requête préparée avec un paramètre pour éviter l'injection SQL
        String sql = "SELECT * FROM categories WHERE UserID = ?";

        try (PreparedStatement preparedStatement = this.connexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);

            ResultSet resultat = preparedStatement.executeQuery();
            ArrayList<Category> categories = new ArrayList<>();

            while (resultat.next()) {
                String nom = resultat.getString("CategoryName");
                Category c = new Category(nom);
                categories.add(c);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
            session.setAttribute("categories", categories);
            dispatcher.forward(request, response);

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
