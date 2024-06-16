<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="option" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.dto.RutinaDTO" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<%@ page import="es.uma.taw_grupo12.dto.EjercicioRutinaDTO" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 16/05/2024
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>

<%
    List<EjercicioDTO> ejercicioDTOList = (List<EjercicioDTO>) request.getAttribute("ejercicioList");
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");
    List<EjercicioRutinaDTO> ejerciciosRutina = (List<EjercicioRutinaDTO>) request.getAttribute("ejerciciosRutina");

%>
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

        .table th, .table td {
            text-align: center; /* Center text in thead and tbody */
            font-size: 0.85rem; /* Make tbody text smaller */
            width: 14.28%; /* Ensure equal width for all columns */
        }

        .table thead th {
            font-size: 1rem; /* Keep thead text size default */
        }
        .table-container {
            margin: 0 auto;
            width: 90%;
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
<h1>Rutina: <%=rutina.getNombre()%></h1>

<form:form method="post" action="/entrenador/addEjercicio" modelAttribute="ejercicioRutinaDTO">
    <div class="m-5 d-flex flex-row " id="anyadir-ej">
        <form:hidden path="rutina"></form:hidden>
        <form:select required="required" path="diassemana" >
            <form:option value="1" label="Lunes"></form:option>
            <form:option value="2" label="Martes"></form:option>
            <form:option value="3" label="Miercoles"></form:option>
            <form:option value="4" label="Jueves"></form:option>
            <form:option value="5" label="Viernes"></form:option>
            <form:option value="6" label="Sábado"></form:option>
            <form:option value="7" label="Domingo"></form:option>
        </form:select>
        <form:input required="required" type="number" min="1" placeholder="Repeticiones"  path="repeticiones"/>
        <form:input required="required" type="number" min="1" placeholder="Series"  path="series"/>
        <form:input required="required" type="number" min="1" placeholder="Peso"  path="peso"/>
        <form:select required="required" path="ejercicio">
            <form:options items="${ejercicioList}" itemLabel="nombre" itemValue="idejercicio"></form:options>
        </form:select>
        <form:button class="btn btn-primary">Añadir ejercicio</form:button>
    </div>
</form:form>
<div class="table-container">
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
        <td>
            <%=ejerciciorutina[0]%>
            <%
                if(ejerciciorutina[0] != ""){
            %>
            <form method="post" action="/entrenador/rutina/subir">
                <input type="hidden" value="<%=ids[0]%>" name="id">
                <button type="submit">Subir</button>
            </form>
            <form method="post" action="/entrenador/rutina/bajar">
                <input type="hidden" value="<%=ids[0]%>" name="id">
                <button type="submit">Bajar</button>
            </form>
            <form method="get" action="/entrenador/rutina/borrar">
                <input type="hidden" value="<%=ids[0]%>" name="id">
                <button type="submit">Borrar</button>
            </form>
            <form method="post" action="/entrenador/rutina/editar">
                <input type="hidden" value="<%=ids[0]%>" name="id">
                <button type="submit">Editar</button>
            </form>
            <%
                }
            %>
        </td>
        <td>
            <%=ejerciciorutina[1]%>
            <%
                if(ejerciciorutina[1] != ""){
            %>
            <form method="post" action="/entrenador/rutina/subir">
                <input type="hidden" value="<%=ids[1]%>" name="id">
                <button type="submit">Subir</button>
            </form>
            <form method="post" action="/entrenador/rutina/bajar">
                <input type="hidden" value="<%=ids[1]%>" name="id">
                <button type="submit">Bajar</button>
            </form>
            <form method="post" action="/entrenador/rutina/borrar">
                <input type="hidden" value="<%=ids[1]%>" name="id">
                <button type="submit">Borrar</button>
            </form>
            <form method="post" action="/entrenador/rutina/editar">
                <input type="hidden" value="<%=ids[1]%>" name="id">
                <button>Editar</button>
            </form>
            <%
                }
            %>
        </td>
        <td>
            <%=ejerciciorutina[2]%>
            <%
                if(ejerciciorutina[2] != ""){
            %>
            <form>
                <input type="hidden" value="<%=ids[2]%>" name="id">
                <button>Subir</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[2]%>" name="id">
                <button>Bajar</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[2]%>" name="id">
                <button>Borrar</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[2]%>" name="id">
                <button>Editar</button>
            </form>
            <%
                }
            %>
        </td>
        <td>
            <%=ejerciciorutina[3]%>
            <%
                if(ejerciciorutina[3] != ""){
            %>
            <form>
                <input type="hidden" value="<%=ids[3]%>" name="id">
                <button>Subir</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[3]%>" name="id">
                <button>Bajar</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[3]%>" name="id">
                <button>Borrar</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[3]%>" name="id">
                <button>Editar</button>
            </form>
            <%
                }
            %>
        </td>
        <td>
            <%=ejerciciorutina[4]%>
            <%
                if(ejerciciorutina[4] != ""){
            %>
            <form>
                <input type="hidden" value="<%=ids[4]%>" name="id">
                <button>Subir</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[4]%>" name="id">
                <button>Bajar</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[4]%>" name="id">
                <button>Borrar</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[4]%>" name="id">
                <button>Editar</button>
            </form>
            <%
                }
            %>
        </td>
        <td>
            <%=ejerciciorutina[5]%>
            <%
                if(ejerciciorutina[5] != ""){
            %>
            <form>
                <input type="hidden" value="<%=ids[5]%>" name="id">
                <button>Subir</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[5]%>" name="id">
                <button>Bajar</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[5]%>" name="id">
                <button>Borrar</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[5]%>" name="id">
                <button>Editar</button>
            </form>
            <%
                }
            %>
        </td>
        <td>
            <%=ejerciciorutina[6]%>
            <%
                if(ejerciciorutina[6] != ""){
            %>
            <form>
                <input type="hidden" value="<%=ids[6]%>" name="id">
                <button>Subir</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[6]%>" name="id">
                <button>Bajar</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[6]%>" name="id">
                <button>Borrar</button>
            </form>
            <form>
                <input type="hidden" value="<%=ids[6]%>" name="id">
                <button>Editar</button>
            </form>
            <%
                }
            %>
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
