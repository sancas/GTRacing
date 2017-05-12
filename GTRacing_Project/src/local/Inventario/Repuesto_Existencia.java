/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.Inventario;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import local.DAO.ExistenciaRepues_DAO;
import local.DAO.Proveedores_DAO;
import local.DAO.Repuestos_DAO;
import local.DAO.Roles_DAO;
import local.LC.ProveedoresLC;
import local.LC.Repuestos_LC;
import local.PrincipalFrame.Principal_Frame;

/**
 *
 * @author Leonel
 */
public class Repuesto_Existencia extends javax.swing.JInternalFrame {

    //variables globales
    DefaultTableModel modeloRepExitencia;
    ExistenciaRepues_DAO exDao = new ExistenciaRepues_DAO();
    Repuestos_DAO repDao = new Repuestos_DAO();
    Proveedores_DAO provDao = new Proveedores_DAO();
    int idExistencia;
    
    public Repuesto_Existencia() {
        //Cargando los datos de la tabla
        modeloRepExitencia = new DefaultTableModel(null, getColums());
        getRows();

        //Iniciando los componentes del formulario
        initComponents();
        setVisible(true);
        
        //Cargando acciones por rol
        accionRol();

        //Carrgado datos
        provDao.listaProveedor(jcbxProveedor);
        repDao.listaRepuestos(jcbxRepuesto);
        SNumero(jtfCantidad);
        sDecimal(jtfVenta);
        sDecimal(jtfCompra);

        //Ocultar columnas
        jtblRepExis.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblRepExis.getColumnModel().getColumn(0).setMinWidth(0);
        jtblRepExis.getColumnModel().getColumn(0).setPreferredWidth(0);

        //Centrando el formulario
        int wdith = Principal_Frame.jdpContenedor.getWidth() - this.getWidth();
        int hght = Principal_Frame.jdpContenedor.getHeight() - this.getHeight();
        this.setLocation(wdith / 2, hght / 2);
    }
    
    private String[] getColums(){
        String[] columnas = new String[]{"Id","Repuesto", "Proveedor", "Cantidad", "Precio Compra", "Precio Venta", "Agregado por"};
        return columnas;
    }
    
