<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/Styles/Administrador/cabeceraAdministrador.css">
    <title>Cabecera Administrador</title>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item dropdown">
                <button class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    Opciones
                </button>
                <ul class="dropdown-menu dropdown-menu-dark">
                    <li><a class="dropdown-item" href="/administrador/asignarClientes">Asignar Clientes</a></li>
                    <li><a class="dropdown-item" href="/administrador/gestionarUsuarios">Gestionar Usuarios</a></li>
                    <li><a class="dropdown-item" href="/administrador/gestionarEjercicios">Gestionar Ejercicios</a></li>
                    <li><a class="dropdown-item" href="/administrador/hestionarPlatos">Gestionar Platos</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link volver-incio" href="/administrador/inicio" >Inicio</a>
            </li>
        </ul>

    </div>

    <div class="mx-auto">
        <h1><%= request.getAttribute("tituloCabeceraAdmin") %></h1>
    </div>

    <div class="container-fluid">
        <ul class="navbar-nav ms-auto">
            <li class="nav-item">
                <form action="/administrador/verPerfil" method="post">
                    <button type="submit" class="btn btn-primary boton-perfil">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"></path>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"></path>
                        </svg>
                        Mi Perfil
                    </button>
                </form>
            <li class="nav-item">
                <form action="/cerrarSesion" method="get">
                    <button type="submit" class="btn btn-outline-danger">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0z"></path>
                            <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z"></path>
                        </svg>
                    Cerrar Sesión
                    </button>
                </form>
            </li>
        </ul>
    </div>
</nav>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>

