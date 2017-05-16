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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import local.Conexion.Conexion;
import local.LC.Cargos;

/**
 *
 * @author Leonel
 */
public class Cargos_DAO {
    //Varibles globales
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstate;
    
    public ResultSet getRowsCargo(){
        try {
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("Select * from cargos");
            myRs = myPstate.executeQuery();
            
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Cargos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            this.close();
        }
    }
    
    public ResultSet getSearchRows(String dato){
        try {
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("Select * from cargos where nombrecargo like '%" + dato + "%'");
            //myPstate.setString(1, dato);
            myRs = myPstate.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Cargos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            this.close();
        }
    }
    
    public int addCargo(String nameCargo, double salario, String descripcion){
        int i;
        try {
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("INSERT INTO cargos(nombrecargo, salriocargo, descripcargo) VALUES (?, ?, ?)");
            myPstate.setString(1, nameCargo);
            myPstate.setDouble(2, salario);
            myPstate.setString(3, descripcion);
            i = myPstate.executeUpdate();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Cargos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            this.close();
        }
    }
    
    public int updateCargo(int id, String nameCargo, double salario, String descripcion){
        int i;
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("UPDATE cargos SET nombrecargo=?, salriocargo=?, descripcargo=? WHERE idcargo=?");
            myPstate.setString(1, nameCargo);
            myPstate.setDouble(2, salario);
            myPstate.setString(3, descripcion);
            myPstate.setInt(4, id);
            i = myPstate.executeUpdate();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Cargos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            this.close();
        }
    }
    
    public  int deleteCargos(int id){
        int i;
        try {
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("DELETE FROM cargos WHERE idcargo=?");
            myPstate.setInt(1, id);
            
            i = myPstate.executeUpdate();
            
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Cargos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            this.close();
        }
    }
    
    public void listaCargos(JComboBox jcbx){
        DefaultComboBoxModel jcbxModel;
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select * from cargos");
            myRs = myPstate.executeQuery();
            jcbxModel = new DefaultComboBoxModel();
            jcbx.setModel(jcbxModel);
            while(myRs.next()){
                jcbxModel.addElement(new Cargos(myRs.getInt(1), myRs.getString(2)));
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Cargos_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close();
        }
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
