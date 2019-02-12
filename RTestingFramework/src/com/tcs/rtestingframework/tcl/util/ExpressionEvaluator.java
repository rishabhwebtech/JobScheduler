/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.tcl.util;

import com.tcs.rtestingframework.tcl.exception.InvalidSyntaxException;
import com.tcs.rtestingframework.tcl.parser.TCLParser;
import com.tcs.rtestingframework.tcl.tag.Field;
import com.tcs.rtestingframework.util.StringUtils;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author 1299792
 */
public class ExpressionEvaluator {
    WebDriver driver;
    TCLParser parser;
    int endPoint=0;
    TCLUtil util ; 
    public ExpressionEvaluator(WebDriver driver,TCLParser parser){
      this.driver = driver;
      this.parser = parser;
      this.util =   new TCLUtil();
    }
    
 public boolean eval(String expression) throws NumberFormatException, InvalidSyntaxException{
   boolean  retValue=false;
   Stack<String> temp=  scanner(expression);
   String firstOperand = "";
   List<String> result = new ArrayList<String>();
    while(true){
       try{
         
        String operation = temp.pop();
        if(operation.equals("(")){
           String insideBracket = temp.pop();
            
        }else if(operation.equals("==")){
          String secondOperand = temp.pop();
          if(firstOperand.indexOf("@")==0){
           boolean retValue1 =    specialVariableHandling(firstOperand,secondOperand,"==");
           result.add(String.valueOf(retValue1));
          }else{
          Field variable =  this.parser.getDeclaredVariables(firstOperand);
         
          String type = variable.getType();
          WebElement element = null;
          try{
            element = this.parser.getVariableElement(driver, variable);
          }catch(org.openqa.selenium.NoSuchElementException ex){
             
              return false;
          }
          String value = element.getText();
          switch(type){
              case "String":
              {
               if(value.equals(secondOperand)){
                result.add("true");
              }else{
                result.add("false");
              }
                   break;
              }
              case "Integer" :
              {
                 int v =  Integer.parseInt(value);
                 if(v==Integer.parseInt(secondOperand)){
                   result.add("true");
                 }else{
                   result.add("false");
                 }
                  break;
              } 
              case "Float":
              {
                 float v =  Float.parseFloat(value);
                 if(v==Float.parseFloat(secondOperand)){
                   result.add("true");
                 }else{
                   result.add("false");
                 }   
                break;
              }
          }
          
        }
        }else if(operation.equals(">=")){
           
        }else if(operation.equals("<=")){
          
        }else if(operation.equals("!=")){
          String secondOperand = temp.pop();
          Field variable =  this.parser.getDeclaredVariables(firstOperand);
          String type = variable.getType();
          String location = variable.getLocation();
          WebElement element = null;
          try{
          element = this.parser.getVariableElement(driver, variable);
          }catch(NoSuchElementException ex){
             return true;
          }
          String value = element.getText();
          if(location.length()!=0){
            driver.switchTo().defaultContent();
          } 
          
          
          switch(type){
              case "String":
              {
               if(!value.equals(secondOperand)){
                result.add("true");
              }else{
                result.add("false");
              }
                   break;
              }
              case "Integer" :
              {
                 int v =  Integer.parseInt(value);
                 if(v!=Integer.parseInt(secondOperand)){
                   result.add("true");
                 }else{
                   result.add("false");
                 }
                  break;
              } 
              case "Float":
              {
                 float v =  Float.parseFloat(value);
                 if(v!=Float.parseFloat(secondOperand)){
                   result.add("true");
                 }else{
                   result.add("false");
                 }   
                break;
              }
          }
        }else if(operation.equals(")")){
        
        }else if(operation.equals("<>")){
           String secondOperand = temp.pop();
           Field variable =  this.parser.getDeclaredVariables(firstOperand);
           String type = variable.getType();
           WebElement element = this.parser.getVariableElement(driver, variable);
           String value = element.getText();
          switch(type){
              case "String":
              {
               if(value.contains(secondOperand)){
                result.add("true");
              }else{
                result.add("false");
              }
                   break;
              }
          }
        }else if(operation.equals("||")){
           result.add("||");
        }else if(operation.equals("&&")){
           result.add("&&");
        }else {
          firstOperand = operation;
        }
        
       }catch(EmptyStackException ex){
         break;
       }
    }
    
    retValue=Boolean.valueOf(result.get(0));
 
    
    
   return retValue;
 }
    
