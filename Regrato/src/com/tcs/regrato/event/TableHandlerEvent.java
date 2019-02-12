/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.event;

import com.tcs.regrato.modal.CommandEditorModal;
import com.tcs.regrato.modal.CommandEditorTableModel;
import com.tcs.regrato.modal.ErrorStore;
import com.tcs.regrato.util.Comman;
import com.tcs.rtestingframework.tcl.parser.TCLRule;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author 1299792
 */
public class TableHandlerEvent implements TableModelListener{

    Comman comman;
    public TableHandlerEvent() {
        this.comman=new Comman();
    }

    
    
    @Override
    public void tableChanged(TableModelEvent e) {
       
        TCLRule rule = new TCLRule();
        int row = e.getFirstRow();
        
        int column = e.getColumn();
        ErrorStore store = ErrorStore.getErrorRefrence();
        String msg = "";
        
        CommandEditorTableModel model = (CommandEditorTableModel)e.getSource();
        int hashCode = model.hashCode();
       
//        System.out.println("Hash Code "+hashCode);
        String columnName = model.getColumnName(column);
        try{
        String data = (String)model.getValueAt(row, column);
//        int var  = Integer.parseInt((String)model.getValueAt(row, 0));
//        model.setRowNumByVar(var, row);
        boolean retValueC =true;
        if(columnName.equals("Action")){
        int retValue = JOptionPane.showConfirmDialog(null,"Do you want to delete row ? ","Row Deletion", JOptionPane.OK_CANCEL_OPTION);
           if(retValue == JOptionPane.OK_OPTION){
              CommandEditorTableModel m = (CommandEditorTableModel)model;
              m.removeRow(row);
              
              new Comman().rearrangeVariableInJTable(model);
           }
          return;
        }else if(columnName.equals("")){
           return;
        }else if(columnName.equals("Command")){
        
        
         retValueC = rule.isValidCommand(data);
          
         if(retValueC==false){
              boolean isError=false;
            if(column == 1){
               msg = "Invalid Command";
               
               isError = true;
         
              store.set(hashCode,Integer.toString(row+1), isError);              
               
            }else{
               store.set(hashCode,Integer.toString(row+1), isError);     
            }
           
        }       
        
        }else if(columnName.equals("Map Name")){
           boolean isHost;
            try {
              isHost = Boolean.valueOf(comman.readSemaphore("isHost"));
                
                if(isHost==true){
                     boolean isError = false;
                 retValueC =  rule.isValidMode(data);
                if(retValueC==false){
                   msg = "Invalid Mode";
                   isError=true;
                   store.set(hashCode,Integer.toString(row+1), isError);
                }else{
                   store.set(hashCode,Integer.toString(row+1), false);
                }
           }
                
            } catch (IOException ex) {
                Logger.getLogger(TableHandlerEvent.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        

        if(msg.equals("")==false){
            JOptionPane.showMessageDialog(null, msg,"Error",JOptionPane.ERROR_MESSAGE);
        }
        }catch(IndexOutOfBoundsException ex){
          
        }
    }
     
}
