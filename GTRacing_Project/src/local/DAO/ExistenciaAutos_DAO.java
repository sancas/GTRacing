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
public class ExistenciaAutos_DAO {
    //Variable Globales
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstat;
    
    public ResultSet getListExisAuto(){
        try{
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select existenciaautos.idexistenciaautos, autos.modeloauto, "
                    + "proveedores.nameproveedor, existenciaautos.preciocompra, "
                    + "existenciaautos.precioventa, empleados.nombreempleado, "
                    + "usuarios.username from existenciaautos "
                    + "join usuarios on existenciaautos.iduseradd = usuarios.idusuario "
                    + "join empleados on usuarios.idempleado = empleados.idempleado "
                    + "join proveedores on proveedores.idproveedor = existenciaautos.idproveedor "
                    + "join autos on autos.idauto = existenciaautos.idautos");
            myRs = myPstat.executeQuery();
            return myRs;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener datos de autos.");
            Logger.getLogger(ExistenciaAutos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int addExisAutos(int idrep, int idprov, double pcompra, double pventa){
        int idusuario, accion;
        idusuario = local.Pool_Variable.Variables.getIdUsuario();
        try{
            myConn.EstablecerConn();
            
            myPstat = myConn.con.prepareStatement("insert into existenciaautos(idautos, idproveedor, "
                    + "iduseradd, preciocompra, precioventa) values(?,?,?,?,?)");
            myPstat.setInt(1, idrep);
            myPstat.setInt(2, idprov);
            myPstat.setInt(3, idusuario);
            myPstat.setDouble(4, pcompra);
            myPstat.setDouble(5, pventa);
            accion = myPstat.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al ingresar datos de existencia.");
            Logger.getLogger(ExistenciaAutos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public int updateExisAutos(int idrep, int idprov, double pcompra, double pventa, int idexistencia){
        int idusuario, accion;
        idusuario = local.Pool_Variable.Variables.getIdUsuario();
        try{
            myConn.EstablecerConn();
            
            myPstat = myConn.con.prepareStatement("update existenciaautos set idautos=?, "
                    + "idproveedor=?, iduseradd=?, preciocompra=?, precioventa=? "
                    + "where idexistenciaautos = ?");
            myPstat.setInt(1, idrep);
            myPstat.setInt(2, idprov);
            myPstat.setInt(3, idusuario);
            myPstat.setDouble(4, pcompra);
            myPstat.setDouble(5, pventa);
            myPstat.setInt(6, idexistencia);
            accion = myPstat.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al actualizar de existencia datos.");
            Logger.getLogger(ExistenciaAutos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public ResultSet getNombreExisAutos(int idex){
        try{
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select autos.modeloauto from existenciaautos "
                    + "join autos on existenciaautos.idautos = autos.idauto "
                    + "where idexistenciaautos = ?");
            myPstat.setInt(1, idex);
            myRs = myPstat.executeQuery();
            return myRs;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener nombre de repuesto.");
            Logger.getLogger(ExistenciaAutos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int deleteExisAutos(int idex){
        int isdeleted = 0;
        try{
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("delete from existenciaautos where idexistenciaautos = ?");
            myPstat.setInt(1, idex);
            isdeleted = myPstat.executeUpdate();
            return isdeleted;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al elminar datos para tabla autos: ");
            Logger.getLogger(ExistenciaAutos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return isdeleted;
        }
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
