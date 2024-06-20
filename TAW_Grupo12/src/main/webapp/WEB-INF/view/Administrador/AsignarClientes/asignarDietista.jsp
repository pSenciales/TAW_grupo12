<%
    /**
     * @author María Victoria Huesca
     */
%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="java.util.List" %>

<%
    //OPCION: AÑADIR BOTON DE VOLVER ATRÁS

    List<TrabajadorDTO> dietistasDTO = (List<TrabajadorDTO>) request.getAttribute("trabajadores");
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    List<Integer> trabajadoresCliente = cliente.getTrabajadorList();

    Integer idDietista = 0;

    //compruebo si el cliente ya tiene asignado un entrenador de fuerza para más tarde desasignarrlo
    for (TrabajadorDTO dietistaDTO : dietistasDTO) {
        for(Integer idTrabajador : trabajadoresCliente) {
            if (idTrabajador.equals(dietistaDTO.getIdtrabajador()) ) {
                idDietista = idTrabajador;
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
    <title>Asignar a un cliente un dietista</title>
    <link rel="stylesheet" href="/Styles/Administrador/asignarEntrenadorFuerza.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
</head>
<body>
<div class="parent-container-asignarEntrenadorFuerza">
    <div class="form-container container-asignarEntrenadorFuerza">
        <form action="/administrador/guardarAsignacionClienteTrabajador" method="post" class="form-asignarEntrenadorFuerza">
            <input type="hidden" value="<%=idCliente%>" name="idCliente"/>
            <p>Cliente: <%=nombre%></p>
            <div class="form-group mb-3">
                <label for="entrenador" class="form-label">Dietista que desees asiganar: </label>
                <select id="entrenador" name="idTrabajador" class="form-control mb-3">
                    <option value="0">Selecciona un dietista</option>
                    <%
                        for (TrabajadorDTO dietista : dietistasDTO) {
                    %>
                    <option value="<%=dietista.getIdtrabajador()%>" label="<%=dietista.getNombre()%>"></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <input type="submit" class="btn btn-primary mb-3 boton-asignarEntrenadorFuerza" value="Asignar nuevo dietista" />
        </form>
        <form action="/administrador/desasignarTrabajador" method="post" class="d-flex justify-content-center align-items-center">
            <input type="hidden" value="<%=idDietista%>" name="idTrabajadorDes"/>
            <input type="hidden" value="<%=idCliente%>" name="idClienteDes">
            <input type="submit" class="btn btn-outline-danger boton-desasignarEntrenadorFuerza" value="Desasignar dietista actual" />
        </form>
    </div>
</div>
</body>
</html>