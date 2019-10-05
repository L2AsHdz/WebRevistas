<%-- 
    Document   : Registro
    Created on : 2/10/2019, 11:46:11 PM
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="bootstrap.html" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebRevistas - Registro</title>
        <script type="text/javascript" src="validarRegistro.js"></script>
    </head>
    <body>
        <!--Barra de navegacion-->
        <%@include file="barraNavegacion.html" %><br><br><br>

        <h3>Registrarse</h3><br>
        <!--Formulario-->
        <form class="needs-validation" novalidate action="RegistroUsuarioController" method="POST">
            <div class="form-group">
                <div id="divNombre"class="col-3">
                    <label for="nombre">Nombre</label>
                    <input class="form-control" id="nombre" name="nombre" placeholder="Nombre completo" required>
                    <div class="invalid-feedback" id="errorNombre" ></div>
                </div><br>
                <div class="col-3">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="example@gmail.com" required>
                    <div class="invalid-feedback" id="errorEmail" ></div>
                </div><br>
                <div class="col-3">
                    <label for="userS">Nombre de usuario</label>
                    <input class="form-control" id="userS" name="userS" placeholder="User123" required>
                    <div class="invalid-feedback" id="errorUserS" ></div>
                </div><br>
                <div class="col-3">
                    <label for="pass">Password</label>
                    <input type="password" class="form-control" id="pass" name="pass" required>
                    <div class="invalid-feedback" id="errorPass" ></div>
                </div><br>
                <div class="col-3">
                    <label for="pass2">Confirmar Password</label>
                    <input type="password" class="form-control" id="pass2" name="pass2" required>
                    <div class="invalid-feedback" id="errorPass2" ></div>
                </div><br>
                <div class="col-3">
                    <label for="tipoUser">Tipo de usuario</label>
                    <select class="custom-select" id="tipoUser" name="tipoUser" required>
                        <option value="0">Elegir...</option>
                        <option value="1">Editor</option>
                        <option value="2">Suscriptor</option>
                    </select>
                    <div class="invalid-feedback" id="errorTipo" ></div>
                </div>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </div>
        </form>

        
        <%@include file="scripts.html" %>
    </body>
</html>
