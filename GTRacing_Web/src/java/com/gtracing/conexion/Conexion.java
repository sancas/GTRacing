/*
 * @(#) Conexion.java JDK 1.8.0_111
 * 19/03/2017
 * RN Soft inc.
 */

package com.gtracing.conexion;
import com.google.gson.Gson;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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
        String passwd="j@vA4dv@nC3d#";
        
        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(url,user,passwd);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
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
