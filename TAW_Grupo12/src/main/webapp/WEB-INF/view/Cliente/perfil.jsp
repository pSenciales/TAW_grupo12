<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil</title>
</head>
<body>
<jsp:include page="cabeceraCliente.jsp"/>

<div class="container">
    <div class="left-panel">
        <div class="profile-info">
            <p class="text-center text-decoration-underline fw-bold">Mis datos:</p>
            <form:form modelAttribute="cliente" method="post" action="/cliente/editarCliente">
                <form:hidden path="idcliente"/>
                <p>Nombre y apellidos</p>
                <form:input path="nombre"/>
                <p>Correo electrónico:</p>
                <form:input path="email"/>
                <p>Peso:</p>
                <form:input path="peso"/>
                <p>Altura:</p>
                <form:input path="altura"/>
                <p>Alergias:</p>
                <form:input path="alergias"/>
                <button class="save-button" type="submit">Guardar</button>
            </form:form>
        </div>
    </div>
    <div class="vertical-separator"></div>
    <div class="left-panel">
        <div class="plans">
            <p class="text-center text-decoration-underline fw-bold">Mis planes:</p>
            <input type="text" value="Rutina de ejemplo">
            <input type="text" value="Dieta de ejemplo">
        </div>
    </div>
</div>
</body>
</html>
