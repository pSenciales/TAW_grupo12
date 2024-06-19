<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.RutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.SeguimientoObjetivosDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lacas
  Date: 19/06/2024
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");
    List<SeguimientoObjetivosDTO> feedback = (List<SeguimientoObjetivosDTO>) request.getAttribute("feedback");
    String filtro = (String) request.getAttribute("filtro");
    if (filtro == null)
        filtro = "";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">

<head>
    <title>Feedback del Cliente</title>
    <script src="https://kit.fontawesome.com/af60b01aeb.js" crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <style>
        .table th, .table td {
            text-align: center;
        }

        .table-container {
            width: 100%;
        }

        .select-container {
            display: flex;
            flex-direction: row;
            gap: 1rem;
        }

        .vertical {
            border-left: solid #000000;
            max-height: 2.5rem;
        }

        .link-button {
            background: none;
            border: none;
            color: #0d6efd;
            text-decoration: underline;
            cursor: pointer;
            font: inherit;
        }

        .link-button:hover {
            color: #0a58ca;
        }

        .comunicados-container {
            display: flex;
            flex-direction: column;
            width: 80%;
        }

        .list {
            max-height: 400px;
            overflow-y: auto;
            width: 100%;
        }

        .card {
            width: 50%;
            margin: 1rem;
            text-align: center;
        }

        .feedback-instance {
            display: flex;
            flex-direction: column;
            text-align: left;
            align-items: start;
        }
    </style>
</head>

<body>
<jsp:include page="cabeceraEntrenadorCrosstraining.jsp"></jsp:include>

<div class="m-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="http://localhost:8080/entrenadorcross/clientes">Clientes</a></li>
            <li class="breadcrumb-item">
                <form method="post" action="/entrenadorcross/cliente/mostrar/<%=cliente.getIdcliente()%>" style="display:inline;">
                    <button type="submit" class="link-button"><%=cliente.getNombre()%></button>
                </form>
            </li>
            <li class="breadcrumb-item active" aria-current="page">Feedback de <%=cliente.getNombre()%> en rutina <%=rutina.getNombre()%></li>
        </ol>
    </nav>
</div>

<div class="container d-flex justify-content-center">
    <div class="card me-3">
        <div class="card-header">Feedback</div>
        <div class="card-body table-container list">
            <table class="table">
                <%
                    if (!feedback.isEmpty()) {
                %>
                <tbody>
                <% for (SeguimientoObjetivosDTO s : feedback) {
                    String checked = (s.getRealizado() == 1) ? "checked" : "";
                %>
                <tr>
                    <td>
                        <div class="feedback-instance fw-normal">
                            <%= s.getFecha().toString() + " Ejercicio: " + s.getNombreejercicio() %>, objetivo de (<%= s.getSeriesobjetivo() + "s, " + s.getRepeticionesobjetivo() + "r, " + s.getPesoobjetivo() %> )
                            y realizado con (<%= s.getSeriesrealizadas() + "s, " + s.getRepeticionesrealizadas() + "r, " + s.getPesorealizado()%>)
                            <% if (s.getObservaciones() != null && !s.getObservaciones().isEmpty()) { %>
                            <br>
                            Observaciones: <%= s.getObservaciones() %>
                            <% } %>
                            <br>
                            <div>
                                Realizado: <input disabled type="checkbox" <%= checked %>>
                            </div>
                        </div>
                    </td>
                </tr>
                <% } %>
                </tbody>
                <%
                } else {
                %>
                <tr>
                    <td class="text-danger"><%= cliente.getNombre() %> todavía no ha dado feedback</td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
    </div>

    <div class="mt-3">
        <form class="d-flex flex-column" action="/entrenadorcross/cliente/feedback/<%=cliente.getIdcliente()%>/<%=rutina.getIdrutina()%>" method="post">
            <input class="form-control me-2" type="search" placeholder="Nombre del ejercicio" name="nombre" value="<%=filtro%>">
            <div class="d-flex mt-2">
                <input class="form-control me-2" type="date" name="fecha">
                <button class="btn btn-primary" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>

</html>
