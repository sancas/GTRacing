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
import javax.swing.JOptionPane;
import local.Conexion.Conexion;

/**
 *
 * @author Leonel
 */
public class ExistenciaRepues_DAO {
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstate;
    
    public ResultSet getListExisRepuestos(){
        
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select existenciarepuestos.idexistencia, "
                    + "repuestos.namerepuesto, proveedores.nameproveedor, existenciarepuestos.cantidad, "
                    + "existenciarepuestos.preciocompra, existenciarepuestos.precioventa, empleados.nombreempleado, "
                    + "usuarios.username from existenciarepuestos "
                    + "join usuarios on existenciarepuestos.iduseradd = usuarios.idusuario "
                    + "join empleados on usuarios.idempleado = empleados.idempleado "
                    + "join proveedores on proveedores.idproveedor = existenciarepuestos.idproveedor "
                    + "join repuestos on repuestos.idrepuesto = existenciarepuestos.idrepuesto");
            myRs = myPstate.executeQuery();
            return myRs;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener datos.");
            Logger.getLogger(ExistenciaRepues_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int addExisRepuestos(int idrep, int idprov, int cantidad, double pcompra, double pventa){
        int idusuario, accion;
        idusuario = local.Pool_Variable.Variables.getIdUsuario();
        
        try{
            myConn.EstablecerConn();
            
            myPstate = myConn.con.prepareStatement("insert into existenciarepuestos(idrepuesto, "
                    + "idproveedor, iduseradd, cantidad, preciocompra, precioventa) values(?,?,?,?,?,?)");
            myPstate.setInt(1, idrep);
            myPstate.setInt(2, idprov);
            myPstate.setInt(3, idusuario);
            myPstate.setInt(4, cantidad);
            myPstate.setDouble(5, pcompra);
            myPstate.setDouble(6, pventa);
            accion = myPstate.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al ingresar datos.");
            Logger.getLogger(ExistenciaRepues_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public int updateExisRepuestos(int idrep, int idprov, int cantidad, double pcompra, double pventa, int idexistencia){
        int idusuario, accion;
        idusuario = local.Pool_Variable.Variables.getIdUsuario();
        
        try{
            myConn.EstablecerConn();
            
            
            myPstate = myConn.con.prepareStatement("update existenciarepuestos set idrepuesto=?, "
                    + "idproveedor=?, iduseradd=?, cantidad=?, preciocompra=?, precioventa=? where idexistencia = ?");
            myPstate.setInt(1, idrep);
            myPstate.setInt(2, idprov);
            myPstate.setInt(3, idusuario);
            myPstate.setInt(4, cantidad);
            myPstate.setDouble(5, pcompra);
            myPstate.setDouble(6, pventa);
            myPstate.setInt(7, idexistencia);
            accion = myPstate.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al actualizar.");
            Logger.getLogger(ExistenciaRepues_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public int deleteExisRepuestos(int idex){
        int isdeleted = 0;
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("delete from existenciarepuestos where idexistencia = ?");
            myPstate.setInt(1, idex);
            isdeleted = myPstate.executeUpdate();
            return isdeleted;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al elminar datos.");
            Logger.getLogger(ExistenciaRepues_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return isdeleted;
        }
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
