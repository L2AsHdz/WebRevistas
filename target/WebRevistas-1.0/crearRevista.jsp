<%-- 
    Document   : crearRevista
    Created on : 6/10/2019, 08:52:08 AM
    Author     : asael
--%>

<%@page import="com.ashdz.webrevistas.model.Usuario"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAOImpl"%>
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
        <h5>Crear Revista</h5>
        
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
                    <label for="idCategoria">Categoria</label>
                    <select class="custom-select" id="idCategoria" name="idCategoria" required>
                        <option value="0">Elegir...</option>
                        <%
                            while (rs.next()) {
                                %><option value=<%=rs.getString("IdCategoria")%>><%=rs.getString("NombreCategoria")%></option><%
                            }
                        %>
                    </select>
                    <div class="invalid-feedback" id="errorCat" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorCat").innerHTML = "Debe escoger una categoria.";
                            document.getElementById("idCategoria").className += " is-invalid";
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
                            document.getElementById("errorCuota").innerHTML = "Ingrese 0 si desea que sea gratis la suscripcion.";
                            document.getElementById("cuota").className += " is-invalid";
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
        </form><br><br>
                    
        
        <%
            Usuario user = (Usuario)request.getSession().getAttribute("usuario");
            RevistaDAO revDAO = RevistaDAOImpl.getRevistaDAO();
            ResultSet rs2 = revDAO.getResultSetRev(user.getEmailUsuario());
        %>
        
        <h5>Listado Revistas</h5>
        <!--Tabla de revistas-->
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Editor</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">PrecioSuscripcion</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int i = 1;
                    while (rs2.next()) {
                %><tr>
                    <th scope="row"><%=rs2.getString(1)%></th>
                    <td><%=rs2.getString(2)%></td>
                    <td><%=rs2.getString(3)%></td>
                    <td><%=rs2.getString(4)%></td>
                    <td><%=rs2.getString(5)%></td>
                </tr><%
                    }
                %>

            </tbody>
        </table>
        

        <%@include file="scripts.html" %>
    </body>
</html>
