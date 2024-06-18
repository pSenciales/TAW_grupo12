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
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    List<RutinaDTO> rutinas = (List<RutinaDTO>) request.getAttribute("rutinas");
    List<EjercicioRutinaDTO> ejerciciosRutina = (List<EjercicioRutinaDTO>) request.getAttribute("ejerciciosRutina");
    List<EjercicioDTO> ejercicioDTOList = (List<EjercicioDTO>) request.getAttribute("ejercicioList");
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");
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
<jsp:include page="cabeceraEntrenador.jsp"></jsp:include>

<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h4>Perfil del Cliente</h4>
                </div>
                <div class="card-body">
                    <div class="mb-3">
                        <label class="form-label"><strong>Nombre:</strong></label>
                        <p class="form-control-plaintext"><%=cliente.getNombre()%></p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>Email:</strong></label>
                        <p class="form-control-plaintext"><%=cliente.getEmail()%></p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>Peso:</strong></label>
                        <p class="form-control-plaintext"><%=cliente.getPeso()%> kg</p>
                    </div>
                    <div class="mb-3">
                        <label class="form-label"><strong>Altura:</strong></label>
                        <p class="form-control-plaintext"><%=cliente.getAltura()%> cm</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div>
    <%
        if(rutinas.size()>0){

    %>
    <form method="post" action="/entrenador/cliente/visualizar">

        <input type="hidden" name="id" value="<%=cliente.getIdcliente()%>"/>
        <select name="idrutina">
            <%
                for(RutinaDTO r : rutinas){
                String selected = "";
                if(Objects.equals(r.getIdrutina(), rutina.getIdrutina()))
                    selected = "selected";
            %>
            <option value="<%=r.getIdrutina()%>" <%=selected%>><%=r.getNombre()%></option>
            <%
                }
            %>
        </select>
        <button class="btn btn-primary">Buscar</button>
    </form>

    <table class="table table-bordered table-striped">
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
        <%
            int size = !ejerciciosRutina.isEmpty() ? ejerciciosRutina.getLast().getOrden()+1 : 0;
            for(int i = 0; i<size; i++){


        %>
        <tr>
            <%
                int index = 0;
                String[] ejerciciorutina = new String[]{"","","","","","",""};
                Integer[] ids = new Integer[]{-1,-1,-1,-1,-1,-1,-1,};

                while(index < ejerciciosRutina.size() && ejerciciosRutina.get(index).getOrden() <= i){
                    if(ejerciciosRutina.get(index).getOrden() == i) {
                        boolean encontrada = false;
                        int ej = 0;
                        EjercicioDTO aux = null;
                        while(!encontrada && ej < ejercicioDTOList.size()){
                            aux = ejercicioDTOList.get(ej);
                            if(Objects.equals(aux.getIdejercicio(), ejerciciosRutina.get(index).getEjercicio()))
                                encontrada = true;
                            ej++;
                        }
                        EjercicioRutinaDTO ejercicio = ejerciciosRutina.get(index);
                        ids[ejercicio.getDiassemanaInt() - 1] = ejercicio.getEjercicioRutinaPK();
                        assert aux != null;
                        ejerciciorutina[ejercicio.getDiassemanaInt() - 1] = aux.getNombre()+" "+ejercicio.toString();
                    }
                    index++;
                }
            %>
            <%
                for(int j = 0; j<7; j++){
            %>
            <td>
                <%=ejerciciorutina[j]%>
            </td>
            <%
                }
            %>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <%
        }else{


    %>
    <h5>SIN RUTINAS ASIGNADAS</h5>
    <%
        }
    %>


</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>
