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
    List<EjercicioRutinaDTO> ejerciciosRutina = (List<EjercicioRutinaDTO>) request.getAttribute("ejerciciosRutina");

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

    <style>
        #anyadir-ej *{
            margin-left: 20px;
        }

        .table th, .table td {
            text-align: center; /* Center text in thead and tbody */
            font-size: 0.85rem; /* Make tbody text smaller */
            width: 14.28%; /* Ensure equal width for all columns */
        }

        .table thead th {
            font-size: 1rem; /* Keep thead text size default */
        }
        .table-container {
            margin: 0 auto;
            width: 90%;
        }
        .button-container {
            display: flex;
            align-items: center;
            gap: 5px;
            margin-left: 2rem;
            margin-top: 0.5rem;
        }

    </style>
</head>

<body>
<jsp:include page="cabeceraEntrenador.jsp"></jsp:include>
<h1>Rutina: <%=rutina.getNombre()%></h1>

<form:form method="post" action="/entrenador/addEjercicio" modelAttribute="ejercicioRutinaDTO">
    <div class="m-5 d-flex flex-row " id="anyadir-ej">
        <form:hidden path="rutina"></form:hidden>
        <form:select required="required" path="diassemana" >
            <form:option value="1" label="Lunes"></form:option>
            <form:option value="2" label="Martes"></form:option>
            <form:option value="3" label="Miercoles"></form:option>
            <form:option value="4" label="Jueves"></form:option>
            <form:option value="5" label="Viernes"></form:option>
            <form:option value="6" label="Sábado"></form:option>
            <form:option value="7" label="Domingo"></form:option>
        </form:select>
        <form:input required="required" type="number" min="1" placeholder="Repeticiones"  path="repeticiones"/>
        <form:input required="required" type="number" min="1" placeholder="Series"  path="series"/>
        <form:input required="required" type="number" min="1" placeholder="Peso"  path="peso"/>
        <form:select required="required" path="ejercicio">
            <form:options items="${ejercicioList}" itemLabel="nombre" itemValue="idejercicio"></form:options>
        </form:select>
        <form:button class="btn btn-primary">Añadir ejercicio</form:button>
    </div>
</form:form>
<div class="table-container">
<table class="table table-bordered table-striped">
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
    <%
        int size = !ejerciciosRutina.isEmpty() ? ejerciciosRutina.getLast().getOrden()+1 : 0;
        for(int i = 0; i<size; i++){


    %>
    <tr>
        <%
            int index = 0;
            String[] ejerciciorutina = new String[]{"","","","","","",""};
            Integer[] ids = new Integer[]{-1,-1,-1,-1,-1,-1,-1,};

            while(index < ejerciciosRutina.size() && ejerciciosRutina.get(index).getOrden() <= i){
                if(ejerciciosRutina.get(index).getOrden() == i) {
                    boolean encontrada = false;
                    int ej = 0;
                    EjercicioDTO aux = null;
                    while(!encontrada && ej < ejercicioDTOList.size()){
                        aux = ejercicioDTOList.get(ej);
                        if(Objects.equals(aux.getIdejercicio(), ejerciciosRutina.get(index).getEjercicio()))
                            encontrada = true;
                        ej++;
                    }
                    EjercicioRutinaDTO ejercicio = ejerciciosRutina.get(index);
                    ids[ejercicio.getDiassemanaInt() - 1] = ejercicio.getEjercicioRutinaPK();
                    assert aux != null;
                    ejerciciorutina[ejercicio.getDiassemanaInt() - 1] = aux.getNombre()+" "+ejercicio.toString();
                }
                index++;
            }
        %>
        <%
            for(int j = 0; j<7; j++){
        %>
        <td>
            <%=ejerciciorutina[j]%>
            <%
                if(ejerciciorutina[j] != ""){
            %>
            <div class="button-container">
            <form method="post" action="/entrenador/rutina/subir">
                <input type="hidden" value="<%=ids[j]%>" name="id">
                <button class="btn btn-primary btn-sm" type="submit"><i class="fa-solid fa-arrow-up"></i></button>
            </form>
            <form method="post" action="/entrenador/rutina/bajar">
                <input type="hidden" value="<%=ids[j]%>" name="id">
                <button class="btn btn-primary btn-sm" type="submit"><i class="fa-solid fa-arrow-down"></i></button>
            </form>
            <form method="get" action="/entrenador/rutina/borrar">
                <input type="hidden" value="<%=ids[j]%>" name="id">
                <button class="btn btn-danger btn-sm" type="submit"><i class="fa-solid fa-trash"></i></button>
            </form>
            <form method="post" action="/entrenador/rutina/editar">
                <input type="hidden" value="<%=ids[j]%>" name="id">
                <button class="btn btn-warning btn-sm" type="submit"><i class="fa-solid fa-pen-to-square"></i></button>
            </form>
            </div>
            <%
                }
            %>
        </td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>
