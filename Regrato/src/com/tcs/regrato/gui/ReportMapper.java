/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui;

import com.tcs.regrato.event.ButtonHandler;
import com.tcs.regrato.modal.ExtractMapperModal;
import com.tcs.regrato.modal.RuleEditorModal;
import com.tcs.regrato.resources.AppIcon;
import com.tcs.regrato.resources.Path;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author 1299792
 */
public class ReportMapper extends javax.swing.JFrame {

    /**
     * Creates new form RuleEditor
     */
    private RuleEditorModal ruleModal;
    private ExtractMapperModal model;
    public ReportMapper() {

        this.ruleModal = RuleEditorModal.getRuleEditorModal();
        this.model = ExtractMapperModal.getExtractMapperModal();
        initComponents();
        setProperty();
        setEvent();
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
        jLabelFileName = new javax.swing.JLabel();
        jButtonSaveRule = new javax.swing.JButton();
        jLabelColumn = new javax.swing.JLabel();
        jLabelRow = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelScriptName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxReportType = new javax.swing.JComboBox<>();
        jTextFieldExtract = new javax.swing.JTextField();
        jTextFieldMapper = new javax.swing.JTextField();
        jButtonExtractFile = new javax.swing.JButton();
        jButtonMapperFile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rule Editor");
        setResizable(false);

        jLabel1.setText("Opening File :");

        jLabelFileName.setText(" ");

        jButtonSaveRule.setText("OK");

        jLabelColumn.setText(" ");

        jLabelRow.setText(" ");

        jLabel4.setText("Mapper File : ");

        jLabelScriptName.setText(" ");

        jLabel5.setText("Create New  Mapper Type :");

        jComboBoxReportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Extract", "Meta File" }));

        jButtonExtractFile.setText("Browse");

        jButtonMapperFile.setText("Browse");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabelColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jLabelRow, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 380, Short.MAX_VALUE)
                        .addComponent(jButtonSaveRule))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldExtract, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                    .addComponent(jTextFieldMapper)))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonExtractFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelFileName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonMapperFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelScriptName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxReportType, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelFileName)
                    .addComponent(jTextFieldExtract, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExtractFile))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelScriptName)
                    .addComponent(jTextFieldMapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMapperFile))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxReportType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSaveRule)
                    .addComponent(jLabelColumn)
                    .addComponent(jLabelRow))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExtractFile;
    private javax.swing.JButton jButtonMapperFile;
    private javax.swing.JButton jButtonSaveRule;
    private javax.swing.JComboBox<String> jComboBoxReportType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelColumn;
    private javax.swing.JLabel jLabelFileName;
    private javax.swing.JLabel jLabelRow;
    private javax.swing.JLabel jLabelScriptName;
    private javax.swing.JTextField jTextFieldExtract;
    private javax.swing.JTextField jTextFieldMapper;
    // End of variables declaration//GEN-END:variables

private void setProperty(){
    this.setIconImage(new AppIcon().getAppIcon());
    this.ruleModal.setFileNamePath(jLabelFileName);
//    this.ruleModal.setCodeEditor(jTextAreaCode);
    this.ruleModal.setCol(jLabelColumn);
    this.ruleModal.setRow(jLabelRow);
    this.model.setExtractFile(jTextFieldExtract);
    this.model.setMapperFile(jTextFieldMapper);
    
}
private void setEvent(){
    ButtonHandler handle = new ButtonHandler();
    jButtonSaveRule.setActionCommand("closeReportMapper");
    jButtonSaveRule.addActionListener(handle);
    jButtonExtractFile.setActionCommand("browseExtractFile");
    jButtonMapperFile.setActionCommand("browseMapperFile");
    jButtonExtractFile.addActionListener(handle);
    jButtonMapperFile.addActionListener(handle);
  
}
}
