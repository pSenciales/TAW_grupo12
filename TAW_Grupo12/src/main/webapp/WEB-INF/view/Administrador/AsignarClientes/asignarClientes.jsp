<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Trabajador" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");
    List<TrabajadorDTO> trabajadoresDTO = (List<TrabajadorDTO>) request.getAttribute("trabajadores");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Insertar cabeceraAdministrador.jsp -->
    <title>Asignar Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .container-asignarClientes {
            max-width: 1200px;
            margin: auto;
            padding: 20px;
        }
        .imagenperfil-asignarClientes {
            display: block;
            margin-top: 1.5vh;
            margin-left: auto;
            margin-right: auto;
            width: 20vh;
            height: 20vh;
            border-radius: 50%;
            object-fit: cover;
        }
        .card {
            margin-bottom: 20px;
            border: none;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card-title {
            font-size: 1.25rem;
        }
        .card-text {
            color: #6c757d;
        }
        .input-group {
            margin-bottom: 20px;
        }
        .card-text{
            text-align: left;
        }

        .bold{
            font-weight: bold;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="../cabeceraAdministrador.jsp"/>
</header>
<div class="container-asignarClientes">
    <form:form modelAttribue="filtro" method="post" action="/administrador/filtrarAsignarClientes">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                </svg>
            </span>
            <input type="text" class="form-control" placeholder="Introduzca el nombre o correo de cliente que deseas asignar" aria-label="Input group example" aria-describedby="basic-addon1"/>
        </div>
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form:form>
    <div class="row">
        <% for(ClienteDTO cliente : clientes) {
            String dietista = "";
            String entrenadorCrosstraining = "";
            String entrenadorFuerza = "";

            List<Trabajador> trabajadores = cliente.getTrabajadorList();
            if (trabajadores != null) {
                for (Trabajador trabajador : trabajadores) {
                    for (TrabajadorDTO trabajadorDTO : trabajadoresDTO) {
                        if (trabajadorDTO.getIdtrabajador().equals(trabajador.getIdtrabajador())) {
                            if (trabajadorDTO.getTipo().equals("ENTRENADOR CROSSTRAINNING")) {
                                entrenadorCrosstraining = trabajadorDTO.getNombre();
                            } else if (trabajadorDTO.getTipo().equals("ENTRENADOR FUERZA")) {
                                entrenadorFuerza = trabajadorDTO.getNombre();
                            } else if (trabajadorDTO.getTipo().equals("DIETISTA")) {
                                dietista = trabajadorDTO.getNombre();
                            }
                        }
                    }
                }
            }
        %>
        <div class="col-md-4 col-sm-6">
            <div class="card">
                <img src="<%=cliente.getImagenBase64() != null ? "data:image/jpeg;base64," + cliente.getImagenBase64() : "../Images/Administrador/perfilDefault.jpg" %>" alt="Imagen de perfil" class="imagenperfil-asignarClientes">
                <div class="card-body text-center">
                    <h5 class="card-title mt-3 mb-3"><%=cliente.getNombre()%></h5>
                    <p class="card-text"><span class="bold">Email: </span> <%=cliente.getEmail()%></p>
                    <p class="card-text"><span class="bold">Entrenador Crosstrainning: </span> <%= !entrenadorCrosstraining.isEmpty() ? entrenadorCrosstraining : "ninguno" %></p>
                    <p class="card-text"><span class="bold">Entrenador Fuerza: </span> <%= !entrenadorFuerza.isEmpty() ? entrenadorFuerza : "ninguno" %></p>
                    <p class="card-text"><span class="bold">Dietista: </span> <%= !dietista.isEmpty() ? dietista : "ninguno" %></p>
                    <a href="/administrador/asignarCliente" class="btn btn-primary">Asignar</a>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>