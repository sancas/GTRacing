/*
 * @(#) Conexion.java JDK 1.8.0_111
 * 19/03/2017
 * RN Soft inc.
 */

package Conexion;
import IntercambioVariable.InterVariable;
import java.io.*;
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
    
    public void EstablecerConn(){
        try {
            String cadena = "jdbc:postgresql://localhost:5432/GTRacing";
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
}
