<%@page import="com.ashdz.webrevistas.model.Revista"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAOImpl"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAOImpl"%>
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
        
        <%
            RevistaDAO revDAO = RevistaDAOImpl.getRevistaDAO();
            if (request.getParameter("id") != null) {
                Revista r = revDAO.getObject((Object)Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("revista", r);
            }
        %>

        <!--Barra de navegacion-->
        <%@include file="navBarEditor.html"%><br><br><br>
        <h5>Etiquetar Revista: ${sessionScope.revista.nombreRevista}</h5><br>
        
        <%
            EtiquetaDAO etiqDAO = EtiquetaDAOImpl.getEtiquetaDAO();
            ResultSet rs = etiqDAO.getResultSetEtiqueta();
            
            %><form action="EtiquetarRevistaController" method="POST">&nbsp&nbsp&nbsp&nbsp<%
            while (rs.next()) {
                %><div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="etiquetas" value="<%=rs.getString("IdEtiqueta")%>">
                    <label class="form-check-label" for="inlineCheckbox1"><%=rs.getString("NombreEtiqueta")%></label>
                </div><%
                }
                %><c:if test="${requestScope['errorNull'] != null}">
                        <br>&nbsp&nbsp&nbsp&nbsp<small  class="text-danger">No se ha seleccionado ninguna etiqueta.</small>
                    </c:if>
                <div class="col">
                    <button type="submit" class="btn btn-primary">Etiquetar</button>
                </div>
            </form>


        <%@include file="scripts.html" %>
    </body>
</html>
