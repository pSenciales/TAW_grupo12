<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.RutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioRutinaDTO" %>
<%@ page import="java.util.Objects" %>
<%@ page import="es.uma.taw_grupo12.dto.ClienteDTO" %>
<%@ page import="java.util.StringJoiner" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 16/05/2024
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>

<%
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
%>
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

        .contenedor {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 20px;
        }

        .table-container {
            width: 60%;
            margin-left: 1.5rem;
        }

        .select-container{
            display: flex;
            flex-direction: row;
            gap: 1rem;
        }
        .vertical{
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

    </style>
</head>

<body>
<jsp:include page="cabeceraEntrenador.jsp"></jsp:include>
<div class="m-3">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="http://localhost:8080/entrenador/clientes">Clientes</a></li>
            <li class="breadcrumb-item">
                <form method="post" action="/entrenador/cliente/visualizar/<%=cliente.getIdcliente()%>" style="display:inline;">
                    <button type="submit" class="link-button"><%=cliente.getNombre()%></button>
                </form>
            </li>
            <li class="breadcrumb-item active" aria-current="page">Feedback de <%=cliente.getNombre()%> de </li>
        </ol>
    </nav>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
