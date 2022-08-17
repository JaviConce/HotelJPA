<%@page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Reserva"%>
<%
    String nhabitacion = (String) request.getParameter("nhabitacion");
    List lr = (List) request.getAttribute("lr");
    if (lr.size()==0){%>
        <h5 class="bg-primary text-white text-center">Habitación <%=nhabitacion%> sin reservas...</h5>
    <%}
    else {%>
        <h5 class="bg-primary text-white text-center">Habitación: <%=nhabitacion%></h5>
        <table class="mx-auto table table-striped table-responsive" style="width: 80%;">
        <thead>
            <tr class="textazulo bgazulc">
            <th>N&ordm; Reserva</th>
            <th>N&ordm; Habitacion</th>
            <th>Fecha In</th>
            <th>Fecha Out</th>
            <th>DNI</th>
        </tr>
        </thead>
        <tbody>
        <%
        Reserva reserva=null;
        for (int i=0;i<lr.size();i++){
            reserva=(Reserva) lr.get(i); %>
            <tr>
                <td><%=reserva.getNreserva()%></td>
                <td><%=reserva.getNhabitacion().getNhabitacion() %></td>
                <td><%=reserva.getFechae()%></td>
                <td><%=reserva.getFechas()%></td>
                <td><%=reserva.getDni()%></td>
            </tr>
        <%}%>
        </tbody>
        </table>
    <%}
%>