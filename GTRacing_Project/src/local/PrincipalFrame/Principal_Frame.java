/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.PrincipalFrame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import local.DAO.Empleados_DAO;
import local.DAO.LoginAccess_DAO;
import local.DAO.Roles_DAO;
import local.Empleados.Cargos;
import local.Empleados.Empleados;
import local.Inventario.Auto_Existencia;
import local.Inventario.Autos_Add;
import local.Inventario.Marcas;
import local.Inventario.Proveedores;
import local.Inventario.Repuesto_Add;
import local.Inventario.Repuesto_Existencia;
import local.Pool_Variable.Variables;
import local.Statistics.LessSellCars;
import local.Statistics.LessSellRepuesto;
import local.Statistics.MostSellCars;
import local.Statistics.MostSellRepuesto;
import local.Usuarios.Roles;
import local.Usuarios.Usuarios;
import local.Ventas.Ventas;

/**
 *
 * @author Leonel
 */
public class Principal_Frame extends javax.swing.JFrame {

    //Variables Globales
    ResultSet rs;
    
    /**
     * Creates new form Principal_Frame
     */
    public Principal_Frame() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        jmnVetas.setVisible(false);
        jmnEstatictis.setVisible(false);
        loadSubMenu();
        
        //Variables de inicio
        String Nombres = "";
        LoginAccess_DAO logDao = new LoginAccess_DAO();
        DateFormat dForm = DateFormat.getDateInstance();
        Date fechaActual = new Date();
        
        rs = logDao.getNames(Variables.getCurrentEmploy());
        
