<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lacas
  Date: 19/06/2024
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%
    List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Crear Rutina</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>
<body>
<jsp:include page="cabeceraEntrenadorCrosstraining.jsp"></jsp:include>
<form method="post" action="nuevarutina/guardar">

    <input name="nombre" type="text" placeholder="Nombre" required/>
    <select name="cliente" required>
        <option>Seleccione un cliente</option>
        <%
            for (ClienteDTO c : clientes) {


        %>
        <option value="<%=c.getIdcliente()%>"><%=c.getNombre()%>
        </option>

        <%
            }
        %>
    </select>
    <button type="submit" class="btn btn-primary">Crear</button>


</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>