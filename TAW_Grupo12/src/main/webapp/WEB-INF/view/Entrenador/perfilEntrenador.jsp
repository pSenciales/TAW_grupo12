<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 17/06/2024
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    TrabajadorDTO trabajador = (TrabajadorDTO) request.getAttribute("trabajador");
%>
<html>
<head>
    <title>Perfil</title>
</head>
<body>
<jsp:include page="cabeceraEntrenador.jsp"></jsp:include>

</body>
</html>
