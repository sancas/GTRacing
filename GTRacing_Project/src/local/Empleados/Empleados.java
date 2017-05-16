/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.Empleados;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import local.DAO.Cargos_DAO;
import local.DAO.Empleados_DAO;
import local.DAO.Roles_DAO;
import local.PrincipalFrame.Principal_Frame;

/**
 *
 * @author Leonel
 */
public class Empleados extends javax.swing.JInternalFrame {

    //Variables Globales
    DefaultTableModel viewEmploy;
    Empleados_DAO empDao = new Empleados_DAO();
    Cargos_DAO carDao = new Cargos_DAO();
    int idEmpleado = -1;
    FileInputStream file = null;
    InputStream fotoFile = null;
    int bite = 0;
    boolean isPhotoChange = false;

    public Empleados() {
        //Cargando los datos de la tabla
        viewEmploy = new DefaultTableModel(null, getColums());
        setRows();

        //Iniciando los componentes del formulario
        initComponents();
        setVisible(true);

        //Cargando acciones por rol
        accionRol();
        
        //Carrgado datos
        carDao.listaCargos(jcbxCargos);

        //Ocultar columnas
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable1.getColumnModel().getColumn(3).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(3).setMinWidth(0);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(0);

        //Centrando el formulario
        int wdith = Principal_Frame.jdpContenedor.getWidth() - this.getWidth();
        int hght = Principal_Frame.jdpContenedor.getHeight() - this.getHeight();
        this.setLocation(wdith / 2, hght / 2);
        
        this.jlblFoto.setHorizontalAlignment(JLabel.CENTER);
    }

    private String[] getColums() {
        String[] colums = new String[]{"Id", "Nombe", "Apellido", "Foto", "Fecha Nac", "Domicilio", "DUI", "NIT", "Cargo"};

        return colums;
    }

    private void setRows() {
        ResultSet rs = empDao.getListEmployee();   //obteniendo los dados mediante una clase DAO
        try {
            Object[] myObject = new Object[9];

            while (rs.next()) {
                for (int i = 0; i < myObject.length; i++) {
                    myObject[i] = rs.getObject(i + 1);
                }
                viewEmploy.addRow(myObject);
            }

            rs.close();
            empDao.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String formatoFecha(String anyo, String mes, String dia) {

        switch (mes) {
            case "Enero":
                mes = "01";
                break;
            case "Febrero":
                mes = "02";
                break;
            case "Marzo":
                mes = "03";
                break;
            case "Abril":
                mes = "04";
                break;
            case "Mayo":
                mes = "05";
                break;
            case "Junio":
                mes = "06";
                break;
            case "Julio":
                mes = "07";
                break;
            case "Agosto":
                mes = "08";
                break;
            case "Septiembre":
                mes = "09";
                break;
            case "Octubre":
                mes = "10";
                break;
            case "Noviembre":
                mes = "11";
                break;
            case "Diciembre":
                mes = "12";
                break;
        }

        return anyo + "-" + mes + "-" + dia;
    }
    
    private void limpiar() {
        jtfAPellido.setText("");
        jtfAnyo.setText("");
        jtfDomicilio.setText("");
        jtfDui.setText("");
        jtfNit.setText("");
        jtfNombre.setText("");
        jcbxCargos.setSelectedIndex(0);
        jcbxDia.setSelectedIndex(0);
        jcbxMes.setSelectedIndex(0);
        jlblFoto.setIcon(null);
    }

    private void limpieza() {
        limpiar();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            viewEmploy.removeRow(i);
            i -= 1;
        }

        jlblFoto.setIcon(null);
    }

    private boolean verificarMes() {
        boolean esCorrecto = false;
        int dia = Integer.parseInt(jcbxDia.getSelectedItem().toString());
        String mes = jcbxMes.getSelectedItem().toString();

        if (mes.equals("Febrero") && dia < 29) {
            esCorrecto = true;
        } else if (dia < 31 && (mes.equals("Abril") || mes.equals("Junio") || mes.equals("Septiembre") || mes.equals("Noviembre"))) {
            esCorrecto = true;
        } else if(mes.equals("Enero") || mes.equals("Marzo") || mes.equals("Mayo") || mes.equals("Julio") || mes.equals("Agosto") || mes.equals("Octubre") || mes.equals("Diciembre")){
            esCorrecto = true;
        }

        return esCorrecto;

    }

    private void loadImage() throws IOException {
        ResultSet rs = empDao.loadImage(idEmpleado);
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
                    Image newImg = img.getScaledInstance(jlblFoto.getHeight(), jlblFoto.getHeight(), Image.SCALE_SMOOTH);

                    ImageIcon newIcon = new ImageIcon(newImg);

                    jlblFoto.setIcon(newIcon);
                }
            }

            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
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
                    case 4:
                        jrbtnAdd.setEnabled(true);
                        break;
                        
                    case 5:
                        jrbtnUpdate.setEnabled(true);
                        break;
                        
