/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.StyleConstants;

/**
 *
 * @author 1299792
 */
public class JLabelEditor extends DefaultCellEditor{
    private JLabel label;
    private JTable table;
    public JLabelEditor(JTextField textField) {
        super(textField);
        label = new JLabel("Empty",new ImageIcon(),JLabel.LEFT);
        label.setOpaque(true);
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if(value instanceof JLabel){
    if (isSelected) {
      label.setForeground(table.getSelectionForeground());
      label.setBackground(table.getSelectionBackground());
    } else {
      label.setForeground(table.getForeground());
      label.setBackground(table.getBackground());
    }
        

    try{   
      JLabel thisLabel = (JLabel)value;
      label.setIcon(thisLabel.getIcon());
      label.setHorizontalAlignment(thisLabel.getHorizontalAlignment());
      label.setText(thisLabel.getText());
     }catch(NullPointerException ex){
     
     } 
        return label;
     
     }
        this.table = table;
        
        return super.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
    
}
