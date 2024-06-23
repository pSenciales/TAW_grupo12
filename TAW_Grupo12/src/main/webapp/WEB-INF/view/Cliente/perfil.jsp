<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<%@ page import="es.uma.taw_grupo12.entity.Dieta" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    List<TrabajadorDTO> trabajadorList = (List<TrabajadorDTO>) request.getAttribute("trabajadores");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil</title>
</head>
<body>
<jsp:include page="cabeceraCliente.jsp"/>

<div class="container">
    <div class="left-panel">
        <div class="profile-info">
            <p class="text-center text-decoration-underline fw-bold">Mis datos:</p>
            <form:form modelAttribute="cliente" method="post" action="/cliente/editarCliente">
                <form:hidden path="idcliente"/>
                <p>Nombre y apellidos</p>
                <form:input path="nombre"/>
                <p>Correo electrónico:</p>
                <form:input path="email"/>
                <p>Peso:</p>
                <form:input path="peso"/>
                <p>Altura:</p>
                <form:input path="altura"/>
                <p>Alergias:</p>
                <form:input path="alergias"/>
                <button class="save-button" type="submit">Guardar</button>
            </form:form>
        </div>
    </div>
    <div class="vertical-separator"></div>
    <div class="left-panel">
        <div class="plans">
            <p class="text-center text-decoration-underline fw-bold">Mis planes:</p>
            <div class="container">
                <%
                    if(!cliente.getRutinaList().isEmpty()) {
                        for (Rutina r : cliente.getRutinaList()) {
                %>
                            <div class="row">
                                <div class="col"><a href="#"><%=r.getNombre()%></a><br/></div>
                            </div>
                <%
                        }
                    } else {
                %>
                    <p> No tienes rutinas asignadas</p>
                <%
                    }
                %>
            </div>
            <div class="container">
                <%
                    if(!cliente.getDietaList().isEmpty()) {
                        for (Dieta d : cliente.getDietaList()) {
                %>
                            <div class="row">
                                <div class="col"><a href="#"><%=d.getNombre()%></a><br/></div>
                            </div>
                <%
                        }
                    } else {
                %>
                    <p> No tienes dietas asignadas</p>
                <%
                    }
                %>
            </div>
        </div>
        <div class = "workers">
            <p class="text-center text-decoration-underline fw-bold">Personal asignado:</p>
            <div class="container">
            <%
                if(!trabajadorList.isEmpty()) {
                    for (TrabajadorDTO t : trabajadorList) {
            %>
                        <div class="row">
                            <div class="col"><%=t.getTipo() + ": "%><a href="#"><%=t.getNombre()%></a><br/></div>
                        </div>
            <%
                    }
                } else {
            %>
                <p> No tienes trabajadores asignados</p>
            <%
                }
            %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
