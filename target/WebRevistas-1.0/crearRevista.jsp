<%-- 
    Document   : crearRevista
    Created on : 6/10/2019, 08:52:08 AM
    Author     : asael
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="com.ashdz.webrevistas.DAO.Categoria.CategoriaDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Categoria.CategoriaDAO"%>
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
        
        <%
            CategoriaDAO categoDAO = CategoriaDAOImpl.getCategoriaDAO();
            ResultSet rs = categoDAO.getResultSet();
        %>

        <!--Formulario crearRevista-->
        <form class="needs-validation" novalidate action="RevistaController" method="POST">
            <div class="form-group">

                <!--Input NombreRevista-->
                <div class="col-sm-7">
                    <label for="nombre">Nombre</label>
                    <input class="form-control" id="nombre" name="nombre" placeholder="Nombre Revista" required>
                    <div class="invalid-feedback" id="errorNombre" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorNombre").innerHTML = "El campo es obligatorio.";
                            document.getElementById("nombre").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>
                
                <!--Select Categoria-->
                <div class="col-sm-7">
                    <label for="tipoUser">Categoria</label>
                    <select class="custom-select" id="tipoUser" name="tipoUser" required>
                        <option value="0">Elegir...</option>
                        <%
                            while (rs.next()) {
                                %><option value=<%=rs.getString("IdCategoria")%>><%=rs.getString("NombreCategoria")%></option><%
                            }
                        %>
                    </select>
                    <div class="invalid-feedback" id="errorTipo" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorTipo").innerHTML = "Debe escoger un tipo de usuario.";
                            document.getElementById("tipoUser").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>

                <!--Input Cuota Suscripcion-->
                <div class="col-sm-7">
                    <label for="cuota">Cuota Suscripcion</label>
                    <input type="number" class="form-control" id="cuota" name="cuota" placeholder="Cuota que se cobrar al usuario" required>
                    <div class="invalid-feedback" id="errorCuota" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorCuota").innerHTML = "El campo es obligatorio.";
                            document.getElementById("nombre").className += " is-invalid";
                        </script>
                    </c:if>

                    <c:if test="${requestScope['errorNumber'] != null}">
                        <script>
                            document.getElementById("errorCuota").innerHTML = "No es un numero";
                            document.getElementById("nombre").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>

                <!--Input Descripcion-->
                <div class="col-sm-7">
                    <label for="descrip">Descripcion</label>
                    <textarea class="form-control" id="descrip" rows="3" placeholder="Descripcion de la revista"></textarea>
                    <div class="invalid-feedback" id="errorDescrip" ></div>
                </div>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </div>
        </form>

        <%@include file="scripts.html" %>
    </body>
</html>
