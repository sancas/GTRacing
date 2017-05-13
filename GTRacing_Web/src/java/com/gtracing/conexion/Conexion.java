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
    
    private String getConnectionString() {
        String sConnectionString = null;
        String host = null, dbname = null, username = null, password = null;
        int port = 0;
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader("conf.json"));
            Result result = gson.fromJson(br, Result.class);
            if (result != null)
            {
                Database db = result.getDatabase();
                host = db.getHost();
                port = db.getPort();
                dbname = db.getDbname();
                username = db.getUsername();
                password = db.getPassword();
            }
            sConnectionString = "jdbc:postgresql://"
                    + host + ":"
                    + port + "/"
                    + dbname + "?"
                    + "user=" + username + "&"
                    + "password=" + password;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sConnectionString;
    }
    
    public void EstablecerConn(){
        try {
            String cadena = getConnectionString();
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(cadena);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al conectar con la base de datos: " + e);
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
