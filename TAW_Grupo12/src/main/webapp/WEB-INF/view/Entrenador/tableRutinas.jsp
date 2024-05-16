<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 16/05/2024
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <style>
        #anyadir-ej *{
            margin-left: 20px;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse mx-3" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link " href="#">Portal personal</a>
            <a class="nav-item nav-link active" href="#">Rutinas</a>
            <a class="nav-item nav-link" href="#">Clientes</a>
        </div>
    </div>
    <form method="post" action="/logout">
        <button type="submit" class="btn btn-danger mx-3">logout</button>
    </form>
</nav>
<form>
    <div class="m-5 d-flex flex-row " id="anyadir-ej">
        <select>
            <option value="1">Lunes</option>
            <option value="2">Martes</option>
            <option value="3">Miércoles</option>
            <option value="4">Jueves</option>
            <option value="5">Viernes</option>
            <option value="6">Sábado</option>
            <option value="7">Domingo</option>
        </select>
        <input type="number" min="1" placeholder="Repeticiones"/>
        <input type="number" min="1" placeholder="Series"/>
        <input type="number" min="1" placeholder="Peso"/>
        <select>
            <option>Ejercicio1</option>
            <option>Ejercicio2</option>

        </select>
        <input type="text" placeholder="Nombre"/>

        <button type="submit" class="btn btn-primary">Añadir</button>
    </div>
</form>

<table class="table m-5">
    <thead>
    <tr>
        <th scope="col">Lunes</th>
        <th scope="col">Martes</th>
        <th scope="col">Miercoles</th>
        <th scope="col">Jueves</th>
        <th scope="col">Viernes</th>
        <th scope="col">Sábado</th>
        <th scope="col">Domingo</th>

    </tr>
    </thead>
    <tbody>
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>hola</td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td>ghola</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>
