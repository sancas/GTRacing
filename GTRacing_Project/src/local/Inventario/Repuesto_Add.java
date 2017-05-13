/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.Inventario;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import local.DAO.Repuestos_DAO;
import local.DAO.Roles_DAO;
import local.Empleados.Empleados;
import local.PrincipalFrame.Principal_Frame;

/**
 *
 * @author Leonel
 */
public class Repuesto_Add extends javax.swing.JInternalFrame {

    DefaultTableModel modeloRepuesto;
    Repuestos_DAO resDao = new Repuestos_DAO();
    FileInputStream file = null;
    InputStream fotoFile = null;
    int idRepuesto;
    int bite;
    boolean isPhotoChange;
    
    public Repuesto_Add() {
        //Cargando los datos de la tabla
        modeloRepuesto = new DefaultTableModel(null, getColums());
        getRows();
        
        //Iniciando los componentes del formulario
        initComponents();
        setVisible(true);
        
        //Acciones por rol
        accionRol();
        
        //Ocultar columnas
        jtblRepuestos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblRepuestos.getColumnModel().getColumn(0).setMinWidth(0);
        jtblRepuestos.getColumnModel().getColumn(0).setPreferredWidth(0);
        jtblRepuestos.getColumnModel().getColumn(4).setMaxWidth(0);
        jtblRepuestos.getColumnModel().getColumn(4).setMinWidth(0);
        jtblRepuestos.getColumnModel().getColumn(4).setPreferredWidth(0);

        //Centrando el formulario
        int wdith = Principal_Frame.jdpContenedor.getWidth() - this.getWidth();
        int hght = Principal_Frame.jdpContenedor.getHeight() - this.getHeight();
        this.setLocation(wdith / 2, hght / 2);
        
        this.jlblFoto.setHorizontalAlignment(JLabel.CENTER);
    }
    
    private String[] getColums(){
        String[] columnas = new String[]{"Id","Repuesto", "Estado", "Descripcion", "Foto", "Usuario"};
        return columnas;
    }
    
