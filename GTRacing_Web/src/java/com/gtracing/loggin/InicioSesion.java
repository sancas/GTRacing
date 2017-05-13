/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtracing.loggin;

import com.gtracing.conexion.Conexion;
import com.gtracing.encryption.EncriptadorPassword;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonel
 */
public class InicioSesion {
    //Variables Globales
    Conexion myConn = new Conexion();
    PreparedStatement myPstat;
    ResultSet myRs;
    
    public int Login(String user, String passw){
        int isUser = 0;
        EncriptadorPassword encryp = new EncriptadorPassword("J@va3ncr1p73r");
        String passencrip = encryp.encrypt(passw);
        
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("Select * from usuarioweb where username=? and userpwd=?");
            myPstat.setString(1, user);
            myPstat.setString(2, passencrip);
            
            myRs = myPstat.executeQuery();
            
            if(myRs.next())
                isUser = 1;
        } catch (SQLException ex) {
            Logger.getLogger(InicioSesion.class.getName()).log(Level.SEVERE, null, ex);
            isUser = -1;
        }
        return isUser;
    }
    
    public ResultSet userData(String usuario){
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("Select * from usuarioweb where username=?");
            myPstat.setString(1, usuario);
            
            myRs = myPstat.executeQuery();
            
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(InicioSesion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
