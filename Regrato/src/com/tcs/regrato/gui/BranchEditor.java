/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui;

import com.tcs.jpath.modal.Branch;
import com.tcs.jpath.modal.JConfig;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author 1299792
 */
public class BranchEditor extends DefaultCellEditor{
  protected JComboBox<String> button;
  private JTable table;
  private String label;
  private int rowIndex;
  private boolean isPushed;
  private boolean firstTime=true;
  
  public BranchEditor(JComboBox comboBox) {
    super(comboBox);
    button = comboBox;
    button.setOpaque(true);
//    button.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent e) {
////          deleteRow(rowIndex);
//        fireEditingStopped();
//      }
//    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {

    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();
    isPushed = true;
    rowIndex = row;
  
    this.table = table;
    return button;
  }
//  @Override
//  public Object getCellEditorValue() {
//    if (isPushed) {
//
////       int retValue = JOptionPane.showConfirmDialog(null,"Do you want to delete row ? ","Row Deletion", JOptionPane.OK_CANCEL_OPTION);
////       if(retValue == JOptionPane.OK_OPTION){
////           DefaultTableModel model = (DefaultTableModel)this.table.getModel();
////           model.removeRow(rowIndex);
////        }      
//
//    }
//    isPushed = false;
//
//    return new String(label);
//  }
  @Override
  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }
  @Override
  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }   
}
