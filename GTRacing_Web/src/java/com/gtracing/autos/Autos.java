/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtracing.autos;

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
public class Autos {
    //Variables globales
    Conexion myConn = new Conexion();
    PreparedStatement myPstat;
    ResultSet myRs;
    
    public ResultSet getAutosIndex(){
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("SELECT autos.idauto, marcas.namemarca, autos.modeloauto, autos.anyoauto, existenciaautos.precioventa FROM autos INNER JOIN existenciaautos ON existenciaautos.idautos = autos.idauto INNER JOIN marcas ON autos.idmarca = marcas.idmarca");
            myRs = myPstat.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Autos.class.getName()).log(Level.SEVERE, null, ex);
            return  null;
        }
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
