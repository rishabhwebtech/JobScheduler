/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.event;

import com.tcs.regrato.util.Comman;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 1299792
 */
public class MapperTableEvent implements TableModelListener{

    private Comman comman;
    private org.apache.log4j.Logger log ;
    public MapperTableEvent() {
        this.comman = new Comman();
        this.log = this.comman.getLogger();
    }

    
    
    @Override
    public void tableChanged(TableModelEvent e) {
       int row = e.getFirstRow();
       int col = e.getColumn();
       boolean isDel = false;
       
        try {
            isDel = Boolean.valueOf(comman.readSemaphore("isDel"));
             DefaultTableModel model =    (DefaultTableModel)e.getSource();
              String val = "";
            if(isDel){
               
                int delCol = model.findColumn("Delimiter");
                if(col==delCol){
                    val=(String)model.getValueAt(row, col);
                    if(val.length()>1){
                        JOptionPane.showMessageDialog(null,"Invalid Delimiter","Error" ,JOptionPane.ERROR_MESSAGE);
                        model.setValueAt("", row, col);
                        
                    }
               }
            }else{
                int sizeCol = model.findColumn("Size");
                if(sizeCol==-1){
                  sizeCol=model.findColumn("Delimiter");
                }
                if(col==sizeCol){
                 val=(String)model.getValueAt(row, col);
                 if(val.matches("[0-9]+")==false){
                   JOptionPane.showMessageDialog(null,"Invalid Size, It should be numeric","Error" ,JOptionPane.ERROR_MESSAGE);
                   model.setValueAt("0", row, 1);
                   
                 }
                }
            }
        } catch (IOException ex) {
            this.log.error(ex);
            Logger.getLogger(MapperTableEvent.class.getName()).log(Level.SEVERE, null, ex);
        }catch (NullPointerException ex){
           this.log.error(ex);
         }catch(java.lang.ArrayIndexOutOfBoundsException ex){
         
          
         }
        
        
    }
    
}
