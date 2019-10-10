<%@page import="com.ashdz.webrevistas.model.Usuario"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAOImpl"%>
<%@page import="java.sql.ResultSet"%>
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
        <%@include file="scripts.html" %>
        
        <!--Barra de navegacion-->
        <%@include file="navBarSuscriptor.html"%><br><br><br>
        <h5>Suscripciones</h5><br>
        
        <%
            Usuario user = (Usuario)request.getSession().getAttribute("usuario");
            RevistaDAO revDAO = RevistaDAOImpl.getRevistaDAO();
            ResultSet rs = revDAO.getResultSetRevS(user.getEmailUsuario());
        %>
        
        <form action="revista.jsp" method="POST">
            <div class="row d-flex justify-content-center">
                <table class="table table-striped table-dark w-75 p-3">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nombre</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            try {
                                while (rs.next()) {
                        %><tr>
                            <th scope="row"><%=rs.getString(1)%></th>
                            <td><%=rs.getString(2)%></td>
                            <td><button type="submit" class="btn btn-success btn-block" name="id" value="<%=rs.getString(1)%>" >Ver</td>
                        </tr><%
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        %>

                    </tbody>
                </table>
            </div>
        </form>
        
    </body>
</html>