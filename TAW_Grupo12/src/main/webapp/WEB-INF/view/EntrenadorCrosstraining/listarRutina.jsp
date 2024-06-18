<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.RutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioRutinaDTO" %>
<%@ page import="java.util.Objects" %>
<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 18/06/2024
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>

<%
    List<RutinaDTO> rutinas = (List<RutinaDTO>) request.getAttribute("rutinas");
    List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">

<head>
    <script src="https://kit.fontawesome.com/af60b01aeb.js" crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <style>
        .table th, .table td {
            text-align: center; /* Center text in thead and tbody */
        }

        .table-container {
            margin: 5rem auto;
            width: 90%;
        }
        .contenedor {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .botones {
            display: flex;
            gap: 10px;
        }
        .search-container{
            margin-bottom: 1rem;

        }
        .func-container{
            display: flex;
            gap: 2rem;
        }
        .vertical{
            border-left: solid #000000;
            max-height: 2.5rem;
        }


    </style>
</head>

<body>
<jsp:include page="cabeceraEntrenadorCrosstraining.jsp"></jsp:include>

<div class="table-container">
    <div class="func-container">
        <form action="/entrenadorcross/nuevarutina" method="get">
            <button class="btn btn-success">Crear Rutina</button>
        </form>
        <div class="vertical">
        </div>
        <div class="search-container">
            <form:form method="post" action="/entrenadorcross/filtrar" modelAttribute="filtro">
                <form:input path="nombre" type="text" placeholder="Nombre"/>
                <form:select path="idcliente">
                    <form:option value="-1" label="Seleccione un cliente"/>
                    <form:options items="${clientes}" itemValue="idcliente" itemLabel="nombre"/>
                </form:select>
                <button class="btn btn-primary"><i class="fa-solid fa-magnifying-glass"></i></button>
            </form:form>
        </div>
    </div>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th scope="col">Rutinas</th>
            <th scope="col">Clientes</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(RutinaDTO rutina : rutinas){
                String cliente = "Sin asignar";
                boolean encontrado = false;
                int i = 0;
                while(i<clientes.size() && !encontrado){
                    if(Objects.equals(clientes.get(i).getIdcliente(), rutina.getIdcliente())){
                        encontrado = true;
                        cliente = clientes.get(i).getNombre();
                    }
                    i++;
                }
        %>
        <tr>
            <td class="contenedor">
                <span><%=rutina.getNombre()%></span>
                <div class="botones">

                    <form action="/entrenadorcross/ver/<%=rutina.getIdrutina()%>" method="get">
                        <button class="btn btn-primary"><i class="fa-solid fa-eye"></i></button>
                    </form>
                    <form action="/entrenadorcross/editar" method="post">
                        <input type="hidden" name="id" value="<%=rutina.getIdrutina()%>">
                        <button class="btn btn-warning"><i class="fa-solid fa-pen-to-square"></i></button>
                    </form>
                    <form action="/entrenadorcross/eliminar/<%=rutina.getIdrutina()%>" method="get">
                        <button class="btn btn-danger"><i class="fa-solid fa-trash"></i></button>
                    </form>
                </div>
            </td>
            <td>
                <%=cliente%>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>
