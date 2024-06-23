<%@ page import="java.util.List" %>
<%@ page import="static es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO.equalsOrNull" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<%@ page import="es.uma.taw_grupo12.dto.*" %>
<%@ page import="es.uma.taw_grupo12.entity.Dieta" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar rutinas</title>
    <jsp:include page="cabeceraCliente.jsp"/>
    <%
        List<Dieta> dietas = (List<Dieta>) request.getAttribute("dietas");
        String busqueda = (String) request.getAttribute("busqueda");
        List<DietaDTO> dietasEncontradas = (List<DietaDTO>) request.getAttribute("dietasEncontradas");
    %>
</head>
<body>
<div class="container">
    <h1>Selecciona una dieta</h1>
    <%
        if(dietas == null || dietas.isEmpty())
        {
    %>
    <p>No tienes rutinas dietas</p>
    <a href="/cliente/" class="btn btn-primary">Volver</a>
    <%
    } else {
    %>
    <form action="/cliente/dietas/buscar" method="post">
        <div class="form-group">
            <label for="dieta">Nombre de la dieta:</label>
            <input id="dieta" type="text" name="nombre" value="<%= (busqueda != null) ? busqueda : ""%>">
        </div>
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form>
    <%
        }
    %>

    <%
        if(dietasEncontradas != null && !dietasEncontradas.isEmpty()) {
    %>
    <h2>Dietas encontradas:</h2>
    <%
            for(DietaDTO d : dietasEncontradas) {
    %>
        <a href="/cliente/dietas/dietaSeleccionada/<%=d.getIdDieta()%>"><%=d.getNombre()%></a><br/>
    <%
            }
        } else if (dietasEncontradas != null){
    %>
    <h2>No se encontraron dietas</h2>
    <%
        }
    %>

</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>