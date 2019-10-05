<%-- 
    Document   : inicioAdmin
    Created on : 5/10/2019, 12:13:11 AM
    Author     : asael
--%>

<%@page import="com.ashdz.webrevistas.model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="bootstrap.html" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebRevistas - ${sessionScope.usuario.usuarioSistema}</title>
    </head>
    <body>
        
        <!--Barra de navegacion-->
        <%@include file="navBarAdmin.html"%><br><br><br>
        
        Bienvenido ${sessionScope.usuario.usuarioSistema}
        
        <%@include file="scripts.html" %>
    </body>
</html>