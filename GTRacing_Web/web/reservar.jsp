<%@page import="java.sql.ResultSet"%>
<%@page import="com.gtracing.loggin.InicioSesion"%>
<%@page import="com.gtracing.reserva.Reserva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getParameter("precio") != null && !request.getParameter("precio").equals("")) {
        int bandera;
        int idrepuesto = 0;
        int idauto = 0;
        int idusuario = 0;
        Reserva miReserva = new Reserva();
        HttpSession mySession = request.getSession();
        if (mySession.getAttribute("currentUser") != null) {
            InicioSesion login = new InicioSesion();
            String user = mySession.getAttribute("currentUser").toString();
            ResultSet rs = login.userData(user);
            if (rs.next())
                idusuario = rs.getInt("idUser");
        } else
            idusuario = 1;
        if (request.getParameter("idrepuesto") != null)
            idrepuesto = Integer.parseInt(request.getParameter("idrepuesto"));
        if (request.getParameter("idauto") != null)
            idauto = Integer.parseInt(request.getParameter("idauto"));
        Double precio = Double.parseDouble(request.getParameter("precio")); 
        String idReserva = miReserva.getCadenaAlfanumAleatoria(9);
        bandera = miReserva.reservar(idReserva, idusuario, precio);
        if (bandera > 0)
        {
            bandera = miReserva.detalleReserva(idReserva, idrepuesto, idauto, precio);
            if (bandera > 0)
            {
%>
<div class="alert alert-success alert-dismissible col-md-offset-3 col-md-6" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Exito!</strong> Su ID de reserva es: <%= idReserva %>.
</div>
<%
} else {
%>
<div class="alert alert-danger alert-dismissible col-md-offset-3 col-md-6" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Error!</strong> No se pudo detallar la reserva.
</div>
<% }} else { %>
<div class="alert alert-danger alert-dismissible col-md-offset-3 col-md-6" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Error!</strong> No se pudo reservar.
</div>
<% } } %>