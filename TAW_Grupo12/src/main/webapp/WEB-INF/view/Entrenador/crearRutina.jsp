<<<<<<< Updated upstream
=======
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
>>>>>>> Stashed changes
<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 15/06/2024
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");

%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<jsp:include page="cabeceraEntrenador.jsp"></jsp:include>
<<<<<<< Updated upstream
    <form method="post" action="new-rutina/crear">

        <input name="nombre" type="text" placeholder="Nombre" required/>
        <select name="cliente" required>
            <option>Seleccione un cliente</option>
            <%
                for(ClienteDTO cliente : clientes){


            %>
            <option value="<%=cliente.getIdcliente()%>"><%=cliente.getNombre()%></option>

            <%
                }
            %>
        </select>
        <button type="submit" class="btn btn-primary">Crear</button>



    </form>
=======

    <form:form method="post" action="new-rutina/crear" modelAttribute="rutina">
        <form:hidden path="idrutina"></form:hidden>
        <form:input name="nombre" type="text" placeholder="Nombre" required="required" path="nombre"/>
        <form:select path="idcliente" items="${clientes}" itemValue="idcliente" itemLabel="nombre"></form:select>
        <form:button class="btn btn-primary">Guardar</form:button>
    </form:form>
>>>>>>> Stashed changes

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</body>
</html>
