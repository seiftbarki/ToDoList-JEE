package com.example.todo.servlets;

import com.example.todo.config.DB;
import com.example.todo.models.Category;
import com.example.todo.models.Tasks;

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
import java.util.Date;
import java.util.List;

@WebServlet(value = "/TaskServlet",name = "task")
public class TaskServlet extends HttpServlet {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID de l'utilisateur depuis la session
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

// Récupérer l'ID de la catégorie depuis les paramètres de la requête
        int catId = Integer.parseInt(request.getParameter("catid"));

// Récupérer les autres valeurs de la tâche depuis les paramètres de la requête
        String taskName = request.getParameter("taskname");
        String taskDate = request.getParameter("taskdate");

// Utiliser une requête préparée pour éviter l'injection SQL
        String sql = "INSERT INTO tasks (TaskName, DueDate, IsCompleted,UserID, CategoryID) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connexion.prepareStatement(sql)) {
            // Remplacez les "?" par les valeurs appropriées
            preparedStatement.setString(1, taskName);
            preparedStatement.setString(2, taskDate);
            preparedStatement.setBoolean(3, false); // Vous pouvez définir iscomplete comme vous le souhaitez
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, catId);

            // Exécuter la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer l'ID de l'utilisateur depuis la session
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        String sql = "SELECT * FROM tasks WHERE UserID = ?";
        ArrayList<Tasks> tasks = new ArrayList<>();

        try (PreparedStatement preparedStatement = this.connexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultat = preparedStatement.executeQuery();


            while (resultat.next()) {

                String nom = resultat.getString("TaskName");
                java.sql.Date sqlDate = resultat.getDate("DueDate");
                Byte bite = Byte.valueOf(resultat.getString("IsCompleted"));

                // Convertir java.sql.Date en java.util.Date si nécessaire
                java.util.Date date = new java.util.Date(sqlDate.getTime());

                Tasks c = new Tasks(nom, date, bite);
                tasks.add(c);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
            session.setAttribute("tasks", tasks);
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
