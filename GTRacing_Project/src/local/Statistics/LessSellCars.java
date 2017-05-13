package local.Statistics;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class LessSellCars {

    /**
     * @param args the command line arguments
     */
    public static void carrosMenosVendidos() {
        Graficas_DAO gr = new Graficas_DAO();
        ResultSet rs = gr.getgraficaautomas();
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
                "GRAFICAS AUTOS MENOS VENDIDOS",
                data,
                true,
                true,
                false);
            ChartFrame frame = new ChartFrame("Autos menos vendidos", chart);
            frame.pack();
            frame.setVisible(true);
            rs.close();
            gr.close();
        } catch (SQLException ex) {
            Logger.getLogger(LessSellCars.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}