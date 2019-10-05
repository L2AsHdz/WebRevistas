<%-- 
    Document   : CostoRevista
    Created on : 5/10/2019, 01:35:18 PM
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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


        <!--Formulario CostoGlobal-->
        <h5>Costo global de una revista</h5>
        <form class="needs-validation" novalidate action="CostoRevista" method="POST">
            <div class="form-group">
                <div class="col">
                    <label for="costoG">Costo por dia</label>
                    <input type="number" class="form-control" id="costoG" name="costoG" placeholder="Costo global por dia" value="" required>
                    <c:if test="${requestScope['errorUser'] != null}">
                        <script>document.getElementById("user").value = "${errorUser}";</script>
                        <script>document.getElementById("user").className += " is-invalid";</script>
                        <div class="invalid-feedback">
                            Usuario no existe.
                        </div>
                    </c:if>
                </div><br>
                <div class="col">
                    <button type="submit" class="btn btn-primary">Establecer</button>
                </div>
            </div>
        </form>

        <!--Formulario CostoRevista-->
        <h5>Costo revista</h5>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Buscar por nombre" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <form class="needs-validation" novalidate action="CostoRevista" method="POST">
            <div class="form-group">
                <div class="col">
                    <label for="costoG">Costo por dia</label>
                    <input type="number" class="form-control" id="costoG" name="costoG" placeholder="Costo global por dia" value="" required>
                    <c:if test="${requestScope['errorUser'] != null}">
                        <script>document.getElementById("user").value = "${errorUser}";</script>
                        <script>document.getElementById("user").className += " is-invalid";</script>
                        <div class="invalid-feedback">
                            Usuario no existe.
                        </div>
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
