<%-- 
    Document   : registro_cuenta
    Created on : 05-12-2017, 02:25:59 PM
    Author     : Usuario
--%>

<%@page import="com.gtracing.registros.RegUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            
            if(mySession.getAttribute("currentUser") != null){
                
        %>
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
                        <li><a href="logout.jsp">Logout</a></li>
                        <li class="active"><a href="micuenta.jsp">Mi Cuenta</a><span class="sr-only">(current)</span></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

        <div class="container">
            <div class="jumbotron">
                <h3>Paso 2: Reguistro de Cuenta</h3>

                <form>
                    <div class="form-group">
                        <label for="CreditCard">Numero de Tarjeta</label>
                        <input type="text" name="cardNum" class="form-control" id="CreditCard" placeholder="0000 00000 0000 0000 0000" minlength="12" maxlength="12" required>
                    </div>
                    <div class="form-group">
                        <label for="SecureCode">Codigo de Seguridad</label>
                        <input type="text" name="secureCode" class="form-control" id="SecureCode" placeholder="000" minlength="3" maxlength="3" required>
                    </div>
                    <div class="form-group">
                        <label for="CantidadMoney">Credito para compras en este portal</label>
                        <input type="text" name="cantidad" class="form-control" id="CantidadMoney" placeholder="0000.00" required>
                    </div>

                    <button type="submit" name="btnFinalizar" class="btn btn-success">Finalizar</button>
                </form>
                <%
                    if(request.getParameter("btnFinalizar") != null){
                        RegUser reg = new RegUser();
                        int numTarjeta = Integer.parseInt(request.getParameter("carNum")), secureCod = Integer.parseInt(request.getParameter("secureCode"));
                        double cantidad = Double.parseDouble(request.getParameter("cantidad"));
                        
                        
                        if(cantidad > 0){
                            int id = Integer.parseInt(mySession.getAttribute("idUser").toString());
                            int i = reg.ingresoCuenta(id, numTarjeta, secureCod, cantidad);
                            
                            if(i > 0){
                                response.sendRedirect("index.jsp");
                            } else{ %>
                            <p> Error al ingresar la cuenta</p>
                            <%}
                        }%>
                        <p>La cantidad debe de ser mayor a 0</p>
                    <%}
                %>
            </div>
        </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <% } //fin de la verificacion de sesion %>
</body>
</html>