/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.Conexion.Conexion;
import local.DAO.Empleados_DAO;

/**
 *
 * @author sanch
 */
public class Facturas_DAO {
    //Variable Globales
    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstat;
    int LastFacturaID;
    
    //Metedo para ingresar DetalleFactura
    public int addFactura(int idusuario, String cliente, double total) throws ParseException {
        int i;
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("INSERT INTO factura(idusuario, cliente, total) VALUES (?, ?, ?)");
            myPstat.setInt(1, idusuario);
            myPstat.setString(2, cliente);
            myPstat.setDouble(3, total);
            i = myPstat.executeUpdate();           
            myPstat.close();
            setLastFacturaID(idusuario, cliente, total);
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    //Metedo para ingresar DetalleFactura
    public int addDetalleFactura(int idfactura, int idrepuesto, int cantidad, double preciounitario, double subtotal) throws ParseException {
        int i;
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("INSERT INTO detallefactura(idfactura, idrepuesto, cantidad, preciounitario, subtotal) VALUES (?, ?, ?, ?, ?)");
            myPstat.setInt(1, idfactura);
            myPstat.setInt(2, idrepuesto);
            myPstat.setInt(3, cantidad);
            myPstat.setDouble(4, preciounitario);
            myPstat.setDouble(5, subtotal);

            i = myPstat.executeUpdate();
            if (i > 0)
                descontarCantidadRepuestos(idrepuesto, cantidad);
            myPstat.close();

            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    //Metodo para guardar el id de la factura creada
    public void setLastFacturaID(int idusuario, String cliente, double total)
    {
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("SELECT idfactura FROM factura WHERE idusuario = ? AND cliente = ? AND total = ?");
            myPstat.setInt(1, idusuario);
            myPstat.setString(2, cliente);
            myPstat.setDouble(3, total);
            myRs = myPstat.executeQuery();
            if (myRs.next())
                LastFacturaID = myRs.getInt("idfactura");
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Metodo para obtener el id de la factura creada
    public int getLastFacturaID()
    {
        return LastFacturaID;
    }
    
    public int descontarCantidadRepuestos(int idrepuesto, int cantidad)
    {
        int i;
        int nuevaCantidad;
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("SELECT cantidad FROM existenciarepuestos WHERE idrepuesto = ?");
            myPstat.setInt(1, idrepuesto);
            myRs = myPstat.executeQuery();
            if (myRs.next())
            {
                nuevaCantidad = myRs.getInt("cantidad") - cantidad;
                myPstat = myConn.con.prepareStatement("UPDATE existenciarepuestos SET cantidad=? WHERE idrepuesto=?");
                myPstat.setInt(1, nuevaCantidad);
                myPstat.setInt(2, idrepuesto);
                i = myPstat.executeUpdate();

                myPstat.close();

                return i;
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(Empleados_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
