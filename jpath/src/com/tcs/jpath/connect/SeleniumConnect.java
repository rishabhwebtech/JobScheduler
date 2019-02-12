/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.jpath.connect;



import com.tcs.jpath.exception.BrowserNotSupported;
import com.tcs.jpath.modal.DriverProperty;
import com.tcs.jpath.modal.JConfig;
import com.tcs.jpath.util.Comman;
import com.tcs.jpath.util.XmlReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author 1299792
 */
public class SeleniumConnect {

    private static SeleniumConnect instance=null;
    
    
    private SeleniumConnect(){
       
    }
    private static WebDriver driver ;
    public static SeleniumConnect getInstance() throws BrowserNotSupported{
        Comman commanFunction = new Comman();
//      if(instance==null){
         DesiredCapabilities cap =null;
         InternetExplorerOptions ieOptions = null;
          JConfig util = new JConfig();
          DriverProperty driverProperty =  util.getTypeOfDrivetSet();
//        if(!driverProperty.isEmpty()){
            if(driverProperty.getType().equals(DriverType.IE)){
               System.setProperty("webdriver.ie.driver", driverProperty.getPath());
               cap = DesiredCapabilities.internetExplorer();
               cap.setCapability("ignoreZoomSetting", true);
//               LoggingPreferences prep = new LoggingPreferences();
//               prep.enable(LogType.BROWSER, Level.ALL);
//               cap.setCapability(CapabilityType.LOGGING_PREFS, prep);
               
               cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
               ieOptions = new InternetExplorerOptions(cap);
               driver = new InternetExplorerDriver(ieOptions);
               driver.navigate().to(driverProperty.getLoginPage());
               commanFunction.pageWait(driver, 60);
              
            }else if(driverProperty.getType().equals(DriverType.FIREFOX)){
//               DesiredCapabilities firefoxCap=null;
//               firefoxCap = DesiredCapabilities.firefox();
               FirefoxOptions options = new FirefoxOptions();
//               firefoxCap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.IGNORE);
                options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
               System.setProperty("webdriver.gecko.driver", driverProperty.getPath());
//                 System.setProperty("webdriver.firefox.marionette",driverProperty.getPath());
//               driver = new FirefoxDriver(firefoxCap);
               options.setLegacy(true);
//               options.setCapability("marionette",true);
                driver = new FirefoxDriver(options);
               
                System.out.println("URL IS "+driverProperty.getLoginPage());
//                driver.navigate().to(driverProperty.getLoginPage());
               driver.get(driverProperty.getLoginPage());
               commanFunction.pageWait(driver, 60);
            }else{
             throw new BrowserNotSupported(driverProperty.getType()+" Browser Not Supported By Jpath");
            }
//        }
          instance = new SeleniumConnect();
//      }
      return instance;
    }
    
   

    public WebDriver getDriver() {
 
        return driver;
    }

 
    
    public  void closeDriver() throws BrowserNotSupported{
     try{   
      driver.close();
     }catch(Exception ex){
     
     } finally{
       JConfig util = new JConfig();
       DriverProperty driverProperty =  util.getTypeOfDrivetSet();
       if(driverProperty.getType().equals(DriverType.IE)){
           try {
               driverTaskManagerKill(DriverType.IE);
           } catch (IOException ex) {
               Logger.getLogger(SeleniumConnect.class.getName()).log(Level.SEVERE, null, ex);
           } catch (InterruptedException ex) {
               Logger.getLogger(SeleniumConnect.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else if(driverProperty.getType().equals(DriverType.FIREFOX)){
           try {
               driverTaskManagerKill(DriverType.FIREFOX);
           } catch (IOException ex) {
               Logger.getLogger(SeleniumConnect.class.getName()).log(Level.SEVERE, null, ex);
           } catch (InterruptedException ex) {
               Logger.getLogger(SeleniumConnect.class.getName()).log(Level.SEVERE, null, ex);
           }
         
       }else{
          throw new BrowserNotSupported(driverProperty.getType()+" Browser Not Supported By Jpath");
       }
     
     }
    }
    
    
   private static void driverTaskManagerKill(String driverName) throws IOException, InterruptedException{
     ProcessBuilder cmdBuilder  = new ProcessBuilder();
     if(driverName.equals("ie")){
        cmdBuilder.command("C:\\Windows\\System32\\cmd.exe", "/c ","taskkill", "/F", "/IM" ,"IEDriverServer.exe","/T");
     }else if(driverName.equals("firefox")){
         cmdBuilder.command("C:\\Windows\\System32\\cmd.exe", "/c ","taskkill", "/F", "/IM" ,"geckodriver.exe","/T");
     }
     Process process=cmdBuilder.start();
     
     InputStream inputStream =process.getInputStream();
     InputStream errorStream = process.getErrorStream();
     Scanner in = new Scanner(inputStream);
     while(in.hasNext()){
        System.out.println("Driver response:"+ in.nextLine());
     }
     
     process.waitFor();
 }   
    
    
}
