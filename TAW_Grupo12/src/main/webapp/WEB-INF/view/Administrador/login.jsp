<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>

<form:form method="post" action="/login/iniciarsesion" modelAttribute="usuario">
    <label for="email">Email: </label>
    <form:input id="email" path="email" type="email" size="100" maxlength="100" />
    <label for="contrasenya"></label>
    <form:input id="contrasenya" path="contrasenya" type="password"/>
    <form:button>Iniciar Sesión</form:button>
</form:form>
<br>
<div border="1"></div>
<p>¿No tienes cuenta?</p>
<form:form method="post" action="/registro">
    <form:button>Crear Cuenta</form:button>
</form:form>

</body>
</html>