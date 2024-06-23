<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.PlatoDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%
    List<String> franjas = (List<String>) request.getAttribute("franjas");
    List<String> dias = (List<String>) request.getAttribute("dias");
    List<PlatoDTO> platos = (List<PlatoDTO>) request.getAttribute("platos");
    List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");
%>
<%--Created by IntelliJ IDEA.
User: Chen Chen Longxiang
--%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <h1>DETALLES DIETA</h1>
    </head>
    <body>
        <div class="container">
            <form:form action="/dietista/postDieta/" modelAttribute="dieta" method="post">
                <form:hidden path="idDieta"/>
                <div class="form-group">
                    <label for="nombre">Nombre Dieta: </label>
                    <form:input path="nombre" id="nombre" requiered="true" placeholder="Nombre de la dieta"/>
                </div>
                <div class="form-group">
                    <label for="cliente">Cliente Asignado: </label>
                    <form:select path="idCliente" id="cliente" requiered="true" cssClass="form-select">
                        <form:option value="">Seleccione un cliente</form:option>
                        <form:options items="${clientes}" itemValue="idcliente" itemLabel="nombre"/>
                    </form:select>
                </div>
                <br><br>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Franja Horaria</th>
                            <th>Lunes</th>
                            <th>Martes</th>
                            <th>Miercoles</th>
                            <th>Jueves</th>
                            <th>Viernes</th>
                            <th>Sabado</th>
                            <th>Domingo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="franjas" items="${franjas}" varStatus="status">
                            <tr>
                                <td>${franjas}</td>
                                <c:forEach var="dias" items="${dias}" varStatus="dayStatus">
                                    <td>
                                        <form:hidden path="platoDietaList[${status.index * 7 + dayStatus.index}].franjaHoraria" value="${franjas}"></form:hidden>
                                        <form:hidden path="platoDietaList[${status.index * 7 + dayStatus.index}].diasSemana" value="${dias}"></form:hidden>
                                        <form:select path="platoDietaList[${status.index * 7 + dayStatus.index}].idPlato" cssClass="form-select">
                                            <form:option value="">Seleccione un plato</form:option>
                                            <form:options items="${platos}" itemValue="idplato" itemLabel="nombre"/>
                                        </form:select>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br><br>
                <button type="submit">Guardar Dieta</button>
            </form:form>
        </div>
        <br><br>
        <button>
            <a href="/dietista/">Volver</a>
        </button>
    </body>
</html>