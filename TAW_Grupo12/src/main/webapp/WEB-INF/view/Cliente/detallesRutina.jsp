<%
    /**
     * @author Ignacio Morillas Rosell
     */
%>

<%@ page import="es.uma.taw_grupo12.dto.EjercicioRutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<%@ page import="es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO" %>
<%@ page import="static es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO.equalsOrNull" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rutinas</title>
    <jsp:include page="cabeceraCliente.jsp"/>
    <%
        List<Rutina> rutinaDTOS = (List<Rutina>) request.getAttribute("rutinas");
        String dia = (String) request.getAttribute("dia");
        List<EjercicioRutinaDTO> ejercicioRutinaDTOList = (List<EjercicioRutinaDTO>) request.getAttribute("listaEjerciciosRutina");
        List<EjercicioDTO> ejercicioDTOList = (List<EjercicioDTO>) request.getAttribute("listaEjercicios");
        List<SeguimientoObjetivosDTO> seguimientoObjetivosDTOS = (List<SeguimientoObjetivosDTO>) request.getAttribute("listaSeguimientos");
    %>
</head>
<body>
<div class="container">
    <h1>Selecciona una Rutina</h1>
    <%
        if(rutinaDTOS == null || rutinaDTOS.isEmpty())
        {
    %>
    <p>No tienes rutinas asignadas</p>
    <a href="/cliente/" class="btn btn-primary">Volver</a>
    <%
        } else {
            EjercicioRutinaDTO ejercicioDTO = null;
            if(ejercicioRutinaDTOList != null && !ejercicioRutinaDTOList.isEmpty()) {
                ejercicioDTO = ejercicioRutinaDTOList.getFirst();
            }
    %>
    <form action="/cliente/rutinas/" method="post">
        <div class="form-group">
            <label for="rutina">Rutina:</label>
            <select id="rutina" name="rutinaId" class="form-control">
                <%
                    for(Rutina r : rutinaDTOS) {
                        if(ejercicioDTO != null && ejercicioDTO.getRutina() == r.getIdrutina()) {
                %>
                <option value="<%=r.getIdrutina()%>" selected><%=r.getNombre()%></option>
                <%
                    } else {
                %>
                <option value="<%=r.getIdrutina()%>" ><%=r.getNombre()%></option>
                <%
                        }
                    }
                %>
            </select>
            <select name="dia" id="diaSemana" class="form-control">
                <option value="Lunes" <%= (dia != null && dia.equalsIgnoreCase("Lunes")) ? "selected" : "" %>>Lunes</option>
                <option value="Martes" <%= (dia != null && dia.equalsIgnoreCase("Martes")) ? "selected" : "" %>>Martes</option>
                <option value="Miércoles" <%= (dia != null && dia.equalsIgnoreCase("Miércoles")) ? "selected" : "" %>>Miércoles</option>
                <option value="Jueves" <%= (dia != null && dia.equalsIgnoreCase("Jueves")) ? "selected" : "" %>>Jueves</option>
                <option value="Viernes" <%= (dia != null && dia.equalsIgnoreCase("Viernes")) ? "selected" : "" %>>Viernes</option>
                <option value="Sábado" <%= (dia != null && dia.equalsIgnoreCase("Sábado")) ? "selected" : "" %>>Sábado</option>
                <option value="Domingo" <%= (dia != null && dia.equalsIgnoreCase("Domingo")) ? "selected" : "" %>>Domingo</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Seleccionar</button>
    </form>
    <%
        }
    %>

    <%
        if (ejercicioDTOList != null ) {
    %>
    <h2>Ejercicios</h2>
    <%
        if(ejercicioRutinaDTOList.isEmpty()) {
    %>
    <p>No tienes ejercicios los <%=dia%>, ¡descansa!</p>
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
            <form action="/cliente/rutinas/guardarSeguimiento" method="post">
                <h5><%=ej.getNombre()%></h5>
                <input type="hidden" name="idRutina" value="<%=ejR.getRutina()%>">
                <input type="hidden" name="dia" value="<%=ejR.getDiassemana()%>">
                <input type="hidden" name="nombreEj" value="<%=ej.getNombre()%>">
                <input type="hidden" name="pesoOb" value="<%=ejR.getPeso()%>">
                <input type="hidden" name="repOb" value="<%=ejR.getRepeticiones()%>">
                <input type="hidden" name="seriesOb" value="<%=ejR.getSeries()%>">
                <p><strong>Series:</strong> <%=ejR.getSeries()%></p>
                <p><strong>Repeticiones:</strong> <%=ejR.getRepeticiones()%></p>
                <p><strong>Cantidad:</strong> <%=ejR.getPeso()%></p>
                <%-- <%= seguimiento.getVideo() %> --%>
                <p>Mi rendimiento:</p>
                <label>
                    Ejercicio realizado
                    <input type="checkbox" name="hecho" value="1"
                        <%= (seguimiento != null && seguimiento.getRealizado() == 1) ? "checked" : "" %>>
                </label>
                <p><strong>Series realizadas:</strong></p>
                <input type="number" name="ser"
                       value="<%= (seguimiento != null && seguimiento.getSeriesrealizadas() != null)
                       ? seguimiento.getSeriesrealizadas() : ejR.getSeries() %>"><br />
                <p><strong>Repeticiones realizadas:</strong></p>
                <input type="number" name="rep"
                       value="<%= (seguimiento != null && seguimiento.getRepeticionesrealizadas() != null)
                       ? seguimiento.getRepeticionesrealizadas() : ejR.getRepeticiones() %>"><br /><br />
                <p><strong>Cantidad realizada:</strong></p>
                <input type="text" name="cant"
                       value="<%= (seguimiento != null && seguimiento.getPesorealizado()  != null)
                       ? seguimiento.getPesorealizado() : ejR.getPeso() %>"><br /><br />
                <p><strong>Observaciones:</strong></p>
                <input type="text" name="obs"
                       value="<%= (seguimiento != null && seguimiento.getObservaciones() != null)
                       ? seguimiento.getObservaciones() : "" %>"><br /><br />
                <input type="submit" value="Guardar rendimiento">
            </form>
        </li>
        <%
                }
        %>
    </ul>
    <%
            }
        }
    %>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>