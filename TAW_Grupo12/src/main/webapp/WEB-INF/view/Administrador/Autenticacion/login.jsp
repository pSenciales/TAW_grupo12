<%
    /**
     * @author María Victoria Huesca Peláez
     */
%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="/Styles/Administrador/login.css">
    <title>Login</title>
</head>

<body>
<div class="parent-container-login">
    <div class="form-container container-login">
        <h1>Inicio de Sesión</h1>

<!---------------------------------------- FORMULARIO DE INICIO DE SESIÓN ---------------------------------------------->

        <form:form method="post" action="/autenticar" modelAttribute="usuario" class="form-horizontal">
            <% if (request.getAttribute("errorLogin") != null && !request.getAttribute("errorLogin").equals("")) { %>
            <div class="alert alert-danger" role="alert"><%= request.getAttribute("errorLogin") %> </div>
            <br>
            <% } %>

            <div class="form-group miForm-group-login">
                <label for="email" class="col-form-label">Email:</label>
                <form:input id="email" path="email" type="email" size="60" maxlength="45"
                            class="form-control miForm-control-login"/>
            </div>

            <div class="form-group miForm-group-login">
                <label for="contrasenya" class="col-form-label">Contraseña:</label>
                <form:input id="contrasenya" path="contrasenya" type="password" size="60" maxlength="45"
                            class="form-control miForm-control-login"/>
            </div>

            <div class="form-group text-center miForm-group-login">
                <form:button class="btn btn-primary w-30">Iniciar Sesión</form:button>
            </div>
        </form:form>
        <br>
        <div class="border-top my-2"></div>
        <p class="text-center">¿No tienes cuenta?</p>

<!---------------------------------------- BOTÓN PARA CREAR CUENTA NUEVA ---------------------------------------------->

        <form method="get" action="/elegirRegistro" class="text-center">
            <button type="submit" value="Crear Cuenta" class="btn btn-secondary w-30">Crear Cuenta</button>
        </form>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

</body>
</html>
