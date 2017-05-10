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
public class Usuarios_DAO {

    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstat;

    //Metodo para agregar Usuario
    public int addUser(String username, String passwd, int empleado, int rol) {
        int i = 0;
        
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("INSERT INTO public.usuarios(username, userpasswd, idempleado, idrol) VALUES (?, ?, ?, ?)");
            myPstat.setString(1, username);
            myPstat.setString(2, passwd);
            myPstat.setInt(3, empleado);
            myPstat.setInt(4, rol);
            
            i = myPstat.executeUpdate();
            
            myPstat.close();
            
            return i;
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //Metodo para actuallizar Usuario
    public int updateUser(int iduser, String username, String passwd, int empleado, int rol) {
        int i = 0;
        
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("UPDATE public.usuarios SET username=?, userpasswd=?, idempleado=?, idrol=? WHERE idusuario=?");
            myPstat.setString(1, username);
            myPstat.setString(2, passwd);
            myPstat.setInt(3, empleado);
            myPstat.setInt(4, rol);
            myPstat.setInt(5, iduser);
            
            i = myPstat.executeUpdate();
            
            myPstat.close();
            
            return i;
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //metodo para eliminar usuario
    public int deleteUser(int id) {
        int i = 0;
        
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("DELETE FROM public.usuarios WHERE idusuario=?");
            myPstat.setInt(1, id);
            
            i = myPstat.executeUpdate();
            
            myPstat.close();
            
            return i;
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //metodo para llenar la tabla usuarios
    public ResultSet getListUser() {
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select usuarios.idusuario, usuarios.username, usuarios.userpasswd, empleados.dui, roles.namerol from usuarios join empleados on usuarios.idempleado = empleados.idempleado join roles on usuarios.idrol = roles.idrol");
            myRs = myPstat.executeQuery();
            
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //Verifica si el nombre de usuario seleccionado exite
    public int userExist(String name){
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select username from usuarios where username = ?");
            myPstat.setString(1, name);
            myRs = myPstat.executeQuery();
            
            if(myRs.next())
                return 1;
            else
                return 0;
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    //Metodo para cerrar la conexion
    public void close(){
        myConn.CerrarConn();
    }
}
