/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.Usuarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import local.DAO.Roles_DAO;
import local.Empleados.Cargos;
import local.PrincipalFrame.Principal_Frame;

/**
 *
 * @author Leonel
 */
public class Roles extends javax.swing.JInternalFrame {

    //Varibales globales
    int idRoles = -1;
    DefaultTableModel viewRoles;
    Roles_DAO rolDao = new Roles_DAO();
    
    public Roles() {
        //Cragando el modelo de la tabla
        viewRoles = new DefaultTableModel(null, getColums());
        getRows();
        
        //Iniciando los componentes del formulario
        initComponents();
        setVisible(true);
        
        
        //Ocultar columnas
        jtblRoles.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblRoles.getColumnModel().getColumn(0).setMinWidth(0);
        jtblRoles.getColumnModel().getColumn(0).setPreferredWidth(0);

        //Centrando el formulario
        int wdith = Principal_Frame.jdpContenedor.getWidth() - this.getWidth();
        int hght = Principal_Frame.jdpContenedor.getHeight() - this.getHeight();
        this.setLocation(wdith / 2, hght / 2);
    }
    
    //metodo para establecer la cabecera de la tabla
    private String[] getColums() {
        String[] colums = new String[]{"Id", "Rol", "Descricion"};

        return colums;
    }

