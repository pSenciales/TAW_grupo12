<%
    /**
     * @author María Victoria Huesca Peláez
     */
%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="es.uma.taw_grupo12.dto.PlatoDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
    List<String> alergenos = Arrays.asList("Gluten", "Frutos de cáscara", "Crustáceos", "Apio", "Huevo", "Mostaza", "Pescado", "Sésamo", "Cacahuetes", "Sulfitos", "Soja", "Altramuces", "Leche", "Moluscos");
    List<PlatoDTO> platosDTO = (List<PlatoDTO>) request.getAttribute("platos");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>Gestionar Platos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container-gestionarPlatos {
            max-width: 1200px;
            margin: auto;
            padding: 15px;
        }

        .input-group {
            margin-bottom: 20px;
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

        .card-text {
            text-align: left;
        }

        .bold {
            font-weight: bold;
        }

        .form-group {
            margin-top: 1.5rem;
        }

        .imagen-gestionarPlatos {
            display: block;
            margin-top: 1.5vh;
            margin-left: auto;
            margin-right: auto;
            width: 50%; /* adjust as needed */
            height: auto; /* maintain aspect ratio */
            border-radius: 50%;
            object-fit: cover;
        }

    </style>
</head>

<body>

<header>
    <jsp:include page="../cabeceraAdministrador.jsp"/>
</header>

<div class="container-gestionarPlatos">

        <% if (request.getAttribute("errorGestionarPlato") != null) { %>
    <div class="alert alert-danger">
        <%= request.getAttribute("errorGestionarPlato") %>
    </div>
        <% } %>

<!------------------------------ FILTRO DE BÚSQUEDA DE PLATO POR NOMBRE ------------------------------------------->

    <form:form modelAttribute="filtroPlatos" method="post" action="/administrador/platos/filtrarGestionarPlatos">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search"
                     viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                </svg>
            </span>
            <form:input type="text" class="form-control"
                    placeholder="Introduzca el nombre del plato que deseas gestionar" path="busqueda"/>

<!------------------------------ BOTÓN QUE MUESTRA LOS FILTRO DE ALÉRGENOS ------------------------------------------->

            <button type="button" class="btn btn-secondary" id="filtros">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-filter"
                 viewBox="0 0 16 16">
                    <path d="M6 10.5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5m-2-3a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5m-2-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5"></path>
                </svg>
            </button>
        </div>

<!------------ MODAL QUE MUESTRA EL FORMULARIO PARA SELECCIONAR LOS ALERGENOS A FILTRAR ------------------------------->

        <div class="modal fade" id="filtrosModal" tabindex="-1" aria-labelledby="filtrosModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="filtrosModalLabel">Filtros</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="form-check">
                            <label class="form-check-label">Alérgenos: </label><br>
                            <form:checkboxes path="sinAlergenos" items="<%=alergenos%>" id="checkAlergenos"
                                         delimiter="<br>" class="form-check-input"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                     <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <form:button class="btn btn-primary">Aplicar filtros</form:button>
                </div>
            </div>
        </div>
    </div>

    </form:form>

    <div class="row">
        <div class="col-md-4 col-sm-6 text-center">

<!--------------------------------------- BOTÓN PARA AÑADIR UN NUEVO PLATO -------------------------------------------->

            <button type="button" id="addPlatoButton" class="btn btn-light mt-5" data-bs-toggle="modal"
                    data-bs-target="#addPlatoModal">
                <svg xmlns="http://www.w3.org/2000/svg" width="250" height="250" fill="currentColor"
                     class="bi bi-file-earmark-plus" viewBox="0 0 16 16">
                    <path d="M8 6.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V11a.5.5 0 0 1-1 0V9.5H6a.5.5 0 0 1 0-1h1.5V7a.5.5 0 0 1 .5-.5"></path>
                    <path d="M14 4.5V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h5.5zm-3 0A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5z"></path>
                </svg>
                <div class="mt-3">Añadir plato</div>
            </button>

        </div>

<!----------------------------- MODAL QUE MUESTRA EL FORMULARIO PARA CREAR UN NUEVO PLATO ------------------------------>

        <div class="modal fade" id="addPlatoModal" tabindex="-1" aria-labelledby="addPlatoModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addPlatoModalLabel">Plato nuevo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form:form modelAttribute="platoNuevo" action="/administrador/platos/crearPlato" method="post"
                                   enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="nombreNuevo">Nombre:</label>
                                <form:input path="nombre" type="text" id="nombreNuevo" class="form-control"
                                            required="true"/>
                            </div>
                            <div class="form-group">
                                <label for="descripcionNueva">Descripcion:</label>
                                <form:textarea path="descripcion" id="descripcionNueva" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label for="alergenosNuevos">Alergenos:</label>
                                <form:textarea path="alergenos" id="alergenosNuevos" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label for="imagenNueva">Imagen:</label>
                                <form:input type="file" path="videoFile" id="imagenNueva" class="form-control"/>
                            </div>
                            <div align="center">
                                <form:button class="btn btn-primary mt-3 mb-2">Añadir plato</form:button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>

 <!-------------------------------------------- LISTADO DE PLATOS ----------------------------------------------------->

        <% if (platosDTO != null && !platosDTO.isEmpty()) {
            for (PlatoDTO platoDTO : platosDTO) { %>
        <div class="col-md-4 col-sm-6">
            <div class="card d-flex flex-column">
                <img src="<%=platoDTO.getImagenBase64() != null ? "data:image/jpeg;base64," + platoDTO.getImagenBase64() : "/Images/Administrador/platoDefault.jpg" %>"
                     alt="Imagen de plato" class="imagen-gestionarPlatos">
                <div class="card-body text-center flex-grow-1">
                    <h5 class="card-title mt-3 mb-3"><%=platoDTO.getNombre()%>
                    </h5>
                    <p class="card-text"><span class="bold">Descripción: </span> <%=platoDTO.getDescripcion()%>
                    </p>
                    <p class="card-text"><span
                            class="bold">Alérgenos: </span> <%=platoDTO.getAlergenos() != null ? platoDTO.getAlergenos() : "Ninguno"%>
                    </p>

<!------------------------------------- BOTÓN PARA EDITAR DATOS PLATO ------------------------------------------------->

                    <a href="#" class="btn btn-outline-primary mt-1 mb-2" data-bs-toggle="modal"
                       data-bs-target="#editarPlatoModal<%=platoDTO.getIdplato()%>"
                       data-plato-id="<%=platoDTO.getIdplato()%>"
                       id="modalPlato<%=platoDTO.getIdplato()%>">Gestionar</a>

<!---------------------------- MODAL QUE MUESTRA EL FORMULARIO PARA EDITAR UN PLATO ----------------------------------->

                    <div class="modal fade" id="editarPlatoModal<%=platoDTO.getIdplato()%>" tabindex="-1"
                         aria-labelledby="editarPlatoeModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editarPlatoModalLabel">Editar Plato</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body ps-4 pt-3" style="text-align: start">
                                    <form:form action="/administrador/platos/guardarPlato" modelAttribute="platoModel"
                                               method="post" enctype="multipart/form-data">
                                        <form:hidden path="idplato" value="<%= platoDTO.getIdplato() %>"/>
                                        <img src="<%= platoDTO.getImagenBase64() != null ? "data:image/jpeg;base64," + platoDTO.getImagenBase64() : "/Images/Administrador/platoDefault.jpg"%>"
                                             alt="Imagen del plato" class="imagen-gestionarPlatos">
                                        <div class="form-group">
                                            <label for="video" class="form-label">Imagen del plato: </label>
                                            <form:input type="file" path="videoFile" id="video"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="nombre" class="form-label">Nombre: </label>
                                            <form:input class="form-control" type="text"
                                                        value="<%= platoDTO.getNombre() %>" path="nombre"
                                                        id="nombre" maxlength="45"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="descripcion" class="form-label">Descripción: </label>
                                            <form:textarea class="form-control" path="descripcion" id="descripcion"
                                                           placeholder="<%= platoDTO.getDescripcion()%>"
                                                           maxlength="150"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="alergenos" class="form-label">Alergenos: </label>
                                            <form:textarea class="form-control"
                                                           placeholder="<%= platoDTO.getAlergenos() %>" path="alergenos"
                                                           id="alergenos" maxlength="150"/>
                                        </div>
                                        <div class="d-flex justify-content-center mt-3">
                                            <form:button class="btn btn-primary">Guardar cambios</form:button>
                                        </div>
                                    </form:form>
                                </div>

<!-------------------------------------------- BOTÓN PARA ELIMINAR UN PLATO ------------------------------------------->

                                <div class="modal-footer d-flex justify-content-center">
                                    <form action="/administrador/platos/eliminarPlato" method="post">
                                        <input type="hidden" value="<%= platoDTO.getIdplato() %>" name="idPlato"/>
                                        <button type="submit" class="btn btn-outline-danger" id="eliminarPlato">
                                            Eliminar
                                            plato
                                        </button>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <% }
        } %>
    </div>

    <script>
        document.getElementById('filtros').addEventListener('click', function () {
            var myModal = new bootstrap.Modal(document.getElementById('filtrosModal'), {});
            myModal.show();
        });
        document.querySelectorAll('#modalPlato').forEach(item => {
            item.addEventListener('click', function () {
                var myModal = new bootstrap.Modal(document.getElementById('editarPlatoModal'), {});
                myModal.show();
            });
        });
    </script>

</body>
</html>