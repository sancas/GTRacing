/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtracing.registros;

import com.gtracing.conexion.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonel
 */
public class RegUser {
    //Variables Globales
    Conexion myConn = new Conexion();
    PreparedStatement myPstat;
    ResultSet myRs;
    
    public int reguistroUsuario(String pNom, String sNom, String pApel, String sApel, String user, String pass, String dia, String mes, String anyo){
        int isReg;
        String fecha = formatoFecha(anyo, mes, dia);
        try {
            //Formateando la fecha para ingresarla a la bd
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date myDate = formato.parse(fecha);
            java.sql.Date sqlDate = new Date(myDate.getTime());
            
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("INSERT INTO usuarioweb(pname, sname, papellido, sapellido, username, userpwd, fechanac) VALUES (?, ?, ?, ?, ?, ?, ?)");
            myPstat.setString(1, pNom);
            myPstat.setString(2, sNom);
            myPstat.setString(3, pApel);
            myPstat.setString(4, sApel);
            myPstat.setString(5, user);
            myPstat.setString(6, pass);
            myPstat.setDate(7, sqlDate);
            
            isReg = myPstat.executeUpdate();
            
            myPstat.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(RegUser.class.getName()).log(Level.SEVERE, null, ex);
            isReg = -1;
        }
        return isReg;
    }
    
    public int validacionUsuario(String usuario){
        int existe;
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select * from usuarioweb where username=?");
            myPstat.setString(1, usuario);
            
            existe = myPstat.executeUpdate();
            
            myPstat.close();
        } catch (SQLException ex) {
            Logger.getLogger(RegUser.class.getName()).log(Level.SEVERE, null, ex);
            existe = -1;
        }
        return existe;
    }
    
    public int ingresoCuenta(int idUser, int card, int secureCod, double cantidad){
        int isIn;
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("INSERT INTO public.cuentacompras(idwebuser, tarjetabanco, securekey, cantidaddinero) VALUES (?, ?, ?, ?)");
            myPstat.setInt(1, idUser);
            myPstat.setInt(2, card);
            myPstat.setInt(3, secureCod);
            myPstat.setDouble(4, cantidad);
            
            isIn = myPstat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegUser.class.getName()).log(Level.SEVERE, null, ex);
            isIn = -1;
        }
        return isIn;
    }
    
    private String formatoFecha(String anyo, String mes, String dia) {
        switch (mes) {
            case "Enero":
                mes = "01";
                break;
            case "Febrero":
                mes = "02";
                break;
            case "Marzo":
                mes = "03";
                break;
            case "Abril":
                mes = "04";
                break;
            case "Mayo":
                mes = "05";
                break;
            case "Junio":
                mes = "06";
                break;
            case "Julio":
                mes = "07";
                break;
            case "Agosto":
                mes = "08";
                break;
            case "Septiembre":
                mes = "09";
                break;
            case "Octubre":
                mes = "10";
                break;
            case "Noviembre":
                mes = "11";
                break;
            case "Diciembre":
                mes = "12";
                break;
        }
        return anyo + "-" + mes + "-" + dia;
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