    //Metodo para obtener las filas de la tabla
    private void getRows() {
        ResultSet rs = rolDao.getListRol();   //obteniendo los dados mediante una clase DAO
        try {
            Object[] myObject = new Object[3];

            while (rs.next()) {
                for (int i = 0; i < myObject.length; i++) {
                    myObject[i] = rs.getObject(i + 1);
                }
                viewRoles.addRow(myObject);
            }

            rs.close();
            rolDao.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cargos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //metodo para verifica si se ha seleccionado un checkbox de accion
    private boolean giveAction(){
        boolean isAction = false;
        JCheckBox[] misCheckBox = new JCheckBox[]{jchkCargosAdd, jchkCargoEdit, jchkCargosDel, jchkEmpleadosAdd, jchkEmpleadosEdit,
                jchkEmpleadosDel, jchkRolesAdd, jchkRolesEdit, jchkRolesDel, jchkUsuariosAdd, jchkUsuariosEdit, jchkUsuariosDel, 
                jchkProveedorAdd, jchkProveedorEdit, jchkProveedorDel, jchkRepuestosAdd, jchkRepuestosEdit, jchkRepuestosDel,
                jchkAutosAdd, jchkAutosEdit, jchkAutosDel};
        
        for (JCheckBox miCheckBox : misCheckBox) {
            if (miCheckBox.isSelected()) {
                isAction = true;
            }
        }
        
        return isAction;
    }
    
    private int setTareas(){
        int size = 0;
        int isAdd;
        
        JCheckBox[] misCheckBox = new JCheckBox[]{jchkCargosAdd, jchkCargoEdit, jchkCargosDel, jchkEmpleadosAdd, jchkEmpleadosEdit,
                jchkEmpleadosDel, jchkRolesAdd, jchkRolesEdit, jchkRolesDel, jchkUsuariosAdd, jchkUsuariosEdit, jchkUsuariosDel, 
                jchkProveedorAdd, jchkProveedorEdit, jchkProveedorDel, jchkRepuestosAdd, jchkRepuestosEdit, jchkRepuestosDel,
                jchkAutosAdd, jchkAutosEdit, jchkAutosDel};
        
        for (JCheckBox miCheckBox : misCheckBox) {
            if (miCheckBox.isSelected()) {
                size++;
            }
        }
        
        int[] idTareas = new int[size];
        
        for (int i = 0; i < idTareas.length; i++) {
            for (int j = 0; j < misCheckBox.length; j++) {
                if(misCheckBox[j].isSelected()){
                    idTareas[i] = j + 1;
                    misCheckBox[j].setSelected(false);
                    break;
                }
            }
        }
        
        if(jrbtAdd.isSelected()){
            idRoles = rolDao.getCurrentRol();
        }
        isAdd = rolDao.inputTareas(idTareas, idRoles);
       
        return isAdd;
    }
    
    //meteod para cargar los checkbox
    private void loadCheck(){
        JCheckBox[] misCheckBox = new JCheckBox[]{jchkCargosAdd, jchkCargoEdit, jchkCargosDel, jchkEmpleadosAdd, jchkEmpleadosEdit,
                jchkEmpleadosDel, jchkRolesAdd, jchkRolesEdit, jchkRolesDel, jchkUsuariosAdd, jchkUsuariosEdit, jchkUsuariosDel, 
                jchkProveedorAdd, jchkProveedorEdit, jchkProveedorDel, jchkRepuestosAdd, jchkRepuestosEdit, jchkRepuestosDel,
                jchkAutosAdd, jchkAutosEdit, jchkAutosDel};
        
        ResultSet rs = rolDao.getTareasRol(idRoles);
        
        try {
            while(rs.next()){
                int i = rs.getInt(3);
                
                for (int j = 0; j < misCheckBox.length; j++) {
                    if(i == (j + 1)){
                        misCheckBox[j].setSelected(true);
                        
                        if(i > 0 && i <4){
                            misCheckBox[0].setEnabled(true);
                            misCheckBox[1].setEnabled(true);
                            misCheckBox[2].setEnabled(true);
                            jchkCargos.setSelected(true);
                        } else if(i > 3 && i <7){
                            misCheckBox[3].setEnabled(true);
                            misCheckBox[4].setEnabled(true);
                            misCheckBox[5].setEnabled(true);
                            jchkEmpleados.setSelected(true);
                        } else if(i > 6 && i < 10){
                            misCheckBox[6].setEnabled(true);
                            misCheckBox[7].setEnabled(true);
                            misCheckBox[8].setEnabled(true);
                            jchkRoles.setSelected(true);
                        } else if(i > 9 && i < 13){
                            misCheckBox[9].setEnabled(true);
                            misCheckBox[10].setEnabled(true);
                            misCheckBox[11].setEnabled(true);
                            jchkUsuarios.setSelected(true);
                        } else if(i > 12 && i < 16){
                            misCheckBox[12].setEnabled(true);
                            misCheckBox[13].setEnabled(true);
                            misCheckBox[14].setEnabled(true);
                            jchkProveedor.setSelected(true);
                        } else if(i > 15 && i < 19){
                            misCheckBox[15].setEnabled(true);
                            misCheckBox[16].setEnabled(true);
                            misCheckBox[17].setEnabled(true);
                            jchkRepuestos.setSelected(true);
                        } else if(i > 18 && i < 22){
                            misCheckBox[18].setEnabled(true);
                            misCheckBox[19].setEnabled(true);
                            misCheckBox[20].setEnabled(true);
                            jchkAutos.setSelected(true);
                        }
                    }
                }
            }
            
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Roles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int deleteTareasRol(){
        int isDelete = rolDao.delTareas(idRoles);
        
        return isDelete;
    }
    
    //metodo para verificar los permisos para el formulario cargos
    private void accionRol(){
        ResultSet rs = rolDao.listaTareas();
        int acciones;
        
        try {
            while(rs.next()){
                acciones = rs.getInt(1);
                switch(acciones){
                    case 7:
                        jrbtAdd.setEnabled(true);
                        break;
                        
                    case 8:
                        jrbtUpdate.setEnabled(true);
                        break;
                        
                    case 9:
                        jrbtDelete.setEnabled(true);
                        break;
                }
                
                if(acciones > 9)
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Roles.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //metodo para limpiar los elementos del formulario
    private void limpieza() {
        jtfDescripcion.setText("");
        jtfRol.setText("");
        JCheckBox[] misCheckBox = new JCheckBox[]{jchkCargosAdd, jchkCargoEdit, jchkCargosDel, jchkEmpleadosAdd, jchkEmpleadosEdit,
                jchkEmpleadosDel, jchkRolesAdd, jchkRolesEdit, jchkRolesDel, jchkUsuariosAdd, jchkUsuariosEdit, jchkUsuariosDel, 
                jchkProveedorAdd, jchkProveedorEdit, jchkProveedorDel, jchkRepuestosAdd, jchkRepuestosEdit, jchkRepuestosDel,
                jchkAutosAdd, jchkAutosEdit, jchkAutosDel, jchkCargos, jchkEmpleados, jchkRoles, jchkUsuarios, jchkProveedor, 
                jchkRepuestos, jchkAutos};
        
        for (int i = 0; i < misCheckBox.length; i++) {
            misCheckBox[i].setSelected(false);
            if(i < 21){
                misCheckBox[i].setEnabled(false);
            }
        }
        
        for (int i = 0; i < jtblRoles.getRowCount(); i++) {
            viewRoles.removeRow(i);
            i -= 1;
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
        jLabel4 = new javax.swing.JLabel();
        jrbtAdd = new javax.swing.JRadioButton();
        jrbtUpdate = new javax.swing.JRadioButton();
        jrbtDelete = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jtfBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblRoles = new javax.swing.JTable();
        jchkCargos = new javax.swing.JCheckBox();
        jchkCargosAdd = new javax.swing.JCheckBox();
        jchkCargoEdit = new javax.swing.JCheckBox();
        jchkCargosDel = new javax.swing.JCheckBox();
        jchkEmpleados = new javax.swing.JCheckBox();
        jchkRoles = new javax.swing.JCheckBox();
        jchkUsuarios = new javax.swing.JCheckBox();
        jchkProveedor = new javax.swing.JCheckBox();
        jchkEmpleadosAdd = new javax.swing.JCheckBox();
        jchkEmpleadosEdit = new javax.swing.JCheckBox();
        jchkEmpleadosDel = new javax.swing.JCheckBox();
        jchkRolesAdd = new javax.swing.JCheckBox();
        jchkRolesEdit = new javax.swing.JCheckBox();
        jchkRolesDel = new javax.swing.JCheckBox();
        jchkUsuariosAdd = new javax.swing.JCheckBox();
        jchkUsuariosEdit = new javax.swing.JCheckBox();
        jchkUsuariosDel = new javax.swing.JCheckBox();
        jchkProveedorAdd = new javax.swing.JCheckBox();
        jchkProveedorEdit = new javax.swing.JCheckBox();
        jchkProveedorDel = new javax.swing.JCheckBox();
        jchkRepuestosAdd = new javax.swing.JCheckBox();
        jchkRepuestosEdit = new javax.swing.JCheckBox();
        jchkRepuestosDel = new javax.swing.JCheckBox();
        jchkAutos = new javax.swing.JCheckBox();
        jchkVentas = new javax.swing.JCheckBox();
        jchkEstadisticas = new javax.swing.JCheckBox();
        jchkAutosAdd = new javax.swing.JCheckBox();
        jchkAutosEdit = new javax.swing.JCheckBox();
        jchkAutosDel = new javax.swing.JCheckBox();
        jchkRepuestos = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jtfRol = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtfDescripcion = new javax.swing.JTextArea();
        jbtnAceptar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel4.setText("Acciones");

        jbtgAcciones.add(jrbtAdd);
        jrbtAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtAdd.setText("Agregar");
        jrbtAdd.setEnabled(false);

        jbtgAcciones.add(jrbtUpdate);
        jrbtUpdate.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtUpdate.setText("Editar");
        jrbtUpdate.setEnabled(false);

        jbtgAcciones.add(jrbtDelete);
        jrbtDelete.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtDelete.setText("Eliminar");
        jrbtDelete.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbtUpdate)
                            .addComponent(jrbtAdd)
                            .addComponent(jrbtDelete))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel4)
                .addGap(70, 70, 70)
                .addComponent(jrbtAdd)
                .addGap(18, 18, 18)
                .addComponent(jrbtUpdate)
                .addGap(18, 18, 18)
                .addComponent(jrbtDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("Buscar");

        jtfBuscar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtblRoles.setModel(viewRoles);
        jtblRoles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblRolesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblRoles);

        jchkCargos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkCargos.setText("Cargos");
        jchkCargos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jchkCargosMouseClicked(evt);
            }
        });

        jchkCargosAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkCargosAdd.setText("Agregar");
        jchkCargosAdd.setEnabled(false);

        jchkCargoEdit.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkCargoEdit.setText("Editar");
        jchkCargoEdit.setEnabled(false);

        jchkCargosDel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkCargosDel.setText("Eliminar");
        jchkCargosDel.setEnabled(false);

        jchkEmpleados.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkEmpleados.setText("Empleados");
        jchkEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jchkEmpleadosMouseClicked(evt);
            }
        });

