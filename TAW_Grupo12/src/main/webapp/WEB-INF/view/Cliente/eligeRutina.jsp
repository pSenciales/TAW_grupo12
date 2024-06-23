<%
    /**
     * @author Ignacio Morillas Rosell
     */
%>

<%@ page import="java.util.List" %>
<%@ page import="es.uma.taw_grupo12.entity.Rutina" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        List<Rutina> rutinaList = (List<Rutina>) request.getAttribute("rutinas");
    %>
</head>
<body>
    <form action="/cliente/rutina" method="post">
        Elige rutina
        <select name="id">
            <%
                for(Rutina r : rutinaList) {
            %>
            <option value="<%=r.getIdrutina()%>"><%=r.getNombre()%></option>
            <%
                }
            %>
        </select>
        <br/>
        <input type="submit" value="Seleccionar">
    </form>
</body>
</html>
