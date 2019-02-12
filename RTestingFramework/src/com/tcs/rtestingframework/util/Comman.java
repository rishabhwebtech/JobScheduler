/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author 1299792
 */
public class Comman {
    public void pageWait(WebDriver driver,int seconds){
        WebDriverWait pageWait = new WebDriverWait(driver, seconds);
        ExpectedCondition waitForPageLoad = new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver driver) {
             return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
         }
         };
               try{
               pageWait.until(waitForPageLoad);
             }catch( org.openqa.selenium.JavascriptException ex){
               ex.printStackTrace();
              }
    }
    
    public BufferedWriter writeLogsInFile(String log,String path){
         BufferedWriter out=null;
        try {
 
            // Open given file in append mode.
            out = new BufferedWriter(
                   new FileWriter(path, true));
            out.write(log);
            out.write("\r\n");
            out.flush();
            out.close();
        }
        catch (IOException e) {
           e.printStackTrace();
        }
       return out;
    }
    public String rearrangeBranch(String branchName){
      String branch = "";
      if(branchName.indexOf(">")>0){
        String temp [] = branchName.split(">");
        int len = temp.length;
        for(int i=len-1;i>=0;i--){
           branch = branch +">" + temp[i];
        }
        StringBuffer temp1 = new StringBuffer(branch);
        temp1.deleteCharAt(0);
        branch = temp1.toString();
      }else{
        branch = branchName;
      }
      
      return branch;
    }
}
