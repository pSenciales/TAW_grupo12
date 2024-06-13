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
<h1>REGISTRO CLIENTE</h1>
<form:form modelAttribute="cliente" action="/guardarCliente" method="post" enctype="multipart/form-data">
    <% if (request.getAttribute("errorRegistro") != null && !request.getAttribute("errorRegistro").equals("")) { %>
        <div class="error"><%= request.getAttribute("errorRegistro") %></div>
    <% } %>
    <br>
    <label for="nombre">Nombre</label>
    <form:input id="nombre" type="text" path="nombre" size="60" maxlength="45" required="true"/>
    <br>

    <label for="email" type="email">Email</label>
    <form:input id="email" type="email" path="email" size="60" maxlength="45" required="true" autocomplete="false"/>
    <br>

    <label for="contrasenya">Contraseña</label>
    <form:input id="contrasenya" type="password" path="contrasenya" size="60" maxlength="45" required="true" autocomplete="false"/>
    <br>

    <label for="imagenPerfil">Selecciona imagen de perfil</label> <br>
    <form:input id="imagenPerfil" type="file" path="imagenperfilFile" accept="image/*"/>
    <br>

    <label for="peso">Peso</label>
    <form:input id="peso" type="number" path="peso" />Kg
    <br>

    <label for="altura">Altura</label>
    <form:input id="altura" type="number" path="altura" />cm
    <br>

    <label for="alergias">Alergias</label>
    <form:textarea id="alergias" path="alergias" maxlength="150" />
    <br>

    <form:button>Aceptar</form:button>

</form:form>
</body>
</html>