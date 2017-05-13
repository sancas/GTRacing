/*
 * @(#) Conexion.java JDK 1.8.0_111
 * 19/03/2017
 * RN Soft inc.
 */

package com.gtracing.conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase sirve para establecer las conexiones a la Base de Datos Postgre SQL
 * @version 1.0 19/03/2017
 * @author Leonel Rivas
 */
public class Conexion {
    public Connection con;
    
    public void EstablecerConn(){
        String url = "jdbc:postgresql://localhost:5432/GTRacing";
        String user="postgres";
        String passwd="";
        
        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url,user,passwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CerrarConn(){ 
        try {
        con.close();
        //rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