                    case 6:
                        jrbtnDelete.setEnabled(true);
                        break;
                }
                
                if(acciones > 6)
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
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

        btgAcciones = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jtfBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jtfAPellido = new javax.swing.JTextField();
        jtfAnyo = new javax.swing.JTextField();
        jcbxDia = new javax.swing.JComboBox<>();
        jcbxMes = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jtfDomicilio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtfDui = new javax.swing.JFormattedTextField();
        jtfNit = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jcbxCargos = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jlblFoto = new javax.swing.JLabel();
        jbtnFoto = new javax.swing.JButton();
        jbtnAceptar = new javax.swing.JButton();
        jPanelAcciones = new javax.swing.JPanel();
        jrbtnAdd = new javax.swing.JRadioButton();
        jrbtnUpdate = new javax.swing.JRadioButton();
        jrbtnDelete = new javax.swing.JRadioButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setMinimumSize(new java.awt.Dimension(400, 650));
        setPreferredSize(new java.awt.Dimension(800, 800));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Buscar");

        jtfBuscar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jtfBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfBuscarKeyPressed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTable1.setModel(viewEmploy);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("Apellido:");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel5.setText("Fecha de Nacimiento:");

        jtfNombre.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtfAPellido.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtfAnyo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jcbxDia.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jcbxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jcbxMes.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jcbxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Domicilio:");

        jtfDomicilio.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel7.setText("DUI:");

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel8.setText("NIT:");

        try {
            jtfDui.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtfDui.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        try {
            jtfNit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-######-###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtfNit.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jtfNit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNitActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setText("Cargo:");

        jcbxCargos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel10.setText("Foto:");

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

        jPanelAcciones.setBackground(new java.awt.Color(80, 81, 79));

        jrbtnAdd.setBackground(new java.awt.Color(80, 81, 79));
        btgAcciones.add(jrbtnAdd);
        jrbtnAdd.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jrbtnAdd.setForeground(new java.awt.Color(242, 95, 92));
        jrbtnAdd.setText("Agregar");
        jrbtnAdd.setEnabled(false);
        jrbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbtnAddActionPerformed(evt);
            }
        });

        jrbtnUpdate.setBackground(new java.awt.Color(80, 81, 79));
        btgAcciones.add(jrbtnUpdate);
        jrbtnUpdate.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jrbtnUpdate.setForeground(new java.awt.Color(242, 95, 92));
        jrbtnUpdate.setText("Editar");
        jrbtnUpdate.setEnabled(false);

        jrbtnDelete.setBackground(new java.awt.Color(80, 81, 79));
        btgAcciones.add(jrbtnDelete);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jtfBuscar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jtfNombre))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfAPellido)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfNit)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfDui))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfDomicilio))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbtnFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbxCargos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbxDia, 0, 70, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbxMes, 0, 121, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfAnyo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfAPellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jtfDui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcbxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtfDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jcbxCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnFoto)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnAceptar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtfNitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNitActionPerformed

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
                    Image icono = ImageIO.read(j.getSelectedFile()).getScaledInstance(jlblFoto.getHeight(), jlblFoto.getHeight(), Image.SCALE_SMOOTH);
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
        if (!jtfNombre.getText().isEmpty() && !jtfAPellido.getText().isEmpty() && !jtfAnyo.getText().isEmpty() && !jtfDomicilio.getText().isEmpty() && !jtfDui.getText().isEmpty() && !jtfNit.getText().isEmpty() && jcbxCargos.getSelectedIndex() >= 0 && jcbxDia.getSelectedIndex() >= 0 && jcbxMes.getSelectedIndex() >= 0) {
            if (jrbtnAdd.isSelected()) {

                if (verificarMes()) {
                    try {
                        int isAdd;
                        String fecha = formatoFecha(jtfAnyo.getText(), jcbxMes.getSelectedItem().toString(), jcbxDia.getSelectedItem().toString());
                        local.LC.Cargos c = (local.LC.Cargos) jcbxCargos.getSelectedItem();
                        int cago = c.getIdcargo();
                        isAdd = empDao.addEmploy(jtfNombre.getText(), jtfAPellido.getText(), file, bite, fecha, jtfDomicilio.getText(), jtfDui.getText(), jtfNit.getText(), cago);

                        if (isAdd > 0) {
                            limpieza();
                            setRows();
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La fecha de nacimiento es incorrecta");
                }

            } else if (jrbtnUpdate.isSelected()) {
                if (idEmpleado > 0) {
                    boolean vMes = verificarMes();
                    if (vMes) {
                        if (isPhotoChange) {
                            isPhotoChange = false; //colocando la bandera en falsa para una siguiente accion

                            try {
                                int isAdd;
                                String fecha = formatoFecha(jtfAnyo.getText(), jcbxMes.getSelectedItem().toString(), jcbxDia.getSelectedItem().toString());
                                local.LC.Cargos c = (local.LC.Cargos) jcbxCargos.getSelectedItem();
                                int cago = c.getIdcargo();
                                isAdd = empDao.updateEmployWhitPhoto(idEmpleado, jtfNombre.getText(), jtfAPellido.getText(), file, bite, fecha, jtfDomicilio.getText(), jtfDui.getText(), jtfNit.getText(), cago);

                                if (isAdd > 0) {
                                    limpieza();
                                    setRows();
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                int isAdd;
                                String fecha = formatoFecha(jtfAnyo.getText(), jcbxMes.getSelectedItem().toString(), jcbxDia.getSelectedItem().toString());
                                local.LC.Cargos c = (local.LC.Cargos) jcbxCargos.getSelectedItem();
                                int cago = c.getIdcargo();
                                isAdd = empDao.upadteEmployWhitoutPhoto(idEmpleado, jtfNombre.getText(), jtfAPellido.getText(), fecha, jtfDomicilio.getText(), jtfDui.getText(), jtfNit.getText(), cago);

                                if (isAdd > 0) {
                                    limpieza();
                                    setRows();
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "La fecha de nacimineto es incorrecta");

                    idEmpleado = -1;
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor selecciona un empleado de la tabla");
                }
            } else if (jrbtnDelete.isSelected()) {
                if (idEmpleado > 0) {
                    int isDelete = empDao.deleteEmploy(idEmpleado);

                    if (isDelete > 0) {
                        limpieza();
                        setRows();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido eliminar el empleado");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor selecciona un empleado de la tabla");
                }
                
                idEmpleado = -1;
            }

        } else {
            JOptionPane.showMessageDialog(null, "No puedes dejar campos vacios");
        }

        empDao.close();
    }//GEN-LAST:event_jbtnAceptarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (jrbtnUpdate.isSelected() || jrbtnDelete.isSelected()) {
            //"Id", "Nombe", "Apellido", "Foto", "Fecha Nac", "Domicilio", "DUI", "NIT", "Cargo"
            idEmpleado = (int) viewEmploy.getValueAt(jTable1.getSelectedRow(), 0);
            jtfNombre.setText((String) viewEmploy.getValueAt(jTable1.getSelectedRow(), 1));
            jtfAPellido.setText((String) viewEmploy.getValueAt(jTable1.getSelectedRow(), 2));
            jtfDomicilio.setText((String) viewEmploy.getValueAt(jTable1.getSelectedRow(), 5));
            jtfDui.setText((String) viewEmploy.getValueAt(jTable1.getSelectedRow(), 6));
            jtfNit.setText((String) viewEmploy.getValueAt(jTable1.getSelectedRow(), 7));
            try {
                loadImage();
            } catch (IOException ex) {
                Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
            }

            String cago = (String) viewEmploy.getValueAt(jTable1.getSelectedRow(), 8);

            for (int i = 0; i < jcbxCargos.getItemCount(); i++) {
                jcbxCargos.setSelectedIndex(i);
                if (cago.equals(jcbxCargos.getSelectedItem().toString())) {
                    break;
                }
            }

            java.sql.Date date = (java.sql.Date) viewEmploy.getValueAt(jTable1.getSelectedRow(), 4);
            String sDate = date.toString();
            String[] separada = sDate.split("-");

            jcbxDia.setSelectedIndex(Integer.parseInt(separada[2]) - 1);
            jcbxMes.setSelectedIndex(Integer.parseInt(separada[1]) - 1);
            jtfAnyo.setText(separada[0]);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jtfBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarKeyPressed
        String search = jtfBuscar.getText();

        if (search.isEmpty()) {
            limpieza();
            setRows();
        } else {
            limpieza();
            ResultSet rs = empDao.getSearchtEmployee(search);   //obteniendo los dados mediante una clase DAO
            try {
                Object[] myObject = new Object[9];

                while (rs.next()) {
                    for (int i = 0; i < myObject.length; i++) {
                        myObject[i] = rs.getObject(i + 1);
                    }
                    viewEmploy.addRow(myObject);
                }
                rs.close();
                carDao.close();
            } catch (SQLException ex) {
                Logger.getLogger(Cargos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jtfBuscarKeyPressed

    private void jrbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbtnAddActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jrbtnAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgAcciones;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnAceptar;
    private javax.swing.JButton jbtnFoto;
    private javax.swing.JComboBox<String> jcbxCargos;
    private javax.swing.JComboBox<String> jcbxDia;
    private javax.swing.JComboBox<String> jcbxMes;
    private javax.swing.JLabel jlblFoto;
    private javax.swing.JRadioButton jrbtnAdd;
    private javax.swing.JRadioButton jrbtnDelete;
    private javax.swing.JRadioButton jrbtnUpdate;
    private javax.swing.JTextField jtfAPellido;
    private javax.swing.JTextField jtfAnyo;
    private javax.swing.JTextField jtfBuscar;
    private javax.swing.JTextField jtfDomicilio;
    private javax.swing.JFormattedTextField jtfDui;
    private javax.swing.JFormattedTextField jtfNit;
    private javax.swing.JTextField jtfNombre;
    // End of variables declaration//GEN-END:variables
}
