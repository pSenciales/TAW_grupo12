<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.RutinaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.StringJoiner" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: lacas
  Date: 19/06/2024
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%
    List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");
    List<RutinaDTO> rutinas = (List<RutinaDTO>) request.getAttribute("rutinas");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">

<head>
    <title>Lista de Clientes</title>
    <script src="https://kit.fontawesome.com/af60b01aeb.js" crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <style>
        .table th, .table td {
            text-align: center;
        }

        .table-container {
            margin: 2rem auto;
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
    </style>
</head>

<body>
<jsp:include page="cabeceraEntrenadorCrosstraining.jsp"></jsp:include>
<div class="container mt-3">
    <div class="row">
        <div class="col">
            <h1 class="text-center">Clientes</h1>
        </div>
    </div>
</div>
<div class="table-container">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th scope="col">Clientes</th>
            <th scope="col">Rutinas</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (ClienteDTO c : clientes) {
                StringJoiner sj = new StringJoiner(" - ");
                for (RutinaDTO rutina : rutinas) {
                    if (Objects.equals(rutina.getIdcliente(), c.getIdcliente()))
                        sj.add(rutina.getNombre());
                }
                if (Objects.equals(sj.toString(), ""))
                    sj.add("Sin asignar");

        %>
        <tr>
            <td class="contenedor">
                <span><%=c.getNombre()%></span>
                <div class="botones">
                    <form action="/entrenadorcross/cliente/mostrar/<%=c.getIdcliente()%>" method="post">
                        <button class="btn btn-primary"><i class="fa-solid fa-eye"></i></button>
                    </form>
                </div>
            </td>
            <td>
                <%=sj.toString()%>
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
