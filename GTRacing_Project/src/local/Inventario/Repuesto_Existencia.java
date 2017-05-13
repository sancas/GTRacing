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
        jPanelAcciones = new javax.swing.JPanel();
        jrbtnAdd = new javax.swing.JRadioButton();
        jrbtnUpdate = new javax.swing.JRadioButton();
        jrbtnDelete = new javax.swing.JRadioButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Repuestos Existencia");
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(500, 500));

        jtblRepExis.setModel(modeloRepExitencia);
        jtblRepExis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblRepExisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblRepExis);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel2.setText("Repuesto:");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Proveedor:");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("Cantidad:");

        jcbxRepuesto.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jcbxProveedor.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtfCantidad.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel5.setText("Precio Compra:");

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel6.setText("Precio Venta:");

        jtfCompra.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jtfVenta.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        jbtnAceptar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jbtnAceptar.setText("Aceptar");
        jbtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAceptarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelAcciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfVenta)
                            .addComponent(jtfCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfCantidad)
                            .addComponent(jcbxProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jbtnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(47, 47, 47)
                        .addComponent(jcbxRepuesto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcbxRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtfVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtnAceptar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAceptarActionPerformed
        if(!jtfCantidad.getText().isEmpty() && !jtfCompra.getText().isEmpty() && !jtfVenta.getText().isEmpty() && jcbxProveedor.getSelectedIndex() >= 0 && jcbxRepuesto.getSelectedIndex() >= 0){
            if(jrbtnAdd.isSelected()){
                int i = 0;
                Repuestos_LC rp = (Repuestos_LC) jcbxRepuesto.getSelectedItem();
                ProveedoresLC pr = (ProveedoresLC) jcbxProveedor.getSelectedItem();
                
                i = exDao.addExisRepuestos(rp.getIdRepuesto(), pr.getIdProveedor() , Integer.parseInt(jtfCantidad.getText()), Double.parseDouble(jtfCompra.getText()), Double.parseDouble(jtfVenta.getText()));
                
                if(i > 0){
                    Limpiar();
                    getRows();
                } else
                    JOptionPane.showMessageDialog(null, "No se puedo ingresar el nuevo enbarque del repuesto");
            } else if(jrbtnUpdate.isSelected()){
                int i = 0;
                Repuestos_LC rp = (Repuestos_LC) jcbxRepuesto.getSelectedItem();
                ProveedoresLC pr = (ProveedoresLC) jcbxProveedor.getSelectedItem();
                
                i = exDao.updateExisRepuestos(rp.getIdRepuesto(), pr.getIdProveedor() , Integer.parseInt(jtfCantidad.getText()), Double.parseDouble(jtfCompra.getText()), Double.parseDouble(jtfVenta.getText()), idExistencia);
                
                if(i > 0){
                    Limpiar();
                    getRows();
                } else
                    JOptionPane.showMessageDialog(null, "No se puedo actualizar el nuevo enbarque del repuesto");
            } else if(jrbtnDelete.isSelected()){
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
        if(jrbtnUpdate.isSelected() || jrbtnDelete.isSelected()){
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanelAcciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup jbtgAcciones;
    private javax.swing.JButton jbtnAceptar;
    private javax.swing.JComboBox<String> jcbxProveedor;
    private javax.swing.JComboBox<String> jcbxRepuesto;
    private javax.swing.JRadioButton jrbtnAdd;
    private javax.swing.JRadioButton jrbtnDelete;
    private javax.swing.JRadioButton jrbtnUpdate;
    private javax.swing.JTable jtblRepExis;
    private javax.swing.JTextField jtfCantidad;
    private javax.swing.JTextField jtfCompra;
    private javax.swing.JTextField jtfVenta;
    // End of variables declaration//GEN-END:variables
}
