package com.example.todo.servlets;

import com.example.todo.config.DB;
import com.example.todo.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(value = "/a",name = "UserServlet")
public class UserServlet extends HttpServlet {
    /*
       Attribut qui représente la connexion a la vase de données
        */
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "SELECT * FROM users";
        try {
            ResultSet resultat=this.statement.executeQuery(sql);
            ArrayList<User> users = new ArrayList<>();
            while(resultat.next()){
                int id =resultat.getInt("UserID");
                String username =resultat.getString("Username");
                String email =resultat.getString("Email");
                String password =resultat.getString("Password");
                User u = new User(id,username,email,password);
                users.add(u);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("users",users);
            dispatcher.forward(request,response);
        } catch (SQLException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        PrintWriter out=response.getWriter();
        if(name != null && !name.equals("")
                && email != null && !email.equals("")
                && password != null && !password.equals("")){
            User user = new User(name,email,password);
            int r = user.save(this.connexion);
            if(r==1){
                response.sendRedirect("/ToDo_war_exploded/login.jsp");
            }else{
                out.println("erreur");
            }

        }
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String methode=request.getMethod();
        String idDelete = request.getParameter("codeDel");
        String operation=request.getParameter("operation");
        if (methode.toLowerCase().equals("post")&&operation.equals("ajouter")){
            this.doPost(request,response);
        }
        else if (methode.toLowerCase().equals("post")&&operation.equals("modifier")){
            this.doPut(request,response);
        }else if(methode.equals("GET")&& idDelete!=null &&!idDelete.equals("")){
            this.doDelete(request,response);
        }
        else{
            this.doGet(request,response);
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
