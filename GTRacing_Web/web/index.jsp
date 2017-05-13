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

        <!-- Contenido de la pagina -->
        <div class="container">
            <div class="jumbotron">
                <h3>Nuestros mejores Productos</h3>
                <div class="row">
                    <%
                        Repuestos myRep = new Repuestos();
                        Autos myCar = new Autos();
                        ResultSet rs = myRep.getRepuestosIndex();
                        String nombreProducto = "", Descripcion = "";
                        int id = 0;

                        while (rs.next()) {
                            id = rs.getInt(1);
                            nombreProducto = rs.getString(2);
                            Descripcion = rs.getString(4);
                    %>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img src="<%=request.getContextPath()%>/MostrarFoto?idfoto=<%=id%>" alt="<%=nombreProducto%>" width="240" height="200">
                            <div class="caption">
                                <h3><%=nombreProducto%></h3>
                                <p><%=Descripcion%></p>
                                <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
                            </div>
                        </div>
                    </div>
                    <% }
                        rs.close();
                        rs = myCar.getAutosIndex();

                        while (rs.next()) {
                            id = rs.getInt(1);
                            nombreProducto = rs.getString(2);
                            Descripcion = rs.getString(4);
                            Descripcion += " " + rs.getString(5);
                    %>
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img src="<%=request.getContextPath()%>/MostrarFotoAuto?idfoto=<%=id%>" alt="<%=nombreProducto%>" width="240" height="200">
                            <div class="caption">
                                <h3><%=nombreProducto%></h3>
                                <p><%=Descripcion%></p>
                                <p><a href="#" class="btn btn-primary" role="button">Button</a> <a href="#" class="btn btn-default" role="button">Button</a></p>
                            </div>
                        </div>
                    </div>
                    <% }%>
                </div><!-- Fin div.row -->
            </div><!-- Fin div.jumbotron -->
        </div><!-- Fin div.container -->
        <!-- Fin del contenido -->

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
