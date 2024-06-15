<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.RutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 16/05/2024
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>

<%
    List<EjercicioDTO> ejercicioDTOList = (List<EjercicioDTO>) request.getAttribute("ejercicioList");
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <style>
        #anyadir-ej *{
            margin-left: 20px;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse mx-3" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link " href="#">Portal personal</a>
            <a class="nav-item nav-link active" href="#">Rutinas</a>
            <a class="nav-item nav-link" href="#">Clientes</a>
        </div>
    </div>
    <form method="post" action="/logout">
        <button type="submit" class="btn btn-danger mx-3">logout</button>
    </form>
</nav>
<h1>Rutina: <%=rutina.getNombre()%></h1>
<form:form method="post" action="/addEjercicio" modelAttribute="ejercicioRutinaDTO">
    <div class="m-5 d-flex flex-row " id="anyadir-ej">
        <form:hidden path="rutina"></form:hidden>
        <form:select path="diassemana" >
            <form:option value="1" label="Lunes"></form:option>
            <form:option value="2" label="Martes"></form:option>
            <form:option value="3" label="Miercoles"></form:option>
            <form:option value="4" label="Jueves"></form:option>
            <form:option value="5" label="Viernes"></form:option>
            <form:option value="6" label="Sábado"></form:option>
            <form:option value="7" label="Domingo"></form:option>
        </form:select>
        <form:input type="number" min="1" placeholder="Repeticiones"  path="repeticiones"/>
        <form:input type="number" min="1" placeholder="Series"  path="series"/>
        <form:input type="number" min="1" placeholder="Peso"  path="peso"/>
        <form:select path="ejercicio">
            <form:options items="${ejercicioList}" itemLabel="nombre" itemValue="idejercicio"></form:options>
        </form:select>
        <form:button class="btn btn-primary">Añadir ejercicio</form:button>
    </div>
</form:form>

<table class="table m-5">
    <thead>
    <tr>
        <th scope="col">Lunes</th>
        <th scope="col">Martes</th>
        <th scope="col">Miercoles</th>
        <th scope="col">Jueves</th>
        <th scope="col">Viernes</th>
        <th scope="col">Sábado</th>
        <th scope="col">Domingo</th>

    </tr>
    </thead>
    <tbody>
    <tr></tr>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>
