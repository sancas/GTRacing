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
import local.LC.AutosLC;

/**
 *
 * @author Leonel
 */
public class Autos_DAO {
    //Variable Globales
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstate;
    
    public ResultSet getListAutos(){
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select autos.idauto, marcas.namemarca, "
                    + "autos.modeloauto, autos.anyoauto, empleados.nombreempleado, usuarios.username, "
                    + "autos.foto from autos "
                    + "join usuarios on autos.iduseradd = usuarios.idusuario "
                    + "join empleados on usuarios.idempleado = empleados.idempleado "
                    + "join marcas on marcas.idmarca = autos.idmarca");
            myRs = myPstate.executeQuery();
            return myRs;
        }
        catch(SQLException ex){
            Logger.getLogger(Autos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            this.close();
        }
    }
    
    public ResultSet loadImage(int idAuto){
        try {
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select foto from autos where idauto=?");
            myPstate.setInt(1, idAuto);
            myRs = myPstate.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Autos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            this.close();
        }
    }
    
    public int addAutos(int idmarca, String modelo, int anyo, FileInputStream file, int bite){
        int idusuario, accion;
        idusuario = local.Pool_Variable.Variables.getIdUsuario();
        try{
            myConn.EstablecerConn();
            
            myPstate = myConn.con.prepareStatement("insert into autos(idmarca, "
                    + "iduseradd, modeloauto, anyoauto, foto) values(?,?,?,?,?)");
            myPstate.setInt(1, idmarca);
            myPstate.setInt(2, idusuario);
            myPstate.setString(3, modelo);
            myPstate.setInt(4, anyo);
            myPstate.setBinaryStream(5, file, bite);
            accion = myPstate.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al ingresar datos de repuestos");
            Logger.getLogger(Autos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            this.close();
        }
    }
    
    public int updateAutos(int idmarca, String modelo, int anyo, int idauto){
        int idusuario, accion;
        idusuario = local.Pool_Variable.Variables.getIdUsuario();
        try{
            myConn.EstablecerConn();
            
            myPstate = myConn.con.prepareStatement("update autos set idmarca = ?, "
                    + "iduseradd = ?, modeloauto = ?, anyoauto = ? where idauto = ?");
            myPstate.setInt(1, idmarca);
            myPstate.setInt(2, idusuario);
            myPstate.setString(3, modelo);
            myPstate.setInt(4, anyo);
            myPstate.setInt(5, idauto);
            accion = myPstate.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al actualizar de repuesto datos");
            Logger.getLogger(Autos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            this.close();
        }
    }
    
    public int updateAutosPhoto(int idmarca, String modelo, int anyo, int idauto, FileInputStream file, int bite){
        int idusuario, accion;
        idusuario = local.Pool_Variable.Variables.getIdUsuario();
        try{
            myConn.EstablecerConn();
            
            myPstate = myConn.con.prepareStatement("update autos set idmarca = ?, "
                    + "iduseradd = ?, modeloauto = ?, anyoauto = ?, foto = ? where idauto = ?");
            myPstate.setInt(1, idmarca);
            myPstate.setInt(2, idusuario);
            myPstate.setString(3, modelo);
            myPstate.setInt(4, anyo);
            myPstate.setBinaryStream(5, file, bite);
            myPstate.setInt(6, idauto);
            accion = myPstate.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al actualizar de repuesto datos.");
            Logger.getLogger(Autos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } finally {
            this.close();
        }
    }
    
    public ResultSet getNombreAutos(int idresp){
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select modeloauto from autos where idauto = ?");
            myPstate.setInt(1, idresp);
            myRs = myPstate.executeQuery();
            return myRs;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener nombre de repuesto para tabla autos.");
            Logger.getLogger(Autos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            this.close();
        }
    }
    
    public int deleteAutos(int idresp){
        int isdeleted = 0;
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("delete from autos where idauto = ?");
            myPstate.setInt(1, idresp);
            isdeleted = myPstate.executeUpdate();
            return isdeleted;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al elminar datos para tabla autos.");
            Logger.getLogger(Autos_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return isdeleted;
        } finally {
            this.close();
        }
    }
    
    public void listaAutos(JComboBox jcbx){
        DefaultComboBoxModel jcbxModel;        
        try{
            myConn.EstablecerConn();
            myPstate = myConn.con.prepareStatement("select * from autos");
            myRs = myPstate.executeQuery();
            jcbxModel = new DefaultComboBoxModel();
            jcbx.setModel(jcbxModel);
            while(myRs.next()){
                jcbxModel.addElement(new AutosLC(myRs.getInt(1), myRs.getString(4)));
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de autos.");
            Logger.getLogger(Autos_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.close();
        }
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
