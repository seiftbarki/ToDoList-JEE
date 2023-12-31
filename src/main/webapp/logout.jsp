<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<%


    // Invalidater la session s'il y en a une
    if (session != null) {
        session.invalidate();
    }

    // Rediriger vers la page de connexion (ou toute autre page appropriée après la déconnexion)
    response.sendRedirect("login.jsp");
%>

