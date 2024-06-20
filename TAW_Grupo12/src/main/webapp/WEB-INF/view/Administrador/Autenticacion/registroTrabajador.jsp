<%
    /**
     * @author María Victoria Huesca
     */
%>

<!--OPCIÓN: AÑADIR UN BOTÓN DE VOLVER ATRÁS-->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <title>Registro Trabajador</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <style>
        body {
            margin-top: 2rem;
            margin-bottom: 2rem;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
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
            text-align: center;
        }
    </style>
</head>

<body>
<div class="form-container">
    <h1>Registro Trabajador</h1>

<!------------------------------ FORMULARIO DE REGISTRO DE UN NUEVO TRABAJADOR ---------------------------------------->

    <form:form modelAttribute="trabajador" action="/guardarTrabajador" method="post" enctype="multipart/form-data">
        <% if (request.getAttribute("errorRegistro") != null && !request.getAttribute("errorRegistro").equals("")) { %>
        <div class="alert alert-danger" role="alert"><%= request.getAttribute("errorRegistro") %>
        </div>
        <% } %>
        <div class="form-group">
            <label for="nombre" class="form-label">Nombre</label>
            <form:input id="nombre" type="text" path="nombre" size="60" maxlength="45" required="true"
                        class="form-control"/>
        </div>
        <div class="form-group">
            <label for="email" type="email" class="form-label">Email</label>
            <form:input id="email" type="email" path="email" size="60" maxlength="45" required="true"
                        autocomplete="false" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="contrasenya" class="form-label">Contraseña</label>
            <form:input id="contrasenya" type="password" path="contrasenya" size="60" maxlength="45" required="true"
                        autocomplete="false" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="tipo" class="form-label">Selecciona un tipo de trabajador</label> <br>
            <form:select id="tipo" path="tipo" required="true">
                <form:options items="${tipos}"/>
            </form:select>
        </div>
        <div class="form-group">
            <label for="imagenPerfil" class="form-label">Selecciona imagen de perfil</label> <br>
            <form:input id="imagenPerfil" type="file" path="imagenperfilFile" accept="image/*" class="form-control"/>
        </div>
        <div class="form-group text-center">
            <form:button class="btn btn-primary w-30">Aceptar</form:button>
        </div>
    </form:form>
</div>
</body>
</html>