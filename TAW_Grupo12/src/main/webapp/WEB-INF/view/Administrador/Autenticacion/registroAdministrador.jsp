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
    <title>Registro Administrador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column;
        }
        .form-container {
            background-color: #ffffff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
        }
        .form-group {
            margin-bottom: 1rem;
        }
        h1 {
            margin-bottom: 1.5rem;
        }
    </style>
</head>

<body>
<div class="form-container">
    <h1>Registro Administrador</h1>

<!--------------------------- FORMULARIO DE REGISTRO DE UN NUEVO ADMINISTRADOR ------------------------------------------->

    <form:form modelAttribute="administrador" action="/guardarAdministrador" method="post">
        <% if (request.getAttribute("errorRegistro") != null && !request.getAttribute("errorRegistro").equals("")) { %>
        <div class="alert alert-danger" role="alert"><%= request.getAttribute("errorRegistro") %></div>
        <% } %>
        <div class="form-group">
            <label for="email" class="form-label">Email</label>
            <form:input id="email" path="email" type="email" size="60" maxlength="45" class="form-control" required="true"/>
        </div>

        <div class="form-group">
            <label for="contrasenya" class="form-label">Contraseña</label>
            <form:password id="contrasenya" path="contrasenya" size="60" maxlength="45" class="form-control" required="true"/>
        </div>

        <div class="form-group text-center">
            <form:button class="btn btn-primary w-30">Aceptar</form:button>
        </div>
    </form:form>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
