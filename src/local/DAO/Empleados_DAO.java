    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.DAO;

import java.io.FileInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import local.Conexion.Conexion;
import local.LC.Empleados;

/**
 *
 * @author Leonel
 */
public class Empleados_DAO {

    //Variable Globales
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstat;

    //Metedo para ingresar datos
    public int addEmploy(String nombre, String apellido, FileInputStream file, int bite, String fecha, String domicilio, String dui, String nit, int cargo) throws ParseException {
        int i = 0;

        try {
            //Formateando la fecha para ingresarla a la bd
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date myDate = formato.parse(fecha);
            java.sql.Date sqlDate = new Date(myDate.getTime());

            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("INSERT INTO empleados(nombreempleado, apellidoempleado, foto, fechanac, domicilioemp, dui, nit, idcargo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            myPstat.setString(1, nombre);
            myPstat.setString(2, apellido);
            myPstat.setBinaryStream(3, file, bite);
            myPstat.setDate(4, sqlDate);
            myPstat.setString(5, domicilio);
            myPstat.setString(6, dui);
            myPstat.setString(7, nit);
            myPstat.setInt(8, cargo);

            i = myPstat.executeUpdate();

            myPstat.close();

            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    //Metodo para actualizar datos
    public int updateEmployWhitPhoto(int id, String nombre, String apellido, FileInputStream file, int bite, String fecha, String domicilio, String dui, String nit, int cargo) throws ParseException{
        int i = 0;
        
        try {
            //Formateando la fecha para ingresarla a la bd
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date myDate = formato.parse(fecha);
            java.sql.Date sqlDate = new Date(myDate.getTime());

            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("UPDATE empleados SET nombreempleado=?, apellidoempleado=?, foto=?, fechanac=?, domicilioemp=?, dui=?, nit=?, idcargo=? WHERE idempleado=?");
            myPstat.setString(1, nombre);
            myPstat.setString(2, apellido);
            myPstat.setBinaryStream(3, file, bite);
            myPstat.setDate(4, sqlDate);
            myPstat.setString(5, domicilio);
            myPstat.setString(6, dui);
            myPstat.setString(7, nit);
            myPstat.setInt(8, cargo);
            myPstat.setInt(9, id);
            
            i = myPstat.executeUpdate();

            myPstat.close();

            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    //Metodo para actualizar la tabla empleado sin foto
    public int upadteEmployWhitoutPhoto(int id, String nombre, String apellido, String fecha, String domicilio, String dui, String nit, int cargo) throws ParseException{
        int i = 0;
        
        try {
            //Formateando la fecha para ingresarla a la bd
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date myDate = formato.parse(fecha);
            java.sql.Date sqlDate = new Date(myDate.getTime());
            
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("UPDATE empleados SET nombreempleado=?, apellidoempleado=?, fechanac=?, domicilioemp=?, dui=?, nit=?, idcargo=? WHERE idempleado=?");
            myPstat.setString(1, nombre);
            myPstat.setString(2, apellido);
            myPstat.setDate(3, sqlDate);
            myPstat.setString(4, domicilio);
            myPstat.setString(5, dui);
            myPstat.setString(6, nit);
            myPstat.setInt(7, cargo);
            myPstat.setInt(8, id);
            
            i = myPstat.executeUpdate();

            myPstat.close();

            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int deleteEmploy(int id){
        int i = 0;
        
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("DELETE FROM empleados WHERE idempleado=?");
            myPstat.setInt(1, id);
            
            i = myPstat.executeUpdate();
            
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public ResultSet loadImage(int idEmpleo){
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select foto from empleados where idempleado=?");
            myPstat.setInt(1, idEmpleo);
            
            myRs = myPstat.executeQuery();
            
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //Metodo para llenar la tabla empleados
    public ResultSet getListEmployee() {
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select empleados.idempleado, empleados.nombreempleado, empleados.apellidoempleado, empleados.foto, empleados.fechanac, empleados.domicilioemp, empleados.dui, empleados.nit, cargos.nombrecargo from empleados  join cargos on empleados.idcargo = cargos.idcargo");
            myRs = myPstat.executeQuery();
            
            return  myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    //Metodo para llenar la tabla empleados con busqueda
    public ResultSet getSearchtEmployee(String nombre) {
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("select empleados.idempleado, empleados.nombreempleado, empleados.apellidoempleado, empleados.foto, empleados.fechanac, empleados.domicilioemp, empleados.dui, empleados.nit, cargos.nombrecargo from empleados  join cargos on empleados.idcargo = cargos.idcargo where empleados.nombreempleado like '%"+ nombre +"%'");
            myRs = myPstat.executeQuery();
            
            return  myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void listaEmpleados(JComboBox jcbx){
        DefaultComboBoxModel jcbxModel;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        
        try{
            myConn.EstablecerConn();
            pStatement = myConn.con.prepareStatement("select idempleado, dui from empleados");
            rs = pStatement.executeQuery();
            jcbxModel = new DefaultComboBoxModel();
            
            jcbx.setModel(jcbxModel);
            
            while(rs.next()){
                jcbxModel.addElement(new Empleados(rs.getInt(1), rs.getString(2)));
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

    //Cerrando la conexion
    public void closeEmpleados() {
        myConn.CerrarConn();
    }
}
