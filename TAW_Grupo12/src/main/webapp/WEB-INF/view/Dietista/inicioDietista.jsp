<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.taw_grupo12.dto.TrabajadorDTO" %>
<%@ page import="es.uma.taw_grupo12.dto.DietaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.ui.Dietista.FiltroDietas" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    TrabajadorDTO trabajador = (TrabajadorDTO) request.getAttribute("trabajador");
    List<DietaDTO> dietas = (List<DietaDTO>) request.getAttribute("dietas");
%>

<%--Created by IntelliJ IDEA.
User: Chen Chen Longxiang
--%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <h1>INICIO DIETISTA</h1>
        <h2>Bienvenidos estimado: <%=trabajador.getNombre()%></h2>
    </head>
    <body>
        <a href="/dietista/crearDieta/">Crear Dieta</a>
        <br><br>
    <%
        if(!dietas.isEmpty()){
    %>
        <form:form action="/dietista/filtrar" modelAttribute="filtro" method="post">
            <div class="form-group">
                <label for="busqueda">Nombre a buscar: </label>
                <form:input path="busqueda" id="busqueda" requiered="true" placeholder="Nombre de la dieta"/>
            </div>
            <button type="submit">Filtrar</button>
        </form:form>
        <br><br>
        <table border="1">
            <thead>
            <tr>
                <th scope="col">Nombre Dieta</th>
                <th scope="col">Detalles Dieta</th>
                <th scope="col">Seguimiento</th>
                <th scope="col">Eliminar Dieta</th>
            </tr>
            </thead>
            <tbody>
            <%
                for(DietaDTO dieta: dietas){
            %>
            <tr>
                <th scope="col"><%=dieta.getNombre()%></th>
                <th scope="col"><a href="/dietista/verDieta/<%=dieta.getIdDieta()%>">Ver detalles</a></th>
                <th scope="col"><a href="/dietista/verSeguimiento/<%=dieta.getIdCliente()%>/<%=dieta.getIdDieta()%>">Ver seguimientos</a></th>
                <th scope="col"><a href="/dietista/eliminar/<%=dieta.getIdDieta()%>">Eliminar</a></th>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <h1>NO TIENES NINGUNA DIETA CREADA</h1>
    <%
        }
    %>
    </body>
<br/>
    <button>
        <a href="/cerrarSesion">Logout</a>
    </button>

</html>