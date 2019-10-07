<%-- 
    Document   : inicioSuscriptor
    Created on : 5/10/2019, 12:13:37 AM
    Author     : asael
--%>

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
        <%@include file="navBarSuscriptor.html"%><br><br><br>

        Bienvenid@ ${sessionScope.usuario.usuarioSistema}

        <%@include file="scripts.html" %>
    </body>
</html>
