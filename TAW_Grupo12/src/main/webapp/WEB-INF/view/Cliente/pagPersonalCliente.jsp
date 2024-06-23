<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<%@ page import="es.uma.taw_grupo12.entity.Dieta" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Página personal</title>
</head>
<body>
<jsp:include page="cabeceraCliente.jsp"/>
<%
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    List<TrabajadorDTO> trabajadorList = (List<TrabajadorDTO>) request.getAttribute("trabajadores");
%>
<div class="container-sm text-center mt-5 mb-5 list-group">
    <div class="plans list-group-item">
        <div class="container">
            <p class="text-center text-decoration-underline fw-bold">Mis rutinas:</p>
            <%
                if(!cliente.getRutinaList().isEmpty()) {
                    for (Rutina r : cliente.getRutinaList()) {
            %>
            <div class="row">
                <div class="col-12">
                    <%=r.getNombre()%>
                </div>
                <div class="w-100"></div>
            </div>
            <%
                }
            } else {
            %>
            <p>No tienes rutinas asignadas</p>
            <%
                }
            %>
        </div>
        <div class="container">
            <p class="text-center text-decoration-underline fw-bold">Mis dietas:</p>
            <%
                if(!cliente.getDietaList().isEmpty()) {
                    for (Dieta d : cliente.getDietaList()) {
            %>
            <div class="row">
                <div class="col-12">
                    <%=d.getNombre()%>
                </div>
                <div class="w-100"></div>
            </div>
            <%
                }
            } else {
            %>
            <p>No tienes dietas asignadas</p>
            <%
                }
            %>
        </div>
    </div>
    <div class="workers list-group-item">
        <p class="text-center text-decoration-underline fw-bold">Personal asignado:</p>
        <div class="container">
            <%
                if(!trabajadorList.isEmpty()) {
                    for (TrabajadorDTO t : trabajadorList) {
            %>
            <div class="row">
                <div class="col-12">
                    <%=t.getTipo() + ": " + t.getNombre()%>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <p>No tienes trabajadores asignados</p>
            <%
                }
            %>
        </div>
    </div>
    <form method="post" action="/cliente/salir" class="mt-5">
        <input type="submit" value="Cerrar sesion">
    </form>
</div>


</body>
</html>