        jchkRoles.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkRoles.setText("Roles");
        jchkRoles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jchkRolesMouseClicked(evt);
            }
        });

        jchkUsuarios.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkUsuarios.setText("Usuarios");
        jchkUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jchkUsuariosMouseClicked(evt);
            }
        });

        jchkProveedor.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkProveedor.setText("Proveedores");
        jchkProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jchkProveedorMouseClicked(evt);
            }
        });

        jchkEmpleadosAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkEmpleadosAdd.setText("Agregar");
        jchkEmpleadosAdd.setEnabled(false);

        jchkEmpleadosEdit.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkEmpleadosEdit.setText("Editar");
        jchkEmpleadosEdit.setEnabled(false);

        jchkEmpleadosDel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkEmpleadosDel.setText("Eliminar");
        jchkEmpleadosDel.setEnabled(false);

        jchkRolesAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkRolesAdd.setText("Agregar");
        jchkRolesAdd.setEnabled(false);

        jchkRolesEdit.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkRolesEdit.setText("Editar");
        jchkRolesEdit.setEnabled(false);

        jchkRolesDel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkRolesDel.setText("Eliminar");
        jchkRolesDel.setEnabled(false);

        jchkUsuariosAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkUsuariosAdd.setText("Agregar");
        jchkUsuariosAdd.setEnabled(false);

        jchkUsuariosEdit.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkUsuariosEdit.setText("Editar");
        jchkUsuariosEdit.setEnabled(false);

        jchkUsuariosDel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkUsuariosDel.setText("Eliminar");
        jchkUsuariosDel.setEnabled(false);

        jchkProveedorAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkProveedorAdd.setText("Agregar");
        jchkProveedorAdd.setEnabled(false);

        jchkProveedorEdit.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkProveedorEdit.setText("Editar");
        jchkProveedorEdit.setEnabled(false);

        jchkProveedorDel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkProveedorDel.setText("Eliminar");
        jchkProveedorDel.setEnabled(false);

        jchkRepuestosAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkRepuestosAdd.setText("Agregar");
        jchkRepuestosAdd.setEnabled(false);

        jchkRepuestosEdit.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkRepuestosEdit.setText("Editar");
        jchkRepuestosEdit.setEnabled(false);

        jchkRepuestosDel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkRepuestosDel.setText("Eliminar");
        jchkRepuestosDel.setEnabled(false);

        jchkAutos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkAutos.setText("Autos");
        jchkAutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jchkAutosMouseClicked(evt);
            }
        });

        jchkVentas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkVentas.setText("Ventas");

        jchkEstadisticas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkEstadisticas.setText("Estadisticas");

        jchkAutosAdd.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkAutosAdd.setText("Agregar");
        jchkAutosAdd.setEnabled(false);

        jchkAutosEdit.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkAutosEdit.setText("Editar");
        jchkAutosEdit.setEnabled(false);

        jchkAutosDel.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkAutosDel.setText("Eliminar");
        jchkAutosDel.setEnabled(false);

        jchkRepuestos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jchkRepuestos.setText("Repuestos");
        jchkRepuestos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jchkRepuestosMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Rol");

        jtfRol.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Descripcion");

        jScrollPane2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtfDescripcion.setColumns(20);
        jtfDescripcion.setRows(5);
        jScrollPane2.setViewportView(jtfDescripcion);

        jbtnAceptar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jbtnAceptar.setText("Aceptar");
        jbtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAceptarActionPerformed(evt);
            }
        });

        jbtnCancelar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jbtnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jchkCargos)
                                    .addComponent(jchkCargosAdd)
                                    .addComponent(jchkCargoEdit)
                                    .addComponent(jchkCargosDel)
                                    .addComponent(jchkRepuestos)
                                    .addComponent(jchkRepuestosAdd)
                                    .addComponent(jchkRepuestosEdit)
                                    .addComponent(jchkRepuestosDel))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jchkEmpleados)
                                            .addComponent(jchkEmpleadosAdd)
                                            .addComponent(jchkEmpleadosEdit)
                                            .addComponent(jchkEmpleadosDel)
                                            .addComponent(jchkAutos)
                                            .addComponent(jchkAutosEdit)
                                            .addComponent(jchkAutosDel))
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jchkRoles)
                                            .addComponent(jchkRolesAdd)
                                            .addComponent(jchkRolesEdit)
                                            .addComponent(jchkRolesDel)
                                            .addComponent(jchkVentas))
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jchkUsuarios)
                                                    .addComponent(jchkUsuariosAdd)
                                                    .addComponent(jchkUsuariosEdit)
                                                    .addComponent(jchkUsuariosDel))
                                                .addGap(37, 37, 37)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jchkProveedorAdd)
                                                    .addComponent(jchkProveedorEdit)
                                                    .addComponent(jchkProveedorDel)
                                                    .addComponent(jchkProveedor)))
                                            .addComponent(jchkEstadisticas)))
                                    .addComponent(jchkAutosAdd)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jtfRol, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbtnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(29, 29, 29)
                        .addComponent(jtfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jtfRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jchkCargos)
                    .addComponent(jchkEmpleados)
                    .addComponent(jchkRoles)
                    .addComponent(jchkUsuarios)
                    .addComponent(jchkProveedor))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jchkCargosAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkCargoEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkCargosDel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jchkEmpleadosAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkEmpleadosEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkEmpleadosDel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jchkRolesAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkRolesEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkRolesDel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jchkUsuariosAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkUsuariosEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkUsuariosDel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jchkProveedorAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkProveedorEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkProveedorDel)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jchkRepuestos)
                    .addComponent(jchkAutos)
                    .addComponent(jchkVentas)
                    .addComponent(jchkEstadisticas))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jchkRepuestosAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkRepuestosEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jchkRepuestosDel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jchkAutosAdd)
                            .addComponent(jbtnAceptar))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jchkAutosEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jchkAutosDel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jbtnCancelar)))))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAceptarActionPerformed
        boolean isAction = giveAction();
        if(!jtfRol.getText().isEmpty() && !jtfDescripcion.getText().isEmpty() && isAction){
            if(jrbtAdd.isSelected()){
                int i;
                int o;
                
                i = rolDao.insertRol(jtfRol.getText(), jtfDescripcion.getText());
                o = setTareas();
                
                if(i > 0 && o > 0){
                    limpieza();
                    getRows();
                } else{
                    JOptionPane.showMessageDialog(null, "Error al ingresar datos");
                }
                
            } else if(jrbtUpdate.isSelected()){
                int i = rolDao.updateRol(idRoles, jtfRol.getText(), jtfDescripcion.getText());
                int o = deleteTareasRol();
                int u = setTareas();
                
                if(i > 0 && o > 0 && u > 0){
                    limpieza();
                    getRows();
                } else{
                    JOptionPane.showMessageDialog(null, "Error al actualizar datos");
                }
                
                idRoles = -1;
            } else if(jrbtDelete.isSelected()){
                int o = deleteTareasRol();
                int i = rolDao.deleteRol(idRoles);
                
                if(i > 0 && o > 0){
                    limpieza();
                    getRows();
                } else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar datos");
                }
                
                idRoles = -1;
            } else{
                JOptionPane.showMessageDialog(null, "Debes seleccionar una accion");
            }
        } else{
            JOptionPane.showMessageDialog(null, "Debe de seleccionar una tarea y no debes dejar campos de texto vacios");
        }
        
        rolDao.close();
    }//GEN-LAST:event_jbtnAceptarActionPerformed

    private void jchkCargosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jchkCargosMouseClicked
        if(jchkCargos.isSelected()){
            jchkCargosAdd.setEnabled(true);
            jchkCargoEdit.setEnabled(true);
            jchkCargosDel.setEnabled(true);
        } else{
            jchkCargosAdd.setEnabled(false);
            jchkCargoEdit.setEnabled(false);
            jchkCargosDel.setEnabled(false);
        }
    }//GEN-LAST:event_jchkCargosMouseClicked

    private void jchkEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jchkEmpleadosMouseClicked
        if(jchkEmpleados.isSelected()){
            jchkEmpleadosAdd.setEnabled(true);
            jchkEmpleadosEdit.setEnabled(true);
            jchkEmpleadosDel.setEnabled(true);
        } else{
            jchkEmpleadosAdd.setEnabled(false);
            jchkEmpleadosEdit.setEnabled(false);
            jchkEmpleadosDel.setEnabled(false);
        }
    }//GEN-LAST:event_jchkEmpleadosMouseClicked

    private void jchkRolesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jchkRolesMouseClicked
        if(jchkRoles.isSelected()){
            jchkRolesAdd.setEnabled(true);
            jchkRolesEdit.setEnabled(true);
            jchkRolesDel.setEnabled(true);
        } else {
            jchkRolesAdd.setEnabled(false);
            jchkRolesEdit.setEnabled(false);
            jchkRolesDel.setEnabled(false);
        }
    }//GEN-LAST:event_jchkRolesMouseClicked

    private void jchkUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jchkUsuariosMouseClicked
        if(jchkUsuarios.isSelected()){
            jchkUsuariosAdd.setEnabled(true);
            jchkUsuariosEdit.setEnabled(true);
            jchkUsuariosDel.setEnabled(true);
        } else {
            jchkUsuariosAdd.setEnabled(false);
            jchkUsuariosEdit.setEnabled(false);
            jchkUsuariosDel.setEnabled(false);
        }
    }//GEN-LAST:event_jchkUsuariosMouseClicked

    private void jchkProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jchkProveedorMouseClicked
        if(jchkProveedor.isSelected()){
            jchkProveedorAdd.setEnabled(true);
            jchkProveedorEdit.setEnabled(true);
            jchkProveedorDel.setEnabled(true);
        } else{
            jchkProveedorAdd.setEnabled(false);
            jchkProveedorEdit.setEnabled(false);
            jchkProveedorDel.setEnabled(false);
        }
    }//GEN-LAST:event_jchkProveedorMouseClicked

    private void jchkRepuestosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jchkRepuestosMouseClicked
        if(jchkRepuestos.isSelected()){
            jchkRepuestosAdd.setEnabled(true);
            jchkRepuestosEdit.setEnabled(true);
            jchkRepuestosDel.setEnabled(true);
        } else{
            jchkRepuestosAdd.setEnabled(false);
            jchkRepuestosEdit.setEnabled(false);
            jchkRepuestosDel.setEnabled(false);
        }
    }//GEN-LAST:event_jchkRepuestosMouseClicked

    private void jchkAutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jchkAutosMouseClicked
        if(jchkAutos.isSelected()){
            jchkAutosAdd.setEnabled(true);
            jchkAutosEdit.setEnabled(true);
            jchkAutosDel.setEnabled(true);
        } else{
            jchkAutosAdd.setEnabled(false);
            jchkAutosEdit.setEnabled(false);
            jchkAutosDel.setEnabled(false);
        }
    }//GEN-LAST:event_jchkAutosMouseClicked

    private void jtblRolesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblRolesMouseClicked
        if(jrbtDelete.isSelected() || jrbtUpdate.isSelected()){
            idRoles = (int) viewRoles.getValueAt(jtblRoles.getSelectedRow(), 0);
            jtfRol.setText((String) viewRoles.getValueAt(jtblRoles.getSelectedRow(), 1));
            jtfDescripcion.setText((String) viewRoles.getValueAt(jtblRoles.getSelectedRow(), 2));
            loadCheck();
        }
    }//GEN-LAST:event_jtblRolesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.ButtonGroup jbtgAcciones;
    private javax.swing.JButton jbtnAceptar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JCheckBox jchkAutos;
    private javax.swing.JCheckBox jchkAutosAdd;
    private javax.swing.JCheckBox jchkAutosDel;
    private javax.swing.JCheckBox jchkAutosEdit;
    private javax.swing.JCheckBox jchkCargoEdit;
    private javax.swing.JCheckBox jchkCargos;
    private javax.swing.JCheckBox jchkCargosAdd;
    private javax.swing.JCheckBox jchkCargosDel;
    private javax.swing.JCheckBox jchkEmpleados;
    private javax.swing.JCheckBox jchkEmpleadosAdd;
    private javax.swing.JCheckBox jchkEmpleadosDel;
    private javax.swing.JCheckBox jchkEmpleadosEdit;
    private javax.swing.JCheckBox jchkEstadisticas;
    private javax.swing.JCheckBox jchkProveedor;
    private javax.swing.JCheckBox jchkProveedorAdd;
    private javax.swing.JCheckBox jchkProveedorDel;
    private javax.swing.JCheckBox jchkProveedorEdit;
    private javax.swing.JCheckBox jchkRepuestos;
    private javax.swing.JCheckBox jchkRepuestosAdd;
    private javax.swing.JCheckBox jchkRepuestosDel;
    private javax.swing.JCheckBox jchkRepuestosEdit;
    private javax.swing.JCheckBox jchkRoles;
    private javax.swing.JCheckBox jchkRolesAdd;
    private javax.swing.JCheckBox jchkRolesDel;
    private javax.swing.JCheckBox jchkRolesEdit;
    private javax.swing.JCheckBox jchkUsuarios;
    private javax.swing.JCheckBox jchkUsuariosAdd;
    private javax.swing.JCheckBox jchkUsuariosDel;
    private javax.swing.JCheckBox jchkUsuariosEdit;
    private javax.swing.JCheckBox jchkVentas;
    private javax.swing.JRadioButton jrbtAdd;
    private javax.swing.JRadioButton jrbtDelete;
    private javax.swing.JRadioButton jrbtUpdate;
    private javax.swing.JTable jtblRoles;
    private javax.swing.JTextField jtfBuscar;
    private javax.swing.JTextArea jtfDescripcion;
    private javax.swing.JTextField jtfRol;
    // End of variables declaration//GEN-END:variables
}
