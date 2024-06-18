<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.RutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioRutinaDTO" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 16/05/2024
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>

<%
    List<EjercicioDTO> ejercicioDTOList = (List<EjercicioDTO>) request.getAttribute("ejercicioList");
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");
    EjercicioRutinaDTO ejercicioRutinaDTO = (EjercicioRutinaDTO) request.getAttribute("ejercicioRutinaDTO");
    String selected[] = new String[]{"", "", "", "", "", "", ""};
    selected[ejercicioRutinaDTO.getDiassemanaInt()-1] = "selected";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/af60b01aeb.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body>
<jsp:include page="cabeceraEntrenador.jsp"></jsp:include>
<div class="m-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="http://localhost:8080/entrenador/rutinas">Rutinas</a></li>
            <li class="breadcrumb-item"><a href="http://localhost:8080/entrenador/visualizar/<%=rutina.getIdrutina()%>"><%=rutina.getNombre()%></a></li>
            <li class="breadcrumb-item active" aria-current="page">Editar ejercicio</li>
        </ol>
    </nav>
</div>
<div class="container">
    <form:form action="/entrenador/rutina/editar-process" modelAttribute="ejercicioRutinaDTO">
        <div class="m-5 d-flex flex-row align-items-center" id="anyadir-ej">
            <form:hidden path="rutina"/>
            <form:hidden path="ejercicioRutinaPK"/>
            <form:select required="required" path="diassemana" class="form-select">
                <form:option value="1" label="Lunes" selected="<%=selected[0]%>"/>
                <form:option value="2" label="Martes" selected="<%=selected[1]%>"/>
                <form:option value="3" label="Miércoles" selected="<%=selected[2]%>"/>
                <form:option value="4" label="Jueves" selected="<%=selected[3]%>"/>
                <form:option value="5" label="Viernes" selected="<%=selected[4]%>"/>
                <form:option value="6" label="Sábado" selected="<%=selected[5]%>"/>
                <form:option value="7" label="Domingo" selected="<%=selected[6]%>"/>
            </form:select>
            <form:input required="required" type="number" min="1" placeholder="Repeticiones" path="repeticiones" class="form-control"/>
            <form:input required="required" type="number" min="1" placeholder="Series" path="series" class="form-control"/>
            <form:input required="required" type="number" min="1" placeholder="Peso" path="peso" class="form-control"/>
            <form:select required="required" path="ejercicio" class="form-select" items="${ejercicioDTOList}" itemLabel="nombre" itemValue="idejercicio">
            </form:select>
            <form:button class="btn btn-primary">Guardar ejercicio</form:button>
        </div>
    </form:form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>
