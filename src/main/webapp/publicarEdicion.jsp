<%@page import="com.ashdz.webrevistas.model.Usuario"%>
<%@page import="com.ashdz.webrevistas.DAO.Publicacion.PublicacionDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Publicacion.PublicacionDAOImpl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="bootstrap.html" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebRevistas - ${sessionScope.usuario.usuarioSistema}</title>
    <body>

        <!--Barra de navegacion-->
        <%@include file="navBarEditor.html"%><br><br><br>
        <h5>Publicar Edicion</h5>

        <%
            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
            RevistaDAO revDAO = RevistaDAOImpl.getRevistaDAO();
            ResultSet rs = revDAO.getResultSetRevAll(user.getEmailUsuario());
        %>

        <!--Formuario Publicacion Edicion-->
        <form class="needs-validation" novalidate action="PublicacionController" method="POST" enctype = "multipart/form-data" accept="application/pdf">
            <div class="form-group">

                <!--Select Revista-->
                <div class="col-sm-7">
                    <label for="revista">Revista</label>
                    <select class="custom-select" id="revista" name="revista" required>
                        <option value="0">Elegir...</option>
                        <%
                            while (rs.next()) {
                                %><option value=<%=rs.getString(1)%>><%=rs.getString(1)%>  -  <%=rs.getString(2)%></option><%
                            }
                        %>
                    </select>
                    <div class="invalid-feedback" id="errorRev" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorRev").innerHTML = "Debe escoger una revista.";
                            document.getElementById("revista").className += " is-invalid";
                        </script>
                    </c:if>
                </div><br>


                <!--Input Fecha-->
                <div class="col-sm-7">
                    <label for="edicion">Numero Edicion</label>
                    <input class="form-control" id="edicion" name="edicion" readonly>
                    <div class="invalid-feedback" id="errorEdicion" ></div>
                </div><br>
                
                <%
                    PublicacionDAO publiDAO = PublicacionDAOImpl.getPublicacionDAO();
                    if (request.getParameter("revista") != null) {
                        int id = Integer.parseInt(request.getParameter("revista"));
                        request.setAttribute("newEdicion", publiDAO.getNoEdicionSig(id));
                        System.out.println("${request.newEdicion}");
                        %><script>
                            document.getElementById("edicion").value = "${request.newEdicion}";
                        </script><%
                    }
                %>

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
                    <br><label for="revista">Revista</label>
                </div>

                <!--Eleccion de archivo-->&nbsp;&nbsp;&nbsp;
                <div class="custom-file col-sm-7">
                    <input type="file" class="custom-file-input" id="archivo" name="archivo" accept=".pdf">
                    <label class="custom-file-label" for="archivo">Seleccionar Archivo(pdf)</label>
                    <div class="invalid-feedback" id="errorFile" ></div>
                    <c:if test="${requestScope['errorEmpty'] != null}">
                        <script>
                            document.getElementById("errorFile").innerHTML = "No ha escogido archivo.";
                            document.getElementById("archivo").className += " is-invalid";
                        </script>
                    </c:if>
                </div>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary">Publicar</button>
            </div>
        </form><br><br>


        <%@include file="scripts.html" %>
    </body>
</html>
