/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.Inventario;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import local.DAO.Autos_DAO;
import local.DAO.Marcas_DAO;
import local.DAO.Roles_DAO;
import local.Empleados.Empleados;
import local.LC.Marcas_LC;
import local.PrincipalFrame.Principal_Frame;

/**
 *
 * @author Leonel
 */
public class Autos_Add extends javax.swing.JInternalFrame {

    //Varibales globales
    DefaultTableModel modeloAutos;
    Marcas_DAO marDao = new Marcas_DAO();
    Autos_DAO autDao = new Autos_DAO();
    FileInputStream file = null;
    InputStream fotoFile = null;
    int bite = 0;
    boolean isPhotoChange = false;
    int idAuto;
    
    public Autos_Add() {
        //Cargando los datos de la tabla
        modeloAutos = new DefaultTableModel(null, getColums());
        getRows();

        //Iniciando los componentes del formulario
        initComponents();
        setVisible(true);
        
        //Acciones por rol
        accionRol();

        //Carrgado datos
        marDao.listaMarcas(jcbxMarca);
        SNumero(jtfAnyo);

        //Ocultar columnas
        jtblAutos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblAutos.getColumnModel().getColumn(0).setMinWidth(0);
        jtblAutos.getColumnModel().getColumn(0).setPreferredWidth(0);

        //Centrando el formulario
        int wdith = Principal_Frame.jdpContenedor.getWidth() - this.getWidth();
        int hght = Principal_Frame.jdpContenedor.getHeight() - this.getHeight();
        this.setLocation(wdith / 2, hght / 2);
    }
    
    private String[] getColums(){
        String[] columnas = new String[]{"Id","Marca", "Modelo", "Año", "Agregado por", "Usuario"};
        return columnas;
    }
    
