<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.RutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioRutinaDTO" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 16/05/2024
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<EjercicioDTO> ejercicioDTOList = (List<EjercicioDTO>) request.getAttribute("ejercicioList");
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");
    EjercicioRutinaDTO ejercicioRutinaDTO = (EjercicioRutinaDTO) request.getAttribute("ejercicioRutinaDTO");
    String selected[] = new String[]{"", "", "", "", "", "", ""};
    selected[ejercicioRutinaDTO.getDiassemanaInt()-1] = "selected";
%>
<!doctype html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://kit.fontawesome.com/af60b01aeb.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Editar Ejercicio</title>

    <link rel="stylesheet" type="text/css" href="/Styles/Entrenador/editarEjercicioRutina.css">
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
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h4>Editar Ejercicio</h4>
                </div>
                <div class="card-body">
                    <form:form method="post" action="/entrenador/rutina/editar-process" modelAttribute="ejercicioRutinaDTO">
                        <form:hidden path="rutina"/>
                        <form:hidden path="ejercicioRutinaPK"/>

                        <div class="form-group">
                            <label for="diassemana">Día de la Semana</label>
                            <form:select required="required" path="diassemana" class="form-select">
                                <form:option value="1" label="Lunes" selected="<%=selected[0]%>"/>
                                <form:option value="2" label="Martes" selected="<%=selected[1]%>"/>
                                <form:option value="3" label="Miércoles" selected="<%=selected[2]%>"/>
                                <form:option value="4" label="Jueves" selected="<%=selected[3]%>"/>
                                <form:option value="5" label="Viernes" selected="<%=selected[4]%>"/>
                                <form:option value="6" label="Sábado" selected="<%=selected[5]%>"/>
                                <form:option value="7" label="Domingo" selected="<%=selected[6]%>"/>
                            </form:select>
                        </div>

                        <div class="form-group">
                            <label for="series">Series</label>
                            <form:input required="required" type="number" min="1" placeholder="Series" path="series" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="repeticiones">Repeticiones</label>
                            <form:input required="required" type="number" min="1" placeholder="Repeticiones" path="repeticiones" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="peso">Peso</label>
                            <form:input required="required" type="number" min="1" placeholder="Peso" path="peso" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <label for="ejercicio">Ejercicio</label>
                            <form:select required="required" path="ejercicio" class="form-select" items="${ejercicioDTOList}" itemLabel="nombre" itemValue="idejercicio">
                                <form:option value="" label="Seleccione un ejercicio"/>
                            </form:select>
                        </div>

                        <div class="form-group text-center">
                            <form:button class="btn btn-primary mt-3">Guardar ejercicio</form:button>
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
