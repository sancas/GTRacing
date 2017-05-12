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
import javax.swing.JOptionPane;
import local.Conexion.Conexion;
import local.LC.ProveedoresLC;

/**
 *
 * @author Leonel
 */
public class Proveedores_DAO {
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstate;
    
    public ResultSet getListProveedor(){
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select idproveedor, nameproveedor, direccion, pbxprovedor from proveedores");
            myRs = myPstate.executeQuery();
            return myRs;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener datos.");
            Logger.getLogger(Proveedores_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int addProveedor(String prov, String dir, String pbx){
        int accion;
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("insert into proveedores(nameproveedor, direccion, pbxprovedor) values(?,?,?)");
            myPstate.setString(1, prov);
            myPstate.setString(2, dir);
            myPstate.setString(3, pbx);
            accion = myPstate.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al ingresar datos.");
            Logger.getLogger(Proveedores_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public int updateProveedor(String proveedor, String pbx, String dir, int idproveedor){
        int accion;
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("update proveedores set nameproveedor = ?, direccion = ?, pbxprovedor = ? where idproveedor = ?");
            myPstate.setString(1, proveedor);
            myPstate.setString(2, dir);
            myPstate.setString(3, pbx);
            myPstate.setInt(4, idproveedor);
            accion = myPstate.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos.");
            Logger.getLogger(Proveedores_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public int deleteProveedor(int idprov){
        int isdeleted = 0;
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("delete from proveedores where idproveedor = ?");
            myPstate.setInt(1, idprov);
            isdeleted = myPstate.executeUpdate();
            return isdeleted;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al eliminar datos.");
            Logger.getLogger(Proveedores_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return isdeleted;
        }
    }
    
    public void listaProveedor(JComboBox jcbx){
        DefaultComboBoxModel jcbxModel;        
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select * from proveedores");
            myRs = myPstate.executeQuery();
            jcbxModel = new DefaultComboBoxModel();
            jcbx.setModel(jcbxModel);
            while(myRs.next()){
                jcbxModel.addElement(new ProveedoresLC(myRs.getInt(1), myRs.getString(2)));
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener datos.");
            Logger.getLogger(Proveedores_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
