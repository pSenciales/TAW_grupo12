<%
    /**
     * @author María Victoria Huesca
     */
%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href=""
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background-color: #ffffff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 1rem;
        }
        .form-control {
            border-radius: 0.25rem;
        }
        .btn-link {
            padding-left: 0;
        }
    </style>
</head>
<body>
<div class="form-container">
    <form:form method="post" action="/autenticar" modelAttribute="usuario" class="form-horizontal">
        <% if (request.getAttribute("errorLogin") != null && !request.getAttribute("errorLogin").equals("")) { %>
        <div class="alert alert-danger" role="alert"><%= request.getAttribute("errorLogin") %></div>
        <br>
        <% } %>

        <div class="form-group">
            <label for="email" class="col-form-label">Email:</label>
            <form:input id="email" path="email" type="email" size="60" maxlength="45" class="form-control" />
        </div>

        <div class="form-group">
            <label for="contrasenya" class="col-form-label">Contraseña:</label>
            <form:input id="contrasenya" path="contrasenya" type="password" size="60" maxlength="45" class="form-control" />
        </div>

        <div class="form-group text-center">
            <form:button class="btn btn-primary w-30">Iniciar Sesión</form:button>
        </div>
    </form:form>
    <br>
    <div class="border-top my-3"></div>
    <p class="text-center">¿No tienes cuenta?</p>
    <form method="post" action="/elegirRegistro" class="text-center">
        <button type="submit" value="Crear Cuenta" class="btn btn-secondary w-30">Crear Cuenta</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
