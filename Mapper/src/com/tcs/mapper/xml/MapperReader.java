/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.mapper.xml;

import com.tcs.mapper.modal.Body;
import com.tcs.mapper.modal.Field;
import com.tcs.mapper.modal.Footer;
import com.tcs.mapper.modal.Header;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author 1299792
 */
public class MapperReader {

    File mapPath;
    public MapperReader(File mapPath) {
        this.mapPath = mapPath;
    }
    
    
    
    public Document getDoc() throws ParserConfigurationException, SAXException, IOException{
      Document doc = null;
      
        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = fac.newDocumentBuilder();
        doc= build.parse(mapPath);
        doc.normalize();
      
      return doc;
    
    }
    
    
    public Element getRootElement(){
      Element rootElement = null;
        try {
            Document doc =  getDoc();
            rootElement=doc.getDocumentElement();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MapperReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(MapperReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapperReader.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      return rootElement;
    
    }
 
    public Body getBody(){
    
      Body body = new Body();
      Element rootElement = getRootElement();
      boolean del = Boolean.valueOf(rootElement.getAttribute("del"));
      NodeList list =  rootElement.getElementsByTagName("body");
      Element bodyElement =     (Element)list.item(0);
      
      boolean isEmpty=false;
      NodeList childNodes =    bodyElement.getChildNodes();
      
      int len = childNodes.getLength();
      ArrayList<Field> fieldArray = new ArrayList<>();
      for(int i =0;i<len;i++){
         
          Node node = childNodes.item(i);
          short type = node.getNodeType();
          if(type==Node.ELEMENT_NODE){
              Field field = new Field();
              Element fieldElement = (Element)node;
              int size = 0;
              if(del){
                 size = -1;
              }else{
                  size=Integer.parseInt(fieldElement.getAttribute("size"));
              }
              String name = fieldElement.getTextContent();
              field.setName(name);
              field.setSize(size);
              fieldArray.add(field);
              
         }
      }
       if(fieldArray.size()==0){
        isEmpty=true;
      }
      
      body.setisEmpty(isEmpty);
      body.setField(fieldArray);
      return body;   
    
    
    }
    
    
    
    
    
    
    
    
    
    public Footer getFooter(){
    
      Footer foot = new Footer();
      Element rootElement = getRootElement();
      boolean del = Boolean.valueOf(rootElement.getAttribute("del"));
      NodeList list =  rootElement.getElementsByTagName("footer");
      Element footerElement =     (Element)list.item(0);
      boolean isEmpty=false;
      
      NodeList childNodes =  null;
      
      try{
        childNodes=footerElement.getChildNodes();
      }catch(NullPointerException ex){
        isEmpty=true;
      }
      
      int len = childNodes.getLength();
      ArrayList<Field> fieldArray = new ArrayList<>();
      for(int i =0;i<len;i++){
         
          Node node = childNodes.item(i);
          short type = node.getNodeType();
          if(type==Node.ELEMENT_NODE){
              Field field = new Field();
              Element fieldElement = (Element)node;
              int size = 0;
              if(del){
                 size = -1;
              }else{
                  size=Integer.parseInt(fieldElement.getAttribute("size"));
              }
              String name = fieldElement.getTextContent();
              field.setName(name);
              field.setSize(size);
              fieldArray.add(field);
              
         }
      }
      
       if(fieldArray.size()==0){
        isEmpty=true;
      }
      foot.setisEmpty(isEmpty);
      foot.setField(fieldArray);
      return foot;   
    
    
    }
    
    public Header getHeader(){
      Header head = new Header();
      Element rootElement = getRootElement();
      boolean del = Boolean.valueOf(rootElement.getAttribute("del"));
      NodeList list =  rootElement.getElementsByTagName("header");
      Element headerElement =     (Element)list.item(0);
      
      boolean isEmpty=false;
      
      NodeList childNodes = null;
      try{
      childNodes=headerElement.getChildNodes();
      }catch(NullPointerException ex){
        isEmpty=true;
      }
      int len = childNodes.getLength();
      ArrayList<Field> fieldArray = new ArrayList<>();
      
      for(int i =0;i<len;i++){
         
          Node node = childNodes.item(i);
          short type = node.getNodeType();
          if(type==Node.ELEMENT_NODE){
              Field field = new Field();
              Element fieldElement = (Element)node;
              int size = 0;
              if(del){
                 size = -1;
              }else{
                  size=Integer.parseInt(fieldElement.getAttribute("size"));
              }
              String name = fieldElement.getTextContent();
              field.setName(name);
              field.setSize(size);
              fieldArray.add(field);
               
         }
      }
      if(fieldArray.size()==0){
        isEmpty=true;
      }
      head.setisEmpty(isEmpty);
      head.setField(fieldArray);
      return head;
    }
   
}
