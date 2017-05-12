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
import local.LC.Marcas_LC;

/**
 *
 * @author Leonel
 */
public class Marcas_DAO {
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstat;
    
    public ResultSet getListMarca(){
        try{
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select idmarca, namemarca, paismarca from marcas");
            myRs = myPstat.executeQuery();
            return myRs;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener datos.");
            Logger.getLogger(Marcas_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int addMarca(String marca, String pais){
        int accion;
        try{
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("insert into marcas(namemarca, paismarca) values(?,?)");
            myPstat.setString(1, marca);
            myPstat.setString(2, pais);
            accion = myPstat.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al ingresar datos");
            Logger.getLogger(Marcas_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public int updateMarcas(String marca, String pais, int idmarca){
        int accion;
        try{
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("update marcas set namemarca = ?, paismarca = ? where idmarca = ?");
            myPstat.setString(1, marca);
            myPstat.setString(2, pais);
            myPstat.setInt(3, idmarca);
            accion = myPstat.executeUpdate();
            return accion;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al actualizar datos.");
            Logger.getLogger(Marcas_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    public ResultSet getNombreMarca(int idmarca){
        try{
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select namemarca from marcas where idmarca = ?");
            myPstat.setInt(1, idmarca);
            myRs = myPstat.executeQuery();
            return myRs;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener nombre del proveedor");
            Logger.getLogger(Marcas_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int deleteMarca(int idmarca){
        int isdeleted = 0;
        try{
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("delete from marcas where idmarca = ?");
            myPstat.setInt(1, idmarca);
            isdeleted = myPstat.executeUpdate();
            return isdeleted;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al obtener datos.");
            Logger.getLogger(Marcas_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return isdeleted;
        }
    }
    
    public void listaMarcas(JComboBox jcbx){
        DefaultComboBoxModel jcbxModel;        
        try{
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select * from marcas");
            myRs = myPstat.executeQuery();
            jcbxModel = new DefaultComboBoxModel();
            jcbx.setModel(jcbxModel);
            while(myRs.next()){
                jcbxModel.addElement(new Marcas_LC(myRs.getInt(1), myRs.getString(2)));
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Marcas_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        myConn.CerrarConn();
    }
}
