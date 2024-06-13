<%
    /**
     * @author María Victoria Huesca
     */
%>

<!--OPCIÓN: AÑADIR UN BOTÓN DE VOLVER ATRÁS-->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="es">
<head>

</head>
<body>

<p>Seleccione el tipo de usuario que desea crear: </p>

<form action="/registro" method="get">

    <input type="radio" value="cliente" id="cliente" name="tipo"/>
    <label for="cliente">Cliente</label>
    <br>

    <input type="radio" value="trabajador" id="trabajador" name="tipo"/>
    <label for="trabajador">Trabajador</label>
    <br>

    <input type="radio" value="administrador" id="administrador" name="tipo">
    <label for="administrador">Administrador</label>
    <br>

    <input type="submit" value="Continuar">
</form>

</body>
</html>