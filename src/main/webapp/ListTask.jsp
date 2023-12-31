<%@ page import="com.example.todo.models.Tasks" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: seift
  Date: 21/12/2023
  Time: 17:58
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
<h3>nombre de personnes : <%= ((ArrayList<Tasks>)request.getAttribute("tasks")).size() %> </h3>
<table border="1">
  <tr>
    <th>ID</th>
    <th>NOM</th>
    <th>PRENOM</th>
    <th>NIVEAU</th>
    <th>MODIFIER</th>
    <th>SUPPRIMER</th>
  </tr>
  <% for(int i = 0; i < ((ArrayList<Tasks>)request.getAttribute("tasks")).size(); i++){
    out.print("<tr>");

    out.println("<td>"+((ArrayList<Tasks>)request.getAttribute("tasks")).get(i).getNameTask() +"</td>");
    out.println("<td>"+((ArrayList<Tasks>)request.getAttribute("tasks")).get(i).getDate() +"</td>");
    out.println("<td>"+((ArrayList<Tasks>)request.getAttribute("tasks")).get(i).getIsCom() +"</td>");

    out.println("<td><a href='edit.jsp?code="+((ArrayList<Tasks>)request.getAttribute("tasks")).get(i).getId()+"'>Modifier</a></td>");
    out.println("<td><a href='./?codeDel="+((ArrayList<Tasks>)request.getAttribute("tasks")).get(i).getId()+"' onclick='return confirm(\"Supprimer cette personne?\")'>Supprimer</a></td>");
    out.println("</tr>");
  }


  %>


</table>

</body>
</html>
