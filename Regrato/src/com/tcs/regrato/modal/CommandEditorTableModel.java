/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.modal;

import com.google.common.collect.HashBiMap;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 1299792
 */
public class CommandEditorTableModel extends DefaultTableModel{
       boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true,true
       };
      
//    List<Color> rowColor=new ArrayList<>();
//    private  ArrayList<Color>  rowColor ;
    private Map<Integer,Color> rowColor;
    private Map<Integer,Integer> varToRow; 
    public CommandEditorTableModel() {
//        super();
//        this.rowColor=new ArrayList<>();
        this.rowColor = new HashMap<>();
        this.varToRow = new HashMap<>();
    }

  
     
    @Override
    public boolean isCellEditable(int row, int column) {
     return  this.canEdit[column];
    }
    
    public void setRowColor(int row,Color c){
       this.rowColor.put(row, c);      
        fireTableRowsUpdated(row, row);

    }
    public Color getRowColor(int row){
      return this.rowColor.get(row);
    }
    
    public int getRowNumByVar(int var){
       return this.varToRow.get(var);
    }
    public void setRowNumByVar(int var,int row){
      
       
       this.varToRow.put(var, row);
    }
    
}
