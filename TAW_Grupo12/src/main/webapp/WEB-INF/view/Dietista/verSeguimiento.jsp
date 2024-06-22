<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.DietaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.SeguimientoDietaDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    TrabajadorDTO trabajador = (TrabajadorDTO) request.getAttribute("trabajador");
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    List<SeguimientoDietaDTO> seguimientos = (List<SeguimientoDietaDTO>) request.getAttribute("seguimientos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <h1>DETALLE DE SEGUIMIENTO</h1>
    <h2>Bienvenidos estimado: <%=trabajador.getNombre()%></h2>
</head>
<body>
<h1>Seguimiento de <%=cliente.getNombre()%></h1>
<br><br>

<%
    if(!seguimientos.isEmpty()){
%>
    <table border="1">
        <thead>
        <tr>
            <th scope="col">Nombre Plato</th>
            <th scope="col">Fecha</th>
            <th scope="col">Comido</th>
            <th scope="col">Cantidad / Objetivo</th>
            <th scope="col">Observaciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(SeguimientoDietaDTO s : seguimientos){
        %>
        <tr>
            <th scope="col"><%=s.getNombrePlato()%></th>
            <th scope="col"><%=s.getFecha()%></th>
            <th scope="col"><%=s.getComido()%></th>
            <th scope="col"><%=s.getCantidad()%> / <%=s.getCantidadObjeto()%></th>
            <th scope="col"><%=s.getObservaciones()%></th>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
<%
    } else {
%>
    <h1>NO HAY OBSERVACIONES DE ESTE CLIENTE TODAVIA</h1>
<%

    }
%>
<button>
    <a href="/dietista/">Volver</a>
</button>
</body>
</html>