<%-- 
    Document   : etiquetas
    Created on : 6/10/2019, 08:20:11 AM
    Author     : asael
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAO"%>
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
        <h5>Agregar etiqueta</h5>

        <!--Formulario crearEtiqueta-->
        <form class="needs-validation" novalidate action="EtiquetaController" method="POST">
            <div class="form-row align-items-center">
                <div class="col-sm-4 my-1">
                    <input type="text" class="form-control" id="etiqueta" name="etiqueta" placeholder="Nombre Etiqueta">
                    <div class="invalid-feedback" id="errorEtiq" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorEtiq").innerHTML = "El campo no puede estar vacio.";
                            document.getElementById("etiqueta").className += " is-invalid";
                        </script>
                    </c:if>
                </div>
                <div class="col-auto my-1">
                    <button type="submit" class="btn btn-primary">Agregar</button>
                </div>
            </div>
        </form><br>


        <h5>Listado de etiquetas</h5>

        <%
            EtiquetaDAO etiquetaDAO = EtiquetaDAOImpl.getEtiquetaDAO();
            ResultSet rs = etiquetaDAO.getResultSetEtiqueta();
        %>

        <!--Tabla etiquetas-->
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
                    <td><%=rs.getString("NombreEtiqueta")%></td>
                </tr><%
                    }
                %>

            </tbody>
        </table>

        <%@include file="scripts.html" %>
    </body>
</html>
