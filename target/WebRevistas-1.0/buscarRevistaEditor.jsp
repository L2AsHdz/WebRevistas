<%@page import="com.ashdz.webrevistas.model.Usuario"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAOImpl"%>
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
        
        <c:if test="${requestScope['succesE'] != null}">
            <script>
                alert("Revista etiquetada exitosamente");
            </script>
            <%
                request.getSession().removeAttribute("revista");
            %>
        </c:if>

        <!--Barra de navegacion-->
        <%@include file="navBarEditor.html"%><br><br><br>
        <h5>Buscar revista a etiquetar</h5>

        <!--Formulario Buscar Revista-->
        <form class="form-inline my-2 my-lg-0" action="buscarRevistaEditor.jsp" method="POST">
            <input class="form-control mr-sm-2" type="search" id="name" name="name" placeholder="Buscar por nombre" aria-label="Search">
            <button class="btn btn-outline-success my-2 mr-sm-2" type="submit" id="buscar" name="buscar">Buscar</button>
        </form>
        <small id="passwordHelpBlock" class="form-text text-muted">
            Ingrese el nombre de una revista o parte de el para buscar 
            <br>coincidencias, deje en blanco para ver todas las revistas.
        </small><br><br>

        <!--Tabla de revistas-->
        <h5>Lista de Revistas</h5>
        <%
            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
            RevistaDAO revDAO = RevistaDAOImpl.getRevistaDAO();
            ResultSet rs = null;
            String like;
            System.out.println(user.getEmailUsuario());
            if (request.getParameter("name") != null) {
                like = request.getParameter("name");
                rs = revDAO.getResultSetSearch(like,user.getEmailUsuario());
            }
        %>
        
        <form action="etiquetarRevista.jsp" method="POST">
        <table class="table w-75 p-3">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Editor</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">Precio</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <%
                    try {
                        while (rs.next()) {
                %><tr>
                    <td><%=rs.getString(1)%></th>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td><button type="submit" class="btn btn-outline-info" name="id" value="<%=rs.getString(1)%>">Etiquetar</button></td>
                </tr><%
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                %>

            </tbody>
        </table></form>
	
        <%@include file="scripts.html" %>
    </body>
</html>
