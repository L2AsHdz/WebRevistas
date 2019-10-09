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

                <!--Input Nombre-->
                <div class="col">
                    <label for="nombre">Nombre</label>
                    <input class="form-control" id="nombre" name="nombre" placeholder="Nombre completo" required>
                    <div class="invalid-feedback" id="errorNombre" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorNombre").innerHTML = "El campo es obligatorio.";
                            document.getElementById("nombre").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>

                <!--Input Email-->
                <div class="col">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="example@gmail.com" required>
                    <div class="invalid-feedback" id="errorEmail" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorEmail").innerHTML = "El campo es obligatorio.";
                            document.getElementById("email").className += " is-invalid";
                        </script>
                    </c:if>

                    <c:if test="${requestScope['errorExiste'] != null}">
                        <script>
                            document.getElementById("errorEmail").innerHTML = "El usuario ya existe.";
                            document.getElementById("email").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>

                <!--Input NombreUsuario-->
                <div class="col">
                    <label for="userS">Nombre de usuario</label>
                    <input class="form-control" id="userS" name="userS" placeholder="Ingrese nombre de usuario" required>
                    <div class="invalid-feedback" id="errorUserS" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorUserS").innerHTML = "El campo es obligatorio.";
                            document.getElementById("userS").className += " is-invalid";
                        </script>
                    </c:if>

                    <c:if test="${requestScope['errorExiste'] != null}">
                        <script>
                            document.getElementById("errorUserS").innerHTML = "El usuario ya existe.";
                            document.getElementById("userS").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>

                <!--Input Pass-->
                <div class="col">
                    <label for="pass">Password</label>
                    <input type="password" class="form-control" id="pass" name="pass" placeholder="password" required>
                    <div class="invalid-feedback" id="errorPass" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorPass").innerHTML = "El campo es obligatorio.";
                            document.getElementById("pass").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>

                <!--Input Pass2-->
                <div class="col">
                    <label for="pass2">Confirmar Password</label>
                    <input type="password" class="form-control" id="pass2" name="pass2" placeholder="confirmar password" required>
                    <div class="invalid-feedback" id="errorPass2" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorPass2").innerHTML = "El campo es obligatorio.";
                            document.getElementById("pass2").className += " is-invalid";
                        </script>
                    </c:if>

                    <c:if test="${requestScope['errorPass'] != null}">
                        <script>
                            document.getElementById("errorPass2").innerHTML = "Las contraseñas no coinciden.";
                            document.getElementById("pass").className += " is-invalid";
                            document.getElementById("pass2").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>

                <!--Select TipoUsuario-->
                <div class="col">
                    <label for="tipoUser">Tipo de usuario</label>
                    <select class="custom-select" id="tipoUser" name="tipoUser" required>
                        <option value="0">Elegir...</option>
                        <option value="1">Editor</option>
                        <option value="2">Suscriptor</option>
                    </select>
                    <div class="invalid-feedback" id="errorTipo" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorTipo").innerHTML = "Debe escoger un tipo de usuario.";
                            document.getElementById("tipoUser").className += " is-invalid";
                        </script>
                    </c:if>
                </div>
            </div>

            <div class="col">
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </div>
        </form>


        <%@include file="scripts.html" %>
    </body>
</html>
