<%-- 
    Document   : cuentas
    Created on : 12-may-2017, 21:53:00
    Author     : Leonel
--%>
<%@page import="java.util.*"%>
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

        <!-- Codigo CSS -->
        <style>
            #leftPanel{
                background-color:#0079ac;
                color:#fff;
                text-align: center;
            }

            #rightPanel{
                min-height:415px;
            }

            /* Credit to bootsnipp.com for the css for the color graph */
            .colorgraph {
                height: 5px;
                border-top: 0;
                background: #c4e17f;
                border-radius: 5px;
                background-image: -webkit-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
                background-image: -moz-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
                background-image: -o-linear-gradient(left, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
                background-image: linear-gradient(to right, #c4e17f, #c4e17f 12.5%, #f7fdca 12.5%, #f7fdca 25%, #fecf71 25%, #fecf71 37.5%, #f0776c 37.5%, #f0776c 50%, #db9dbe 50%, #db9dbe 62.5%, #c49cde 62.5%, #c49cde 75%, #669ae1 75%, #669ae1 87.5%, #62c2e4 87.5%, #62c2e4);
            }
        </style>
    </head>
    <body>
        <%
            HttpSession mySession = request.getSession();

            if (mySession.getAttribute("currentUser") != null) {
                InicioSesion login = new InicioSesion();
                String user = mySession.getAttribute("currentUser").toString();
                ResultSet rs = login.userData(user);
                String usuario = "", first_name = "", middle_name = "", last_name = "", sur_name = "";
                String descripcion = "";
                String nameOfDay[]
                        = {
                            "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"
                        };
                String nameOfMonth[]
                        = {
                            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
                        };
                if (rs.next()) {
                    first_name = rs.getString(2);
                    middle_name = rs.getString(3);
                    last_name = rs.getString(4);
                    sur_name = rs.getString(5);
                    descripcion = first_name + " " + middle_name
                            + " " + last_name + " " + sur_name;
                    descripcion += ", nacio un hermoso " + nameOfDay[rs.getDate(8).getDay()];
                    descripcion += " en el mes de " + nameOfMonth[rs.getDate(8).getMonth()];
                    descripcion += " y para el " + rs.getDate(8).getYear() + " ya estaba presente "
                            + "en este mundo.";
                    usuario = rs.getString(6);
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
            <br>
            <br>
            <div class="row" id="main">
                <div id="output">
                </div>
                <div class="col-md-4 well" id="leftPanel">
                    <div class="row">
                        <div class="col-md-12">
                            <div>
                                <img src="img/usuario.png" alt="Usuario" class="img-circle img-thumbnail">
                                <h2><%=usuario%></h2>
                                <p><%=descripcion%></p>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-warning">
                                        Social</button>
                                    <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span><span class="sr-only">Social</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">Twitter</a></li>
                                        <li><a href="#">Google +</a></li>
                                        <li><a href="#">Facebook</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#">Github</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-8 well" id="rightPanel">
                    <div class="row">
                        <div class="col-md-12">
                            <form role="form" id="miform" action="#" method="POST">
                                <h2>Edita tu perfil.<small>Siempre es facil.</small></h2>
                                <hr class="colorgraph">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="text" name="first_name" id="first_name" class="form-control input-lg" placeholder="Primer Nombre" tabindex="1">
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="text" name="middle_name" id="middle_name" class="form-control input-lg" placeholder="Segundo Nombre" tabindex="2">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="text" name="last_name" id="last_name" class="form-control input-lg" placeholder="Primer Apellido" tabindex="3">
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="text" name="sur_name" id="sur_name" class="form-control input-lg" placeholder="Segundo Apellido" tabindex="4">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="user_name" id="username" class="form-control input-lg" placeholder="Ususario" tabindex="5" required>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" tabindex="6">
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-lg" placeholder="Confirm Password" tabindex="7">
                                        </div>
                                    </div>
                                </div>
                                <hr class="colorgraph">
                                <div class="row">
                                    <div class="col-xs-12 col-md-6"></div>
                                    <div class="col-xs-12 col-md-6"><input type="submit" class="btn btn-success btn-block btn-lg"></input></div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>       
    </div>
    <!-- Fin del contenido -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#first_name").val("<%=first_name%>");
            $("#middle_name").val("<%=middle_name%>");
            $("#last_name").val("<%=last_name%>");
            $("#sur_name").val("<%=sur_name%>");
            $("#username").val("<%=usuario%>");

            $("#miform").submit(function (e) {
                e.preventDefault();
                $.ajax({
                    type: 'POST',
                    url: "modificarusuario.jsp",
                    data: $("#miform").serialize(),
                    success: function (msg) {
                        $('<div id="output"></div>').replaceAll('#output');
                        $('#output').append(msg);
                        $('div.alert').hide();
                        $('div.alert').alert();
                        $('div.alert').fadeTo(5000, 500).slideUp(500, function () {
                            $('div.alert').slideUp(500);
                        });
                    },
                    error: function () {
                        alert(data);
                    }
                });
            });
        });
    </script>
    <% } else {
            response.sendRedirect("error_logeo.jsp");
        }
    %>
</body>
</html>