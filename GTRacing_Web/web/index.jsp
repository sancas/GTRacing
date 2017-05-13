<%-- 
    Document   : index
    Created on : 05-12-2017, 01:14:23 PM
    Author     : Usuario
--%>

<%@page import="com.gtracing.autos.Autos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.gtracing.repuesto.Repuestos"%>
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
        <link href="https://fonts.googleapis.com/css?family=Pacifico&subset=latin-ext,vietnamese" rel="stylesheet">
        <!-- Website Font style -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Quicksand:300,400,500,700&subset=latin-ext,vietnamese" rel="stylesheet">
        <style type="text/css">
            @import url('https://fonts.googleapis.com/css?family=Quicksand:300,400,500,700&subset=latin-ext,vietnamese');   
            body{font-family: 'Quicksand', sans-serif;}
            h4{
                font-weight: 600;
            }
            p{
                font-size: 12px;
                margin-top: 5px;
            }
            .price{
                font-size: 30px;
                margin: 0 auto;
                color: #333;
            }

            .thumbnail{
                opacity:0.70;
                -webkit-transition: all 0.5s; 
                transition: all 0.5s;
            }

            .thumbnail>img {
                height: 240px;
                width: 240px;
            }

            .thumbnail:hover{
                opacity:1.00;
                box-shadow: 0px 0px 10px #4bc6ff;
            }
            .line{
                margin-bottom: 5px;
            }
            @media screen and (max-width: 770px) {
                .right{
                    float:left;
                    width: 100%;
                }
            }
            span.thumbnail {
                border: 1px solid #00c4ff !important;
                border-radius: 0px !important;
                -webkit-box-shadow: 0px 0px 14px 0px rgba(0,0,0,0.16);
                -moz-box-shadow: 0px 0px 14px 0px rgba(0,0,0,0.16);
                box-shadow: 0px 0px 14px 0px rgba(0,0,0,0.16);
                padding: 10px;
            }
            .container h4{margin-top:70px; margin-bottom:30px;}
            button {    margin-top: 6px;
            }
            .right {
                float: right;
                border-bottom: 2px solid #0a5971;
            }
            .btn-info {
                color: #fff;
                background-color: #19b4e2;
                border-color: #19b4e2;
                font-size:13px;
                font-weight:600;
            }
        </style>
    </head>
    <body>
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
                        <li><a href="#">Nosotros<span class="sr-only">(current)</span></a></li>
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
                        <%
                            HttpSession mySession = request.getSession();
                            if (mySession.getAttribute("currentUser") != null) {
                        %>
                        <li><a href="logout.jsp">Logout</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Mi Cuenta <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="cuentas.jsp">Mi Usuario</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="compras.jsp">Mis Compras</a></li>
                            </ul>
                        </li>
                        <%
                        } else {
                        %>
                        <li><a href="login.jsp">Login</a></li>
                        <li><a href="registro.jsp">Registrate</a></li>
                            <%
                                }
                            %>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <!-- Fin del menu -->
        
        <div id="output"></div>
        <!-- Contenido de la pagina -->
        <div class="container">
            <h3>Nuestros mejores Productos</h3>
            <div class="row">
                <%
                    Repuestos myRep = new Repuestos();
                    Autos myCar = new Autos();
                    ResultSet rs = myRep.getRepuestosIndex();
                    String nombreProducto = "", Descripcion = "";
                    double Precio;
                    int id = 0;

                    while (rs.next()) {
                        id = rs.getInt(1);
                        nombreProducto = rs.getString(2);
                        Descripcion = rs.getString(3);
                        if (Descripcion.length() > 25) {
                            Descripcion = Descripcion.substring(0, 25) + "...";
                        }
                        Precio = rs.getDouble(4);
                %>
                <div class="col-md-3 col-sm-6">
                    <span class="thumbnail">
                        <img src="<%=request.getContextPath()%>/MostrarFoto?idfoto=<%=id%>" alt="<%=nombreProducto%>">
                        <h4><%=nombreProducto%></h4>
                        <div class="ratings">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                        </div>
                        <p><%=Descripcion%>.</p>
                        <hr class="line">
                        <div class="row">
                            <div class="col-md-6 col-sm-6">
                                <p class="price">$<%=Precio%></p>
                            </div>
                            <div class="col-md-6 col-sm-6">
                                <a href="#" onclick="repuestos(<%=id%>,<%=Precio%>);"><button class="btn btn-info right">RESERVAR</button></a>
                            </div>

                        </div>
                    </span>
                </div>
                <% }
                    rs.close();
                    rs = myCar.getAutosIndex();

                    while (rs.next()) {
                        id = rs.getInt(1);
                        nombreProducto = rs.getString(2);
                        Descripcion = rs.getString(3);
                        Descripcion += " " + rs.getString(4);
                        Precio = rs.getDouble(5);
                %>
                <div class="col-md-3 col-sm-6">
                    <span class="thumbnail">
                        <img src="<%=request.getContextPath()%>/MostrarFotoAuto?idfoto=<%=id%>" alt="<%=nombreProducto%>">
                        <h4><%=nombreProducto%></h4>
                        <div class="ratings">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star-empty"></span>
                        </div>
                        <p><%=Descripcion%>.</p>
                        <hr class="line">
                        <div class="row">
                            <div class="col-md-6 col-sm-6">
                                <p class="price">$<%=Precio%></p>
                            </div>
                            <div class="col-md-6 col-sm-6">
                                <input type="hidden" name="idreserva" value="<%=id%>" />
                                <a href="#" onclick="autos(<%=id%>,<%=Precio%>);"><button class="btn btn-info right" >RESERVAR</button></a>
                            </div>

                        </div>
                    </span>
                </div>
                <% }%>
            </div><!-- Fin div.row -->
        </div><!-- Fin div.container -->
        <!-- Fin del contenido -->

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript">
            function repuestos($idrepuesto, $precio)
            {
                if (confirm("Esta seguro que desea continuar?"))
                {
                    $.ajax({
                        type: "POST",
                        url: "reservar.jsp",
                        data: { "idrepuesto" : $idrepuesto,
                                "precio" : $precio
                            },
                        success: function (msg) {
                            $('<div id="output"></div>').replaceAll('#output');
                            $('#output').append(msg);
                            $('div.alert').hide();
                            $('div.alert').alert();
                            $('div.alert').fadeTo(5000, 500);
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown) {
                            alert("Surgio un error inesperado");
                        }
                    });
                }
            }
            function autos($idauto, $precio)
            {
                if (confirm("Esta seguro que desea continuar?"))
                {
                    $.ajax({
                        type: "POST",
                        url: "reservar.jsp",
                        data: { "idauto" : $idauto,
                                "precio" : $precio
                            },
                        success: function (msg) {
                            $('<div id="output"></div>').replaceAll('#output');
                            $('#output').append(msg);
                            $('div.alert').hide();
                            $('div.alert').alert();
                            $('div.alert').fadeTo(5000, 500);
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown) {
                            alert("Surgio un error inesperado.");
                        }
                    });
                }
            }
        </script>
    </body>
</html>
