<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>

</head>
<body>

<form:form method="post" action="/iniciarsesion" modelAttribute="usuario">
    ${error}
    <label for="email">Email: </label>
    <form:input id="email" path="email" type="email" size="60" maxlength="45" />
    <br>
    <label for="contrasenya">Contraseña: </label>
    <form:input id="contrasenya" path="contrasenya" type="password" size="60" maxlength="45"/>
    <br>
    <form:button>Iniciar Sesión</form:button>
</form:form>
<br>
<div border="1"></div>
<p>¿No tienes cuenta?</p>
<form method="post" action="/registro">
    <input type="submit" value="Crear Cuenta"></input>
</form>

</body>
</html>