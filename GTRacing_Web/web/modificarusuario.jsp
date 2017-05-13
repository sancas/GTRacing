<%@page import="com.gtracing.users.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getParameter("user_name") != null && !request.getParameter("user_name").equals("")) {
        int bandera;
        Usuarios misUsuarios = new Usuarios();
        bandera = misUsuarios.modificarUsuario(request.getParameter("user_name"), request.getParameter("first_name"), request.getParameter("middle_name"),
                request.getParameter("last_name"), request.getParameter("sur_name"), request.getParameter("password"));
        if (bandera == 1) {
%>
<div class="alert alert-success alert-dismissible col-md-offset-3 col-md-6" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Exito!</strong> Datos actualizados correctamente.
</div>
<%
} else {
%>
<div class="alert alert-danger alert-dismissible fade in col-md-offset-3 col-md-6" role="alert"> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
    <strong>Error!</strong> No se pudo actualizar la información.
</div>
<%
    }
} else {
%>
<div class="alert alert-danger alert-dismissible col-md-offset-3 col-md-6" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Error!</strong> El usuario no puede estar vacio.
</div>
<% }%>