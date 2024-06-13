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

</head>
<body>
<h1>REGISTRO TRABAJADOR</h1>
<form:form modelAttribute="trabajador" action="/guardarTrabajador" method="post" enctype="multipart/form-data">
    ${errorRegistro}
    <br>
    <label for="nombre">Nombre</label>
    <form:input id="nombre" type="text" path="nombre" size="60" maxlength="45" required="true"/>
    <br>

    <label for="email" type="email">Email</label>
    <form:input id="email" type="email" path="email" size="60" maxlength="45" required="true" autocomplete="false"/>
    <br>

    <label for="contrasenya">Contraseña</label>
    <form:input id="contrasenya" type="password" path="contrasenya" size="60" maxlength="45" required="true"  autocomplete="false"/>
    <br>

    <label for="tipo">Selecciona un tipo de trabajador</label> <br>
    <form:select id="tipo" path="tipo" required="true">
        <form:options items="${tipos}" />
    </form:select>
    <br>

    <label for="imagenPerfil">Selecciona imagen de perfil</label> <br>
    <form:input id="imagenPerfil" type="file" path="imagenperfilFile" accept="image/*"/>
    <br>

    <form:button>Aceptar</form:button>

</form:form>
</body>
</html>