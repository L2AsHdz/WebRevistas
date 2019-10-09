<%-- 
    Document   : buscarRevistaE
    Created on : 8/10/2019, 10:05:21 AM
    Author     : asael
--%>

<%@page import="com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAO"%>
<%@page import="java.sql.ResultSet"%>
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
        <h5>Buscar Revista</h5>


        <!--Buscar Revista por etiquetas-->
        <%
            EtiquetaDAO etiqDAO = EtiquetaDAOImpl.getEtiquetaDAO();
            ResultSet rs = etiqDAO.getResultSetEtiqueta();

        %><form action="BuscarRevistaEController" method="POST">&nbsp&nbsp&nbsp&nbsp
            <%while (rs.next()) {
            %><div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" name="etiquetas" value="<%=rs.getString("IdEtiqueta")%>">
                <label class="form-check-label" for="inlineCheckbox1"><%=rs.getString("NombreEtiqueta")%></label>
            </div><%
                }
            %><c:if test="${requestScope['errorNull'] != null}">
                <br>&nbsp&nbsp&nbsp&nbsp<small  class="text-danger">No se ha seleccionado ninguna etiqueta.</small>
            </c:if>
            <div class="col">
                <button type="submit" class="btn btn-primary">Buscar</button>
            </div>
        </form><br>

        <!--Tabla de revistas-->
        <h5>Lista de Revistas</h5>
        <%!private String sql = null;%>
        <%
            ResultSet rs2 = null;
            if (request.getAttribute("sql") != null) {
                sql = request.getAttribute("sql").toString();
                rs2 = etiqDAO.getResultSetRev(sql);
            }
        %>

        <form action="buscarRevistaE.jsp" method="POST">
            <table class="table w-75 p-3">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Editor</th>
                        <th scope="col">Precio</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        try {
                            while (rs2.next()) {
                    %><tr>
                        <th scope="row"><%=rs2.getString(1)%></th>
                        <td><%=rs2.getString(2)%></td>
                        <td><%=rs2.getString(3)%></td>
                        <td><%=rs2.getString(4)%></td>
                        <td><button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#preview<%=rs2.getString(1)%>" name="id" value="<%=rs2.getString(1)%>">Previsualizar</button></td>
                    </tr><%}
                        } catch (Exception e) {}%>

                </tbody>
            </table>
        </form>

        <!-- Modal -->
        <%
            ResultSet rs3 = null;
            String etiquetas;
            if (sql != null) {
                rs3 = etiqDAO.getResultSetRev2(sql);
            }
            try {
                while (rs3.next()) {
        %>
        <div class="modal fade" id="preview<%=rs3.getString(1)%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalScrollableTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalScrollableTitle"><%=rs3.getString(2)%></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <b>Editor:</b> <%=rs3.getString(3)%><br>
                        <b>Cuota Suscripcion:</b> 
                        <%
                            int c = Integer.parseInt(rs3.getString(5));
                            if (c == 0) {
                                %>Gratis<%
                            }else{
                                %><%=rs3.getString(5)%><%
                            }
                        %><br>
                        <b>Categoria:</b> <%=rs3.getString(6)%><br>
                        <b>Descripcion:</b>
                        <%
                            if (rs3.getString(4) == null) {
                                %>Sin descripcion.<%
                            } else {
                                %><%=rs3.getString(4)%><%
                            }
                        %><br>
                        <b>Etiquetas:</b> <%=etiqDAO.getEtiquetasByRev(rs3.getInt(1))%>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Suscribirse</button>
                    </div>
                </div>
            </div>
        </div>
        <%
                }
            } catch(Exception e){}
        %>
    </body>
</html>
