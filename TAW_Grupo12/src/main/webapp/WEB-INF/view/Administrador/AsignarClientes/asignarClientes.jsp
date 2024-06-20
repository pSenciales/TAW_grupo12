<%
    /**
     * @author María Victoria Huesca
     */
%>

<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Trabajador" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");
    List<TrabajadorDTO> trabajadoresDTO = (List<TrabajadorDTO>) request.getAttribute("trabajadores");
    String filtro = request.getParameter("filtroClientes");
    if (filtro == null) filtro = "";
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>Asignar Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-beta1/js/bootstrap.min.js"></script>

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

        .form-control{
            width: 80%;
        }
    </style>
    <script>
        //SCRIPT PARA MOSTRAR EL MODAL DE ASIGNAR TRABAJADOR
        $(document).ready(function() {
            $('#asignarButton').click(function() {
                $('#asignarForm').submit();
            });

            $('#asignarModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget) // Button that triggered the modal
                var clienteId = button.data('cliente-id') // Extract the client ID from the data attribute

                var modal = $(this)
                modal.find('#clienteId').val(clienteId) // Set the value of the hidden input field
            })
        });
    </script>
</head>

<body>

<header>
    <jsp:include page="../cabeceraAdministrador.jsp"/>
</header>

<!--------------------------- MODAL PARA ELEGIR QUE TIPO TRABAJADOR ASIGNAR ------------------------------------------->

<div class="modal fade" id="asignarModal" tabindex="-1" aria-labelledby="asignarModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="asignarModalLabel">Asignar Trabajador</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="asignarForm" action="/administrador/asignarCliente" method="post">
                    <input type="hidden" id="clienteId" name="clienteId">
                    <div class="mb-3">
                        <label for="tipoTrabajador" class="form-label">Tipo de Trabajador</label>
                        <select class="form-select" id="tipoTrabajador" name="tipoTrabajador">
                            <option selected>Selecciona...</option>
                            <option value="ENTRENADOR FUERZA">Entrenador Fuerza</option>
                            <option value="ENTRENADOR CROSSTRAINING">Entrenador Crosstraining</option>
                            <option value="DIETISTA">Dietista</option>
                        </select>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary" id="asignarButton">Asignar</button>
            </div>
        </div>
    </div>
</div>
<div class="container-asignarClientes">

<!--------------------------- FILTRO DE BÚSQUEDA DE CLIENTES POR NOMBRE O CORREO -------------------------------------->

    <form:form modelAttribute="filtroClientes" method="post" action="/administrador/filtrarAsignarClientes">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                </svg>
            </span>
            <form:input type="text" class="form-control" placeholder="Introduzca el nombre o correo de cliente que deseas asignar"  path="busqueda"/>
        </div>
    </form:form>

<!------------------------------------------ LISTADO DE CLIENTES ------------------------------------------------------>

    <div class="row">
        <% for(ClienteDTO cliente : clientes) {

/*************************************** OBTENER CADA TRABAJADOR ASIGNADO PARA MOSTRARLO ******************************/
            String dietista = "";
            String entrenadorCrosstraining = "";
            String entrenadorFuerza = "";

            List<Integer> trabajadores = cliente.getTrabajadorList();
            if (trabajadores != null) {
                for (Integer trabajador : trabajadores) {
                    for (TrabajadorDTO trabajadorDTO : trabajadoresDTO) {
                        if (trabajadorDTO.getIdtrabajador().equals(trabajador)) {
                            if (trabajadorDTO.getTipo().equals("entrenadorcross")) {
                                entrenadorCrosstraining = trabajadorDTO.getNombre();
                            } else if (trabajadorDTO.getTipo().equals("entrenador")) {
                                entrenadorFuerza = trabajadorDTO.getNombre();
                            } else if (trabajadorDTO.getTipo().equals("dietista")) {
                                dietista = trabajadorDTO.getNombre();
                            }
                        }
                    }
                }
            }
        %>
        <div class="col-md-4 col-sm-6">
            <div class="card">
                <img src="<%=cliente.getImagenBase64() != null ? "data:image/jpeg;base64," + cliente.getImagenBase64() : "/Images/Administrador/perfilDefault.jpg" %>" alt="Imagen de perfil" class="imagenperfil-asignarClientes">
                <div class="card-body text-center">
                    <h5 class="card-title mt-3 mb-3"><%=cliente.getNombre()%></h5>
                    <p class="card-text"><span class="bold">Email: </span> <%=cliente.getEmail()%></p>
                    <p class="card-text"><span class="bold">Entrenador Crosstraining: </span> <%= !entrenadorCrosstraining.isEmpty() ? entrenadorCrosstraining : "ninguno" %></p>
                    <p class="card-text"><span class="bold">Entrenador Fuerza: </span> <%= !entrenadorFuerza.isEmpty() ? entrenadorFuerza : "ninguno" %></p>
                    <p class="card-text"><span class="bold">Dietista: </span> <%= !dietista.isEmpty() ? dietista : "ninguno" %></p>
                    <a href="#" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#asignarModal" data-cliente-id="<%=cliente.getIdcliente()%>">Asignar</a>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>