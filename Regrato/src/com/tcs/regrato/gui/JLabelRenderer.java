/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author 1299792
 */
public class JLabelRenderer extends JLabel implements TableCellRenderer{

    public JLabelRenderer(String text,ImageIcon icon,int aling) {
     super(text,icon,aling);
    }
   
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
     if(value instanceof JLabel){
      if (isSelected) {
          setBackground(table.getSelectionBackground());
          setForeground(table.getSelectionForeground());
     
      } else {
      setBackground(table.getBackground());
      setForeground(table.getForeground());
      }
//        setText((String) value);
      JLabel label = (JLabel)value;

     try{   
      setIcon(label.getIcon());
      setHorizontalAlignment(label.getHorizontalAlignment());
      setText(label.getText());
     }catch(NullPointerException ex){}
       
     }
      return this;

    }
    
}
