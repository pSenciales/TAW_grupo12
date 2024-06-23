<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.*" %>
<%@ page import="static es.uma.taw_grupo12.dto.SeguimientoDietaDTO.equalsOrNull" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rutinas</title>
    <jsp:include page="cabeceraCliente.jsp"/>
    <%
        String dietaSel = (String) request.getAttribute("dietaSel");
        List<PlatoDietaDTO> platoDietaDTOList = (List<PlatoDietaDTO>) request.getAttribute("listaPlatosDieta");
        List<PlatoDTO> platoDTOList = (List<PlatoDTO>) request.getAttribute("listaPlatos");
        List<SeguimientoDietaDTO> seguimientoDietaDTOS = (List<SeguimientoDietaDTO>) request.getAttribute("listaSeguimientos");

        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    %>
</head>
<body>
<div class="container">
    <h2>Dieta: <%=dietaSel%></h2>
    <h4>Platos</h4>
    <%
        if(platoDietaDTOList.isEmpty()) {
    %>
    <p>No hay platos en la dieta</p>
    <%
    } else {
    %>
    <table class="table table-bordered">
        <thead>
        <tr>
            <% for(String dia : diasSemana) { %>
            <th><%= dia %></th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <% for(int i = 0; i < platoDietaDTOList.size(); i++) { %>
        <tr>
            <% for(String dia : diasSemana) { %>
            <td>
                <%
                    // Iterar sobre los platos y colocar en la celda correspondiente al día
                    boolean encontrado = false;
                    for(int j = 0; j < platoDietaDTOList.size(); j++) {
                        PlatoDietaDTO plD = platoDietaDTOList.get(j);
                        PlatoDTO pl = platoDTOList.get(j);
                        /* Obtengo el Seguimiento más reciente de ese ejercicio, si existe*/
                        SeguimientoDietaDTO seguimiento = null;
                        if (seguimientoDietaDTOS != null && !seguimientoDietaDTOS.isEmpty()) {
                            seguimiento = seguimientoDietaDTOS.stream()
                                    .filter(s -> equalsOrNull(s.getNombrePlato(), pl.getNombre()) &&
                                            equalsOrNull(s.getCantidadObjeto(), plD.getCantidad()))
                                    .reduce((first, second) -> second)
                                    .orElse(null);
                        }

                        // Mostrar el plato solo si es del día actual
                        if (dia.equals(plD.getDiasSemana())) {
                            encontrado = true;
                %>
                <h3><%=plD.getFranjaHoraria()%></h3>
                <ul>
                    <br/>
                    <li><strong>Comida:</strong> <%=pl.getNombre()%></li>
                    <li><strong>Cantidad Objetivo:</strong> <%=plD.getCantidad()%></li>
                    <li><strong>Calorías:</strong> <%=plD.getCalorias()%></li>

                    <br/>
                    <h4>Mi progreso:</h4>
                    <li>
                        <strong>Plato comido:</strong>
                        <input type="checkbox" name="hecho" value="1" disabled <%= (seguimiento != null && seguimiento.getComido() == 1) ? "checked" : "" %>>
                    </li>
                    <li><strong>Cantidad comida:</strong> <%= (seguimiento != null && seguimiento.getCantidad() != null) ? seguimiento.getCantidad() : "" %></li>
                    <li><strong>Observaciones:</strong> <%= (seguimiento != null && seguimiento.getObservaciones() != null) ? seguimiento.getObservaciones() : "" %></li>
                </ul>
                <%
                        }
                    }
                    // Si no se encontró ningún plato para el día, muestra un mensaje
                    if (!encontrado) {
                %>
                <p>No hay plato asignado para este día.</p>
                <% } %>
            </td>
            <% } // Fin del ciclo de días %>
        </tr>
        <tr><td colspan="<%= diasSemana.length %>"><hr></td></tr> <!-- Separador -->
        <% } // Fin del ciclo de platos %>
        </tbody>
    </table>
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