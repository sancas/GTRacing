/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtracing.servlets;

import com.gtracing.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonel
 */
public class FotoAuto {
    //Variables Globales
    Conexion myConn = new Conexion();
    PreparedStatement myPstat;
    ResultSet myRs;
    
    public byte[] miFoto(int id){
        byte[] foto = null;
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("SELECT foto FROM autos WHERE idauto = ?");
            myPstat.setInt(1, id);
            myRs = myPstat.executeQuery();
            
            if(myRs.next())
                foto = myRs.getBytes(1);
            
            myPstat.close();
        } catch (SQLException ex) {
            Logger.getLogger(FotoAuto.class.getName()).log(Level.SEVERE, null, ex);
            foto = null;
        }
        return foto;
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
