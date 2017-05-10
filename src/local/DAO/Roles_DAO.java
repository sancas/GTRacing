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
import local.LC.Roles;
import local.Pool_Variable.Variables;

/**
 *
 * @author Leonel
 */
public class Roles_DAO {

    //Variables globales
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstat;

    //metodo para agregar el rol
    public int insertRol(String rol, String descripcio) {
        int i = 0;

        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("INSERT INTO roles(namerol, descripcion) VALUES (?, ?)");
            myPstat.setString(1, rol);
            myPstat.setString(2, descripcio);

            i = myPstat.executeUpdate();

            myPstat.close();

            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Roles_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public void listaRoles(JComboBox jcbx){
        DefaultComboBoxModel jcbxModel;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        
        try{
            myConn.EstablecerConn();
            pStatement = myConn.con.prepareStatement("select * from roles");
            rs = pStatement.executeQuery();
            jcbxModel = new DefaultComboBoxModel();
            
            jcbx.setModel(jcbxModel);
            
            while(rs.next()){
                jcbxModel.addElement(new Roles(rs.getInt(1), rs.getString(2)));
            }
        }
        catch(Exception ex){
        }
        finally{
            try{
                myConn.CerrarConn();
                rs.close();
                pStatement.close();
            }
            catch(Exception e){
                
            }
        }
    }
    
    //Metodo para actualizar roles
    public int updateRol(int id, String rol, String des){
        int i = 0;

        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("UPDATE public.roles SET namerol=?, descripcion=? WHERE idrol=?");
            myPstat.setString(1, rol);
            myPstat.setString(2, des);
            myPstat.setInt(3, id);

            i = myPstat.executeUpdate();

            myPstat.close();

            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Roles_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    //metodo para eliminar roles
    public  int deleteRol(int id){
        int i = 0;

        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("DELETE FROM public.roles WHERE idrol=?");
            myPstat.setInt(1, id);

            i = myPstat.executeUpdate();

            myPstat.close();

            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Roles_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //metodo para ingresar las tareas relacionadas al rol
    public int inputTareas(int[] idTarea, int idRol) {
        int i = 0;

        try {
            myConn.EstablecerConn();

            for (int j = 0; j < idTarea.length; j++) {
                myPstat = myConn.con.prepareStatement("INSERT INTO public.tareas_roles(idrol, idtarea) VALUES (?, ?)");
                myPstat.setInt(1, idRol);
                myPstat.setInt(2, idTarea[j]);

                i = myPstat.executeUpdate();
            }

            myPstat.close();

            return i;

        } catch (SQLException ex) {
            Logger.getLogger(Roles_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //Metodo para eliminar las tareas
    public int delTareas(int idrol) {
        int i = 0;

        try {
            myConn.EstablecerConn();

            myPstat = myConn.con.prepareStatement("DELETE FROM tareas_roles WHERE idrol = ?");
            myPstat.setInt(1, idrol);

            i = myPstat.executeUpdate();

            myPstat.close();

            return i;

        } catch (SQLException ex) {
            Logger.getLogger(Roles_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //metodo para obtener el ultimo rol ingresado
    public int getCurrentRol() {
        int rol = 0;

        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select idrol from roles order by idrol DESC");
            myRs = myPstat.executeQuery();

            if (myRs.next()) {
                rol = myRs.getInt(1);
            }

            myRs.close();

            return rol;
        } catch (SQLException ex) {
            Logger.getLogger(Roles_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //Metodo para obtener las tareas que puede hacer un usuario
    public ResultSet getTareasRol(int idRol) {

        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("SELECT * FROM public.tareas_roles WHERE idrol = ?");
            myPstat.setInt(1, idRol);
            myRs = myPstat.executeQuery();

            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Roles_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //Metodo para llenar la tabla empleados
    public ResultSet getListRol() {
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select * from roles");
            myRs = myPstat.executeQuery();

            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ResultSet listaTareas(){
        
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select idtarea from tareas_roles where idrol = ?");
            myPstat.setInt(1, Variables.getUserLevel());
            myRs = myPstat.executeQuery();

            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //metodo para cerrar la conexion
    public void close() {
        myConn.CerrarConn();
    }
}
