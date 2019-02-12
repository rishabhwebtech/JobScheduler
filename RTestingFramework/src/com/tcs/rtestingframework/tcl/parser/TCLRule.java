/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.rtestingframework.tcl.parser;

import java.util.Arrays;

/**
 *
 * @author 1299792
 */
public class TCLRule {
  
    public boolean isValidCommand(String i){
      boolean flag=false;
      String indent[] = TCLRules.COMMAND;
      Arrays.sort(indent);
      int retValue = Arrays.binarySearch(indent,i);
      if(retValue >= 0){
        flag = true;
      }
      return flag;
    }
    
    public boolean isValidMode(String i ){
      boolean flag=false;
      String indent[] = TCLRules.MODE;
      for(String s:indent){
        if(i.equals(s)){
          flag=true;
        }
      }
      
      return flag;  
    
    }
    
   public boolean isValidAnnotation(String i){
      boolean flag=false;
      String anno[] = TCLRules.ANNOTAION;
      Arrays.sort(anno);
      int retValue = Arrays.binarySearch(anno,i);
      if(retValue >= 0){
        flag = true;
      }
      return flag; 
   }
}
