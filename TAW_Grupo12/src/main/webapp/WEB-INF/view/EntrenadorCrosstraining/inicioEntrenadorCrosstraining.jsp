<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %><%--
  Created by IntelliJ IDEA.
  User: Guillermo
  Date: 18/06/2024
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%
    TrabajadorDTO trabajador = (TrabajadorDTO) request.getAttribute("trabajador");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">
<head>
    <title>Mi Perfil</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .container {
            margin-top: 50px;
        }

        .table th, .table td {
            text-align: center; /* Center text in thead and tbody */
        }

        .contenedor {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 20px;
        }
    </style>
</head>

<body>
<jsp:include page="cabeceraEntrenadorCrosstraining.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col">
            <h1 class="text-center">Mi Perfil</h1>
        </div>
    </div>
    <div class="container mt-5 col">
        <div class="contenedor">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4>Perfil de entrenador cross-training</h4>
                    </div>
                    <div class="card-body">
                        <div class="mb-3">
                            <label class="form-label"><strong>Nombre:</strong></label>
                            <p class="form-control-plaintext"><%=trabajador.getNombre()%>
                            </p>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><strong>Email:</strong></label>
                            <p class="form-control-plaintext"><%=trabajador.getEmail()%>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous">

        </script>
    </div>
</div>

</body>
</html>