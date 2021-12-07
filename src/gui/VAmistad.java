/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class VAmistad extends javax.swing.JFrame {

    ArrayList<String> solicitudes;
    
    public VAmistad(ArrayList<String> a) {
        this.solicitudes = a;
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

        NameField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        FriendButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        RequestList = new javax.swing.JList();
        ExitButton = new javax.swing.JButton();
        ErrorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre:");

        FriendButton.setBackground(new java.awt.Color(150, 238, 238));
        FriendButton.setText("Amistad");
        FriendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FriendButtonActionPerformed(evt);
            }
        });

        RequestList.setModel(new ModeloListaString());
        RequestList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        RequestList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RequestListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(RequestList);

        ExitButton.setBackground(new java.awt.Color(238, 2, 2));
        ExitButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ExitButton.setForeground(new java.awt.Color(251, 251, 251));
        ExitButton.setText("salir");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        ErrorLabel.setForeground(new java.awt.Color(251, 1, 1));
        ErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ErrorLabel.setText("Error! Solicitud incorrecta!");
        ErrorLabel.setEnabled(false);
        ErrorLabel.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(FriendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ExitButton)))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ExitButton)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(ErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FriendButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FriendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FriendButtonActionPerformed
        if(NameField.getText().length() > 0 && solicitudes.contains(NameField.getText())){
            
            
            if(ErrorLabel.isEnabled())
                ErrorLabel.setEnabled(false);
        }else{
            ErrorLabel.setEnabled(true);
        }
    }//GEN-LAST:event_FriendButtonActionPerformed

    private void RequestListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RequestListMouseClicked
        ModeloListaString m = (ModeloListaString) RequestList.getModel();
        if(m.getSize() != 0){
            int i = RequestList.getSelectedIndex();
            NameField.setText(this.solicitudes.get(i));
        }
    }//GEN-LAST:event_RequestListMouseClicked

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_ExitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ErrorLabel;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton FriendButton;
    private javax.swing.JTextField NameField;
    private javax.swing.JList RequestList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
