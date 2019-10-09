<%@page import="java.sql.ResultSet"%>
<%@page import="com.ashdz.webrevistas.DAO.Categoria.CategoriaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Categoria.CategoriaDAOImpl"%>
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
        <%@include file="navBarEditor.html"%><br><br><br>
        <h5>Crear Cateoria</h5>

        <!--Formulario crearRevista-->
        <form class="needs-validation" novalidate action="CategoriaController" method="POST">
            <div class="form-row align-items-center">
                <div class="col-sm-4 my-1">
                    <input type="text" class="form-control" id="catego" name="catego" placeholder="Nombre Categoria">
                    <div class="invalid-feedback" id="errorCat" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorCat").innerHTML = "El campo no puede estar vacio.";
                            document.getElementById("catego").className += " is-invalid";
                        </script>
                    </c:if>
                </div>
                <div class="col-auto my-1">
                    <button type="submit" class="btn btn-primary">Agregar</button>
                </div>
            </div>
        </form><br>


        <h5>Listado de categorias</h5>

        <%
            CategoriaDAO categoDAO = CategoriaDAOImpl.getCategoriaDAO();
            ResultSet rs = categoDAO.getResultSet();
        %>

        <!--Tabla categorias-->
        <table class="table w-75 p-3">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nombre</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int i = 1;
                    while (rs.next()) {
                %><tr>
                    <th scope="row"><%=(i++)%></th>
                    <td><%=rs.getString("NombreCategoria")%></td>
                </tr><%
                    }
                %>

            </tbody>
        </table>

        <%@include file="scripts.html" %>
    </body>
</html>