    private void getRows(){
        ResultSet rs = autDao.getListAutos();
        
        try{
            Object datos[] = new Object[6];
            
            while(rs.next()){
                for (int i = 0; i < datos.length; i++) {
                    datos[i] = rs.getObject(i+1);
                }        
                modeloAutos.addRow(datos);
            }
            
            rs.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al cargar datos.");
            Logger.getLogger(Autos_Add.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SNumero(JTextField t){
        t.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(Character.isLetter(c)){
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }
    
    private void loadImage() throws IOException {
        ResultSet rs = autDao.loadImage(idAuto);
        InputStream is;
        ImageIcon foto;

        try {
            if (rs.next()) {
                is = rs.getBinaryStream(1);

                BufferedImage bf = ImageIO.read(is);
                foto = new ImageIcon(bf);

                Image img = foto.getImage();
                Image newImg = img.getScaledInstance(jlblFoto.getWidth(), jlblFoto.getHeight(), Image.SCALE_DEFAULT);

                ImageIcon newIcon = new ImageIcon(newImg);

                jlblFoto.setIcon(newIcon);
            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Limpiar(){
        jtfAnyo.setText("");
        jtfModelo.setText("");
        jcbxMarca.setSelectedIndex(0);
        jlblFoto.setIcon(null);
        
        for (int i = 0; i < jtblAutos.getRowCount(); i++) {
            modeloAutos.removeRow(i);
            i-=1;
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
                    case 19:
                        jrbtnAdd.setEnabled(true);
                        break;
                        
                    case 20:
                        jrbtnUpdate.setEnabled(true);
                        break;
                        
                    case 21:
                        jrbtnDelete.setEnabled(true);
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autos_Add.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jrbtnAdd = new javax.swing.JRadioButton();
        jrbtnUpdate = new javax.swing.JRadioButton();
        jrbtnDelete = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblAutos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcbxMarca = new javax.swing.JComboBox<>();
        jtfModelo = new javax.swing.JTextField();
        jtfAnyo = new javax.swing.JTextField();
        jbtnAceptar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jlblFoto = new javax.swing.JLabel();
        jbtnFoto = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel1.setText("Acciones");

        jbtgAcciones.add(jrbtnAdd);
        jrbtnAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtnAdd.setText("Agregar");
        jrbtnAdd.setEnabled(false);

        jbtgAcciones.add(jrbtnUpdate);
        jrbtnUpdate.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtnUpdate.setText("Editar");
        jrbtnUpdate.setEnabled(false);

        jbtgAcciones.add(jrbtnDelete);
        jrbtnDelete.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtnDelete.setText("Eliminar");
        jrbtnDelete.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbtnDelete)
                    .addComponent(jrbtnUpdate)
                    .addComponent(jLabel1)
                    .addComponent(jrbtnAdd))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(jrbtnAdd)
                .addGap(39, 39, 39)
                .addComponent(jrbtnUpdate)
                .addGap(36, 36, 36)
                .addComponent(jrbtnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtblAutos.setModel(modeloAutos);
        jtblAutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblAutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblAutos);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Marca");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Modelo");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("Año");

        jcbxMarca.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtfModelo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtfAnyo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jbtnAceptar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jbtnAceptar.setText("Aceptar");
        jbtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAceptarActionPerformed(evt);
            }
        });

        jbtnCancelar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jbtnCancelar.setText("Cancelar");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel5.setText("Foto");

        jlblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jbtnFoto.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jbtnFoto.setText("Foto");
        jbtnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jcbxMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfModelo)
                                    .addComponent(jtfAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jlblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 26, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jbtnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnFoto)
                        .addGap(82, 82, 82))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jcbxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtfModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtfAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jlblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnFoto)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnAceptar)
                            .addComponent(jbtnCancelar))
                        .addGap(24, 24, 24))))
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
        if(jcbxMarca.getSelectedIndex() >= 0 && !jtfModelo.getText().isEmpty() && !jtfAnyo.getText().isEmpty()){
            if(jrbtnAdd.isSelected() && bite > 0){
                int i;
                Marcas_LC ma = (Marcas_LC) jcbxMarca.getSelectedItem();
                int id = ma.getIdMarca();
                
                i = autDao.addAutos(id, jtfModelo.getText(), Integer.parseInt(jtfAnyo.getText()), file, bite);
                
                if(i > 0){
                    Limpiar();
                    getRows();
                } else{
                    JOptionPane.showMessageDialog(null, "No pudimos ingresar al auto");
                }
            } else if(jrbtnUpdate.isSelected()){
                int i;
                Marcas_LC ma = (Marcas_LC) jcbxMarca.getSelectedItem();
                int id = ma.getIdMarca();
                
                if(isPhotoChange)
                    i = autDao.updateAutosPhoto(id, jtfModelo.getText(), Integer.parseInt(jtfAnyo.getText()), idAuto, file, bite);
                else
                    i = autDao.updateAutos(id, jtfModelo.getText(), Integer.parseInt(jtfAnyo.getText()), idAuto);
                
                if(i > 0){
                    Limpiar();
                    getRows();
                } else
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el auto seleccionado");
            } else if(jrbtnDelete.isSelected()){
                int i;
                i = autDao.deleteAutos(idAuto);
                
                if(i > 0){
                    Limpiar();
                    getRows();
                } else
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el auto seleccionado");
            }
        } else
            JOptionPane.showMessageDialog(null, "No puedes dejar campos en blanco");
        
        bite = 0;
        idAuto = -1;
        autDao.close();
    }//GEN-LAST:event_jbtnAceptarActionPerformed

    private void jtblAutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblAutosMouseClicked
        if(jrbtnUpdate.isSelected() || jrbtnDelete.isSelected()){
            idAuto = (int) modeloAutos.getValueAt(jtblAutos.getSelectedRow(), 0);
            String ma = (String) modeloAutos.getValueAt(jtblAutos.getSelectedRow(), 1);
            jtfModelo.setText((String) modeloAutos.getValueAt(jtblAutos.getSelectedRow(), 2));
            int an = (int) modeloAutos.getValueAt(jtblAutos.getSelectedRow(), 3);
            try {
                loadImage();
            } catch (IOException ex) {
                Logger.getLogger(Autos_Add.class.getName()).log(Level.SEVERE, null, ex);
            }
            jtfAnyo.setText(Integer.toString(an));
            
            for (int i = 0; i < jcbxMarca.getItemCount(); i++) {
                jcbxMarca.setSelectedIndex(i);
                
                if(jcbxMarca.getSelectedItem().toString().equals(ma)){
                    break;
                }
            }
        }
    }//GEN-LAST:event_jtblAutosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup jbtgAcciones;
    private javax.swing.JButton jbtnAceptar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnFoto;
    private javax.swing.JComboBox<String> jcbxMarca;
    private javax.swing.JLabel jlblFoto;
    private javax.swing.JRadioButton jrbtnAdd;
    private javax.swing.JRadioButton jrbtnDelete;
    private javax.swing.JRadioButton jrbtnUpdate;
    private javax.swing.JTable jtblAutos;
    private javax.swing.JTextField jtfAnyo;
    private javax.swing.JTextField jtfModelo;
    // End of variables declaration//GEN-END:variables
}
