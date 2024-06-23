<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<%@ page import="es.uma.taw_grupo12.entity.Dieta" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil</title>
</head>
<body>
<jsp:include page="cabeceraCliente.jsp"/>

<div class="container-sm text-center">
        <div class="list-group">
            <div class="profile-info list-group-item">
                <p class="text-center text-decoration-underline fw-bold ">Mis datos:</p>
                <form:form modelAttribute="cliente" method="post" action="/cliente/editarCliente">
                    <form:hidden path="idcliente"/>
                    <p><strong>Nombre y apellidos</strong></p>
                    <form:input path="nombre"/>
                    <p><strong>Correo electrónico:</strong></p>
                    <form:input path="email"/>
                    <p><strong>Peso:</strong></p>
                    <form:input path="peso"/>
                    <p><strong>Altura:</strong></p>
                    <form:input path="altura"/>
                    <p><strong>Alergias:</strong></p>
                    <form:input path="alergias"/><br/><br/>
                    <button class="save-button" type="submit">Guardar</button>
                </form:form>
            </div>
        </div>
        <!--div class="vertical-separator"></div-->
    </div>

</div>
</body>
</html>
