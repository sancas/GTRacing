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
 * @author sanch
 */
public class Repuestos_DAO {
    //Varibles globales
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstate;
    
    public ResultSet getRepuestos() {
        try {
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("SELECT idrepuesto, namerepuesto, respuestoIsActive FROM repuestos");
            myRs = myPstate.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Cargos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ResultSet getRepuestos(int id) {
        try {
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("SELECT cantidad, precioventa FROM existenciarepuestos WHERE idrepuesto = ?");
            myPstate.setInt(1, id);
            myRs = myPstate.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Cargos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
