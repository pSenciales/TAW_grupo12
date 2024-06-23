<%
    /**
     * @author Ignacio Morillas Rosell
     */
%>

<%@ page import="es.uma.taw_grupo12.entity.Dieta" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.PlatoDietaDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.PlatoDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.SeguimientoDietaDTO" %>
<%@ page import="static es.uma.taw_grupo12.dto.SeguimientoDietaDTO.equalsOrNull" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dietas</title>
    <jsp:include page="cabeceraCliente.jsp"/>
    <%
        List<Dieta> dieta = (List<Dieta>) request.getAttribute("dietas");
        String dia = (String) request.getAttribute("dia");
        List<PlatoDietaDTO> platoDietaDTOList = (List<PlatoDietaDTO>) request.getAttribute("listaPlatosDieta");
        List<PlatoDTO> platoDTOList = (List<PlatoDTO>) request.getAttribute("listaPlatos");
        List<SeguimientoDietaDTO> seguimientoDietaDTOList = (List<SeguimientoDietaDTO>) request.getAttribute("listaSeguimientos");
    %>
</head>
<body>
<div class="container">
    <h1>Selecciona una Dieta</h1>
    <%
        if(dieta == null || dieta.isEmpty())
        {
    %>
    <p>No tienes dietas asignadas</p>
    <a href="/cliente/" class="btn btn-primary">Volver</a>
    <%
    } else {
        PlatoDietaDTO platoDietaDTO = null;
        if(platoDietaDTOList != null && !platoDietaDTOList.isEmpty()) {
            platoDietaDTO = platoDietaDTOList.getFirst();
        }
    %>
    <form action="/cliente/dietas/" method="post">
        <div class="form-group">
            <label for="dieta">Dieta:</label>
            <select id="dieta" name="rutinaId" class="form-control">
                <%
                    for(Dieta d : dieta) {
                        if(platoDietaDTO != null && platoDietaDTO.getIdDieta() == d.getIddieta()) {
                %>
                <option value="<%=d.getIddieta()%>" selected><%=d.getNombre()%></option>
                <%
                } else {
                %>
                <option value="<%=d.getIddieta()%>" ><%=d.getNombre()%></option>
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
        if (platoDTOList != null ) {
    %>
    <h2>Platos</h2>
    <%
        if(platoDietaDTOList.isEmpty()) {
    %>
    <p>No tienes platos planificados los <%=dia%>, ¡improvisa!</p>
    <%
    } else {
    %>
    <ul class="list-group">
        <%
            for(int i = 0; i < platoDietaDTOList.size(); i++) {
                PlatoDietaDTO platoD = platoDietaDTOList.get(i);
                PlatoDTO plato = platoDTOList.get(i);
                /* Obtengo el Seguimiento más reciente de ese plato, si existe*/
                SeguimientoDietaDTO seguimiento = null;
                if (seguimientoDietaDTOList != null && !seguimientoDietaDTOList.isEmpty()) {
                    seguimiento = seguimientoDietaDTOList.stream()
                            .filter(s -> equalsOrNull(s.getNombrePlato(), plato.getNombre()))
                            .reduce((first, second) -> second)
                            .orElse(null);
                }
        %>
        <li class="list-group-item">
            <form action="/cliente/dietas/guardarSeguimiento" method="post">
                <h3><%=platoD.getFranjaHoraria()%></h3>
                <h5><%=plato.getNombre()%></h5>
                <input type="hidden" name="idDieta" value="<%=platoD.getIdDieta()%>">
                <input type="hidden" name="dia" value="<%=platoD.getDiasSemana()%>">
                <input type="hidden" name="nombrePlato" value="<%=plato.getNombre()%>">
                <input type="hidden" name="cantOb" value="<%=platoD.getCantidad()%>">
                <p><strong>Calorías:</strong> <%=platoD.getCalorias()%></p>
                <p><strong>Cantidad:</strong> <%=platoD.getCantidad()%></p>
                <%-- <%= seguimiento.getVideo() %> --%>
                <p>Mi progreso:</p>
                <label>
                    Plato comido
                    <input type="checkbox" name="comido" value="1"
                        <%= (seguimiento != null && seguimiento.getComido() == 1) ? "checked" : "" %>>
                </label>
                <p><strong>Cantidad comida:</strong></p>
                <input type="number" name="cantidadComida"
                       value="<%= (seguimiento != null && seguimiento.getCantidad() != null)
                       ? seguimiento.getCantidad() : platoD.getCantidad() %>"><br />
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