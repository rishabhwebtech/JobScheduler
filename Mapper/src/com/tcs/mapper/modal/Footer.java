/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.mapper.modal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1299792
 */
public class Footer {
    private  ArrayList<Field> field; 
    private boolean isEmpty;
    public ArrayList<Field> getField() {
        return field;
    }

    public void setField(ArrayList<Field> field) {
        this.field = field;
    }
    
    
    
    public boolean isEmpty(){
      return isEmpty;
    }
    
        public void setisEmpty(boolean empty){
        this.isEmpty=empty;
    }
    
}
