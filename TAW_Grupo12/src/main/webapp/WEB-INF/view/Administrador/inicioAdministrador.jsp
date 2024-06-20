<%
    /**
     * @author María Victoria Huesca
     */
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/Styles/Administrador/inicioAdministrador.css">
    <title>Inicio Administrador</title>
    <style>
        body{
            overflow: hidden;
        }
        .container-inicio {
            display: flex;
            flex-direction: column;
            flex-wrap: wrap;
            height: 90vh;
            width: 100%;
            align-content: center;
            justify-content: center;
            background-image: url('/Images/Administrador/fondoInicioAdministrador.jpg');
            background-size: cover;
            background-position: center;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="cabeceraAdministrador.jsp"/>
</header>
<div class="container-inicio">
    <h1>Hola, <%= request.getAttribute("correoAdministrador")%> </h1>
</div>
</body>
</html>