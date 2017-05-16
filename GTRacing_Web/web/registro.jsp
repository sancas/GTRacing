<%-- 
    Document   : registro
    Created on : 05-12-2017, 02:10:46 PM
    Author     : Usuario
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="com.gtracing.loggin.InicioSesion"%>
<%@page import="com.gtracing.encryption.EncriptadorPassword"%>
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
            span[ng-click] {
                cursor: pointer;
            }
        </style>
    </head>
    <body ng-app="myApp">
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
                        <li><a href="index.jsp">Home</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Productos <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="autos.jsp">Automoviles</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="repuestos.jsp">Repuestos</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="cart.jsp"><ngcart-summary></ngcart-summary></a>
                        </li>
                        <%
                            HttpSession mySession = request.getSession();
                            if (mySession.getAttribute("currentUser") != null) {
                        %>
                        <li><a href="logout.jsp">Logout</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Mi Cuenta <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="cuentas.jsp">Mi Usuario</a></li>
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

        <div class="container">
            <div class="jumbotron">
                <h3>Paso 1: Reguistro de Usuarios</h3>

                <form name="regitro" method="post" action="registro.jsp">
                    <div class="form-group">
                        <label for="PrimerNombre">Primer Nombre</label>
                        <input type="text" name="pNombre" class="form-control" id="PrimerNombre" placeholder="Juan" required>
                    </div>
                    <div class="form-group">
                        <label for="SegundoNombre">Segundo Nombre</label>
                        <input type="text" name="sNombre" class="form-control" id="SegundoNombre" placeholder="Antonio">
                    </div>
                    <div class="form-group">
                        <label for="PrimerApellido">Primer Apellido</label>
                        <input type="text" name="pApellido" class="form-control" id="PrimerApellido" placeholder="Perez" required>
                    </div>
                    <div class="form-group">
                        <label for="SegundoApellido">Segundo Apellido</label>
                        <input type="text" name="sApellido" class="form-control" id="SegundoApellido" placeholder="Sanchez">
                    </div>
                    <div class="form-group">
                        <label for="Usuario">Usuario</label>
                        <input type="text" name="username" class="form-control" id="Usuario" placeholder="juan97">
                    </div>
                    <div class="form-group">
                        <label for="Password1">Contrase&ntilde;a</label>
                        <input type="password" name="passwd" class="form-control" id="Password1" placeholder="Password">
                    </div>
                    <div class="form-inline">
                        <div class="form-group">
                            <label for="FechaNac">Nacimiento</label>
                            <select name="dia" class="form-control">
                                <option>01</option>
                                <option>02</option>
                                <option>03</option>
                                <option>04</option>
                                <option>05</option>
                                <option>06</option>
                                <option>07</option>
                                <option>08</option>
                                <option>09</option>
                                <option>10</option>
                                <option>11</option>
                                <option>12</option>
                                <option>13</option>
                                <option>14</option>
                                <option>15</option>
                                <option>16</option>
                                <option>17</option>
                                <option>18</option>
                                <option>19</option>
                                <option>20</option>
                                <option>21</option>
                                <option>22</option>
                                <option>23</option>
                                <option>24</option>
                                <option>25</option>
                                <option>26</option>
                                <option>27</option>
                                <option>28</option>
                                <option>29</option>
                                <option>30</option>
                                <option>31</option>
                            </select>
                            <select name="mes" class="form-control">
                                <option>Enero</option>
                                <option>Febrero</option>
                                <option>Marzo</option>
                                <option>Abril</option>
                                <option>Mayo</option>
                                <option>Junio</option>
                                <option>Julio</option>
                                <option>Agosto</option>
                                <option>Septiembre</option>
                                <option>Octubre</option>
                                <option>Noviembre</option>
                                <option>Diciembre</option>
                            </select>
                            <input type="text" name="anyo" class="form-control" id="anyo" placeholder="A&ntilde;o" required>
                        </div>
                    </div>
                    <button type="submit" name="btnSig" class="btn btn-success">Siguiente</button>
                </form>

                <%
                    if (request.getParameter("btnSig") != null) {
                        RegUser myReg = new RegUser();
                        InicioSesion log = new InicioSesion();

                        EncriptadorPassword encryp = new EncriptadorPassword("J@va3ncr1p73r");

                        String pNombre = request.getParameter("pNombre"), sNombre = request.getParameter("sNombre");
                        String pApellido = request.getParameter("pApellido"), sApellido = request.getParameter("sApellido");
                        String usuario = request.getParameter("username"), passwd = request.getParameter("passwd"), dia = request.getParameter("dia");
                        String mes = request.getParameter("mes"), anyo = request.getParameter("anyo");

                        int userExiste = myReg.validacionUsuario(usuario);

                        if (userExiste < 0) {
                            String newPass = encryp.encrypt(passwd);

                            userExiste = myReg.reguistroUsuario(pNombre, sNombre, pApellido, sApellido, usuario, newPass, dia, mes, anyo);

                            if (userExiste > 0) {
                                mySession.setAttribute("currentUser", usuario);
                                ResultSet rs = log.userData(usuario);
                                if (rs.next()) {
                                    mySession.setAttribute("idUser", rs.getInt(1));
                                }
                                response.sendRedirect("index.jsp");
                            } else { %>
                <p>Error al ingresar el usario!</p>
                <%
                    }
                } else { %>
                <p>El nombre de usuario seleccionado ya existe, por favor selecciona otro</p>
                <%
                        }
                    }
                %>
            </div>
        </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/angular.js"></script>
        <script src="js/ngCart.js"></script>
        <%@include file="templates.html" %>
    </body>
</html>