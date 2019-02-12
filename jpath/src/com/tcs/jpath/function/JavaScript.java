/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.jpath.function;

import com.tcs.jpath.connect.DriverType;
import com.tcs.jpath.exception.BrowserNotSupported;
import com.tcs.jpath.modal.DriverProperty;
import com.tcs.jpath.modal.JConfig;
import com.tcs.jpath.util.XmlReader;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author 1299792
 */
public class JavaScript {
    WebDriver driver;
    DriverProperty prop;
    JConfig config;
    public  JavaScript(WebDriver driver){
      this.driver = driver;
      this.config = new JConfig();
      this.prop = this.config.getTypeOfDrivetSet();
    }
    
     
    public void addDOMEvent() throws JavascriptException, BrowserNotSupported{
        
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        String text =   "";
        String helperFunction =  "";
        String eventMutation   = "";
        String fileWrite = "";
        String jsonStringify = "";
        String browserType =  this.prop.getType();
        String getIframeFunction = "";
        String domEventRemove = "";
        String eventCall = "";
        if(browserType.equals(DriverType.IE)){
            helperFunction = ScriptText.IE_EVENT_FUNCTION;
            text = ScriptText.IE_DOM_EVENT;
            fileWrite = ScriptText.IE_FILE_WRITE;
            jsonStringify = ScriptText.IE_JSON_STRINGYFY_FUNCTION;
            getIframeFunction = ScriptText.GENRIC_GET_IFRAME; // Tested on IE 7 and Moz
            
            eventCall = ScriptText.IE_EVENT_CALL;
        }else if(browserType.equals(DriverType.FIREFOX)){
            text = ScriptText.FIREFOX_DOM_EVENT;
            getIframeFunction = ScriptText.GENRIC_GET_IFRAME;
            helperFunction = ScriptText.IE_EVENT_FUNCTION;
            eventCall = ScriptText.IE_EVENT_CALL;
        }else{
          throw  new  BrowserNotSupported(browserType+" Not Supported By Jpath");
        }
//        System.out.println("First");
         executor.executeScript(ScriptText.GLOBAL_VAR);
         executor.executeScript(ScriptText.IE_XPATH_FINDER_FUNCTION_VER_1);
         executor.executeScript(helperFunction);
         if(jsonStringify.equals("")==false){
           executor.executeScript(jsonStringify);
         }
         if(fileWrite.equals("")==false){
           executor.executeScript(fileWrite);
         }
         executor.executeScript(text);
         executor.executeScript(getIframeFunction);
         executor.executeScript(ScriptText.searchForMyKadAppletFunctionVer2);
         executor.executeScript(ScriptText.callBackFunctionToAttachEventOnIframe);
         executor.executeScript(eventCall);
         if(domEventRemove.equals("")==false){
           executor.executeScript(domEventRemove);
         }
      
    }
}
