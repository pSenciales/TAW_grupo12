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
    List<ClienteDTO> clientesDTO = (List<ClienteDTO>) request.getAttribute("clientes");
    List<TrabajadorDTO> trabajadoresDTO = (List<TrabajadorDTO>) request.getAttribute("trabajadores");
    List<String> tiposUsuario = new ArrayList<>();
    tiposUsuario.add("Cliente");
    tiposUsuario.add("Trabajador");
    List<String> tiposTrabajador = new ArrayList<>();
    tiposTrabajador.add("Entrenador fuerza");
    tiposTrabajador.add("Entrenador crosstrainning");
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<header>
    <jsp:include page="../cabeceraAdministrador.jsp"/>
</header>
<div class="container-asignarClientes">
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
    </div>
    <button type="button" class="btn btn-secondary" id="filtros">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-filter"
             viewBox="0 0 16 16">
            <path d="M6 10.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5m-2-3a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5m-2-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5"></path>
        </svg>
    </button>
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
    <% for (ClienteDTO clienteDTO : clientesDTO) { %>
    <div class="col-md-4 col-sm-6">
        <div class="card">
            <img src="<%=clienteDTO.getImagenBase64() != null ? "data:image/jpeg;base64," + clienteDTO.getImagenBase64() : "../Images/Administrador/perfilDefault.jpg" %>"
                 alt="Imagen de perfil" class="imagenperfil-asignarClientes">
            <div class="card-body text-center">
                <h5 class="card-title mt-3 mb-3"><%=clienteDTO.getNombre()%>
                </h5>
                <p class="card-text"><span class="bold">Email: </span> <%=clienteDTO.getEmail()%>
                </p>
                <p class="card-text"><span class="bold">Tipo Usuario: </span> cliente </p>
                <a href="#" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#asignarModal"
                   data-cliente-id="<%=clienteDTO.getIdcliente()%>">Gestionar</a>
            </div>
        </div>
    </div>
    <% } %>
    <% for (TrabajadorDTO trabajadorDTO : trabajadoresDTO) { %>
    <div class="col-md-4 col-sm-6">
        <div class="card">
            <img src="<%=trabajadorDTO.getImagenBase64() != null ? "data:image/jpeg;base64," + trabajadorDTO.getImagenBase64() : "../Images/Administrador/perfilDefault.jpg" %>"
                 alt="Imagen de perfil" class="imagenperfil-asignarClientes">
            <div class="card-body text-center">
                <h5 class="card-title mt-3 mb-3"><%=trabajadorDTO.getNombre()%>
                </h5>
                <p class="card-text"><span class="bold">Email: </span> <%=trabajadorDTO.getEmail()%>
                </p>
                <p class="card-text"><span class="bold">Tipo Usuario: </span> trabajador </p>
                <p class="card-text"><span class="bold">Tipo Trabajador: </span> <%= trabajadorDTO.getTipo() %>
                </p>
                <a href="#" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#asignarModal"
                   data-cliente-id="<%=trabajadorDTO.getIdtrabajador()%>">Gestionar</a>
            </div>
        </div>
    </div>
    <% } %>
</div>
<script>
    document.getElementById('filtros').addEventListener('click', function () {
        var myModal = new bootstrap.Modal(document.getElementById('filtrosModal'), {});
        myModal.show();
    });
</script>
</body>
</html>


