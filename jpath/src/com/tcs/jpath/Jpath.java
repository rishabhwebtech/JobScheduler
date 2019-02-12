/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.jpath;

import com.google.gson.JsonObject;
import com.tcs.jpath.connect.SeleniumConnect;
import com.tcs.jpath.exception.BrowserNotSupported;
import com.tcs.jpath.function.JavaScript;
import com.tcs.jpath.info.GetInfo;
import com.tcs.jpath.util.Comman;
import com.tcs.jpath.util.ExcelWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
 
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.write.WriteException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author 1299792
 */
public class Jpath {

    /**
     * @param args the command line arguments
     */
    String fileName;
    SeleniumConnect connect=null;
    public Jpath(String fileName){
      this.fileName = fileName;
    }
    
    public Jpath( ){
       
    }
    
    public void traceEvents() throws BrowserNotSupported, FileNotFoundException{
      if(this.fileName.equals("")){
         throw new FileNotFoundException("No Name Given To Store Trace");
      }
      Comman comman = new Comman();
      comman.deleteTempFile();
      GetInfo info = new GetInfo();
            JsonObject infoObject = info.getProductInfo();
            String appName = infoObject.get("Application_Name").getAsString();
            String authorName = infoObject.get("Author_Name").getAsString();
            String version    = infoObject.get("Version").getAsString();
            System.out.println(appName+" Application Started");
            System.out.println("Version "+version);
            System.out.println("Author "+authorName);
            System.out.println("");
            System.out.println("-------------------------------");
          try{
            this.connect = SeleniumConnect.getInstance();
            WebDriver driver = this.connect.getDriver();
            JavaScript script = new JavaScript(driver);
            com.tcs.jpath.util.Login login = new com.tcs.jpath.util.Login(driver);
            login.doLogin();
            comman.pageWait(driver, 60);
            script.addDOMEvent();
          }catch(JavascriptException ex){
              ex.printStackTrace();
                        try {
                this.connect.closeDriver();
                System.exit(0);
            } catch (BrowserNotSupported e) {
                Logger.getLogger(Jpath.class.getName()).log(Level.SEVERE, null, e);
            }
          } 
    }
    public void stopTraceEvents() throws IOException, FileNotFoundException, BrowserNotSupported, WriteException{
         ExcelWriter write = new ExcelWriter(this.fileName);
               if(this.fileName == null){
                  throw new FileNotFoundException("No Name Given To Store Trace");
                }
         write.writeAllElementsDetailsInExcel();
         this.connect = SeleniumConnect.getInstance();
         this.connect.closeDriver();
    }
//    public static void main(String[] args) throws IOException, FileNotFoundException, WriteException {
//        SeleniumConnect connect=null;
//         Scanner in = new Scanner(System.in);
//        try {
//            String mode="" ; 
//            String fileName = "temp.xls";
//            Comman comman = new Comman();
//            comman.deleteTempFile();
//            GetInfo info = new GetInfo();
//            JsonObject infoObject = info.getProductInfo();
//            String appName = infoObject.get("Application_Name").getAsString();
//            String authorName = infoObject.get("Author_Name").getAsString();
//            String version    = infoObject.get("Version").getAsString();
//            System.out.println(appName+" Application Started");
//            System.out.println(mode+"Started");
//            System.out.println("Version "+version);
//            System.out.println("Author "+authorName);
//            System.out.println("");
//            System.out.println("-------------------------------");
////            ExcelWriter write = new ExcelWriter("TestCase_1.xls");
//            ExcelWriter write = new ExcelWriter(fileName);
//            connect = SeleniumConnect.getInstance();
//            WebDriver driver = connect.getDriver();
//            JavaScript script = new JavaScript(driver);
//            System.out.println("Do you want to start capturing browser events(y/n)");
//            char option1 = in.next().charAt(0);
//           
//            if(option1=='y' || option1 == 'Y'){
//                
//            script.addDOMEvent();
//            System.out.println("Capturing Started....");
//            }else{
//                driver.close();
//              
//            }
//            System.out.println("Do you want to stop capturing browser events(y/n)");
//            while((option1 = in.next().charAt(0))=='n'){
//              System.out.println("Do you want to stop capturing browser events(y/n)");
//            }
//            if(option1=='y' || option1=='Y'){
//                write.writeAllElementsDetailsInExcel();
//            }else{
//              System.out.println("Invalid Choice ");
//              
//            }
//        } catch (BrowserNotSupported ex) {
//            Logger.getLogger(Jpath.class.getName()).log(Level.SEVERE, null, ex);
//        } catch(JavascriptException ex){
////            ex.printStackTrace();
//            
//        } finally{
//            try {
//                connect.closeDriver();
//                System.exit(0);
//            } catch (BrowserNotSupported ex) {
//                Logger.getLogger(Jpath.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//        
//    }
    
}
