<%
    /**
     * @author María Victoria Huesca
     */
%>

<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Trabajador" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //OPCIÓN: AÑADIR QUE SE PUEDAN SELECCIONAR VARIOS TIPOS DE TRABAJADORES
    List<ClienteDTO> clientesDTO = (List<ClienteDTO>) request.getAttribute("clientes");
    List<TrabajadorDTO> trabajadoresDTO = (List<TrabajadorDTO>) request.getAttribute("trabajadores");
    List<String> tiposUsuario = new ArrayList<>();
    tiposUsuario.add("Cliente");
    tiposUsuario.add("Trabajador");
    List<String> tiposTrabajador = new ArrayList<>();
    tiposTrabajador.add("Entrenador fuerza");
    tiposTrabajador.add("Entrenador crosstraining");
    tiposTrabajador.add("Dietista");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Insertar cabeceraAdministrador.jsp -->
    <title>Asignar Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/Styles/Administrador/asignarClientes">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
            padding-left: 2.5rem;
            padding-right: 2.5rem;
            padding-bottom: 2.5rem;
        }
        .container-gestionarUsuarios {
            max-width: 1200px;
            margin: auto;
            padding: 20px;
        }
        .imagenperfil-gestionarUsuarios {
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
            height: 200px;
            overflow-y: auto;
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
<div class="container-gestionarUsuarios">
    <form:form modelAttribute="filtroUsuarios" method="post" action="/administrador/filtrarGestionarClientes">
    <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search"
                     viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                </svg>
            </span>
        <form:input type="text" class="form-control"
                    placeholder="Introduzca el nombre o correo de usaurio que deseas gestionar" path="busqueda"/>
        <button type="button" class="btn btn-secondary" id="filtros">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-filter"
                 viewBox="0 0 16 16">
                <path d="M6 10.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5m-2-3a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5m-2-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5"></path>
            </svg>
        </button>
    </div>

        <div class="modal fade" id="filtrosModal" tabindex="-1" aria-labelledby="filtrosModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="filtrosModalLabel">Filtros</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="form-check">
                            <label class="form-check-label">Tipo de usuario: </label><br>

                            <form:checkboxes path="tipoUsuario" items="<%=tiposUsuario%>" id="tipoUsuarios"
                                             delimiter="<br>" class="form-check-input"/>
                        </div>
                        <div class="form-check">
                            <label  class="form-check-label mt-2">Tipo de trabajador: </label><br>
                            <form:select path="tipoTrabajador" items="<%=tiposTrabajador%>" id="tipoTrabajadores"
                                             delimiter="<br>" class="form-select"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <form:button class="btn btn-primary">Aplicar filtros</form:button>
                    </div>
                </div>
            </div>
        </div>
</div>
</form:form>
<div class="row">
    <% if(clientesDTO != null && !clientesDTO.isEmpty()){
        for (ClienteDTO clienteDTO : clientesDTO) { %>
    <div class="col-md-4 col-sm-6">
        <div class="card d-flex flex-column" style="height: 25rem;">
            <img src="<%=clienteDTO.getImagenBase64() != null ? "data:image/jpeg;base64," + clienteDTO.getImagenBase64() : "../Images/Administrador/perfilDefault.jpg" %>"
                 alt="Imagen de perfil" class="imagenperfil-gestionarUsuarios">
            <div class="card-body text-center flex-grow-1">
                <h5 class="card-title mt-3 mb-3"><%=clienteDTO.getNombre()%>
                </h5>
                <p class="card-text"><span class="bold">Email: </span> <%=clienteDTO.getEmail()%>
                </p>
                <p class="card-text"><span class="bold">Tipo Usuario: </span> cliente </p>
                <a href="#" class="btn btn-outline-primary mt-5" data-bs-toggle="modal" data-bs-target="#asignarModal"
                   data-cliente-id="<%=clienteDTO.getIdcliente()%>">Gestionar</a>

                <!-- Modal para editar los datos del cliente -->
                <div class="modal fade" id="editarClienteModal" tabindex="-1" aria-labelledby="editarClienteModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="editarClienteModalLabel">Editar Cliente</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <!-- Aquí iría tu formulario para editar los datos del cliente -->
                                <form:form action="/administrador/guardarCliente" modelAttribute="clienteModel" method="post">
                                    <form:hidden path="idcliente" value="<%= clienteDTO.getIdcliente() %>"/>
                                    <img src="<%=clienteDTO.getImagenBase64() != null ? "data:image/jpeg;base64," + clienteDTO.getImagenBase64() : "../Images/Administrador/perfilDefault.jpg" %>"
                                         alt="Imagen de perfil" class="imagenperfil-gestionarUsuarios">
                                    <label for="imagenperfil">Imagen de perfil: </label><form:input type="file" path="imagenperfilFile" id="imagenperfil"/>

                                    <label for="nombre">Nombre: </label><form:input type="text" value="<%= clienteDTO.getNombre() %>" path="nombre" required="true" id="nombre" maxlength="45"/>
                                    <label for="email">Email: </label><form:input type="email" value="<%= clienteDTO.getEmail() %>" path="email" required="true" id="email" maxlength="45"/>
                                    <label for="altura">Altura: </label><form:input type="number" value="<%= clienteDTO.getAltura()%>" path="altura" id="altura"/>
                                    <label for="peso">Peso: </label><form:input type="number" value="<%= clienteDTO.getPeso()%>" path="peso" id="peso"/>
                                </form:form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                <button type="button" class="btn btn-primary">Guardar cambios</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <% }} %>
    <% if(trabajadoresDTO != null && !trabajadoresDTO.isEmpty()){
        for (TrabajadorDTO trabajadorDTO : trabajadoresDTO) { %>
    <div class="col-md-4 col-sm-6">
        <div class="card d-flex flex-column" style="height: 25rem;">
            <img src="<%=trabajadorDTO.getImagenBase64() != null ? "data:image/jpeg;base64," + trabajadorDTO.getImagenBase64() : "../Images/Administrador/perfilDefault.jpg" %>"
                 alt="Imagen de perfil" class="imagenperfil-gestionarUsuarios"">
            <div class="card-body text-center flex-grow-1">
                <h5 class="card-title mt-3 mb-3"><%=trabajadorDTO.getNombre()%>
                </h5>
                <p class="card-text"><span class="bold">Email: </span> <%=trabajadorDTO.getEmail()%>
                </p>
                <p class="card-text"><span class="bold">Tipo Usuario: </span> trabajador </p>
                <p class="card-text"><span class="bold">Tipo Trabajador: </span> <%= trabajadorDTO.getTipo().toLowerCase() %>
                </p>
                <a href="#" class="btn btn-outline-primary mt-1" data-bs-toggle="modal" data-bs-target="#asignarModal"
                   data-cliente-id="<%=trabajadorDTO.getIdtrabajador()%>">Gestionar</a>
            </div>
        </div>
    </div>
    <% }} %>
</div>
<script>
    document.getElementById('filtros').addEventListener('click', function () {
        var myModal = new bootstrap.Modal(document.getElementById('filtrosModal'), {});
        myModal.show();
    });

    // Controlador de eventos para los botones "Gestionar"
    document.querySelectorAll('[data-bs-toggle="modal"]').forEach(item => {
        item.addEventListener('click', event => {
            // Obtén el ID del cliente
            const clienteId = event.target.getAttribute('data-cliente-id');

            // Aquí puedes hacer una petición AJAX para obtener los datos del cliente
            // y llenar el formulario dentro del modal. Por ejemplo:

            // $.get(`/api/clientes/${clienteId}`, function(data) {
            //   // Llena el formulario con los datos del cliente
            //   $('#nombreCliente').val(data.nombre);
            //   $('#emailCliente').val(data.email);
            //   // etc.
            // });
        });
    });
</script>
</body>
</html>


