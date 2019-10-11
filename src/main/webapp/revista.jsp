<%@page import="java.sql.ResultSet"%>
<%@page import="com.ashdz.webrevistas.DAO.Comentario.ComentarioDAO"%>
<%@page import="com.ashdz.webrevistas.DAO.Comentario.ComentarioDAOImpl"%>
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

        <div class="w-75 p-3">
            <div class="card">
                <div class="card-header"><%=rev.getNombreRevista()%></div>
                <div class="card-body">

                    <!-- Datos Revista -->
                    <div class="">
                        <div class="col">
                            <b>Editor:</b> <%=userDAO.getByEmail(rev.getEmailEditor()).getNombreUsuario()%>
                        </div>
                        <div class="col">
                            <b>Categoria:</b>  <%=categoDAO.getObject(rev.getIdCategoria()).getNombre()%>
                        </div>
                        <div class="col">
                            <b>Descripcion:</b>  <%
                                if (rev.getDescripcion() == null) {
                            %>Sin descripcion.<%
                            } else {
                            %><%=rev.getDescripcion()%><%
                                }
                            %>
                        </div>
                        <div class="col">
                            <b>Etiquetas:</b> <%=etiqDAO.getEtiquetasByRev(rev.getIdRevista())%>
                        </div>
                        <div class="col">
                            <b>Cuota Suscripcion:</b>  <%
                                float c = rev.getCuotaSuscripcion();
                                if (c == 0) {
                            %>Gratis<%
                            } else {
                            %><%=rev.getCuotaSuscripcion()%><%
                                }
                            %>
                        </div>
                    </div><br>

                    <!--Like-->
                    <form  action="LikeController" method="POST">
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
                    </form><br>

                    <%
                        ComentarioDAO commentDAO = ComentarioDAOImpl.getComentarioDAO();
                        ResultSet rs = commentDAO.getResultSetComents();
                    %>

                    <!--Comentarios-->
                    <form  action="CommentController" method="POST">
                        <div class="card">
                            <div class="card-header">Comentarios</div>
                            <div class="card-body text-success">

                                <div class="form-group">
                                    <textarea class="form-control" id="commentText" name="commentText" rows="2"placeholder="Escribe un comentario." required></textarea>
                                </div>

                                <div class="row">
                                    <!--Input Fecha-->
                                    <div class="col">
                                        <input type="date" class="form-control" id="fecha" name="fecha" required value="<%=LocalDate.now()%>">
                                        <div class="invalid-feedback" id="errorFecha" ></div>
                                    </div>

                                    <div class="col">
                                        <button type="submit" class="btn btn-secondary btn-sm" id="comment" name="idR" value="<%=rev.getIdRevista()%>" >Comentar</button>
                                    </div>
                                </div><br><br>
                                
                                
                                <%while (rs.next()) {%>
                                <div class="card">
                                    <div class="card-body">
                                        <h6 class="card-title text-dark"><%=rs.getString(4)%> ~ <%=rs.getString(2)%></h6>
                                        <div class="dropdown-divider"></div>
                                        <p class="card-text text-body"><%=rs.getString(5)%></p>
                                        <footer class="blockquote-footer">Comentario #<%=rs.getString(1)%></footer>
                                    </div>
                                </div><br>
                                <%}%>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>






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
