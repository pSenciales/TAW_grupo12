<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="java.util.List" %>
<%
    List<TrabajadorDTO> entrenadores = (List<TrabajadorDTO>) request.getAttribute("trabajadores");
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    Integer id = cliente.getIdcliente();
    String nombre = cliente.getNombre();
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <title>Asignar Cliente un Entrenador Fuerza</title>
</head>
<body>
<div class="form-container"></div>
<form action="/administrador/guardarAsignacionClienteEntrenadorFuerza" method="post">
    <input type="hidden" value="<%=id%>" />
    <p>Cliente: <%=nombre%></p>
    <label for="entrenador">Entrenador de fuerza que desees asiganar: </label>
    <select id="entrenador" name="entrenador">
        <option value="0">Selecciona un entrenador</option>
        <%
            for (TrabajadorDTO entrenador : entrenadores) {
        %>
            <option value="<%=entrenador.getIdtrabajador()%>" label="<%=entrenador.getNombre()%>"></option>
        <%
            }
        %>
    </select>
</form>
</body>
</html>