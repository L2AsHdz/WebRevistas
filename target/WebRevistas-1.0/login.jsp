<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="bootstrap.html" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebRevistas - Login</title>
    </head>
    <body>
        <!--Barra de navegacion-->
        <%@include file="barraNavegacion.html" %><br><br><br>

        <h3>Login</h3><br>

        <!--Formulario Login-->
        <form class="needs-validation" novalidate action="ValidarLogin" method="POST">
            <div class="form-group">
                <div class="col">
                    <label for="user">Usuario</label>
                    <input class="form-control" id="user" name="user" placeholder="Email o usuario" value="${succes.usuarioSistema}" required>
                    <c:if test="${requestScope['errorUser'] != null}">
                        <script>document.getElementById("user").value = "${errorUser}";</script>
                        <script>document.getElementById("user").className += " is-invalid";</script>
                        <div class="invalid-feedback">
                            Usuario no existe.
                        </div>
                    </c:if>
                </div><br>
                <div class="col">
                    <label for="pass">Password</label>
                    <input type="password" class="form-control" id="pass" name="pass" placeholder="Ingrese password" value="${succes.password}" required>
                    <c:if test="${requestScope['errorPass'] != null}">
                        <script>document.getElementById("user").value = "${errorPass}";</script>
                        <script>document.getElementById("pass").className += " is-invalid";</script>
                        <div class="invalid-feedback">
                            Contraseña incorrecta.
                        </div>
                    </c:if>
                </div><br>
                <div class="col">
                    <button type="submit" class="btn btn-primary">Iniciar sesion</button>
                </div>
            </div>
        </form>

        <%@include file="scripts.html" %>
    </body>
</html>
