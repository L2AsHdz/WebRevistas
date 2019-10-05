<%-- 
    Document   : index
    Created on : 2/10/2019, 02:32:40 PM
    Author     : asael
--%>

<%@page import="com.ashdz.webrevistas.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <%
            if (session.getAttribute("usuario") != null) {
                Usuario u = (Usuario) session.getAttribute("usuario");
                switch (u.getTipoUsuario()) {
                    case 1:
                        response.sendRedirect("inicioAdmin.jsp");
                        break;
                    case 2:
                        response.sendRedirect("inicioEditor.jsp");
                        break;
                    case 3:
                        response.sendRedirect("inicioSuscriptor.jsp");
                        break;
                }

            }
        %>
        <%@include file="bootstrap.html" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebRevistas</title>
    </head>
    <body>
        <%@include file="barraNavegacion.html" %>
        <%@include file="scripts.html" %>
    </body>
</html>