  private boolean logicalOperationEval(List<String> result){
      boolean flag = false;
      
      return flag;
    
      
  }
    
  private Stack<String> scanner(String expression) throws InvalidSyntaxException{
       String []sc = null;
       Stack<String> operation = new Stack<String>();
       String operand="";
       String operator="";
       List<String> storage = new ArrayList<>();
 
       for(int i =0;i<expression.length();i++){
        char token = expression.charAt(i);
        
         if(Character.isLetter(token) || Character.isDigit(token) || token=='@'){
           if(operator.length()>0){
              if(operator.indexOf(")")>=0 || operator.indexOf("(")>=0){
                String subCompinedOperator = "";
                for(int j=0;j<operator.length();j++){
                   char subToken = operator.charAt(j);
                   if(subToken=='|'){
                      
                      storage.add("||");
                      j=j+1;
                   }else if(subToken=='&'){
                     storage.add("&&");
                      j=j+1;
                   }else if(subToken=='('){
                     storage.add("(");
                   }else if(subToken==')'){
                     storage.add(")");
                   }else if(subToken=='>'){
                    if(operator.contains(">=")){
                         storage.add(">=");
                         j=j+1;
                    }else {
                       storage.add(">");
                    }
                   }else if(subToken=='<'){
                    if(operator.contains("<=")){
                         storage.add("<=");
                         j=j+1;
                    }else {
                       storage.add("<");
                    }
                   }else if(subToken=='='){
                    if(operator.contains("==")){
                         storage.add("==");
                         j=j+1;
                    }else {
                        throw new InvalidSyntaxException("= not allowed in expression");
                    }
                   }else if(subToken=='!'){
                       if(operator.contains("!=")){
                          storage.add("!=");
                           j=j+1;                       
                       }else{
                         throw new InvalidSyntaxException("! not allowed in expression");
                       }
                   }
                   
                }
             
              }else{
                 storage.add(operator);
              }
             operator="";
           }
           operand=operand+token;
           
         }else if(StringUtils.isOperator(token) || StringUtils.isMathOperator(token)){
           
           if(operand.length()>0){
               storage.add(operand);
               operand=""; 
           }
           
           operator=operator+token;
             
         }else if(token=='\''){
             operand=getStringScanner(expression,i+1);
             if(operator.length()>0){
             storage.add(operator);
             operator="";
             }
             i=endPoint;
         }else if(token==' '){
           continue;
         }
          
       }
         if(operand.length()>0){
               storage.add(operand);
               operand=""; 
           }
         
         if(operator.length()>0){
            storage.add(operator);
            operator="";
              
           
         }
       
       sc = storage.stream().toArray(String[]::new);
       
      for(int i=sc.length-1;i>=0;i--){
          operation.push(sc[i]);
      }
       return operation;
    
    }
   
   
 private String getStringScanner(String expression,int startingPoint){
   String string="";
   endPoint=0;
   for(int i = startingPoint;i<expression.length();i++){
        char token = expression.charAt(i);
        if(token=='\''){
            endPoint=i;
           break;
        }
   }
   string = expression.substring(startingPoint,endPoint);
   return string;
 }
 
 
 private boolean specialVariableHandling(String specialVariable,String secondOperand,String operator){
    
    boolean flag=false;
     switch(specialVariable){
         case "@alert":
         Alert alert = null;
         try{ 
          alert= this.driver.switchTo().alert();
          String text = alert.getText();
          if(operator.equals("==")){
              if(secondOperand.equals(text)){
                flag=true;
              }else{
                flag=false;
              }
          }else if(operator.equals("!=")){
              if(!secondOperand.equals(text)){
               flag=true;
              }else{
                flag=false;
              }
          }
          
          alert.accept();
          this.util.switchOutsideFrame(driver);
         }catch(UnhandledAlertException ex){
            if(operator.equals("==")){
              flag=false;
            }else if(operator.equals("!=")){
              flag=true;
            }
         }catch(NoAlertPresentException ex){
               if(operator.equals("==")){
              flag=false;
            }else if(operator.equals("!=")){
              flag=true;
            } 
         }
         break;
     
     
     }
    
    
    
    return flag;
   
 }
}
