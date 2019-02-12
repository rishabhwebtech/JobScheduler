/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.tcl.util;

import com.tcs.rtestingframework.tcl.tag.Field;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author 1299792
 */
public class TCLUtil {
    
    
    public boolean isAlert(WebDriver driver) {
        boolean flag=true;
        try{
            Alert alert  =    driver.switchTo().alert();
        }catch(org.openqa.selenium.NoAlertPresentException ex){
          flag = false;
        }
        
        return flag;
    }
    
    public void switchInsideFrame(WebDriver driver,String location){
        if(location.equals(" ") || location.equals("")){
          return;
        }
          String []loc  = location.split(">");
          for(String s : loc){
             driver.switchTo().frame(s);
          }
    }
    
    public void switchOutsideFrame(WebDriver driver){
        driver.switchTo().defaultContent();
    }
    public WebElement findElement(WebDriver driver,Field field)throws NoSuchElementException{
      WebElement el = null;
      String location = field.getLocation();
      switchInsideFrame(driver, location);
      String by = field.getBy();
      String map = field.getMap();
      switch (by){
          case "id":
             
              el=driver.findElement(By.id(map));
           
          break;    
          case "className":
              el=driver.findElement(By.className(map));
          break;
          case "cssSelector":
              el=driver.findElement(By.cssSelector(map));
          break;
      
      }
       
      return el;
    }
    
}
