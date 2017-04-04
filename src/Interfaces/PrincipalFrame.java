/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaces;
import Conexion.Conexion;
import Inventario.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LeonIV
 */
public class PrincipalFrame extends javax.swing.JFrame {

    ResultSet myResult;
    Conexion con;
    
    public PrincipalFrame() {
        String Name = "";
        DateFormat dform = DateFormat.getDateInstance();
        Date fechaActual = new Date();
        
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
        this.setLocationRelativeTo(null);
        loadResultSet();
        try {
            if(myResult.next()){
                Name = myResult.getString(1);
                Name += " " + myResult.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jlbNombre.setText(Name);
        jlbDate.setText(dform.format(fechaActual));
    }

    private void loadResultSet(){
        con = new Conexion();
        myResult = con.getUserName();
        con.CerrarConn();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jlbNombre = new javax.swing.JLabel();
        jlbDate = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmnEmpleados = new javax.swing.JMenu();
        jmiAddEmple = new javax.swing.JMenuItem();
        jmnUsuarios = new javax.swing.JMenu();
        jmnRepuestos = new javax.swing.JMenu();
        jmiOpen = new javax.swing.JMenuItem();
        jmnAutos = new javax.swing.JMenu();
        jmnVentas = new javax.swing.JMenu();
        jmniOpenSells = new javax.swing.JMenuItem();
        jmnGraficas = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Usuarios.png"))); // NOI18N

        jlbNombre.setText("Name");

        jlbDate.setText("date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jlbNombre))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jlbDate)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jlbNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbDate)
                .addContainerGap(372, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 698, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jmnEmpleados.setText("Empleados");

        jmiAddEmple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jmiAddEmple.setText("Agregar");
        jmnEmpleados.add(jmiAddEmple);

        jMenuBar1.add(jmnEmpleados);

        jmnUsuarios.setText("Usuarios");
        jMenuBar1.add(jmnUsuarios);

        jmnRepuestos.setText("Repuestos");

        jmiOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/show.png"))); // NOI18N
        jmiOpen.setText("Abrir");
        jmiOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiOpenActionPerformed(evt);
            }
        });
        jmnRepuestos.add(jmiOpen);

        jMenuBar1.add(jmnRepuestos);

        jmnAutos.setText("Autos");
        jMenuBar1.add(jmnAutos);

        jmnVentas.setText("Ventas");

        jmniOpenSells.setText("jMenuItem1");
        jmniOpenSells.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmniOpenSellsActionPerformed(evt);
            }
        });
        jmnVentas.add(jmniOpenSells);

        jMenuBar1.add(jmnVentas);

        jmnGraficas.setText("Graficos");
        jMenuBar1.add(jmnGraficas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiOpenActionPerformed
        AddRepuestos ad = new AddRepuestos();
        jDesktopPane1.add(ad);
        jDesktopPane1.moveToFront(ad);
    }//GEN-LAST:event_jmiOpenActionPerformed

    private void jmniOpenSellsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmniOpenSellsActionPerformed
        // TODO add your handling code here:
        Ventas ventas = new Ventas();
        jDesktopPane1.add(ventas);
        jDesktopPane1.moveToFront(ventas);
    }//GEN-LAST:event_jmniOpenSellsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlbDate;
    private javax.swing.JLabel jlbNombre;
    private javax.swing.JMenuItem jmiAddEmple;
    private javax.swing.JMenuItem jmiOpen;
    private javax.swing.JMenu jmnAutos;
    private javax.swing.JMenu jmnEmpleados;
    private javax.swing.JMenu jmnGraficas;
    private javax.swing.JMenu jmnRepuestos;
    private javax.swing.JMenu jmnUsuarios;
    private javax.swing.JMenu jmnVentas;
    private javax.swing.JMenuItem jmniOpenSells;
    // End of variables declaration//GEN-END:variables
}