    private void getRows(){
        ResultSet rs = resDao.getListRepuestos();
        
        try{
            Object datos[] = new Object[6];
            
            while(rs.next()){
                for (int i = 0; i < datos.length; i++) {
                    datos[i] = rs.getObject(i+1);
                    if(i == 2){
                        if(rs.getInt(i+1) == 1)
                            datos[i] = "Activo";
                        else
                            datos[i] = "Inactivo";
                    }
                }
                modeloRepuesto.addRow(datos);
            }
            
            rs.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al cargar datos.");
            Logger.getLogger(Repuesto_Add.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadImage() throws IOException {
        ResultSet rs = resDao.loadImage(idRepuesto);
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
                    Image newImg = img.getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(), Image.SCALE_DEFAULT);

                    ImageIcon newIcon = new ImageIcon(newImg);

                    jlblFoto.setIcon(newIcon);
                }
            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void limpieza(){
        jtfDescripcion.setText("");
        jtfRepuesto.setText("");
        jlblFoto.setIcon(null);
        jbtgEstado.clearSelection();
        
        for (int i = 0; i < jtblRepuestos.getRowCount(); i++) {
            modeloRepuesto.removeRow(i);
            i -= 1;
        }
    }
    
    //metodo para verificar los permisos para el formulario cargos
    private void accionRol(){
        Roles_DAO rolDao = new Roles_DAO();
        ResultSet rs = rolDao.listaTareas();
        int acciones;
        
        try {
            while(rs.next()){
                acciones = rs.getInt(1);
                switch(acciones){
                    case 16:
                        jrbtnAdd.setEnabled(true);
                        break;
                        
                    case 17:
                        jrbtnUpdate.setEnabled(true);
                        break;
                        
                    case 18:
                        jrbtnDelete.setEnabled(true);
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repuesto_Add.class.getName()).log(Level.SEVERE, null, ex);
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

        jbtgAcciones = new javax.swing.ButtonGroup();
        jbtgEstado = new javax.swing.ButtonGroup();
        jPanelAcciones = new javax.swing.JPanel();
        jrbtnAdd = new javax.swing.JRadioButton();
        jrbtnUpdate = new javax.swing.JRadioButton();
        jrbtnDelete = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblRepuestos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfRepuesto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtfDescripcion = new javax.swing.JTextArea();
        jrbtActivo = new javax.swing.JRadioButton();
        jrbtInactivo = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jlblFoto = new javax.swing.JLabel();
        jbtnFoto = new javax.swing.JButton();
        jbtnAceptar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Repuestos");
        setEnabled(false);
        setMinimumSize(new java.awt.Dimension(500, 600));
        setPreferredSize(new java.awt.Dimension(600, 800));

        jPanelAcciones.setBackground(new java.awt.Color(80, 81, 79));

        jrbtnAdd.setBackground(new java.awt.Color(80, 81, 79));
        jrbtnAdd.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jrbtnAdd.setForeground(new java.awt.Color(242, 95, 92));
        jrbtnAdd.setText("Agregar");
        jrbtnAdd.setEnabled(false);

        jrbtnUpdate.setBackground(new java.awt.Color(80, 81, 79));
        jrbtnUpdate.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jrbtnUpdate.setForeground(new java.awt.Color(242, 95, 92));
        jrbtnUpdate.setText("Editar");
        jrbtnUpdate.setEnabled(false);

        jrbtnDelete.setBackground(new java.awt.Color(80, 81, 79));
        jrbtnDelete.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jrbtnDelete.setForeground(new java.awt.Color(242, 95, 92));
        jrbtnDelete.setText("Eliminar");
        jrbtnDelete.setEnabled(false);

        javax.swing.GroupLayout jPanelAccionesLayout = new javax.swing.GroupLayout(jPanelAcciones);
        jPanelAcciones.setLayout(jPanelAccionesLayout);
        jPanelAccionesLayout.setHorizontalGroup(
            jPanelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrbtnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbtnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbtnDelete)
                .addContainerGap())
        );
        jPanelAccionesLayout.setVerticalGroup(
            jPanelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbtnAdd)
                    .addComponent(jrbtnDelete)
                    .addComponent(jrbtnUpdate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Buscar:");

        jTextField1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtblRepuestos.setModel(modeloRepuesto);
        jtblRepuestos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblRepuestosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblRepuestos);

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Repuesto:");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("Descripcion:");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel5.setText("Estado:");

        jtfRepuesto.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtfDescripcion.setColumns(20);
        jtfDescripcion.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jtfDescripcion.setRows(3);
        jScrollPane2.setViewportView(jtfDescripcion);

        jbtgEstado.add(jrbtActivo);
        jrbtActivo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtActivo.setText("Activo");

        jbtgEstado.add(jrbtInactivo);
        jrbtInactivo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtInactivo.setText("Inactivo");

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Foto:");

        jlblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jbtnFoto.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jbtnFoto.setText("Foto");
        jbtnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFotoActionPerformed(evt);
            }
        });

