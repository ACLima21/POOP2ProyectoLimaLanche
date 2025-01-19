/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author Ismael Lima
 */
public class DetailRoomInterfaz extends javax.swing.JFrame {

    /**
     * Creates new form DetailRoomInterfaz
     */
    public DetailRoomInterfaz() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbRoomName = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnGetBack = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cbBreakfast = new javax.swing.JCheckBox();
        cbLunch = new javax.swing.JCheckBox();
        cbDinner = new javax.swing.JCheckBox();
        cbParking = new javax.swing.JCheckBox();
        cbTransport = new javax.swing.JCheckBox();
        btnMoreInformation = new javax.swing.JButton();
        lbRoomType = new javax.swing.JLabel();
        lbPricePerNight = new javax.swing.JLabel();
        lbCapacity = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbRoomSize = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbAvailability = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbServicesIncluded = new javax.swing.JLabel();
        btnWantIt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 250, 236));

        lbRoomName.setFont(new java.awt.Font("Dubai", 1, 36)); // NOI18N
        lbRoomName.setForeground(new java.awt.Color(61, 61, 61));
        lbRoomName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRoomName.setText("*NOMBRE DE HABITACIÓN*");

        jPanel2.setBackground(new java.awt.Color(245, 236, 213));

        jLabel3.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(61, 61, 61));
        jLabel3.setText("Precio por noche:");

        jLabel4.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(61, 61, 61));
        jLabel4.setText("Tipo de habitación:");

        jLabel7.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(61, 61, 61));
        jLabel7.setText("Capacidad máxima:");

        btnGetBack.setBackground(new java.awt.Color(87, 142, 126));
        btnGetBack.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        btnGetBack.setForeground(new java.awt.Color(255, 250, 236));
        btnGetBack.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ismael Lima\\Documents\\GitHub\\SEGUNDO SEMESTRE - POO\\POOP2ProyectoLimaLanche\\P2Proyecto_Lanche_Lima\\src\\main\\java\\icons\\getBack.png")); // NOI18N
        btnGetBack.setText("Volver");
        btnGetBack.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGetBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetBackActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(251, 245, 221));

        jLabel17.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(61, 61, 61));
        jLabel17.setText("Servicios extras:");

        cbBreakfast.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        cbBreakfast.setText("Desayuno");
        cbBreakfast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBreakfastActionPerformed(evt);
            }
        });

        cbLunch.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        cbLunch.setText("Almuerzo");
        cbLunch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLunchActionPerformed(evt);
            }
        });

        cbDinner.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        cbDinner.setText("Cena");
        cbDinner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDinnerActionPerformed(evt);
            }
        });

        cbParking.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        cbParking.setText("Estacionamiento");
        cbParking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbParkingActionPerformed(evt);
            }
        });

        cbTransport.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        cbTransport.setText("Transporte");
        cbTransport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTransportActionPerformed(evt);
            }
        });

        btnMoreInformation.setBackground(new java.awt.Color(251, 245, 221));
        btnMoreInformation.setIcon(new javax.swing.ImageIcon("C:\\Users\\Ismael Lima\\Documents\\GitHub\\SEGUNDO SEMESTRE - POO\\POOP2ProyectoLimaLanche\\P2Proyecto_Lanche_Lima\\src\\main\\java\\icons\\question.png")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(cbBreakfast, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cbLunch, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cbDinner, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cbParking, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cbTransport, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnMoreInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbBreakfast, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbLunch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbDinner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbParking, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTransport, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMoreInformation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbRoomType.setBackground(new java.awt.Color(255, 255, 255));
        lbRoomType.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lbRoomType.setForeground(new java.awt.Color(61, 61, 61));
        lbRoomType.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbRoomType.setText(" usuarioCliente");
        lbRoomType.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        lbRoomType.setOpaque(true);

        lbPricePerNight.setBackground(new java.awt.Color(255, 255, 255));
        lbPricePerNight.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lbPricePerNight.setForeground(new java.awt.Color(61, 61, 61));
        lbPricePerNight.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbPricePerNight.setText(" usuarioCliente");
        lbPricePerNight.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        lbPricePerNight.setOpaque(true);

        lbCapacity.setBackground(new java.awt.Color(255, 255, 255));
        lbCapacity.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lbCapacity.setForeground(new java.awt.Color(61, 61, 61));
        lbCapacity.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCapacity.setText(" usuarioCliente");
        lbCapacity.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        lbCapacity.setOpaque(true);

        jLabel5.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(61, 61, 61));
        jLabel5.setText("Tamaño de habitación:");

        lbRoomSize.setBackground(new java.awt.Color(255, 255, 255));
        lbRoomSize.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lbRoomSize.setForeground(new java.awt.Color(61, 61, 61));
        lbRoomSize.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbRoomSize.setText(" usuarioCliente");
        lbRoomSize.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        lbRoomSize.setOpaque(true);

        jLabel6.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(61, 61, 61));
        jLabel6.setText("Disponibilidad:");

        lbAvailability.setBackground(new java.awt.Color(255, 255, 255));
        lbAvailability.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lbAvailability.setForeground(new java.awt.Color(61, 61, 61));
        lbAvailability.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbAvailability.setText(" usuarioCliente");
        lbAvailability.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        lbAvailability.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(61, 61, 61));
        jLabel8.setText("Servicios incluidos:");

        lbServicesIncluded.setBackground(new java.awt.Color(255, 255, 255));
        lbServicesIncluded.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lbServicesIncluded.setForeground(new java.awt.Color(61, 61, 61));
        lbServicesIncluded.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbServicesIncluded.setText(" usuarioCliente");
        lbServicesIncluded.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        lbServicesIncluded.setOpaque(true);

        btnWantIt.setBackground(new java.awt.Color(87, 142, 126));
        btnWantIt.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        btnWantIt.setForeground(new java.awt.Color(255, 250, 236));
        btnWantIt.setText("¡La Quiero!");
        btnWantIt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWantItActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addComponent(lbRoomType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRoomSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addComponent(lbPricePerNight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addComponent(lbAvailability, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addComponent(lbCapacity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addComponent(lbServicesIncluded, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnWantIt, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnGetBack, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPricePerNight, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbRoomSize, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbServicesIncluded, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGetBack)
                    .addComponent(btnWantIt))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbRoomName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lbRoomName)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGetBackActionPerformed

    private void cbBreakfastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBreakfastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBreakfastActionPerformed

    private void cbLunchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLunchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLunchActionPerformed

    private void cbDinnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDinnerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDinnerActionPerformed

    private void cbParkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbParkingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbParkingActionPerformed

    private void cbTransportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTransportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTransportActionPerformed

    private void btnWantItActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWantItActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnWantItActionPerformed

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
            java.util.logging.Logger.getLogger(DetailRoomInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailRoomInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailRoomInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailRoomInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetailRoomInterfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGetBack;
    public javax.swing.JButton btnMoreInformation;
    public javax.swing.JButton btnWantIt;
    public javax.swing.JCheckBox cbBreakfast;
    public javax.swing.JCheckBox cbDinner;
    public javax.swing.JCheckBox cbLunch;
    public javax.swing.JCheckBox cbParking;
    public javax.swing.JCheckBox cbTransport;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JLabel lbAvailability;
    public javax.swing.JLabel lbCapacity;
    public javax.swing.JLabel lbPricePerNight;
    private javax.swing.JLabel lbRoomName;
    public javax.swing.JLabel lbRoomSize;
    public javax.swing.JLabel lbRoomType;
    public javax.swing.JLabel lbServicesIncluded;
    // End of variables declaration//GEN-END:variables
}
