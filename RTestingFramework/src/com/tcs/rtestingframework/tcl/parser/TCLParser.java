/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.tcl.parser;

import com.tcs.rtestingframework.tcl.tag.Field;
import com.tcs.rtestingframework.tcl.tag.TestCase;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author 1299792
 */
public class TCLParser {
    
    
    private String testCaseXmlPath;
    private Element rootNode;
    private static TCLParser instance=null;
    public TCLParser(){}
     
    public  TCLParser(String testCaseXmlPath){
       this.testCaseXmlPath = testCaseXmlPath;
        try {
           this.rootNode = getRootElemement();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TCLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(TCLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TCLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static TCLParser getTCLParserInstance(String path){
       if(instance==null){
         instance = new TCLParser(path);
       }
       return instance;
    }
    public  TestCase  getRootElementProp(){
      TestCase rootElement = new TestCase();
      String name =  this.rootNode.getAttribute("name");
      String path =  this.rootNode.getAttribute("path");
      rootElement.setName(name);
      rootElement.setPath(path);
      return rootElement;
    
    }
    public Element getRootElemement() throws ParserConfigurationException, SAXException, IOException{
      Document doc=null;
      Element rootNode=null;
      
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        fact.setNamespaceAware(true);
        DocumentBuilder build=fact.newDocumentBuilder();
        
        doc=build.parse(this.testCaseXmlPath);
        
        doc.normalize();
        rootNode =  (Element)doc.getElementsByTagName("tcl:TestCase").item(0);
        
      return rootNode;
    }
    
    public WebElement getVariableElement(WebDriver driver,Field field)throws org.openqa.selenium.NoSuchElementException{
      WebElement element = null;
      String by  = field.getBy();
      String map = field.getMap();
      String location = field.getLocation();
      if(location.length()!=0){
          driver.switchTo().frame(location);
      }
      
      if(by.equals("id")){
         element =   (WebElement)driver.findElement(By.id(map));
      }else if(by.equals("xpath")){
         element =   (WebElement)driver.findElement(By.xpath(map));
      }else if(by.equals("cssSelector")){
         element =   (WebElement)driver.findElement(By.cssSelector(map));
      }else if(by.equals("className")){
         element =    (WebElement)driver.findElement(By.className(map));
      }
    
      return element;
    
    }
    
    public Field getDeclaredVariables(String name){
        
        Field field = new Field();
        Element declareSection = (Element)this.rootNode.getElementsByTagName("tcl:declare").item(0);
        NodeList list = declareSection.getElementsByTagName("tcl:field");
        
        for(int i =0 ; i<list.getLength();i++){
           Element element  =   (Element)list.item(i);
           String value = element.getAttribute("name");
           if(value.equals(name)){
              String name1 = element.getAttribute("name");
              String type = element.getAttribute("type");
              String map  = element.getAttribute("map");
              String by   = element.getAttribute("by");
              String location =               element.getAttribute("location");
              
              boolean mandatory  = Boolean.valueOf(element.getAttribute("mandatory"));
              field.setBy(by);
              field.setType(type);
              field.setName(name1);
              field.setMandatory(mandatory);
              field.setMap(map);
              if(location!="" || location!=null || !location.isEmpty()){
                field.setLocation(location);
              }
               break;
           }
        }
        
        return field;
    }
    
    public NodeList getAllCases(){
         NodeList retValue = null;
         NodeList allCases = this.rootNode.getElementsByTagName("tcl:case");
         
         retValue = allCases;
         
         return retValue;
    }
    
    public Document getNewDocument() throws ParserConfigurationException{
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        return document;
    }
    
    public void saveXml(String xmlFilePath,Document document) throws TransformerConfigurationException, TransformerException{
    
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(xmlFilePath));
        transformer.transform(domSource, streamResult);
            System.out.println("Done creating XML File");

    }
    
}
