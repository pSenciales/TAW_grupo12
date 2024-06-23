<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Página personal</title>
</head>
<body>
<jsp:include page="cabeceraCliente.jsp"/>
<h1>Pág personal cliente</h1>
<form method="post" action="/cliente/salir">
    <input type="submit" value="Cerrar sesion">
</form>

</body>
</html>
