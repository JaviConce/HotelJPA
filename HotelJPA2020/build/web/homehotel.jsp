     <%-- 
    Document   : home
    Author     : pacopulido
--%>

<%@page import="java.util.List"%>
<%@page import="entities.Habitacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <!-- Bootstrap CSS -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/mycss.css">
    <link rel="stylesheet" href="css/jquery-ui.css">
  <title>Web Hotel Azarquiel</title>
</head>

<body>
<%
    String ficha = (String)session.getAttribute("ficha");
    Habitacion habitacion;
%>
  <!-- NavBar-->
  <div class="container shadow bg-primary">
    <nav class="navbar navbar-expand-md navbar-light">
      <a class="navbar-brand bgazulc textazulo px-2" href="#"><i class="fa fa-home textgranate"></i> Hotel Azarquiel <i class="fa fa-home textgranate"></i></a>
      <button class="navbar-toggler custom-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link text-white <%=(ficha.equals("entrada")?"active":"")%>" href="Controller?op=entradaoreserva&ficha=entrada">Entrada</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white <%=(ficha.equals("reserva")?"active":"")%>" href="Controller?op=entradaoreserva&ficha=reserva">Habitaciones</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
    <!-- ENTRADAS -->
    <%
    if (ficha.equals("entrada")) {
    %>
  <div class="container shadow">
    <div class="row">
      <div class="col-md-6 offset-md-3 shadow my-3 py-3">
        <h5 class="bg-primary text-white text-center">ENTRADAS AL HOTEL</h5>
        <form action="Controller?op=hospedar" method="POST" name="fentrada">
          <div class="form-group">
            <label for="dni">Dni</label>
            <input name="dni" placeholder="dni" id="dni" type="text" class="form-control" required>
          </div>
          <div class="form-group">
            <label for="nhabitacion">Habitación</label>
            <%
              List lhl=(List) session.getAttribute("lhl");
            %>  
            <select class="form-control" name="nhabitacion" id="nhabitacion" required>
              <option value="" disabled selected>Elija Habitación</option>
              <%
                for (int i=0;i<lhl.size();i++){
                    habitacion=(Habitacion) lhl.get(i);
                    %>
                    <option value="<%=habitacion.getNhabitacion()%>"><%=habitacion.getNhabitacion()%></option>
              <%}%>
            </select>
          </div>
          <div class="form-group">
            <label for="fecha">Fecha Salida</label>
            <input name="fecha" placeholder="Fecha Salida" id="fecha" type="text" class="form-control datepicker" required>
          </div>
          <button type="submit" class="btn btn-primary">Hospedar</button>
        </form>
      </div>
    </div>
  </div>
    <%}%>
    <!-- RESERVAS -->
    <%if (ficha.equals("reserva")) {  
        List lh = (List) session.getAttribute("lh");
    %>
  <div class="container shadow">
    <div class="row mt-2">
      <div class="col-md-8 offset-md-2 shadow table-responsive-sm py-3">
          <h5 class="bg-primary text-white text-center">LISTADO DE HABITACIONES PARA CONSULTA DE RESERVAS</h5>
          <table class="w-100 table table-striped">
              <thead>
                  <tr class="textazulo bgazulc">
                  <th>N&ordm; Habitacion</th>
                  <th>N&ordm; Personas</th>
                  <th>Precio</th>
                  <th>Ocupada(Si/No)</th>
                </tr>
              </thead>
              <tbody>
                <%
                    for (int i=0;i<lh.size();i++){
                        habitacion=(Habitacion) lh.get(i);
                        %>
                        <tr>
                            <td><button class="btn-primary showreservas" data-id="<%=habitacion.getNhabitacion()%>" data-toggle="modal" data-target="#modalreservas">Reservas de la <%=habitacion.getNhabitacion()%></button></td>
                            <td><%=habitacion.getNpersonas()%></td>
                            <td><%=habitacion.getPrecio()%></td>
                            <td><%=(habitacion.getOcupada()==Short.valueOf("1")?"Si":"No")%></td>
                        </tr>
                        <%
                    }
                %>           
              </tbody>
          </table>
        </div>
    </div>
  </div>
    <%}%>
    <!-- Modal-->
<div class="modal fade" id="modalreservas" tabindex="-1" role="dialog" aria-labelledby="modalreservas" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">RESERVAS</h5>
      </div>
      <div class="modal-body">
        <div class="row">
          <div id="reservas" class="col-sm-12">
               <!--se rellena con ajax-->
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Aceptar</button>
      </div>
    </div>
  </div>
</div>
<!-- The snackbar -->
<% String mensaje = (String)request.getAttribute("mensaje");
    if (mensaje!=null){
        %>
        <div id="snackbar"><%=mensaje%></div>
        <script type="text/javascript">
            toast();
        </script>
    <%}
%>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="js/myjs.js"></script>
  
</body>

</html>
