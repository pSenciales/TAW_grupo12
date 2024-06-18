<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %><%--
  Created by IntelliJ IDEA.
  User: Guillermo
  Date: 18/06/2024
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%
    TrabajadorDTO trabajador = (TrabajadorDTO) request.getAttribute("trabajador");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Pagina Inicio CrossTraining</title>
</head>
<body>
<jsp:include page="cabeceraEntrenadorCrosstraining.jsp"></jsp:include>

<h1>Página del entrenador de Cross-training: <%=trabajador.getNombre()%></h1>

</body>

</html>