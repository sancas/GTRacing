<%-- 
    Document   : login
    Created on : 05-12-2017, 01:15:57 PM
    Author     : Leonel Rivas
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="com.gtracing.loggin.InicioSesion"%>
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

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
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
                        <button type="submit" class="btn btn-default">Buscar</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="#">Loging</a><span class="sr-only">(current)</span></li>
                        <li><a href="registro.jsp">Registrate</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

        <div class="container">
            <div class="jumbotron">
                <form name="ingreso" action="login.jsp" method="post">
                    <div class="form-group">
                        <label for="Usuario">Usuario</label>
                        <input type="text" name="usuario" class="form-control" id="Usuario" placeholder="juan97">
                    </div>
                    <div class="form-group">
                        <label for="Passwprd1">Contrase&ntilde;a</label>
                        <input type="password" name="passwd" class="form-control" id="Password1" placeholder="Password">
                    </div>
                    
                    <button type="submit" name="Ingresar" class="btn btn-primary">Ingresar</button>
                </form>
            </div>
        </div>
    </div>
    
    <%
        InicioSesion log = new InicioSesion();
        
        if(request.getParameter("Ingresar") != null){
            String username = request.getParameter("usuario");
            String myPasswd = request.getParameter("passwd");
            
            int isLogeable = log.Login(username, myPasswd);
            
            if(isLogeable > 0){
                HttpSession mySession = request.getSession();
                mySession.setAttribute("currentUser", username);
                ResultSet rs = log.userData(username);
                if(rs.next()){
                    mySession.setAttribute("idUser", rs.getInt(1));
                }
                response.sendRedirect("index.jsp");
            } else{
                response.sendRedirect("login_error.jsp");
            }
        }
    %>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>