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
            <form method="post" action="/editarDatosCliente">
                <h2>NOMBRE Y APELLIDOS</h2>
                <input type="text" value="Nombre Apellido">
                <p>Correo electrónico:</p>
                <input type="text" value="correoemplejo@a.com">
                <p>Número de teléfono:</p>
                <input type="text" value="123 456 789">
                <p>Peso:</p>
                <input type="text" value="xx Kg">
                <p>Altura:</p>
                <input type="text" value="xxx cm">
                <p>Alergias:</p>
                <input type="text" value="Alergia 1, alergia 2, alergia 3,...">
                <button class="save-button" type="submit">Guardar</button>
            </form>
        </div>
        <div class="horizontal-separator"></div>
        <div class="plans">
            <p class="text-center text-decoration-underline fw-bold">Mis planes:</p>
            <input type="text" value="Rutina de ejemplo">
            <input type="text" value="Dieta de ejemplo">
        </div>
    </div>
    <div class="vertical-separator"></div>
    <div class="right-panel">
        <div class="chart">
            <p>Evolución</p>
        </div>
    </div>
</div>
</body>
</html>
