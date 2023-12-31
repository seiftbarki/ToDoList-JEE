<%@ page import="com.example.todo.models.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.todo.models.Tasks" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<div class="container-fluid">
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <!-- Ajouter la classe "d-flex" pour aligner les éléments à gauche -->
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 d-flex">
                <li class="nav-item">
                    <a class="nav-link" href="welcome.jsp">acceuil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="task.jsp">ajout tache</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ListTask.jsp">liste des taches</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">log out</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<h2>Welcome, <%= session.getAttribute("username") %></h2>
<p>This is a protected page.</p>
<!-- Ajoutez ceci dans votre fichier welcome.jsp -->

<div style="border: 1px solid #ccc; padding: 20px; width: 50%; margin: auto;">
    <form action="./cat" method="post">
        <div class="mb-3">
            <label for="newCategory" class="form-label">Nouvelle categoire</label>
            <input type="text" class="form-control" id="newCategory" name="newCategory" style="width: 50%" required>
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <button type="submit" class="btn btn-primary">Ajouter categorie</button>
    </form>
</div>



</body>
</html>
