/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.util;

import com.google.common.collect.HashBiMap;
import com.tcs.rtestingframework.exception.InvalidAttributeValueException;
import com.tcs.rtestingframework.wrapper.DriverProperty;
import com.tcs.rtestingframework.wrapper.LoginModule;
import com.tcs.rtestingframework.xml.XmlProperty;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author 1299792
 */
public class XmlUtil {
    
    private Element getRootNodeDocument(int type) throws ParserConfigurationException, SAXException, IOException{
        Document doc = null;
        Element rootNode = null; 
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build  =    fact.newDocumentBuilder();
        if(type==0){
        doc=build.parse("xml"+File.separator+"screen mapping"+File.separator+"screen_frame_mapping.xml");
        }else if(type == 1){
          doc=build.parse("xml"+File.separator+"config"+File.separator+"driverconfig.xml");
        }else if(type==2){
          doc=build.parse("xml"+File.separator+"module"+File.separator+"login.xml");
         }

        doc.normalize();
        if(type==0){
          rootNode = (Element)doc.getElementsByTagName("map").item(0);
        }else if(type==1){
           rootNode = (Element)doc.getElementsByTagName("config").item(0);
        }else if(type==2){
           rootNode = (Element)doc.getElementsByTagName("login").item(0);
        }
        return rootNode;
    
    }
    
    public LoginModule getLoginDetails() throws InvalidAttributeValueException{
      LoginModule mod  = new LoginModule();
      String  usernameType="";
      String  passwordType="";
      String  branchType="";
      String  institutionType="";
      
      String userNameValue="" ; 
      String passwordValue="" ; 
      String branchValue="";
      String institutionValue="" ; 
      
      
          
      String userNameContent="";
      String passwordContent="";
      String branchContent="";
      String institutionContent="";
      
        try {
             Element rootNode = getRootNodeDocument(XmlProperty.LOGIN_MODULE);
             Element usernameElement     = (Element)rootNode.getElementsByTagName("username").item(0);
             Element passwordElement     = (Element)rootNode.getElementsByTagName("password").item(0);
             Element branchElement       = (Element)rootNode.getElementsByTagName("branch").item(0);
             Element institutionElement  = (Element)rootNode.getElementsByTagName("institution").item(0);
             if(usernameElement!=null){
                usernameType   =   usernameElement.getAttribute("type");
                if(!usernameType.equals("id") && !usernameType.equals("name")){
                    throw new InvalidAttributeValueException(usernameType+" Attribute Value Not Allowed");
                 }
                
                userNameValue  =   usernameElement.getTextContent().replaceAll("\\r\\n","");
                userNameContent = usernameElement.getAttribute("value");
             }
             if(passwordElement!=null){
                passwordType  = passwordElement.getAttribute("type");
                
                 if(!passwordType.equals("id") && !passwordType.equals("name")){
                    throw new InvalidAttributeValueException(passwordType+" Attribute Value Not Allowed");
                 }
                 passwordContent = passwordElement.getAttribute("value");
                 passwordValue  =   passwordElement.getTextContent().replaceAll("\\r\\n","");
             }
             if(branchElement!=null){
                 branchType  = passwordElement.getAttribute("type");
                   if(!branchType.equals("id") && !branchType.equals("name")){
                    throw new InvalidAttributeValueException(branchType+" Attribute Value Not Allowed");
                    }
                 branchValue  =   branchElement.getTextContent().replaceAll("\\r\\n","");
                 branchContent = branchElement.getAttribute("value");
             }
             if(institutionElement!=null){
                institutionType = institutionElement.getAttribute("type");
                 if(!institutionType.equals("id") && !institutionType.equals("name")){
                    throw new InvalidAttributeValueException(institutionType+" Attribute Value Not Allowed");
                 }
                 institutionContent = institutionElement.getAttribute("value");
                 institutionValue  =   institutionElement.getTextContent().replaceAll("\\r\\n","");
             }
              mod.setBranchType(branchType);
              mod.setBranchValue(branchValue);
              mod.setInstitutionType(institutionType);
              mod.setInstitutionValue(institutionValue);
              mod.setUserNameType(usernameType);
              mod.setUserNameValue(userNameValue);
              mod.setPasswordType(passwordType);
              mod.setPasswordValue(passwordValue);
              mod.setBranchContent(branchContent);
              mod.setInstitutionContent(institutionContent);
              mod.setUserNameContent(userNameContent);
              mod.setPasswordContent(passwordContent);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      
      return mod;
    }
    
    
    public DriverProperty getTypeOfDrivetSet(){
      String type = "";
      String driverPath = "";
      String loginPage="";
      DriverProperty prop = new DriverProperty();
        try {
          Element rootNode =   getRootNodeDocument(XmlProperty.PROJECT_PROPERTY);
          Element driverElement  =  (Element)rootNode.getElementsByTagName("driver").item(0);
          type = driverElement.getAttribute("type");
          driverPath=driverElement.getTextContent().replaceAll("\\r\\n", "");
          prop.setPath(driverPath);
          prop.setType(type);
          Element loginPageElement = (Element)rootNode.getElementsByTagName("loginPage").item(0);
          loginPage=loginPageElement.getTextContent().replaceAll("\\r\\n", "");
          prop.setLoginPage(loginPage);
          
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XmlUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return prop;
    
    }
    
}