        try {
            if(rs.next()){
                Nombres = rs.getString(1);
                Nombres += " " + rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            logDao.closeLogin();
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Principal_Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Inicializando la fecha y el nombre del empleado actual
        jlblEmployName.setText(Nombres);
        jlblDate.setText(dForm.format(fechaActual));
        loadImage();
    }
    
    private void loadSubMenu(){
        Roles_DAO rolDao = new Roles_DAO();
        rs = rolDao.listaTareas();
        int tarea;
        
        try {
            while(rs.next()){
                tarea = rs.getInt(1);
                if(tarea > 0 && tarea < 4){
                    jmiCargo.setEnabled(true);
                } else if(tarea > 3 && tarea < 7){
                    jmiEmpleado.setEnabled(true);
                } else if(tarea > 6 && tarea < 10){
                    jmiRoles.setEnabled(true);
                } else if(tarea > 9 && tarea < 13){
                    jmiUsuario.setEnabled(true);
                } else if(tarea > 12 && tarea< 16){
                    jmiProveedores.setEnabled(true);
                } else if(tarea > 15 && tarea < 19){
                    jmiNewRep.setEnabled(true);
                    jmiExistenciaR.setEnabled(true);
                } else if(tarea > 18 && tarea < 22){
                    jmiNewAuto.setEnabled(true);
                    jmiExistenciaA.setEnabled(true);
                    jmiMarcas.setEnabled(true);
                } else if(tarea == 22) {
                    jmnVetas.setVisible(true);
                } else if(tarea == 23) {
                    jmnEstatictis.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadImage(){
        Empleados_DAO empDao = new Empleados_DAO();
        rs = empDao.loadImage(local.Pool_Variable.Variables.getCurrentEmploy());
        InputStream is;
        ImageIcon foto;

        try {
            if (rs.next()) {
                is = rs.getBinaryStream(1);
                if (is != null)
                {
                    BufferedImage bf = ImageIO.read(is);
                    foto = new ImageIcon(bf);

                    Image img = foto.getImage();
                    Image newImg = img.getScaledInstance(jlblUserImage.getWidth(), jlblUserImage.getHeight(), Image.SCALE_DEFAULT);

                    ImageIcon newIcon = new ImageIcon(newImg);

                    jlblUserImage.setIcon(newIcon);
                }
            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlDatosUsuario = new javax.swing.JPanel();
        jlblEmployName = new javax.swing.JLabel();
        jlblDate = new javax.swing.JLabel();
        jlblUserImage = new javax.swing.JLabel();
        jdpContenedor = new javax.swing.JDesktopPane();
        MenuBar = new javax.swing.JMenuBar();
        jmnEmpleado = new javax.swing.JMenu();
        jmiCargo = new javax.swing.JMenuItem();
        jmiEmpleado = new javax.swing.JMenuItem();
        jmnUsuario = new javax.swing.JMenu();
        jmiRoles = new javax.swing.JMenuItem();
        jmiUsuario = new javax.swing.JMenuItem();
        jmnInventario = new javax.swing.JMenu();
        jmiProveedores = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jmiMarcas = new javax.swing.JMenuItem();
        jmiNewAuto = new javax.swing.JMenuItem();
        jmiExistenciaA = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmiNewRep = new javax.swing.JMenuItem();
        jmiExistenciaR = new javax.swing.JMenuItem();
        jmnVetas = new javax.swing.JMenu();
        jmiVentas = new javax.swing.JMenuItem();
        jmnEstatictis = new javax.swing.JMenu();
        jMenuEstadisticasAutos = new javax.swing.JMenu();
        jMenuItemMostSellCars = new javax.swing.JMenuItem();
        jMenuItemLessSellCars = new javax.swing.JMenuItem();
        jMenuEstadisticasRepuestos = new javax.swing.JMenu();
        jMenuItemMostSellRepuestos = new javax.swing.JMenuItem();
        jMenuItemLessSellRepuestos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlblEmployName.setText("jLabel1");

        jlblDate.setText("jLabel2");

        jlblUserImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jpnlDatosUsuarioLayout = new javax.swing.GroupLayout(jpnlDatosUsuario);
        jpnlDatosUsuario.setLayout(jpnlDatosUsuarioLayout);
        jpnlDatosUsuarioLayout.setHorizontalGroup(
            jpnlDatosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlDatosUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnlDatosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblDate)
                    .addComponent(jlblEmployName)
                    .addComponent(jlblUserImage, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jpnlDatosUsuarioLayout.setVerticalGroup(
            jpnlDatosUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnlDatosUsuarioLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jlblUserImage, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jlblEmployName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlblDate)
                .addContainerGap(393, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdpContenedorLayout = new javax.swing.GroupLayout(jdpContenedor);
        jdpContenedor.setLayout(jdpContenedorLayout);
        jdpContenedorLayout.setHorizontalGroup(
            jdpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );
        jdpContenedorLayout.setVerticalGroup(
            jdpContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        MenuBar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jmnEmpleado.setText("Empleados");

        jmiCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/Cargos.png"))); // NOI18N
        jmiCargo.setText("Cargos");
        jmiCargo.setEnabled(false);
        jmiCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCargoActionPerformed(evt);
            }
        });
        jmnEmpleado.add(jmiCargo);

        jmiEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/employee.png"))); // NOI18N
        jmiEmpleado.setText("Empleados");
        jmiEmpleado.setEnabled(false);
        jmiEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEmpleadoActionPerformed(evt);
            }
        });
        jmnEmpleado.add(jmiEmpleado);

        MenuBar.add(jmnEmpleado);

        jmnUsuario.setText("Usuarios");

        jmiRoles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/roles.png"))); // NOI18N
        jmiRoles.setText("Roles");
        jmiRoles.setEnabled(false);
        jmiRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRolesActionPerformed(evt);
            }
        });
        jmnUsuario.add(jmiRoles);

        jmiUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/user.png"))); // NOI18N
        jmiUsuario.setText("Usuario");
        jmiUsuario.setEnabled(false);
        jmiUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiUsuarioActionPerformed(evt);
            }
        });
        jmnUsuario.add(jmiUsuario);

        MenuBar.add(jmnUsuario);

        jmnInventario.setText("Inventario");

        jmiProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/package.png"))); // NOI18N
        jmiProveedores.setText("Proveedores");
        jmiProveedores.setEnabled(false);
        jmiProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiProveedoresActionPerformed(evt);
            }
        });
        jmnInventario.add(jmiProveedores);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/auto.png"))); // NOI18N
        jMenu1.setText("Autos");

        jmiMarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/marcas.png"))); // NOI18N
        jmiMarcas.setText("Marcas");
        jmiMarcas.setEnabled(false);
        jmiMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMarcasActionPerformed(evt);
            }
        });
        jMenu1.add(jmiMarcas);

        jmiNewAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/new.png"))); // NOI18N
        jmiNewAuto.setText("Nuevo Auto");
        jmiNewAuto.setEnabled(false);
        jmiNewAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNewAutoActionPerformed(evt);
            }
        });
        jMenu1.add(jmiNewAuto);

        jmiExistenciaA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/add.png"))); // NOI18N
        jmiExistenciaA.setText("Agregar Existencia");
        jmiExistenciaA.setEnabled(false);
        jmiExistenciaA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExistenciaAActionPerformed(evt);
            }
        });
        jMenu1.add(jmiExistenciaA);

        jmnInventario.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/repuesto.png"))); // NOI18N
        jMenu2.setText("Repuestos");

        jmiNewRep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/new.png"))); // NOI18N
        jmiNewRep.setText("Nuevo Repuesto");
        jmiNewRep.setEnabled(false);
        jmiNewRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNewRepActionPerformed(evt);
            }
        });
        jMenu2.add(jmiNewRep);

        jmiExistenciaR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/add.png"))); // NOI18N
        jmiExistenciaR.setText("Agregar Existencia");
        jmiExistenciaR.setEnabled(false);
        jmiExistenciaR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExistenciaRActionPerformed(evt);
            }
        });
        jMenu2.add(jmiExistenciaR);

        jmnInventario.add(jMenu2);

        MenuBar.add(jmnInventario);

        jmnVetas.setText("Ventas");

        jmiVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/local/Images/ventas.png"))); // NOI18N
        jmiVentas.setText("Ventas");
        jmiVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiVentasActionPerformed(evt);
            }
        });
        jmnVetas.add(jmiVentas);

        MenuBar.add(jmnVetas);

        jmnEstatictis.setText("Estadisticas");

        jMenuEstadisticasAutos.setText("Autos");

        jMenuItemMostSellCars.setText("Mas vendidos");
        jMenuItemMostSellCars.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMostSellCarsActionPerformed(evt);
            }
        });
        jMenuEstadisticasAutos.add(jMenuItemMostSellCars);

        jMenuItemLessSellCars.setText("Menos vendidos");
        jMenuItemLessSellCars.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLessSellCarsActionPerformed(evt);
            }
        });
        jMenuEstadisticasAutos.add(jMenuItemLessSellCars);

        jmnEstatictis.add(jMenuEstadisticasAutos);

        jMenuEstadisticasRepuestos.setText("Repuestos");

        jMenuItemMostSellRepuestos.setText("Mas Vendidos");
        jMenuItemMostSellRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMostSellRepuestosActionPerformed(evt);
            }
        });
        jMenuEstadisticasRepuestos.add(jMenuItemMostSellRepuestos);

        jMenuItemLessSellRepuestos.setText("Menos Vendidos");
        jMenuItemLessSellRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLessSellRepuestosActionPerformed(evt);
            }
        });
        jMenuEstadisticasRepuestos.add(jMenuItemLessSellRepuestos);

        jmnEstatictis.add(jMenuEstadisticasRepuestos);

        MenuBar.add(jmnEstatictis);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnlDatosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdpContenedor))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlDatosUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jdpContenedor)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCargoActionPerformed
        Cargos crg = new Cargos();
        jdpContenedor.add(crg);
        jdpContenedor.moveToFront(crg);
    }//GEN-LAST:event_jmiCargoActionPerformed

    private void jmiEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEmpleadoActionPerformed
        Empleados em = new Empleados();
        jdpContenedor.add(em);
        jdpContenedor.moveToFront(em);
    }//GEN-LAST:event_jmiEmpleadoActionPerformed

    private void jmiRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRolesActionPerformed
        Roles rl = new Roles();
        jdpContenedor.add(rl);
        jdpContenedor.moveToFront(rl);
    }//GEN-LAST:event_jmiRolesActionPerformed

    private void jmiUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiUsuarioActionPerformed
        Usuarios us = new Usuarios();
        jdpContenedor.add(us);
        jdpContenedor.moveToFront(us);
    }//GEN-LAST:event_jmiUsuarioActionPerformed

    private void jmiProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiProveedoresActionPerformed
        Proveedores pr = new Proveedores();
        jdpContenedor.add(pr);
        jdpContenedor.moveToFront(pr);
    }//GEN-LAST:event_jmiProveedoresActionPerformed

    private void jmiNewRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNewRepActionPerformed
        Repuesto_Add read = new Repuesto_Add();
        jdpContenedor.add(read);
        jdpContenedor.moveToFront(read);
    }//GEN-LAST:event_jmiNewRepActionPerformed

    private void jmiExistenciaRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExistenciaRActionPerformed
        Repuesto_Existencia re = new Repuesto_Existencia();
        jdpContenedor.add(re);
        jdpContenedor.moveToFront(re);
    }//GEN-LAST:event_jmiExistenciaRActionPerformed

    private void jmiMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMarcasActionPerformed
        Marcas ma = new Marcas();
        jdpContenedor.add(ma);
        jdpContenedor.moveToFront(ma);
    }//GEN-LAST:event_jmiMarcasActionPerformed

    private void jmiNewAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNewAutoActionPerformed
        Autos_Add aa = new Autos_Add();
        jdpContenedor.add(aa);
        jdpContenedor.moveToFront(aa);
    }//GEN-LAST:event_jmiNewAutoActionPerformed

    private void jmiExistenciaAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExistenciaAActionPerformed
        Auto_Existencia ae = new Auto_Existencia();
        jdpContenedor.add(ae);
        jdpContenedor.moveToFront(ae);
    }//GEN-LAST:event_jmiExistenciaAActionPerformed

    private void jmiVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiVentasActionPerformed
        // TODO add your handling code here:
        Ventas ve = new Ventas();
        jdpContenedor.add(ve);
        jdpContenedor.moveToFront(ve);
    }//GEN-LAST:event_jmiVentasActionPerformed

    private void jMenuItemMostSellRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMostSellRepuestosActionPerformed
        // TODO add your handling code here:
        MostSellRepuesto.repuestosMasVendidos();
    }//GEN-LAST:event_jMenuItemMostSellRepuestosActionPerformed

    private void jMenuItemLessSellCarsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLessSellCarsActionPerformed
        // TODO add your handling code here:
        LessSellCars.carrosMenosVendidos();
    }//GEN-LAST:event_jMenuItemLessSellCarsActionPerformed

    private void jMenuItemMostSellCarsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMostSellCarsActionPerformed
        // TODO add your handling code here:
        MostSellCars.carrosMasVendidos();
    }//GEN-LAST:event_jMenuItemMostSellCarsActionPerformed

    private void jMenuItemLessSellRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLessSellRepuestosActionPerformed
        // TODO add your handling code here:
        LessSellRepuesto.repuestosMenosVendidos();
    }//GEN-LAST:event_jMenuItemLessSellRepuestosActionPerformed

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
            java.util.logging.Logger.getLogger(Principal_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenuEstadisticasAutos;
    private javax.swing.JMenu jMenuEstadisticasRepuestos;
    private javax.swing.JMenuItem jMenuItemLessSellCars;
    private javax.swing.JMenuItem jMenuItemLessSellRepuestos;
    private javax.swing.JMenuItem jMenuItemMostSellCars;
    private javax.swing.JMenuItem jMenuItemMostSellRepuestos;
    public static javax.swing.JDesktopPane jdpContenedor;
    private javax.swing.JLabel jlblDate;
    private javax.swing.JLabel jlblEmployName;
    private javax.swing.JLabel jlblUserImage;
    private javax.swing.JMenuItem jmiCargo;
    private javax.swing.JMenuItem jmiEmpleado;
    private javax.swing.JMenuItem jmiExistenciaA;
    private javax.swing.JMenuItem jmiExistenciaR;
    private javax.swing.JMenuItem jmiMarcas;
    private javax.swing.JMenuItem jmiNewAuto;
    private javax.swing.JMenuItem jmiNewRep;
    private javax.swing.JMenuItem jmiProveedores;
    private javax.swing.JMenuItem jmiRoles;
    private javax.swing.JMenuItem jmiUsuario;
    private javax.swing.JMenuItem jmiVentas;
    private javax.swing.JMenu jmnEmpleado;
    private javax.swing.JMenu jmnEstatictis;
    private javax.swing.JMenu jmnInventario;
    private javax.swing.JMenu jmnUsuario;
    private javax.swing.JMenu jmnVetas;
    private javax.swing.JPanel jpnlDatosUsuario;
    // End of variables declaration//GEN-END:variables
}
