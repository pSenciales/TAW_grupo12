<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>

</head>
<body>
<h1>REGISTRO ADMINISTRADOR</h1>
<form:form modelAttribute="administrador" action="/guardarAdministrador" method="post">
    ${errorRegistro}
    <br>
    <label for="email" type="email">Email</label>
    <form:file id="email" path="email" type="email" size="60" maxlength="45" required="true"/>
    <br>

    <label for="contrasenya">Contraseña</label>
    <form:input id="contrasenya" type="password" path="contrasenya" size="60" maxlength="45" required="true"/>
    <br>

    <form:button>Aceptar</form:button>

</form:form>
</body>
</html>