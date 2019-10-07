<%-- 
    Document   : buscarRevistaC
    Created on : 7/10/2019, 07:39:50 AM
    Author     : asael
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
        <h5>Buscar Revista</h5>

        <!--Buscar Revista por categoria-->
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" id="name" name="name" placeholder="Buscar por categoria" aria-label="Search">
            <button class="btn btn-outline-success my-2 mr-sm-2" type="submit" id="buscar" name="buscar">Buscar</button>
        </form>
        <small id="passwordHelpBlock" class="form-text text-muted">
            Ingrese el nombre de una categoria o parte de el para buscar 
            <br>coincidencias, deje en blanco para ver todas las revistas.
        </small><br><br>

        <!--Tabla de revistas-->
        <h5>Lista de Revistas</h5>
        <%
            //Usuario user = (Usuario) request.getSession().getAttribute("usuario");
            RevistaDAO revDAO = RevistaDAOImpl.getRevistaDAO();
            ResultSet rs = null;
            String cat;
            //System.out.println(user.getEmailUsuario());
            if (request.getParameter("name") != null) {
                cat = request.getParameter("name");
                rs = revDAO.getResultSetRevByCat(cat);
            }
        %>

        <table class="table w-75 p-3">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Editor</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">PrecioSuscripcion</th>
                </tr>
            </thead>
            <tbody>
                <%
                    try {
                        while (rs.next()) {
                %><tr onclick="document.location = 'index.jsp'">
                    <th scope="row"><%=rs.getString(1)%></th>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                </tr><%
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                %>

            </tbody>
        </table>

        <%@include file="scripts.html" %>
    </body>
</html>
