/**
 * @version 1.0 11/09/98
 */
package com.tcs.regrato.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class ButtonEditor extends DefaultCellEditor {
  protected JButton button;
  
  private JTable table;
  private String label;
  private int rowIndex;
  private boolean isPushed;

  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
//          deleteRow(rowIndex);
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
        
      button.setForeground(Color.BLACK);
      button.setBackground(Color.WHITE);
//      button.setForeground(table.getForeground());
//      button.setBackground(table.getBackground());
    }

    
    button.setFocusPainted(false);
    button.setRolloverEnabled(false);
    button.setEnabled(false);
    label = (value == null) ? "" : value.toString();
    button.setText(label);
    isPushed = true;
    rowIndex = row;
    this.table = table;
    return button;
  }
  @Override
  public Object getCellEditorValue() {
    if (isPushed) {

//       int retValue = JOptionPane.showConfirmDialog(null,"Do you want to delete row ? ","Row Deletion", JOptionPane.OK_CANCEL_OPTION);
//       if(retValue == JOptionPane.OK_OPTION){
//           DefaultTableModel model = (DefaultTableModel)this.table.getModel();
//           model.removeRow(rowIndex);
//        }      

    }
    isPushed = false;
    return new String(label);
  }
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