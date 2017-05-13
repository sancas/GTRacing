/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtracing.reserva;

import com.gtracing.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanch
 */
public class Reserva {
    //Variables globales
    Conexion myConn = new Conexion();
    PreparedStatement myPstat;
    ResultSet myRs;
    
    public int detalleReserva(String idReserva, int idRepuesto, int idAuto, Double subTotal) {
        try {
            myConn.EstablecerConn();
            if (idRepuesto == 0)
            {
                myPstat = myConn.con.prepareStatement("INSERT INTO detalleReserva(idReserva, idAuto, cantidad, subtotal) VALUES(?, ?, 1, ?)");
                myPstat.setInt(2, idAuto);
            }
            else
            {
                myPstat = myConn.con.prepareStatement("INSERT INTO detalleReserva(idReserva, idRepuesto, cantidad, subtotal) VALUES(?, ?, 1, ?)");
                myPstat.setInt(2, idRepuesto);
            }
            myPstat.setString(1, idReserva);
            myPstat.setDouble(3, subTotal);
            return myPstat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Reserva.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int reservar(String cadena, int idWebUser, double totalReserva) {
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("INSERT INTO reservaproducto(idReserva, totalReserva, idWebUser) VALUES(?, ?, ?)");
            myPstat.setString(1, cadena);
            myPstat.setDouble(2, totalReserva);
            myPstat.setInt(3, idWebUser);
            return myPstat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Reserva.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public String getCadenaAlfanumAleatoria(int longitud) {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }
}
