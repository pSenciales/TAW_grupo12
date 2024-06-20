<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.RutinaDTO" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 15/06/2024
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");
%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Crear Rutina</title>
    <link rel="stylesheet" type="text/css" href="/Styles/Entrenador/crearRutina.css">
</head>
<body>
<jsp:include page="cabeceraEntrenador.jsp"></jsp:include>
<%
    String mostrar = rutina.getNombre()==null ? "Crear Rutina" : "Editar "+rutina.getNombre();
%>
<div class="m-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="http://localhost:8080/entrenador/rutinas">Rutinas</a></li>

            <li class="breadcrumb-item active" aria-current="page"><%=mostrar%></li>
        </ol>
    </nav>
</div>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h4><%=mostrar%></h4>
                </div>
                <div class="card-body">
                    <form:form method="post" action="new-rutina/crear" modelAttribute="rutina">
                        <form:hidden path="idrutina"/>

                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <form:input path="nombre" type="text" placeholder="Nombre" required="required" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="idcliente">Cliente</label>
                            <form:select path="idcliente" items="${clientes}" itemValue="idcliente" itemLabel="nombre" class="form-select">
                                <form:option value="" label="Seleccione un cliente"/>
                            </form:select>
                        </div>

                        <div class="form-group text-center">
                            <form:button class="btn btn-primary mt-3">Guardar</form:button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
