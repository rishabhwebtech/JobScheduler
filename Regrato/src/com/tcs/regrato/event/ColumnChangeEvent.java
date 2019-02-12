/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.event;

import com.tcs.regrato.util.Comman;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author 1299792
 */
public class ColumnChangeEvent implements ItemListener{

    private JTable jTable1;
    private Comman comman;
    public ColumnChangeEvent(JTable table) {
         this.comman = new Comman();
         this.jTable1= table;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
              if(e.getStateChange()==ItemEvent.SELECTED){
           
           
            String type =  (String)e.getItem();
              boolean isDel=false;
//              jTable1.setAutoCreateColumnsFromModel(false);
//              jComboBoxDelYesNo.setSelectedItem(type);
            switch(type){
                case "1:Yes":
                    isDel=true;
                  try{
                    jTable1.getColumn("Delimiter").setHeaderValue("Delimiter");
                  }catch(IllegalArgumentException ex){
                     jTable1.getColumn("Size").setHeaderValue("Delimiter");
                  }
                  break;
                case "2:No":
                    isDel=false;
                    try{
                    jTable1.getColumn("Delimiter").setHeaderValue("Size");
                    
                    }catch(IllegalArgumentException ex){
                      
                    }
                    break;  
            
            }
           try{
             
               
               comman.writeSemaphore("isDel", Boolean.toString(isDel));          
           } catch (IOException ex) {
               Logger.getLogger(ColumnChangeEvent.class.getName()).log(Level.SEVERE, null, ex);
           }
            jTable1.revalidate();
            jTable1.repaint();
       }
    }

    
    
    
    
}
