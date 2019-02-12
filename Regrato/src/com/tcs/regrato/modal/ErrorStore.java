/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.modal;

import java.util.Collections;
import java.util.HashMap;
import javax.swing.JTable;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections.map.MultiKeyMap;

/**
 *
 * @author 1299792
 */
public class ErrorStore {
    
//    public   HashMap<String,Boolean> errorRow = null;
    private MultiKeyMap errorRow = null;
    private static ErrorStore instance = null;
    private ErrorStore(){}
    
   public static ErrorStore getErrorRefrence(){
       if(instance == null){
             instance  = new ErrorStore();
//             errorRow = new HashMap<>();
             
//             instance.errorRow = new HashMap<>();  
              instance.errorRow = new MultiKeyMap();
              
             Collections.synchronizedMap(instance.errorRow);
       }
       
       return instance;
   
   }
  
//   public HashMap<String,Boolean> get(){
    public MultiKeyMap get(){
      return instance.errorRow;
   }
   public void set(int hashTable,String key,Boolean value){
       instance.errorRow.put(hashTable,key, value);
   }
}
