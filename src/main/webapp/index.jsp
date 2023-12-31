<%@ page import="com.example.todo.models.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>inscription</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body{
            background-image: url('../img/BG.jpg');

        }
    </style>

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
                    <a class="nav-link active" aria-current="page" href="index.jsp">INSCRIPTION</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="login.jsp">LOGIN</a>
                </li>

            </ul>

        </div>
    </div>
</nav>


<% if(request.getAttribute("message")!=null){
    out.println(request.getAttribute("message"));
}%>

<div style="border: 1px solid #ccc; padding: 20px; width: 50%; margin: auto;">
<form action="./a" method="post">
    <div class="mb-3">
        <label for="nom" class="form-label">UserName</label>
        <input type="text" class="form-control" id="nom" name="name" style="width: 50%" required>
    </div>
    <div class="mb-3">
        <label for="e" class="form-label">Email address</label>
        <input type="text" class="form-control" id="e" name="email" style="width: 50%" required>
    </div>
    <div class="mb-3">
        <label for="pass" class="form-label">Password</label>
        <input type="text" class="form-control" id="pass" name="password" style="width: 50%" required>
    </div>
    <input type="hidden" name="operation" value="ajouter">
    <button type="reset" class="btn btn-primary">Vider</button>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<a href="login.jsp">j'ai déjà un compte</a>
</div>
</body>
</html>