<%--
  Created by IntelliJ IDEA.
  User: seift
  Date: 19/12/2023
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>AUTH</h1>
<form action="" method="post">

    <p>
        <label for="email">EMAIL</label>
        <input type="text" id="email" name="email">
    </p>
    <p>
        <label for="pwd">PASSWORD</label>
        <input type="text" id="pwd" name="password">
    </p>
    <p>
        <input type="hidden" name="operation" value="ajouter">
        <button type="reset">Vider le formulaire</button>
        <button type="submit">Enregistrer</button>

    </p>
</form>



</body>
</html>
