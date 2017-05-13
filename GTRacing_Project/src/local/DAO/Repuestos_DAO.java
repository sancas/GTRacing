/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.DAO;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import local.Conexion.Conexion;
import local.LC.Repuestos_LC;

/**
 *
 * @author Leonel
 */
public class Repuestos_DAO {
    //Varibles globales
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstate;
    
    public ResultSet getListRepuestos(){
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select repuestos.idrepuesto, "
                    + "repuestos.namerepuesto, repuestos.respuestoisactive, repuestos.descripcion, "
                    + "repuestos.foto, usuarios.username from repuestos "
                    + "join usuarios on repuestos.iduseradd = usuarios.idusuario");
            myRs = myPstate.executeQuery();
            return myRs;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener datos.");
            Logger.getLogger(Repuestos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int addRepuestos(String resp, int estado, String descrip, FileInputStream file, int bite){
        int idusuario, accion;
        idusuario = local.Pool_Variable.Variables.getIdUsuario();
        
        try{
            myConn.EstablecerConn();
            
            myPstate = myConn.con.prepareStatement("insert into repuestos(namerepuesto, respuestoisactive, descripcion, foto, iduseradd) values(?,?,?,?,?)");
            myPstate.setString(1, resp);
            myPstate.setInt(2, estado);
            myPstate.setString(3, descrip);
            myPstate.setBinaryStream(4, file, bite);
            myPstate.setInt(5, idusuario);
            accion = myPstate.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al ingresar datos.");
            Logger.getLogger(Repuestos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public int updateRepuestos(String resp, int estado, String descrip, int idrep){
        int idusuario, accion;
        idusuario = local.Pool_Variable.Variables.getIdUsuario();
        try{
            
            myPstate = myConn.con.prepareStatement("update repuestos set namerepuesto = ?, "
                    + "respuestoisactive = ?, descripcion = ?, iduseradd = ? where idrepuesto = ?");
            myPstate.setString(1, resp);
            myPstate.setInt(2, estado);
            myPstate.setString(3, descrip);
            myPstate.setInt(4, idusuario);
            myPstate.setInt(5, idrep);
            accion = myPstate.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al actualizar.");
            Logger.getLogger(Repuestos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public int updateRepuestosPhoto(String resp, int estado, String descrip, FileInputStream file, int bite, int idrep){
        int idusuario, accion;
        idusuario = local.Pool_Variable.Variables.getIdUsuario();
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("update repuestos set namerepuesto = ?, respuestoisactive = ?, descripcion = ?, foto = ?, iduseradd = ? where idrepuesto = ?");
            myPstate.setString(1, resp);
            myPstate.setInt(2, estado);
            myPstate.setString(3, descrip);
            myPstate.setBinaryStream(4, file, bite);
            myPstate.setInt(5, idusuario);
            myPstate.setInt(6, idrep);
            accion = myPstate.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al actualizar de repuesto datos");
            Logger.getLogger(Repuestos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public ResultSet loadImage(int idEmpleo){
        try {
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select foto from repuestos where idrepuesto=?");
            myPstate.setInt(1, idEmpleo);
            myRs = myPstate.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Repuestos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int deleteRepuestos(int idresp){
        int isdeleted = 0;
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("delete from repuestos where idrepuesto = ?");
            myPstate.setInt(1, idresp);
            isdeleted = myPstate.executeUpdate();
            return isdeleted;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al elminar datos.");
            Logger.getLogger(Repuestos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return isdeleted;
        }
    }
    
    public ResultSet getRepuestos() {
        try {
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("SELECT idrepuesto, namerepuesto, respuestoIsActive FROM repuestos");
            myRs = myPstate.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Repuestos_DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Repuestos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void listaRepuestos(JComboBox jcbx){
        DefaultComboBoxModel jcbxModel;
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select * from repuestos");
            myRs = myPstate.executeQuery();
            jcbxModel = new DefaultComboBoxModel();
            jcbx.setModel(jcbxModel);
            while(myRs.next()){
                jcbxModel.addElement(new Repuestos_LC(myRs.getInt(1), myRs.getString(2)));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Repuestos_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
