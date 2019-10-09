<%@page import="com.ashdz.webrevistas.model.Revista"%>
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
        <%@include file="scripts.html" %>

        <!--Barra de navegacion-->
        <%@include file="navBarSuscriptor.html"%><br><br><br>
        <h5>Suscripcion Revista</h5>

        <!--Formulario Suscripcion-->
        <%
            int idR = Integer.parseInt(request.getParameter("id"));
            RevistaDAO revDAO = RevistaDAOImpl.getRevistaDAO();
            Revista rev = revDAO.getObject(idR);
            request.getSession().setAttribute("revista", rev);

        %>
        <form class="needs-validation" novalidate action="SuscribirController" method="POST">
            <div class="form-group">
                
                <!--Revista-->
                <div class="col-sm-7">
                    <label for="revista">Revista</label>
                    <input class="form-control" id="revista" name="revista" value="<%=rev.getNombreRevista()%>" readonly>
                    <div class="invalid-feedback" id="errorEdicion" ></div>
                </div><br>
                
                <!--Input Fecha-->
                <div class="col-sm-7">
                    <label for="fecha">Fecha</label>
                    <input type="date" class="form-control" id="fecha" name="fecha" required>
                    <div class="invalid-feedback" id="errorFecha" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorFecha").innerHTML = "No ha seleccionado una fecha.";
                            document.getElementById("fecha").className += " is-invalid";
                        </script>
                    </c:if>
                </div>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary" name="idR" value="<%=rev.getIdRevista()%>">Suscribirse</button>
            </div>
        </form><br><br>

    </body>
</html>
