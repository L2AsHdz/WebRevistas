<%@page import="com.ashdz.webrevistas.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="bootstrap.html" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebRevistas</title>
    </head>
    <body>
        <%@include file="barraNavegacion.html" %>
        
        <%
            RequestDispatcher dispatcher;
            if (request.getSession().getAttribute("usuario") != null) {
                Usuario u = (Usuario) session.getAttribute("usuario");
                switch (u.getTipoUsuario()) {
                    case 1:
                        //dispatcher = request.getRequestDispatcher("inicioAdmin.jsp");
                        //dispatcher.forward(request, response);
                        response.sendRedirect("inicioAdmin.jsp");
                        break;
                    case 2:
                        //dispatcher = request.getRequestDispatcher("inicioEditor.jsp");
                        //dispatcher.forward(request, response);
                        response.sendRedirect("inicioEditor.jsp");
                        break;
                    case 3:
                        //dispatcher = request.getRequestDispatcher("inicioSuscriptor.jsp");
                        //dispatcher.forward(request, response);
                        response.sendRedirect("inicioSuscriptor.jsp");
                        break;
                }

            }
        %>
        
        <%@include file="scripts.html" %>
    </body>
</html>
