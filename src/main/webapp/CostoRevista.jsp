<%@page import="java.sql.ResultSet"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Precios.PrecioDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Precios.PrecioDAOImpl"%>
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
        <form class="needs-validation" novalidate action="CostoRevistaController" method="POST">
            <div class="form-group">
                <div class="col">
                    <label for="costoG">Porcentaje costo por dia</label>
                    <input type ="number" class="form-control" id="costoG" name="costoG" placeholder="Costo global por dia" value="<%=precioDAO.getPrecio(1)%>" required>
                    <div class="invalid-feedback" id="errorCosto" ></div>
                    <c:if test="${requestScope['errorNumber'] != null}">
                        <script>
                            document.getElementById("errorCosto").innerHTML = "El valor debe ser decimal.";
                            document.getElementById("costoG").className += " is-invalid";
                        </script>
                    </c:if>

                    <c:if test="${requestScope['errorMax'] != null}">
                        <script>
                            document.getElementById("errorCosto").innerHTML = "El valor tiene que ser menor a 0.40";
                            document.getElementById("costoG").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>
                <div class="col">
                    <button type="submit" class="btn btn-primary">Establecer</button>
                </div>
            </div>
        </form>
        
        <!--BuscarPorNombre & Tabla Revistas-->
        <h5>Costo por revista</h5>
        <%@include file="buscarRevistaN.jsp" %>
                
        <form class="needs-validation" novalidate action="PorcentajeAdmin" method="POST">
            <div class="form-group">
                <div class="col">
                    <label for="costoG">Costo por dia</label>
                    <input type="number" class="form-control" id="costoG" name="costoG" placeholder="Costo global por dia" value="" disabled required>
                    <c:if test="${requestScope['errorUser'] != null}">
                        <script>document.getElementById("user").value = "${errorUser}";</script>
                        <script>document.getElementById("user").className += " is-invalid";</script>
                        <div class="invalid-feedback">
                            Usuario no existe.
                        </div>
                    </c:if>
                </div><br>
                <div class="col">
                    <button type="submit" class="btn btn-primary" disabled>Establecer</button>
                </div>
            </div>
        </form>

        <%@include file="scripts.html" %>
    </body>
</html>
