/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.tcl.main;

import com.tcs.rtestingframework.tcl.parser.TCLParser;
import com.tcs.rtestingframework.tcl.tag.Field;
import com.tcs.rtestingframework.tcl.action.PerformAction;
import com.tcs.rtestingframework.tcl.action.TCLAction;
import com.tcs.rtestingframework.tcl.exception.InvalidSyntaxException;
import com.tcs.rtestingframework.tcl.tag.defination.TCLTagDefination;
import com.tcs.rtestingframework.tcl.tag.TestCase;
import com.tcs.rtestingframework.util.Comman;
import java.awt.AWTException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
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
public class TCLMain implements PerformAction{
    
    WebDriver driver ;
    TCLAction tclAction;
    TCLParser parser ; 
    Comman    commanFunction;
    Map<String,Field> variableMapping;
    String logsPath;
    public TCLMain(WebDriver driver,String testCaseClass) {
       this.driver    = driver;
       this.tclAction = new TCLAction(this.driver);
       this.parser    =TCLParser.getTCLParserInstance(testCaseClass);
       this.commanFunction  = new Comman();
       this.variableMapping = new HashMap();
    
    }
 
    
    private List<Element> getTestCases(){
     NodeList allTestCases  = this.parser.getAllCases();
     List<Element> allCaseElement = new ArrayList<Element>();
     for(int i =0;i<allTestCases.getLength();i++){
         Element caseElement =  (Element)allTestCases.item(i);
         allCaseElement.add(caseElement);
     }
     return allCaseElement;
    }
    
    public void executeTestCases(){
      
      TestCase rootElement   = this.parser.getRootElementProp();
      String logsFileName = rootElement.getName();
//      BufferedWriter out = null;
      String logsPathValue    = rootElement.getPath();
      logsPath = logsPathValue+logsFileName+".txt";
 
//      this.declaredVariables=searchDeclaredVariables(variableMapping);
      List<Element> allTestCases                     =   getTestCases();
      Iterator   it = allTestCases.iterator();
      
      
      
      
      while(it.hasNext()){
         this.driver.switchTo().defaultContent();
         Node node = (Node)it.next();
         if(node.getNodeType()==Node.TEXT_NODE){
           continue;
         }
         Element singleCase =   (Element)node;
         String nameOfTestCase = singleCase.getAttribute("name");
         commanFunction.writeLogsInFile("                                                ", logsPath);
         commanFunction.writeLogsInFile("Performing Test Case "+nameOfTestCase, logsPath);
         commanFunction.writeLogsInFile("________________________________________________", logsPath);
         NodeList insideCase =   singleCase.getChildNodes();
         
         int totalChildLength = insideCase.getLength();
         
         for(int i =0 ;i<totalChildLength;i++){
             Node node1    =     insideCase.item(i);
             if(node1.getNodeType()==Node.TEXT_NODE){
              continue;
             }
             Element   actionPerformElement =    (Element)node1;
             performAction(actionPerformElement);
         
         }
      }
         
     
    }
     @Override
     public void performAction(Element element){
        String tagName = element.getTagName();
        WebElement variable = null;
         TCLTagDefination def = new TCLTagDefination(this.driver, this.parser,this.logsPath);
        if(tagName.equalsIgnoreCase("tcl:put")){
          def.putDefination(element);
        }else if(tagName.equalsIgnoreCase("tcl:click")){
           def.clickDefination(element); 
        }else if(tagName.equalsIgnoreCase("tcl:screenshot")){
              def.screenshortDefination(element);
        }else if(tagName.equalsIgnoreCase("tcl:pageWait")){
              def.pageWaitDefination(element);
        }else if(tagName.equalsIgnoreCase("tcl:if")){
             
            try {
                def.ifDefination(element, this);
            } catch (InvalidSyntaxException ex) {
                Logger.getLogger(TCLMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }else if(tagName.equalsIgnoreCase("tcl:else")){
           Element ifElement =    (Element)element.getPreviousSibling();
           System.out.println(ifElement.getTagName());
        }else if(tagName.equalsIgnoreCase("tcl:print")){
           def.printDefination(element);
        }
          
        
     }
    
    private Map<String,WebElement> searchDeclaredVariables(Map<List<String>,List<Field>> variableMapping ){
        Map<String,WebElement> var = new HashMap<>();
        Set<List<String>>  key = variableMapping.keySet();
        List               values    = new ArrayList(variableMapping.values());
        Iterator  it =    key.iterator();
        while(it.hasNext()){
         
         List<String> temp  = (List<String>)it.next();
        
         Iterator it1 = temp.iterator();
         
         while(it1.hasNext()){
           String frameId  = (String)it1.next();
           this.driver.switchTo().frame(frameId);
         
           try{
              Alert  alert = this.driver.switchTo().alert();
              alert.accept();
           }catch(NoAlertPresentException ex){
           
           }catch(UnhandledAlertException ex){
           
           }
           
           
         }
         for(int i = 0;i<values.size();i++){
            List<Field> fieldList =  (List<Field>)values.get(i);
            Iterator it2 = fieldList.iterator();
            while(it2.hasNext()){
               Field field = (Field)it2.next();
               String map  = field.getMap();
               String by   = field.getBy();
               String name = field.getName();
               WebElement element  = null;
               if(by.equals("id")){
                 element=this.driver.findElement(By.id(map));
               }else if(by.equals("xpath")){
                element= this.driver.findElement(By.xpath(map));
               }else if(by.equals("cssselector")){
                element = this.driver.findElement(By.cssSelector(map));
               }
                var.put(name, element);
             }
//             this.driver.switchTo().parentFrame();
        }
           this.driver.switchTo().parentFrame();
               
               
            }
            
            
      
        
        return var;
    } 
     
}
