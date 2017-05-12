/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.Conexion.Conexion;

/**
 *
 * @author Leonel
 */
public class LoginAccess_DAO {
    //Variables Globales
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstat;
    
    public ResultSet getUserData(String userid){
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("SELECT * FROM usuarios WHERE username=?");
            myPstat.setString(1, userid);
            myRs = myPstat.executeQuery();
            
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccess_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ResultSet getNames(int employ){
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("SELECT nombreempleado, apellidoempleado "
                    + "FROM empleados WHERE idempleado=?");
            myPstat.setInt(1, employ);
            myRs = myPstat.executeQuery();
            
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccess_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void closeLogin(){
        try {
            myConn.CerrarConn();
            myRs.close();
            myPstat.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginAccess_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
