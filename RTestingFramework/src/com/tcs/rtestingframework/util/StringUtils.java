/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.util;

/**
 *
 * @author 1299792
 */
public class StringUtils {
    public static boolean isOperator(char c){
      boolean flag=false;
      String op = String.valueOf(c);
      String operatorRegex = "[,/!%<>=|&\\(\\)]";
      if(op.matches(operatorRegex)){
        flag = true;
     }
      
      return flag;
    }
    
    
   public static boolean isOperator(String c){
      boolean flag=false;
      String op = c;
      String operatorRegex = "(>=|<=|!=|==|!=|>|<)";
      
      if(op.matches(operatorRegex)){
        flag = true;
     }
      
      return flag;
    }
   
   
    public static boolean isLogicalOperator(String c){
          boolean flag=false;
          String op = String.valueOf(c);
          String operatorRegex = "(\\|\\||&&)";
           if(op.matches(operatorRegex)){
            flag = true;
           } 
       return flag;
    }
   
    
    
    public static boolean isMathOperator(char c){
     
          boolean flag=false;
          String op = String.valueOf(c);
          String operatorRegex = "[-+*/]";
           if(op.matches(operatorRegex)){
            flag = true;
           }
      
      return flag;
    }
}
