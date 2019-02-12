/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui;

import com.tcs.regrato.modal.CommandEditorTableModel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author 1299792
 */
public class BranchRenderer extends JComboBox<String> implements TableCellRenderer{
    public BranchRenderer(String []args) {
        super(args);
       setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
//      CommandEditorTableModel model=(CommandEditorTableModel)table.getModel();
    
    if (isSelected) {
//          Color c = model.getRowColor(row);
          setBackground(table.getSelectionBackground());
          setForeground(table.getSelectionForeground());
     
    } else {
      setBackground(table.getBackground());
      setForeground(table.getForeground());
   
      
    }
      setSelectedItem(value);
//    setText((value == null) ? "" : value.toString());
    return this;
  } 
}
