
package com.tcs.regrato.gui;
import com.tcs.regrato.modal.CommandEditorTableModel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
public class ButtonRenderer extends JButton implements TableCellRenderer {

  public ButtonRenderer() {
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
      CommandEditorTableModel model=(CommandEditorTableModel)table.getModel();
     
//      setForeground(Color.BLACK);
    if (isSelected) {
      try{
          Color c = model.getRowColor(row);
          setBackground(c);
      }catch(java.lang.IndexOutOfBoundsException ex){
       setBackground(table.getSelectionBackground());
      }
//      setForeground(table.getSelectionForeground());
       setForeground(Color.BLACK);
    } else {
        setForeground(Color.BLACK);
//      setForeground(table.getForeground());
      try{
          Color c = model.getRowColor(row);
          if(c==null){
            setBackground(Color.WHITE);
          }else{
          setBackground(c);
          }
      }catch(java.lang.IndexOutOfBoundsException ex){
//           setBackground(UIManager.getColor("Button.background"));
               setBackground(Color.WHITE);
      }
      
    }
    

     
    setText((value == null) ? "" : value.toString());
    return this;
  }
}