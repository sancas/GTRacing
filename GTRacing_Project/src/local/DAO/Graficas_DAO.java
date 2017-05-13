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
 * @author Gustavo Hernandez
 */
public class Graficas_DAO {

    Conexion myConn = new Conexion();
    ResultSet myRs;
    PreparedStatement myPstat;

    public ResultSet getgraficaautomas() {
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("Select sum(a.cantidad) as suma, b.idauto as automovil, "
                    + "b.modeloauto as modelo from detallefactura AS a "
                    + "inner JOIN autos as b ON a.idauto = b.idauto "
                    + "where a.idauto IN (select a.idauto from autos) "
                    + "group by b.idauto order by suma desc LIMIT 5");
            myRs = myPstat.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Graficas_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getgraficaautomen() {
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("Select sum(a.cantidad) as suma , b.idauto as automovil, "
                    + "b.modeloauto as modelo from detallefactura AS a "
                    + "inner JOIN autos as b ON a.idauto = b.idauto "
                    + "where a.idauto IN (select a.idauto from autos) "
                    + "group by b.idauto order by suma asc LIMIT 5");
            myRs = myPstat.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Graficas_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getgraficarepuestomen() {
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("Select sum(a.cantidad) as suma , b.idrepuesto as respuesto, "
                    + "b.namerepuesto as repuesto from detallefactura AS a "
                    + "inner JOIN repuestos as b ON a.idrepuesto = b.idrepuesto "
                    + "where a.idrepuesto IN (select a.idrepuesto from repuestos) "
                    + "group by b.idrepuesto order by suma desc LIMIT 4");
            myRs = myPstat.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Graficas_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ResultSet getgraficarepuestomas() {
        try {
            myConn.EstablecerConn();
            myPstat = myConn.con.prepareStatement("Select sum(a.cantidad) as suma , b.idrepuesto as respuesto, "
                    + "b.namerepuesto as repuesto from detallefactura AS a "
                    + "inner JOIN repuestos as b ON a.idrepuesto = b.idrepuesto "
                    + "where a.idrepuesto IN (select a.idrepuesto from repuestos) "
                    + "group by b.idrepuesto order by suma asc LIMIT 4");
            myRs = myPstat.executeQuery();
            return myRs;
        } catch (SQLException ex) {
            Logger.getLogger(Graficas_DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void close() {
        myConn.CerrarConn();
    }
}
