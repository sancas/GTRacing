/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtracing.repuesto;

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
public class Repuestos {
    //Variables globales
    Conexion myConn = new Conexion();
    PreparedStatement myPstat;
    ResultSet myRs;
    
    public ResultSet getRepuestosIndex(){
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("SELECT repuestos.idrepuesto, repuestos.namerepuesto, repuestos.descripcion, existenciarepuestos.precioventa FROM repuestos INNER JOIN existenciarepuestos ON existenciarepuestos.idrepuesto = repuestos.idrepuesto WHERE repuestos.respuestoisactive = 1");
            myRs = myPstat.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Repuestos.class.getName()).log(Level.SEVERE, null, ex);
            return  null;
        }
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
