/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtracing.users;

import com.gtracing.conexion.Conexion;
import com.gtracing.encryption.EncriptadorPassword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanch
 */
public class Usuarios {

    //Variables globales
    Conexion myConn = new Conexion();
    PreparedStatement myPstat;
    ResultSet myRs;

    public int modificarUsuario(String user, String fname, String mname, String lname, String sname, String password) {
        EncriptadorPassword encryp = new EncriptadorPassword("J@va3ncr1p73r");
        String passencrip = encryp.encrypt(password);
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("UPDATE usuarioweb SET pname = ?, "
                    + "sname = ?, papellido = ?, sapellido = ?, userpwd = ? "
                    + "WHERE username = ?");
            myPstat.setString(1, fname);
            myPstat.setString(2, mname);
            myPstat.setString(3, lname);
            myPstat.setString(4, sname);
            myPstat.setString(5, passencrip);
            myPstat.setString(6, user);
            return myPstat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