    private void getRows(){
        ResultSet rs = exDao.getListExisRepuestos();
        try{
            Object datos[] = new Object[7];
            
            while(rs.next()){
                for (int i = 0; i < datos.length; i++) {
                    datos[i] = rs.getObject(i+1);
                }        
                modeloRepExitencia.addRow(datos);
            }
            
            rs.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al cargar datos.");
            Logger.getLogger(Repuesto_Existencia.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void sDecimal(JTextField t){
        t.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')){
                    getToolkit().beep();
                    e.consume();
                }
                if(c == '.' && t.getText().contains(".")){
                    e.consume();
                }
            }
        });
    }
    
    private void Limpiar(){
        jtfCantidad.setText("");
        jtfCompra.setText("");
        jtfVenta.setText("");
        jcbxProveedor.setSelectedIndex(0);
        jcbxRepuesto.setSelectedIndex(0);
        
        for (int i = 0; i < jtblRepExis.getRowCount(); i++) {
            modeloRepExitencia.removeRow(i);
            i-=1;
        }
    }
    
    //metodo para verificar los permisos para el formulario cargos
    private void accionRol(){
        Roles_DAO rolDao = new Roles_DAO();
        ResultSet rs = rolDao.listaTareas();
        int acciones = 0;
        
        try {
            while(rs.next()){
                acciones = rs.getInt(1);
                switch(acciones){
                    case 16:
                        jrbtAgregar.setEnabled(true);
                        break;
                        
                    case 17:
                        jrbtEditar.setEnabled(true);
                        break;
                        
                    case 18:
                        jrbtEliminar.setEnabled(true);
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Repuesto_Existencia.class.getName()).log(Level.SEVERE, null, ex);
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
        jrbtAgregar = new javax.swing.JRadioButton();
        jrbtEditar = new javax.swing.JRadioButton();
        jrbtEliminar = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblRepExis = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcbxRepuesto = new javax.swing.JComboBox<>();
        jcbxProveedor = new javax.swing.JComboBox<>();
        jtfCantidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtfCompra = new javax.swing.JTextField();
        jtfVenta = new javax.swing.JTextField();
        jbtnAceptar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel1.setText("Acciones");

        jbtgAcciones.add(jrbtAgregar);
        jrbtAgregar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtAgregar.setText("Agregar");
        jrbtAgregar.setEnabled(false);

        jbtgAcciones.add(jrbtEditar);
        jrbtEditar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtEditar.setText("Editar");
        jrbtEditar.setEnabled(false);

        jbtgAcciones.add(jrbtEliminar);
        jrbtEliminar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jrbtEliminar.setText("Eliminar");
        jrbtEliminar.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbtEditar)
                    .addComponent(jrbtEliminar)
                    .addComponent(jLabel1)
                    .addComponent(jrbtAgregar))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(62, 62, 62)
                .addComponent(jrbtAgregar)
                .addGap(29, 29, 29)
                .addComponent(jrbtEditar)
                .addGap(28, 28, 28)
                .addComponent(jrbtEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtblRepExis.setModel(modeloRepExitencia);
        jtblRepExis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblRepExisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblRepExis);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Repuesto");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Proveedor");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("Cantidad");

        jcbxRepuesto.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jcbxProveedor.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtfCantidad.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel5.setText("Precio Compra");

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Precio Venta");

        jtfCompra.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtfVenta.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbxProveedor, 0, 148, Short.MAX_VALUE)
                            .addComponent(jcbxRepuesto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfCantidad))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(34, 34, 34)
                                .addComponent(jtfCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(44, 44, 44)
                                .addComponent(jtfVenta))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jbtnAceptar)
                                .addGap(23, 23, 23)
                                .addComponent(jbtnCancelar)
                                .addGap(0, 76, Short.MAX_VALUE)))))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcbxRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jtfCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jtfVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbtnAceptar)
                        .addComponent(jbtnCancelar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAceptarActionPerformed
        if(!jtfCantidad.getText().isEmpty() && !jtfCompra.getText().isEmpty() && !jtfVenta.getText().isEmpty() && jcbxProveedor.getSelectedIndex() >= 0 && jcbxRepuesto.getSelectedIndex() >= 0){
            if(jrbtAgregar.isSelected()){
                int i = 0;
                Repuestos_LC rp = (Repuestos_LC) jcbxRepuesto.getSelectedItem();
                ProveedoresLC pr = (ProveedoresLC) jcbxProveedor.getSelectedItem();
                
                i = exDao.addExisRepuestos(rp.getIdRepuesto(), pr.getIdProveedor() , Integer.parseInt(jtfCantidad.getText()), Double.parseDouble(jtfCompra.getText()), Double.parseDouble(jtfVenta.getText()));
                
                if(i > 0){
                    Limpiar();
                    getRows();
                } else
                    JOptionPane.showMessageDialog(null, "No se puedo ingresar el nuevo enbarque del repuesto");
            } else if(jrbtEditar.isSelected()){
                int i = 0;
                Repuestos_LC rp = (Repuestos_LC) jcbxRepuesto.getSelectedItem();
                ProveedoresLC pr = (ProveedoresLC) jcbxProveedor.getSelectedItem();
                
                i = exDao.updateExisRepuestos(rp.getIdRepuesto(), pr.getIdProveedor() , Integer.parseInt(jtfCantidad.getText()), Double.parseDouble(jtfCompra.getText()), Double.parseDouble(jtfVenta.getText()), idExistencia);
                
                if(i > 0){
                    Limpiar();
                    getRows();
                } else
                    JOptionPane.showMessageDialog(null, "No se puedo actualizar el nuevo enbarque del repuesto");
            } else if(jrbtEliminar.isSelected()){
                int i = 0;
                
                i = exDao.deleteExisRepuestos(idExistencia);
                
                if(i > 0){
                    Limpiar();
                    getRows();
                } else
                    JOptionPane.showMessageDialog(null, "No se puedo eliminar el nuevo enbarque del repuesto");
            } else{
                JOptionPane.showMessageDialog(null, "Debes seleccionar una accion para ejecutar");
            }
            
            idExistencia = -1;
            exDao.close();  //cerramos las conexiones creadas
        } else {
            JOptionPane.showMessageDialog(null, "No debes dejar ningun campo vacio");
        }
    }//GEN-LAST:event_jbtnAceptarActionPerformed

    private void jtblRepExisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblRepExisMouseClicked
        if(jrbtEditar.isSelected() || jrbtEliminar.isSelected()){
            //"Id","Repuesto", "Proveedor", "Cantidad", "Precio Compra", "Precio Venta"
            idExistencia = (int) modeloRepExitencia.getValueAt(jtblRepExis.getSelectedRow(), 0);
            String rep = (String) modeloRepExitencia.getValueAt(jtblRepExis.getSelectedRow(), 1);
            String prov = (String) modeloRepExitencia.getValueAt(jtblRepExis.getSelectedRow(), 2);
            int cantidad = (int) modeloRepExitencia.getValueAt(jtblRepExis.getSelectedRow(), 3);
            BigDecimal pCompra = (BigDecimal) modeloRepExitencia.getValueAt(jtblRepExis.getSelectedRow(), 4);
            BigDecimal pVenta = (BigDecimal) modeloRepExitencia.getValueAt(jtblRepExis.getSelectedRow(), 5);
            
            jtfCantidad.setText(Integer.toString(cantidad));
            jtfCompra.setText(pCompra.toString());
            jtfVenta.setText(pVenta.toString());
            
            ProveedoresLC nProv;
            Repuestos_LC nRep;
            
            for (int i = 0; i < jcbxProveedor.getItemCount(); i++) {
                jcbxProveedor.setSelectedIndex(i);
                nProv = (ProveedoresLC)  jcbxProveedor.getSelectedItem();
                if(nProv.getNombreProveedor().equals(prov)){
                    break;
                }
            }
            
            for (int i = 0; i < jcbxRepuesto.getItemCount(); i++) {
                jcbxRepuesto.setSelectedIndex(i);
                nRep = (Repuestos_LC) jcbxRepuesto.getSelectedItem();
                
                if(nRep.getNombreRepuesto().equals(rep)){
                    break;
                }
            }
        }
    }//GEN-LAST:event_jtblRepExisMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup jbtgAcciones;
    private javax.swing.JButton jbtnAceptar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JComboBox<String> jcbxProveedor;
    private javax.swing.JComboBox<String> jcbxRepuesto;
    private javax.swing.JRadioButton jrbtAgregar;
    private javax.swing.JRadioButton jrbtEditar;
    private javax.swing.JRadioButton jrbtEliminar;
    private javax.swing.JTable jtblRepExis;
    private javax.swing.JTextField jtfCantidad;
    private javax.swing.JTextField jtfCompra;
    private javax.swing.JTextField jtfVenta;
    // End of variables declaration//GEN-END:variables
}
