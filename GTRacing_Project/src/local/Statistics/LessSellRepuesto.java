/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.Statistics;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.DAO.Graficas_DAO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Gustavo Hernandez
 */
public class LessSellRepuesto {

    /**
     * @param args the command line arguments
     */
    public static void repuestosMenosVendidos() {
        Graficas_DAO gr = new Graficas_DAO();
        //obtencion de datos
        ResultSet rs = gr.getgraficarepuestomen();
        try {
            String nameAuto;
            int quantitySales;
            DefaultPieDataset data = new DefaultPieDataset();
            while (rs.next()) {
                nameAuto = rs.getString(3);
                quantitySales = rs.getInt(1);
                data.setValue(nameAuto, quantitySales);
            }
            JFreeChart chart = ChartFactory.createPieChart(
                    "GRAFICAS REPUESTO MENOS VENDIDOS",
                    data,
                    true,
                    true,
                    false);
            ChartFrame frame = new ChartFrame("Repuestos menos vendidos", chart);
            frame.pack();
            frame.setVisible(true);
            rs.close();
            gr.close();
        } catch (SQLException ex) {
            Logger.getLogger(LessSellRepuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
