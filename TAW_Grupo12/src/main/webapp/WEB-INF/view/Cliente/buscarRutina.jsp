<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioRutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO" %>
<%@ page import="static es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO.equalsOrNull" %>
<%@ page import="es.uma.taw_grupo12.dto.RutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar rutinas</title>
    <jsp:include page="cabeceraCliente.jsp"/>
    <%
        List<Rutina> rutinas = (List<Rutina>) request.getAttribute("rutinas");
        String busqueda = (String) request.getAttribute("busqueda");
        List<RutinaDTO> rutinasEncontradas = (List<RutinaDTO>) request.getAttribute("rutinasEncontradas");
    %>
</head>
<body>
<div class="container">
    <h1>Selecciona una Rutina</h1>
    <%
        if(rutinas == null || rutinas.isEmpty())
        {
    %>
    <p>No tienes rutinas asignadas</p>
    <a href="/cliente/" class="btn btn-primary">Volver</a>
    <%
    } else {
    %>
    <form action="/cliente/rutinas/buscar" method="post">
        <div class="form-group">
            <label for="rutina">Nombre de la rutina:</label>
            <input id="rutina" type="text" name="nombre" value="<%= (busqueda != null) ? busqueda : ""%>">
        </div>
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form>
    <%
        }
    %>

    <%
        if(rutinasEncontradas != null && !rutinasEncontradas.isEmpty()) {
    %>
    <h2>Rutinas encontradas:</h2>
    <%
            for(RutinaDTO rut : rutinasEncontradas) {
    %>
        <a href="/cliente/rutinas/rutinaSeleccionada/<%=rut.getIdrutina()%>"><%=rut.getNombre()%></a><br/>
    <%
            }
        } else if (rutinasEncontradas != null){
    %>
    <h2>No se encontraron rutinas</h2>
    <%
        }
    %>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>