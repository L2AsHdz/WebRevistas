<%-- 
    Document   : Registro
    Created on : 2/10/2019, 11:46:11 PM
    Author     : asael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Bootstrap.html" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%--Barra de navegacion--%>
        <%@include file="BarraNavegacion.html" %><br><br><br>

        <%--Formulario--%>
        <%@include file="FormRegistro.html" %>
        
        <script>
            (function(){
                'use strict';
                window.addEventListener('load', function(){
                    var forms = document.getElementsByClassName('needs-validation');
                    var validation = Array.prototype.filter.call(forms, function(form){
                        form.addEventListener('submit', function(event){
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();
        </script>
        <%@include file="Scripts.html" %>
    </body>
</html>
