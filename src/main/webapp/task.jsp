<%@ page import="com.example.todo.models.Category" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: seift
  Date: 21/12/2023
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link"  href="welcome.jsp">acceuil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="task.jsp">ajout tache</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"  href="ListTask.jsp">liste des taches</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"  href="logout.jsp">log out</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
<h2>Ajouter une Tâche</h2>

<div style="border: 1px solid #ccc; padding: 20px; width: 50%; margin: auto;">
    <form action="./TaskServlet" method="post">
        <div class="mb-3">
            <label for="taskname" class="form-label">Nom de la tache : </label>
            <input type="text" class="form-control" id="taskname" name="taskname" style="width: 50%" required>
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
            <label for="taskdate" class="form-label">Date de la Tâche:</label>
            <input type="date" class="form-control" id="taskdate" name="taskdate" style="width: 50%"   required>
        </div>
        <div class="mb-3">
            <label for="category" class="form-label">Categorie : </label>
            <select id="category" name="catid" required>
            <%
                ArrayList<Category> categories = (ArrayList<Category>) session.getAttribute("categories");

                if (categories != null && !categories.isEmpty()) {
                    for (int i = 0; i < categories.size(); i++) {
                        out.println("<option>" + categories.get(i).getNomc() + "</option>");
                    }
                } else {
                    out.println("<option value='2'>Aucune catégorie disponible.</option>");
                }
            %>
            </select>
        </div>
        <br>

        <button type="submit" class="btn btn-primary">ajout tache</button>
    </form>
</div>
</body>
</html>
