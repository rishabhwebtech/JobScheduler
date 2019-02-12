/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.tcl.tag.defination;

import com.tcs.rtestingframework.tcl.action.TCLAction;
import com.tcs.rtestingframework.tcl.action.PerformAction;
import com.tcs.rtestingframework.tcl.exception.InvalidSyntaxException;
import com.tcs.rtestingframework.tcl.tag.Field;
import com.tcs.rtestingframework.tcl.main.TCLMain;
import com.tcs.rtestingframework.tcl.parser.TCLParser;
import com.tcs.rtestingframework.tcl.util.ExpressionEvaluator;
import com.tcs.rtestingframework.tcl.util.TCLUtil;
import com.tcs.rtestingframework.util.Comman;
import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author 1299792
 */
public class TCLTagDefination implements TagDefination{
    WebDriver driver;
    TCLParser parser;
    TCLUtil   util;
    Comman commanFunction;
    WebElement variable = null;
    String logsPath;
    TCLAction tclAction;
    public TCLTagDefination(WebDriver driver,TCLParser parser,String logsPath){
      this.driver = driver;
      this.parser = parser;
      this.util = new TCLUtil();
      this.commanFunction = new Comman();
      this.tclAction = new TCLAction(driver);
      this.logsPath = logsPath;
    }
     @Override
    public void putDefination(Element element){
       String value = element.getAttribute("value");
       String variableName = element.getAttribute("var");
       String location     = element.getAttribute("location");
       if(location!=""){
//         this.driver.switchTo().frame(location);
           this.util.switchInsideFrame(this.driver, location);
       }
            Field field   = this.parser.getDeclaredVariables(variableName);
            variable=this.parser.getVariableElement(this.driver, field);
            commanFunction.writeLogsInFile("Entering value in variable "+variableName+": "+value, logsPath);
            this.tclAction.put(variable, value);
//            this.driver.switchTo().defaultContent();
            this.util.switchOutsideFrame(driver);
    }
     @Override
    public void clickDefination(Element element){
        String variableName = element.getAttribute("var");
        String location = element.getAttribute("location");
        if(!location.equals("")){
            
//             this.driver.switchTo().frame(location);
            this.util.switchInsideFrame(driver, location);
       
        }
        Field field   = this.parser.getDeclaredVariables(variableName);
        variable=this.parser.getVariableElement(this.driver, field);
        commanFunction.writeLogsInFile("Clicking "+field.getType()+" Variable "+field.getName(), logsPath);
        this.tclAction.click(variable);
        boolean isAlert =  this.util.isAlert(this.driver);        
       if(!isAlert){
//           this.driver.switchTo().defaultContent();
           this.util.switchOutsideFrame(driver);
       }
    }   
    @Override
    public void screenshortDefination(Element element){
    
         String path = element.getAttribute("path");
           
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            try {
                this.tclAction.getScreenShort(path);
            } catch (AWTException ex) {
                 commanFunction.writeLogsInFile(ex.getMessage(), logsPath);
                Logger.getLogger(TCLMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                commanFunction.writeLogsInFile(ex.getMessage(), logsPath);
                Logger.getLogger(TCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }

    @Override
    public void pageWaitDefination(Element element) {
        
           int seconds =    Integer.decode(element.getAttribute("seconds"));
           this.tclAction.pageWait(seconds);
        
    }

    @Override
    public void ifDefination(Element element,PerformAction action) throws InvalidSyntaxException {
        String expressionToEvaluate = element.getAttribute("test");
        ExpressionEvaluator evalObject = new ExpressionEvaluator(this.driver,this.parser);
        boolean retValue = evalObject.eval(expressionToEvaluate);
        if(retValue==true){
        // Statement Body
         Element ifStatement =      (Element)element.getElementsByTagName("tcl:statement").item(0);
         NodeList ifStatementBlock = ifStatement.getChildNodes();
            for(int i =0;i<ifStatementBlock.getLength();i++){
                Node tempNode =   ifStatementBlock.item(i);
                if(tempNode.getNodeType() == Node.TEXT_NODE){
                   continue;
                }
                Element tempElement = (Element)tempNode;
               
                action.performAction(tempElement);
                
            }
        
        }else{
            Element elseBlock = null;
            elseBlock =  (Element)element.getElementsByTagName("tcl:else").item(0);
            
            if(elseBlock!=null){
              Element elseStatement =      (Element)elseBlock.getElementsByTagName("tcl:statement").item(0);
             NodeList elseStatementBlock = elseStatement.getChildNodes();
             for(int i =0;i<elseStatementBlock.getLength();i++){
                Node tempNode =   elseStatementBlock.item(i);
                if(tempNode.getNodeType() == Node.TEXT_NODE){
                   continue;
                }
                Element tempElement = (Element)tempNode;
               
                action.performAction(tempElement);
                
            }
            }
        }
        
    }
    @Override
    public void printDefination(Element element){
         String content = element.getTextContent();
        commanFunction.writeLogsInFile(content, logsPath);
    }
    
}
