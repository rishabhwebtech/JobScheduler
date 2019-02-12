/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.util;

import com.tcs.regrato.modal.ErrorStore;
import java.util.HashMap;
import javax.swing.JTable;
import org.apache.commons.collections.map.MultiKeyMap;


/**
 *
 * @author 1299792
 */
public class ErrorChecker {
    public int isError(int hashCode,String []key){
       boolean is1=false;
       int rowNum=0;
        ErrorStore store = ErrorStore.getErrorRefrence();
//        HashMap map = store.get();
        MultiKeyMap map = store.get();
       if(key.length!=0){
      
       for(String k : key){
           try{
             boolean flag =  (Boolean)map.get(hashCode,k);
           if(flag==true){
             is1 = true;
             rowNum = Integer.parseInt(k);
             break;
            }
           }catch(NullPointerException ex){}
           }
           
       }
//       return is1;
        return rowNum;
      
    }
    public static String getErrorMessage(int retValue){
      String str=null;
      switch(retValue){
          case 404:
              str = "No Element Found";
           break;
          case 500:
              str = "Unable to connect to server";
          break;
      }
      
      
      return str;
    }
}
