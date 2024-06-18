<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.entity.Trabajador" %>
<%
    List<TrabajadorDTO> entrenadores = (List<TrabajadorDTO>) request.getAttribute("trabajadores");
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    TrabajadorDTO entrenadorFuerza = null;
    for (TrabajadorDTO entrenador : entrenadores) {
        if ("ENTRENADOR FUERZA".equals(entrenador.getTipo())) {
            entrenadorFuerza = entrenador;
            break;
        }
    }
    Integer idCliente = cliente.getIdcliente();
    Integer idEntrenador = entrenadorFuerza != null ? entrenadorFuerza.getIdtrabajador() : 0;
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
    <input type="hidden" value="<%=idCliente%>" name="idCliente"/>
    <p>Cliente: <%=nombre%></p>
    <label for="entrenador">Entrenador de fuerza que desees asiganar: </label>
    <select id="entrenador" name="idEntrenador">
        <option value="0">Selecciona un entrenador</option>
        <%
            for (TrabajadorDTO entrenador : entrenadores) {
        %>
            <option value="<%=entrenador.getIdtrabajador()%>" label="<%=entrenador.getNombre()%>"></option>
        <%
            }
        %>
    </select>
    <input type="submit" class="btn" value="Asignar" />
</form>
<form action="/administrador/desasignarTrabajador" method="post">
    <input type="hidden" value="<%=idEntrenador%>" name="idEntrenadorDes"/>
    <input type="hidden" value="<%=idCliente%>" name="idClienteDes">
    <input type="submit" class="btn" value="Desasignar entrenador de fuerza actual" />
</form>
</body>
</html>