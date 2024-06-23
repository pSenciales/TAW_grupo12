<%@ page import="es.uma.taw_grupo12.dto.EjercicioRutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO" %>
<%@ page import="static es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO.equalsOrNull" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rutinas</title>
    <jsp:include page="cabeceraCliente.jsp"/>
    <%
        List<EjercicioRutinaDTO> ejercicioRutinaDTOList = (List<EjercicioRutinaDTO>) request.getAttribute("listaEjerciciosRutina");
        List<EjercicioDTO> ejercicioDTOList = (List<EjercicioDTO>) request.getAttribute("listaEjercicios");
        List<SeguimientoObjetivosDTO> seguimientoObjetivosDTOS = (List<SeguimientoObjetivosDTO>) request.getAttribute("listaSeguimientos");
    %>
</head>
<body>
<div class="container">
    <h2>Ejercicios</h2>
    <%
        if(ejercicioRutinaDTOList.isEmpty()) {
    %>
    <p>No hay ejercicios en la rutina</p>
    <%
    } else {
    %>
    <ul class="list-group">
        <%
            for(int i = 0; i < ejercicioRutinaDTOList.size(); i++) {
                EjercicioRutinaDTO ejR = ejercicioRutinaDTOList.get(i);
                EjercicioDTO ej = ejercicioDTOList.get(i);
                /* Obtengo el Seguimiento más reciente de ese ejercicio, si existe*/
                SeguimientoObjetivosDTO seguimiento = null;
                if (seguimientoObjetivosDTOS != null && !seguimientoObjetivosDTOS.isEmpty()) {
                    seguimiento = seguimientoObjetivosDTOS.stream()
                            .filter(s -> equalsOrNull(s.getNombreejercicio(), ej.getNombre()) &&
                                    equalsOrNull(s.getPesoobjetivo(), ejR.getPeso()) &&
                                    equalsOrNull(s.getSeriesobjetivo(), ejR.getSeries()) &&
                                    equalsOrNull(s.getRepeticionesobjetivo(), ejR.getRepeticiones()))
                            .reduce((first, second) -> second)
                            .orElse(null);
                }
        %>
        <li class="list-group-item">
            <h5><%=ej.getNombre()%></h5>
            <ul>
                <li><strong>Rutina:</strong> <%=ejR.getRutina()%></li>
                <li><strong>Día de la Semana:</strong> <%=ejR.getDiassemana()%></li>
                <li><strong>Peso Objetivo:</strong> <%=ejR.getPeso()%></li>
                <li><strong>Repeticiones Objetivo:</strong> <%=ejR.getRepeticiones()%></li>
                <li><strong>Series Objetivo:</strong> <%=ejR.getSeries()%></li>
                <li><strong>Series:</strong> <%=ejR.getSeries()%></li>
                <li><strong>Repeticiones:</strong> <%=ejR.getRepeticiones()%></li>
                <li><strong>Cantidad:</strong> <%=ejR.getPeso()%></li>
                <h3>Mi rendimiento:</h3>
                <li>
                    <strong>Ejercicio realizado:</strong>
                    <input type="checkbox" name="hecho" value="1" disabled <%= (seguimiento != null && seguimiento.getRealizado() == 1) ? "checked" : "" %>>
                </li>
                <li><strong>Series realizadas:</strong> <%= (seguimiento != null && seguimiento.getSeriesrealizadas() != null) ? seguimiento.getSeriesrealizadas() : ejR.getSeries() %></li>
                <li><strong>Repeticiones realizadas:</strong> <%= (seguimiento != null && seguimiento.getRepeticionesrealizadas() != null) ? seguimiento.getRepeticionesrealizadas() : ejR.getRepeticiones() %></li>
                <li><strong>Cantidad realizada:</strong> <%= (seguimiento != null && seguimiento.getPesorealizado() != null) ? seguimiento.getPesorealizado() : ejR.getPeso() %></li>
                <li><strong>Observaciones:</strong> <%= (seguimiento != null && seguimiento.getObservaciones() != null) ? seguimiento.getObservaciones() : "" %></li>
            </ul>
        </li>
        <%
            }
        %>
    </ul>
    <%
        }
    %>
    <a href="/cliente/" class="btn btn-primary">Volver</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>