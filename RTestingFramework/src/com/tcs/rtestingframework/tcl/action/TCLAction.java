/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.tcl.action;

import com.tcs.rtestingframework.util.Comman;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author 1299792
 */
public class TCLAction {
    WebDriver driver;
    Comman commanFunction;
    public TCLAction(WebDriver driver){
       this.driver = driver;
       this.commanFunction  = new Comman();
    }
    
    public void pageWait(int seconds){
           this.commanFunction.pageWait(this.driver, seconds);
    }
    
    public void getScreenShort(String fileName) throws AWTException, IOException{
         Robot robot = new Robot();
         BufferedImage screen = null;
         String format = "jpg";
         fileName = fileName + ".jpg";
         Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
         screen = robot.createScreenCapture(rect);
         ImageIO.write(screen, format, new File(fileName));
//         return screen;
    }
   
    public void put(WebElement element,String value){
      try{    
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
       }catch(StaleElementReferenceException ex){
        ex.printStackTrace();
      }
    }
     
    public void click(WebElement element){
        String tagName = element.getTagName();
          if(tagName.equalsIgnoreCase("div")){
           element.click();
          }else{     
           element.sendKeys(Keys.ENTER);
          }

    } 
    

}
