<%@page import="com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAOImpl"%>
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
    </head>
    <body>

        <!--Barra de navegacion-->
        <%@include file="navBarSuscriptor.html"%><br><br><br>
        <h5>Buscar Revista</h5>

        <!--Buscar Revista por categoria-->
        <form class="form-inline my-2 my-lg-0" method="POST">
            <input class="form-control mr-sm-2" type="search" id="name" name="name" placeholder="Buscar por categoria" aria-label="Search">
            <button class="btn btn-outline-success my-2 mr-sm-2" type="submit" id="buscar" name="buscar">Buscar</button>
        </form>
        <small id="passwordHelpBlock" class="form-text text-muted">
            Ingrese el nombre de una categoria o parte de el para buscar 
            <br>coincidencias, deje en blanco para ver todas las revistas.
        </small><br><br>

        <!--Tabla de revistas-->
        <h5>Lista de Revistas</h5>
        <%
            RevistaDAO revDAO = RevistaDAOImpl.getRevistaDAO();
            ResultSet rs = null;
            String cat;
            if (request.getParameter("name") != null) {
                cat = request.getParameter("name");
                rs = revDAO.getResultSetRevByCat(cat);
            }
        %>

        <table class="table w-75 p-3">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Editor</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">Precio</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <%
                    try {
                        while (rs.next()) {
                %><tr>
                    <th scope="row"><%=rs.getString(1)%></th>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td><button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#p<%=rs.getString(1)%>">Previsualizar</td>
                </tr><%
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                %>

            </tbody>
        </table>
                
        <%@include file="scripts.html" %>
        
        <!-- Modal -->
        <%
            EtiquetaDAO etiqDAO = EtiquetaDAOImpl.getEtiquetaDAO();
            if (rs != null) {
                rs.beforeFirst();
            }
            try {
                while (rs.next()) {
        %>
        <form action="suscripcionRevista.jsp" method="POST">
            <div class="modal fade" id="p<%=rs.getString(1)%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-scrollable" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalScrollableTitle"><%=rs.getString(2)%></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <b>Editor:</b> <%=rs.getString(3)%><br>
                            <b>Cuota Suscripcion:</b> 
                            <%
                                int c = Integer.parseInt(rs.getString(5));
                                if (c == 0) {
                                    %>Gratis<%
                                }else{
                                    %><%=rs.getString(5)%><%
                                }
                            %><br>
                            <b>Categoria:</b> <%=rs.getString(4)%><br>
                            <b>Descripcion:</b>
                            <%
                                if (rs.getString(6) == null) {
                                    %>Sin descripcion.<%
                                } else {
                                    %><%=rs.getString(6)%><%
                                }
                            %><br>
                            <b>Etiquetas:</b> <%=etiqDAO.getEtiquetasByRev(rs.getInt(1))%>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary" name="id" value="<%=rs.getString(1)%>" >Suscribirse</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <%
                }
            } catch(Exception e){}
        %>
    </body>
</html>
