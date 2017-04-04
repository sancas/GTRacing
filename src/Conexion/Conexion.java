/*
 * @(#) Conexion.java JDK 1.8.0_111
 * 19/03/2017
 * RN Soft inc.
 */

package Conexion;

import IntercambioVariable.InterVariable;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * La clase sirve para establecer las conexiones a la Base de Datos Postgre SQL
 * @version 1.0 19/03/2017
 * @author Leonel Rivas
 */
public class Conexion {
    private Connection con;
    private PreparedStatement pStatemnt;
    private ResultSet rs;
    
    private String getConnectionString() {
        String sConnectionString = null;
        String host = null, dbname = null;
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
            }
            sConnectionString = "jdbc:postgresql://"
                    + host + ":"
                    + port + "/"
                    + dbname;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sConnectionString;
    }
    
    public void EstablecerConn(){
        try {
            String cadena = getConnectionString();
            String user = "postgres";
            String pwd = "fairytail";
                
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(cadena, user, pwd);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al conectar con la base de datos: " + e);
        }
    }
    
    public void CerrarConn(){ 
        try {
        con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet getDataUser(String user){
        try {
            EstablecerConn();
            pStatemnt = con.prepareStatement("SELECT username, userpasswd, idempleado, idrol FROM usuarios WHERE username=?");
            pStatemnt.setString(1, user);
            rs = pStatemnt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
    
    public ResultSet getUserName(){
        int currentEmpleado;
        currentEmpleado = InterVariable.getIdEmpleado();
        
        try{
            EstablecerConn();
            pStatemnt = con.prepareStatement("SELECT nombreempleado, apellidoempleado FROM empleados where idempleado=?");
            pStatemnt.setInt(1, currentEmpleado);
            rs = pStatemnt.executeQuery();
            return rs;
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Error al obtener nombre de usuario: " + ex);
            return null;
        }
    }
    
    public ResultSet getRepuestos() {
        try {
            EstablecerConn();
            pStatemnt = con.prepareStatement("SELECT idrepuesto, namerepuesto, repuestoisactive FROM repuestos");
            rs = pStatemnt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
    
    public ResultSet getRepuestos(int id) {
        try {
            EstablecerConn();
            pStatemnt = con.prepareStatement("SELECT cantidad, precioventa FROM existenciarepuestos WHERE idrepuesto = ?");
            pStatemnt.setInt(1, id);
            rs = pStatemnt.executeQuery();
            return rs;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
}
