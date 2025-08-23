/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana principal del sistema PURAVIDAMIPYME
 * Gestiona clientes y navegación a otros módulos
 * @author Frank
 */
public class FramePyme extends javax.swing.JFrame {

    private DefaultTableModel modeloTablaClientes;
    
    /**
     * Creates new form FramePyme
     */
    public FramePyme() {
        initComponents();
        configurarTabla();
        configurarEventosMenu();
    }
    /**
     * Configura el modelo de la tabla de clientes
     */
    private void configurarTabla() {
        modeloTablaClientes = (DefaultTableModel) jTableInformacionClientes.getModel();
        // Limpiar las filas vacías por defecto
        modeloTablaClientes.setRowCount(0);
        
        // Agregar algunos datos de ejemplo
        modeloTablaClientes.addRow(new Object[]{"001", "Juan", "Pérez"});
        modeloTablaClientes.addRow(new Object[]{"002", "María", "González"});
        modeloTablaClientes.addRow(new Object[]{"003", "Carlos", "Rodríguez"});
    }
    
    /**
     * Configura los eventos de los elementos del menú
     */
    private void configurarEventosMenu() {
        // Menú Clientes (ventana actual)
        JmenuClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Ya estamos en la ventana de clientes
                JOptionPane.showMessageDialog(FramePyme.this, "Ya se encuentra en la sección de Clientes");
            }
        });
        
        // Menú Catálogo
        jmenuCatalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirVentanaCatalogo();
            }
        });
        
        // Menú Inventario
        jmenuInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirVentanaInventario();
            }
        });
        
        // Menú Órdenes
        JmenuOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirVentanaOrdenes();
            }
        });
        
        // Menú Facturación
        JmenuFacturacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirVentanaFacturacion();
            }
        });
    }
    
    /**
     * Abre la ventana de Catálogo
     */
    private void abrirVentanaCatalogo() {
        try {
            CATALOGO ventanaCatalogo = new CATALOGO();
            ventanaCatalogo.setVisible(true);
            ventanaCatalogo.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir Catálogo: " + e.getMessage());
        }
    }
    
    /**
     * Abre la ventana de Inventario
     */
    private void abrirVentanaInventario() {
        try {
            INVENTARIO ventanaInventario = new INVENTARIO();
            ventanaInventario.setVisible(true);
            ventanaInventario.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir Inventario: " + e.getMessage());
        }
    }
    
    /**
     * Abre la ventana de Órdenes
     */
    private void abrirVentanaOrdenes() {
        try {
            ORDENES ventanaOrdenes = new ORDENES();
            ventanaOrdenes.setVisible(true);
            ventanaOrdenes.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir Órdenes: " + e.getMessage());
        }
    }
    
    /**
     * Abre la ventana de Facturación
     */
    private void abrirVentanaFacturacion() {
        try {
            FACTURIZACION ventanaFacturacion = new FACTURIZACION();
            ventanaFacturacion.setVisible(true);
            ventanaFacturacion.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir Facturación: " + e.getMessage());
        }
    }
    
    /**
     * Valida los campos del formulario
     */
    private boolean validarCampos() {
        String id = txtIDREGISTRAR.getText().trim();
        String nombre = txtNOMBREregistrar.getText().trim();
        String apellido = txtApellidoregistrar.getText().trim();
        
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID es obligatorio");
            txtIDREGISTRAR.requestFocus();
            return false;
        }
        
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio");
            txtNOMBREregistrar.requestFocus();
            return false;
        }
        
        if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El apellido es obligatorio");
            txtApellidoregistrar.requestFocus();
            return false;
        }
        
        // Verificar si el ID ya existe
        for (int i = 0; i < modeloTablaClientes.getRowCount(); i++) {
            if (modeloTablaClientes.getValueAt(i, 0).toString().equals(id)) {
                JOptionPane.showMessageDialog(this, "El ID " + id + " ya existe");
                txtIDREGISTRAR.requestFocus();
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Limpia los campos del formulario
     */
    private void limpiarCampos() {
        txtIDREGISTRAR.setText("");
        txtNOMBREregistrar.setText("");
        txtApellidoregistrar.setText("");
        txtIDREGISTRAR.requestFocus();
    }
    
    /**
     * Agrega un cliente a la tabla
     */
    private void agregarCliente() {
        if (validarCampos()) {
            String id = txtIDREGISTRAR.getText().trim();
            String nombre = txtNOMBREregistrar.getText().trim();
            String apellido = txtApellidoregistrar.getText().trim();
            
            // Agregar nueva fila a la tabla
            modeloTablaClientes.addRow(new Object[]{id, nombre, apellido});
            
            // Mostrar mensaje de confirmación
            JOptionPane.showMessageDialog(this, 
                "Cliente registrado exitosamente:\n" +
                "ID: " + id + "\n" +
                "Nombre: " + nombre + " " + apellido);
            
            // Limpiar campos
            limpiarCampos();
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableInformacionClientes = new javax.swing.JTable();
        txtIDREGISTRAR = new javax.swing.JTextField();
        txtNOMBREregistrar = new javax.swing.JTextField();
        txtApellidoregistrar = new javax.swing.JTextField();
        btnregistrarcliente = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        JmenuClientes = new javax.swing.JMenu();
        jmenuCatalogo = new javax.swing.JMenu();
        jmenuInventario = new javax.swing.JMenu();
        JmenuOrdenes = new javax.swing.JMenu();
        JmenuFacturacion = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("REGISTRAR UN CLIENTE");

        jLabel4.setText("NOMBRE ");

        jLabel5.setText("APELLIDO");

        jLabel6.setText("ID");

        jTableInformacionClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "APELLIDO"
            }
        ));
        jScrollPane1.setViewportView(jTableInformacionClientes);

        txtIDREGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDREGISTRARActionPerformed(evt);
            }
        });

        txtApellidoregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoregistrarActionPerformed(evt);
            }
        });

        btnregistrarcliente.setText("registrar");
        btnregistrarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarclienteActionPerformed(evt);
            }
        });

        JmenuClientes.setText("CLIENTES");
        jMenuBar1.add(JmenuClientes);

        jmenuCatalogo.setText("CATALOGO");
        jMenuBar1.add(jmenuCatalogo);

        jmenuInventario.setText("INVENTARIO");
        jMenuBar1.add(jmenuInventario);

        JmenuOrdenes.setText("ORDENES");
        jMenuBar1.add(JmenuOrdenes);

        JmenuFacturacion.setText("FACTURACION");
        jMenuBar1.add(JmenuFacturacion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtApellidoregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIDREGISTRAR, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(551, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnregistrarcliente)
                                .addGap(336, 336, 336)))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(207, 207, 207)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(119, 119, 119)))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNOMBREregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtIDREGISTRAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNOMBREregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnregistrarcliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtApellidoregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDREGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDREGISTRARActionPerformed
         txtNOMBREregistrar.requestFocus();
    }//GEN-LAST:event_txtIDREGISTRARActionPerformed

    private void txtApellidoregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoregistrarActionPerformed
        // TODO add your handling code here:
          // Al presionar Enter en apellido, registrar cliente
        agregarCliente();
    }//GEN-LAST:event_txtApellidoregistrarActionPerformed

    private void btnregistrarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarclienteActionPerformed
        // TODO add your handling code here:
          agregarCliente();
    }//GEN-LAST:event_btnregistrarclienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(FramePyme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePyme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePyme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePyme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePyme().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu JmenuClientes;
    private javax.swing.JMenu JmenuFacturacion;
    private javax.swing.JMenu JmenuOrdenes;
    private javax.swing.JButton btnregistrarcliente;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableInformacionClientes;
    private javax.swing.JMenu jmenuCatalogo;
    private javax.swing.JMenu jmenuInventario;
    private javax.swing.JTextField txtApellidoregistrar;
    private javax.swing.JTextField txtIDREGISTRAR;
    private javax.swing.JTextField txtNOMBREregistrar;
    // End of variables declaration//GEN-END:variables
}
