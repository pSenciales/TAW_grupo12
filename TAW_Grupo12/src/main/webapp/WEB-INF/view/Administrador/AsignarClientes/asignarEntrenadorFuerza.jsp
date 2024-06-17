<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.entity.Trabajador" %>
<%
    List<TrabajadorDTO> entrenadores = (List<TrabajadorDTO>) request.getAttribute("trabajadores");
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    List<Integer> trabajadoresCliente = cliente.getTrabajadorList();

    Integer idEntrenadorFuerza = null;

    //compruebo si el cliente ya tiene asignado un entrenador de fuerza para más tarde desasignarrlo
    for (TrabajadorDTO entrenador : entrenadores) {
        for(Integer idTrabajador : trabajadoresCliente) {
            if (idTrabajador.equals(entrenador.getIdtrabajador()) && "ENTRENADOR FUERZA".equals(entrenador.getTipo())) {
                idEntrenadorFuerza = idTrabajador;
                break;
            }
        }
    }
    Integer idCliente = cliente.getIdcliente();
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
    <input type="hidden" value="<%=idEntrenadorFuerza%>" name="idEntrenadorDes"/>
    <input type="hidden" value="<%=idCliente%>" name="idClienteDes">
    <input type="submit" class="btn" value="Desasignar entrenador de fuerza actual" />
</form>
</body>
</html>