        jbtnAceptar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jbtnAceptar.setText("Aceptar");
        jbtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jrbtActivo)
                                        .addGap(18, 18, 18)
                                        .addComponent(jrbtInactivo)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jlblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jbtnFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jtfRepuesto))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnFoto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jrbtActivo)
                    .addComponent(jrbtInactivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnAceptar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFotoActionPerformed
        jlblFoto.setIcon(null);
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = j.showOpenDialog(null);

        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                file = new FileInputStream(j.getSelectedFile());
                bite = (int) j.getSelectedFile().length();

                try {
                    Image icono = ImageIO.read(j.getSelectedFile()).getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(), Image.SCALE_DEFAULT);
                    jlblFoto.setIcon(new ImageIcon(icono));
                    jlblFoto.updateUI();
                    if (jrbtnUpdate.isSelected()) {
                        isPhotoChange = true;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnFotoActionPerformed

    private void jbtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAceptarActionPerformed
        if(!jtfRepuesto.getText().isEmpty() && !jtfDescripcion.getText().isEmpty()){
            if(jrbtActivo.isSelected() || jrbtInactivo.isSelected()){
                if(jrbtnAdd.isSelected() && bite > 0){
                    int i, estado;
                    
                    //Verificando si el estado es activo o inactivo
                    if(jrbtActivo.isSelected())
                        estado = 1;
                    else
                        estado = 0;
                    
                    i = resDao.addRepuestos(jtfRepuesto.getText(), estado, jtfDescripcion.getText(), file, bite);
                    
                    if(i > 0){
                        limpieza();
                        getRows();
                    } else
                        JOptionPane.showMessageDialog(null, "No se puedo agregar el repuesto");
                } else if(jrbtnUpdate.isSelected()){
                    int i, estado;
                    
                    //Verificando si el estado es activo o inactivo
                    if(jrbtActivo.isSelected())
                        estado = 1;
                    else
                        estado = 0;
                    
                    //verificamos si se ha cambiado la foto del repuesto
                    if(isPhotoChange)
                        i = resDao.updateRepuestosPhoto(jtfRepuesto.getText(), estado, jtfDescripcion.getText(), file, bite, idRepuesto);
                    else
                        i = resDao.updateRepuestos(jtfRepuesto.getText(), estado, jtfDescripcion.getText(), idRepuesto);
                    
                    if(i > 0){
                        limpieza();
                        getRows();
                    } else
                        JOptionPane.showMessageDialog(null, "No se puede actualizar el repuesto");
                    
                    
                } else if(jrbtnDelete.isSelected()){
                    int i;
                    
                    i = resDao.deleteRepuestos(idRepuesto);
                    
                    if(i > 0){
                        limpieza();
                        getRows();
                    } else
                        JOptionPane.showMessageDialog(null, "No se puede eliminar el repuesto");
                }
                
                isPhotoChange = false;
                bite = 0;
            } else //fin de verificacion del estado
                JOptionPane.showMessageDialog(null, "Selecciona si el estado es activo o inactivo");
        } else //fin de validacion de campos
            JOptionPane.showMessageDialog(null, "No dejes campos vacios");
    }//GEN-LAST:event_jbtnAceptarActionPerformed

    private void jtblRepuestosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblRepuestosMouseClicked
        if(jrbtnDelete.isSelected() || jrbtnUpdate.isSelected()){
            idRepuesto = (int) modeloRepuesto.getValueAt(jtblRepuestos.getSelectedRow(), 0);
            jtfRepuesto.setText((String) modeloRepuesto.getValueAt(jtblRepuestos.getSelectedRow(), 1));
            String es = (String) modeloRepuesto.getValueAt(jtblRepuestos.getSelectedRow(), 2);
            jtfDescripcion.setText((String) modeloRepuesto.getValueAt(jtblRepuestos.getSelectedRow(), 3));
            try {
                loadImage();
            } catch (IOException ex) {
                Logger.getLogger(Repuesto_Add.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(es.equals("Activo"))
                jrbtActivo.setSelected(true);
            else
                jrbtInactivo.setSelected(true);
        }
    }//GEN-LAST:event_jtblRepuestosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.ButtonGroup jbtgAcciones;
    private javax.swing.ButtonGroup jbtgEstado;
    private javax.swing.JButton jbtnAceptar;
    private javax.swing.JButton jbtnFoto;
    private javax.swing.JLabel jlblFoto;
    private javax.swing.JRadioButton jrbtActivo;
    private javax.swing.JRadioButton jrbtInactivo;
    private javax.swing.JRadioButton jrbtnAdd;
    private javax.swing.JRadioButton jrbtnDelete;
    private javax.swing.JRadioButton jrbtnUpdate;
    private javax.swing.JTable jtblRepuestos;
    private javax.swing.JTextArea jtfDescripcion;
    private javax.swing.JTextField jtfRepuesto;
    // End of variables declaration//GEN-END:variables
}
