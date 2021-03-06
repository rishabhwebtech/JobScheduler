/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui;

import com.tcs.regrato.event.ButtonHandler;
import com.tcs.regrato.modal.RunningStatusModal;
import com.tcs.regrato.resources.AppIcon;
import javax.swing.JDialog;

/**
 *
 * @author 1299792
 */
public class RunningStatus extends javax.swing.JDialog {

    /**
     * Creates new form RunningStatus
     */
    private RunningStatusModal runModal;
    public RunningStatus(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.runModal = RunningStatusModal.getRunningStatus();
    
        initComponents();
        setEvent();
        setProperty();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSaveOutput = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneOutput = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Command Output");

        jButtonSaveOutput.setText("Save Output");

        jLabel1.setText("Running Status of Commands");

        jScrollPane2.setViewportView(jTextPaneOutput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSaveOutput)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSaveOutput)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//   public static void main(String []args){
//     RunningStatus s = new RunningStatus(new javax.swing.JFrame(), true);
//     s.setName("DEMO");
//     s.setVisible(true);
//   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSaveOutput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPaneOutput;
    // End of variables declaration//GEN-END:variables

public void setEvent(){
    ButtonHandler handle = new ButtonHandler();
    jButtonSaveOutput.setActionCommand("saveCommandOuput");
    jButtonSaveOutput.addActionListener(handle);

}
public void setProperty(){
      setIconImage(new AppIcon().getAppIcon());
  this.runModal.setOutputScreen(jTextPaneOutput);
  
}

}
