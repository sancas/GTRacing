<%-- 
    Document   : cuentas
    Created on : 12-may-2017, 21:53:00
    Author     : Leonel
--%>

<%@page import="com.gtracing.loggin.InicioSesion"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>GT Racing</title>

        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>
        <%
            HttpSession mySession = request.getSession();

            if (mySession.getAttribute("currentUser") != null) {
                InicioSesion login = new InicioSesion();
                String user = mySession.getAttribute("currentUser").toString();
                ResultSet rs = login.userData(user);
                String nombre = "";
                
                if(rs.next()){
                    nombre = rs.getString(2);
                    nombre += " " + rs.getString(4);
                }
        %>
        <!-- Menu de navegacion -->
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.jsp">GT Racing</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="#">Nosotros</a></li>
                        <li><a href="#">Ubicacion</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Productos <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Automoviles</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Repuestos</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="logout.jsp">Logout</a></li>
                        <li class="dropdown active">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Mi Cuenta <span class="caret"></span></a><span class="sr-only">(current)</span>
                            <ul class="dropdown-menu">
                                <li class="active"><a href="cuentas.jsp">Mi Usuario</a><span class="sr-only">(current)</span></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="compras.jsp">Mis Compras</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <!-- Fin del menu -->

        <!-- Contenido de la pagina -->
        <div class="container">
            <div class="jumbotron">
                <img src="img/usuario.png" alt="Usuario" class="img-circle img-responsive">
                <h3>Bienvenido <%= nombre %></h3>
            </div>
        </div>
        <!-- Fin del contenido -->

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <% } else {
                response.sendRedirect("error_logeo.jsp");
            }
        %>
    </body>
</html>