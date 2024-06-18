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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="/Styles/Administrador/registroTipoUsuario.css">
    <title>Registro Tipo Usuario</title>
</head>
<body>
    <div class="parent-container-registroTipoUsuario">
        <div class="form-container container-registroTipoUsuario">
            <p>Seleccione el tipo de usuario que desea crear: </p>

            <form action="/registro" method="get">
                <div class="miForm-group-registroTipoUsuario">
                   <div class="form-group-registroTipoUsuario">
                       <input type="radio" value="cliente" id="cliente" name="tipo" checked class="miForm-control-registroTipoUsuario"/>
                       <label for="cliente">Cliente</label>
                   </div>

                   <div class="form-group-registroTipoUsuario">
                       <input type="radio" value="trabajador" id="trabajador" name="tipo" class="miForm-control-registroTipoUsuario"/>
                       <label for="trabajador">Trabajador</label>
                   </div>

                    <div class="form-group-registroTipoUsuario">
                        <input type="radio" value="administrador" id="administrador" name="tipo" class="miForm-control-registroTipoUsuario"/>
                        <label for="administrador">Administrador</label>
                    </div>

                </div>
                <div class="btn-continuar-RegistroTipoUsuario d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary w-30 ">Continuar</button>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>