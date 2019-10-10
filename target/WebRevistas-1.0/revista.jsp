<%@page import="java.time.LocalDate"%>
<%@page import="com.ashdz.webrevistas.model.Usuario"%>
<%@page import="com.ashdz.webrevistas.DAO.Like.LikeDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Like.LikeDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Categoria.CategoriaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Categoria.CategoriaDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Usuario.UsuarioDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Usuario.UsuarioDAOImpl"%>
<%@page import="com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Etiqueta.EtiquetaDAOImpl"%>
<%@page import="com.ashdz.webrevistas.model.Revista"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Revista.RevistaDAOImpl"%>
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

        <%
            int id = 0;
            if (request.getSession().getAttribute("idRev") != null) {
                id = (Integer) request.getSession().getAttribute("idRev");
                request.getSession().removeAttribute("idRev");
            } else {
                id = Integer.parseInt(request.getParameter("id"));
            }
            RevistaDAO revDAO = RevistaDAOImpl.getRevistaDAO();
            UsuarioDAO userDAO = UsuarioDAOImpl.getUserDAO();
            LikeDAO likeDAO = LikeDAOImpl.getLikeDAO();
            CategoriaDAO categoDAO = CategoriaDAOImpl.getCategoriaDAO();
            EtiquetaDAO etiqDAO = EtiquetaDAOImpl.getEtiquetaDAO();
            Revista rev = revDAO.getObject(id);
            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        %>

        <h5><%=rev.getNombreRevista()%></h5>
        <div class="">
            <div class="col">
                Editor: <%=userDAO.getByEmail(rev.getEmailEditor()).getNombreUsuario()%>
            </div>
            <div class="col">
                Categoria:  <%=categoDAO.getObject(rev.getIdCategoria()).getNombre()%>
            </div>
            <div class="col">
                Descripcion:  <%
                    if (rev.getDescripcion() == null) {
                %>Sin descripcion.<%
                } else {
                %><%=rev.getDescripcion()%><%
                    }
                %>
            </div>
            <div class="col">
                Etiquetas: <%=etiqDAO.getEtiquetasByRev(rev.getIdRevista())%>
            </div>
            <div class="col">
                Cuota Suscripcion:  <%
                    float c = rev.getCuotaSuscripcion();
                    if (c == 0) {
                %>Gratis<%
                } else {
                %><%=rev.getCuotaSuscripcion()%><%
                    }
                %>
            </div>
        </div>

        <form  action="LikeController" method="POST">
            <div class="w-75 p-3">
                <div class="card">
                    <div class="card-header">Like</div>
                    <div class="card-body">
                        <div class="row">
                            <!--Input Fecha-->
                            <div class="col-sm-6 my-1">
                                <input type="date" class="form-control" id="fecha" name="fecha" required value="<%=LocalDate.now()%>">
                                <div class="invalid-feedback" id="errorFecha" ></div>
                            </div>
                            <div class="col-sm-3 my-1">
                                <button type="submit" class="btn btn-outline-primary" id="like" name="idR" value="<%=rev.getIdRevista()%>">Like</button>
                                <label for="fecha"><%=likeDAO.getNoLikes(id)%></label>
                                <c:if test="${requestScope['like'] != null}">
                                    <script>
                                        document.getElementById("like").className = " btn btn-primary";
                                    </script>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <form  action="LikeController" method="POST">
            <div class="w-75 p-3">
                <div class="card">
                    <div class="card-header">Comentarios</div>
                    <div class="card-body text-success">
                        
                    </div>
                </div>
            </div>
        </form>

        <%
            if (likeDAO.verificarLike(id, user.getEmailUsuario())) {
        %><script>
            document.getElementById("like").className = " btn btn-primary";
        </script><%
        } else {
        %><script>
            document.getElementById("like").className = " btn btn-outline-primary";
        </script><%
            }
        %>

    </body>
</html>
