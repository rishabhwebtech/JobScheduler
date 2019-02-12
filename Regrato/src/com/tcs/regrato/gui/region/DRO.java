/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.region;

import com.tcs.regrato.event.ButtonHandler;
import com.tcs.regrato.gui.thread.LoadMflagsDate;
import com.tcs.regrato.resources.AppIcon;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;

/**
 *
 * @author 1299792
 */
public class DRO extends javax.swing.JDialog {

    /**
     * Creates new form DRO
     */
    public DRO(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setProperty();
        setEvent();
        LoadMflagsDate d = new LoadMflagsDate(jLabelDisplayMflagsDate, "001",jComboBoxChangeInst);
        d.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldDD = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldMM = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldYYYY = new javax.swing.JTextField();
        jButtonDODro = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabelDisplayMflagsDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxChangeInst = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DRO/DRB");

        jLabel1.setText("Enter DRO/DRB Date:");

        jTextFieldDD.setName("DD"); // NOI18N

        jLabel2.setText("DD");

        jLabel3.setText("MM");

        jTextFieldMM.setName("MM"); // NOI18N

        jLabel4.setText("YYYY");

        jTextFieldYYYY.setText(" ");
        jTextFieldYYYY.setName("YYYY"); // NOI18N

        jButtonDODro.setText("OK");

        jLabel5.setText("Current Mflags Date :");

        jLabelDisplayMflagsDate.setText("Loading Current Mflags Date ......");

        jLabel6.setText("Inst :");

        jComboBoxChangeInst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "001", "002" }));
        jComboBoxChangeInst.setName("inst"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonDODro))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldDD, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldMM, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelDisplayMflagsDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jComboBoxChangeInst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelDisplayMflagsDate)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxChangeInst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldMM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButtonDODro)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDODro;
    private javax.swing.JComboBox<String> jComboBoxChangeInst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelDisplayMflagsDate;
    private javax.swing.JTextField jTextFieldDD;
    private javax.swing.JTextField jTextFieldMM;
    private javax.swing.JTextField jTextFieldYYYY;
    // End of variables declaration//GEN-END:variables

public void setProperty(){
//    setModalityType(ModalityType.MODELESS);
  setIconImage(new AppIcon().getAppIcon());
  JTextFieldLimit ddLimit2 = new JTextFieldLimit(2);
  JTextFieldLimit mmLimit2 = new JTextFieldLimit(2);
  JTextFieldLimit yyyyLimit4 = new JTextFieldLimit(4);
  jTextFieldDD.setDocument(ddLimit2);
  jTextFieldMM.setDocument(mmLimit2);
  jTextFieldYYYY.setDocument(yyyyLimit4);
  setResizable(false);
  
}
public void setEvent(){
    ButtonHandler handle = new ButtonHandler();
    jButtonDODro.setActionCommand("doDroDrb");
    jButtonDODro.addActionListener(handle);
    jComboBoxChangeInst.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
          String loadText="Loading Current Mflags Date ......";
          jLabelDisplayMflagsDate.setText(loadText);
          if(e.getStateChange()==ItemEvent.SELECTED){
            String inst =  (String)e.getItem();
            LoadMflagsDate d = new LoadMflagsDate(jLabelDisplayMflagsDate, inst,jComboBoxChangeInst);
            d.execute();
           }
        }
    });
}
}