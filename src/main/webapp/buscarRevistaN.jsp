<%@page import="java.sql.ResultSet"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAO"%>
<!--Formulario CostoRevista-->
<form class="form-inline my-2 my-lg-0">
    <input class="form-control mr-sm-2" type="search" id="name" name="name" placeholder="Buscar por nombre" aria-label="Search">
    <button class="btn btn-outline-success my-2 mr-sm-2" type="submit" id="buscar" name="buscar">Buscar</button>
</form>
<small id="passwordHelpBlock" class="form-text text-muted">
    Ingrese el nombre de una revista o parte de el para buscar 
    <br>coincidencias, deje en blanco para ver todas las revistas.
</small><br><br>

<!--Tabla de revistas-->
<h5>Lista de Revistas</h5>

<%
    RevistaDAO revDAO = RevistaDAOImpl.getRevistaDAO();
    ResultSet rs = null;
    String name;
    if (request.getParameter("name") != null) {
        name = request.getParameter("name");
        rs = revDAO.getResultSetSearchAll(name);
    }
%>

<table class="table w-75 p-3">
    <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Nombre</th>
            <th scope="col">Editor</th>
            <th scope="col">Categoria</th>
            <th scope="col">PrecioSuscripcion</th>
        </tr>
    </thead>
    <tbody>
        <%
            try {
                while (rs.next()) {
        %><tr onclick="document.location = 'index.jsp'">
            <th scope="row"><%=rs.getString(1)%></th>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(3)%></td>
            <td><%=rs.getString(4)%></td>
            <td><%=rs.getString(5)%></td>
        </tr><%
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        %>

    </tbody>
</table><br><br>