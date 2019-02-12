/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.jpath.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tcs.jpath.Path;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;

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
      
   public String convertStreamToString(InputStream stream){
     String string = new String();
      Scanner in = new Scanner(stream);
       while(in.hasNext()){
        string = string + in.nextLine();
       }
     
     return string;
   }
 
   
   public JsonArray getTracedDataFromTemp() throws FileNotFoundException, IOException{
     JsonArray array = new JsonArray();
     FileReader reader = new FileReader(new File(Path.JPATH_FILE));
     BufferedReader bufferReader = new BufferedReader(reader);
     String line = "";
     JsonParser parse = new JsonParser();
     while((line=bufferReader.readLine())!=null){
         
         line = line.replaceAll("@@", "\"");
//         System.out.println(line);
         JsonObject object = new JsonObject();
         object = (JsonObject)parse.parse(line);
         array.add(object);
     }
     
     return array;
   }
   
   public void deleteTempFile(){
     File file = new File(Path.JPATH_FILE);
     file.delete();
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
