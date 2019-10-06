<%-- 
    Document   : porcenAdmin
    Created on : 6/10/2019, 05:54:24 AM
    Author     : asael
--%>

<%@page import="com.ashdz.webrevistas.DAO.Precios.PrecioDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Precios.PrecioDAO"%>
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
        <%@include file="navBarAdmin.html"%><br><br><br>
        
        <%
            PrecioDAO precioDAO = PrecioDAOImpl.getPrecioDAO();
        %>

        <!--Formulario CostoGlobal-->
        <h5>Costo global de una revista</h5>
        <form class="needs-validation" novalidate action="PorcentajeController" method="POST">
            <div class="form-group">
                <div class="col">
                    <label for="porcentajeA">Porcentaje cobrado por suscripcion</label>
                    <input type ="number" class="form-control" id="porcentajeA" name="porcentajeA" placeholder="Porcentaje por siscripcion cobrado" value="<%=precioDAO.getPrecio(2)%>" required>
                    <div class="invalid-feedback" id="errorPercent" ></div>
                    <c:if test="${requestScope['errorNumber'] != null}">
                        <script>
                            document.getElementById("errorPercent").innerHTML = "El valor debe ser decimal.";
                            document.getElementById("porcentajeA").className += " is-invalid";
                        </script>
                    </c:if>
                    
                    <c:if test="${requestScope['errorMax'] != null}">
                        <script>
                            document.getElementById("errorPercent").innerHTML = "El valor tiene que ser menor a 0.60";
                            document.getElementById("porcentajeA").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>
                <div class="col">
                    <button type="submit" class="btn btn-primary">Establecer</button>
                </div>
            </div>
        </form>

        <%@include file="scripts.html" %>
    </body>
</html>
