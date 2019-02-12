/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.jpath.modal;

import com.tcs.jpath.util.Comman;
import com.tcs.jpath.util.XmlReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author 1299792
 */
public class JConfig {
    public Login getLoginDetails(){
      Login login = new Login();
      XmlReader read  = new XmlReader();
        try {
            Document doc =  read.getXmlDocument("config.xml");
            Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
            Element loginElement = (Element)rootElement.getElementsByTagName("login").item(0);
            Element userNameElement  = (Element)loginElement.getElementsByTagName("username").item(0);
            Element passwordeElement = (Element)loginElement.getElementsByTagName("password").item(0);
            Element branchElement  = (Element)loginElement.getElementsByTagName("branch").item(0);
            Element instElement = (Element)rootElement.getElementsByTagName("inst").item(0);
            
            String userName = userNameElement.getTextContent();
            String password = passwordeElement.getTextContent();
            String branch  = branchElement.getTextContent();
            String inst    = instElement.getTextContent();
            
            login.setBranch(branch);
            login.setInst(inst);
            login.setPassword(password);
            login.setUserName(userName);
            
            
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      return login;
    
    }
   public void setLoginDetails(Login login) throws TransformerException{
      
        XmlReader read  = new XmlReader();
        Comman commanFunction = new Comman();
        try {
            Document doc =  read.getXmlDocument("config.xml");
            Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
            Element loginElement = (Element)rootElement.getElementsByTagName("login").item(0);
            Element userNameElement  = (Element)loginElement.getElementsByTagName("username").item(0);
            Element passwordeElement = (Element)loginElement.getElementsByTagName("password").item(0);
            Element branchElement  = (Element)loginElement.getElementsByTagName("branch").item(0);
            Element instElement = (Element)rootElement.getElementsByTagName("inst").item(0);
            
            userNameElement.setTextContent(login.getUserName());
            passwordeElement.setTextContent( login.getPassword());
            branchElement.setTextContent(login.getBranch());
            instElement.setTextContent(login.getInst());
            
            commanFunction.saveXml("config.xml", doc);
            
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
    
    
    }
    public void setTypeOfDrivetSet(DriverProperty prop) throws ParserConfigurationException, SAXException, IOException, TransformerException{
      Comman commanFunction = new Comman();
      String path = prop.getPath();
      String type = prop.getType();
      String page = prop.getLoginPage();
      XmlReader read  = new XmlReader();
      Document doc =  read.getXmlDocument("config.xml");
      Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
      Element driverElement  = (Element)rootElement.getElementsByTagName("driver").item(0);
      Element openPageElement = (Element)rootElement.getElementsByTagName("open-page").item(0);
      driverElement.setAttribute("path", path);
      driverElement.setAttribute("type", type.toLowerCase());
      openPageElement.setAttribute("url", page);
      commanFunction.saveXml("config.xml", doc);
    }
    
    public DriverProperty getTypeOfDrivetSet (){
       XmlReader read  = new XmlReader();
       DriverProperty driver = new DriverProperty();
        try {
            Document doc =  read.getXmlDocument("config.xml");
            Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
            Element driverElement  = (Element)rootElement.getElementsByTagName("driver").item(0);
            Element openPageElement = (Element)rootElement.getElementsByTagName("open-page").item(0);
            String path = driverElement.getAttribute("path");
            String type = driverElement.getAttribute("type");
            String openPage = openPageElement.getAttribute("url");
            driver.setPath(path);
            driver.setType(type);
            driver.setLoginPage(openPage);
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return driver;
    }
   public Host getHost() {
        Host host = new Host();
        try {
           
            XmlReader read  = new XmlReader();
            Document doc =  read.getXmlDocument("config.xml");
            
            Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
            Element driverElement  = (Element)rootElement.getElementsByTagName("host").item(0);
            
            Element userNameElement =  (Element)driverElement.getElementsByTagName("username").item(0);
            Element passwordElement =  (Element)driverElement.getElementsByTagName("password").item(0);
            Element ipElement =  (Element)driverElement.getElementsByTagName("ip").item(0);
            Element knownHostElement =  (Element)driverElement.getElementsByTagName("knownHost").item(0);
            
            String userName=userNameElement.getTextContent() ;
            String password=passwordElement.getTextContent() ;
            String ip=ipElement.getTextContent();
            String knownHost=knownHostElement.getTextContent();
            
            host.setHostIp(ip);
            host.setHostKnownHost(knownHost);
            host.setHostPassword(password);
            host.setHostUserName(userName);
            
           // Day Region
            Element dayHostElement  = (Element)rootElement.getElementsByTagName("dayHost").item(0);
            Element dayUserNameElement =  (Element)dayHostElement.getElementsByTagName("username").item(0);
            Element dayPasswordElement =  (Element)dayHostElement.getElementsByTagName("password").item(0);
            Element dayIpElement =  (Element)dayHostElement.getElementsByTagName("ip").item(0);
            Element dayKnownHostElement =  (Element)dayHostElement.getElementsByTagName("knownHost").item(0);
            
            String dayUserName=dayUserNameElement.getTextContent() ;
            String dayPassword=dayPasswordElement.getTextContent() ;
            String dayIp=dayIpElement.getTextContent();
            String dayKnownHost=dayKnownHostElement.getTextContent();   
            host.setDayHostIp(dayIp);
            host.setDayHostKnonwHost(dayKnownHost);
            host.setDayHostPassword(dayPassword);
            host.setDayHostUserName(dayUserName);
          // Night 
            
          
            Element nightHostElement  = (Element)rootElement.getElementsByTagName("nightHost").item(0);
            Element nightUserNameElement =  (Element)nightHostElement.getElementsByTagName("username").item(0);
            Element nightPasswordElement =  (Element)nightHostElement.getElementsByTagName("password").item(0);
            Element nightIpElement =  (Element)nightHostElement.getElementsByTagName("ip").item(0);
            Element nightKnownHostElement =  (Element)nightHostElement.getElementsByTagName("knownHost").item(0);
            
            String nightUserName=nightUserNameElement.getTextContent() ;
            String nightPassword=nightPasswordElement.getTextContent() ;
            String nightIp=nightIpElement.getTextContent();
            String nightKnownHost=nightKnownHostElement.getTextContent();       
          
            host.setNightHostIp(nightIp);
            host.setNightHostKnownHost(nightKnownHost);
            host.setNightHostPassword(nightPassword);
            host.setNightHostUserName(nightUserName);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
         return host;
   }
   
   
      public void setHost(Host host) {
        try {
            Comman commanFunction = new Comman();
            XmlReader read  = new XmlReader();
            Document doc =  read.getXmlDocument("config.xml");
            Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
            Element driverElement  = (Element)rootElement.getElementsByTagName("host").item(0);
            
            Element userNameElement =  (Element)driverElement.getElementsByTagName("username").item(0);
            Element passwordElement =  (Element)driverElement.getElementsByTagName("password").item(0);
            Element ipElement =  (Element)driverElement.getElementsByTagName("ip").item(0);
            Element knownHostElement =  (Element)driverElement.getElementsByTagName("knownHost").item(0);
            
            userNameElement.setTextContent(host.getHostUserName());
            passwordElement.setTextContent(host.getHostPassword());
            ipElement.setTextContent(host.getHostIp());
            knownHostElement.setTextContent(host.getHostKnownHost());
            
            // Day Region
            Element dayElement  = (Element)rootElement.getElementsByTagName("dayHost").item(0);
            
            Element dayUserNameElement =  (Element)dayElement.getElementsByTagName("username").item(0);
            Element dayPasswordElement =  (Element)dayElement.getElementsByTagName("password").item(0);
            Element dayIpElement =  (Element)dayElement.getElementsByTagName("ip").item(0);
            Element dayKnownHostElement =  (Element)dayElement.getElementsByTagName("knownHost").item(0);
            
            dayUserNameElement.setTextContent(host.getDayHostUserName());
            dayPasswordElement.setTextContent(host.getDayHostPassword());
            dayIpElement.setTextContent(host.getDayHostIp());
            dayKnownHostElement.setTextContent(host.getDayHostKnonwHost());      
            
            // Night Region
            Element nightElement  = (Element)rootElement.getElementsByTagName("nightHost").item(0);
            Element nightUserNameElement =  (Element)nightElement.getElementsByTagName("username").item(0);
            Element nightPasswordElement =  (Element)nightElement.getElementsByTagName("password").item(0);
            Element nightIpElement =  (Element)nightElement.getElementsByTagName("ip").item(0);
            Element nightKnownHostElement =  (Element)nightElement.getElementsByTagName("knownHost").item(0);      
            
            nightUserNameElement.setTextContent(host.getNightHostUserName());
            nightPasswordElement.setTextContent(host.getNightHostPassword());
            nightIpElement.setTextContent(host.getNightHostIp());
            nightKnownHostElement.setTextContent(host.getNightHostKnownHost());         
            
            commanFunction.saveXml("config.xml", doc);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
     public Branch getBranches(){
         Branch brach = new Branch();
         ArrayList<String> arrayOfBranch = new ArrayList<>();
         try {
            Comman commanFunction = new Comman();
            XmlReader read  = new XmlReader();
            Document doc =  read.getXmlDocument("config.xml");
            Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
            Element branchesElement =  (Element)rootElement.getElementsByTagName("branches").item(0);
            NodeList branchList = branchesElement.getElementsByTagName("branch");
            int length = branchList.getLength();
            for(int i=0;i<length;i++){
                Node node =  branchList.item(i);
                if(node.getNodeType()!=Node.TEXT_NODE){
                   Element el = (Element)node;
                   arrayOfBranch.add(el.getTextContent());
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
      brach.setBranches(arrayOfBranch);
      return brach;
     }
     
     public void setBranch(String branchName){
     
        try {
            Comman commanFunction = new Comman();
            XmlReader read  = new XmlReader();
            Document doc =  read.getXmlDocument("config.xml");   
            Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
            Element branchesElement = (Element)rootElement.getElementsByTagName("branches").item(0);
            Element branchElement = (Element)doc.createElement("branch");
            branchElement.setTextContent(branchName);
            branchesElement.appendChild(branchElement);
            commanFunction.saveXml("config.xml", doc);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
     public void deleteBranch(String branchName){
            try {
            Comman commanFunction = new Comman();
            XmlReader read  = new XmlReader();
            Document doc =  read.getXmlDocument("config.xml");
            Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
            Element branchesElement =  (Element)rootElement.getElementsByTagName("branches").item(0);
            NodeList branchList = branchesElement.getElementsByTagName("branch");
            int length = branchList.getLength();
            for(int i=0;i<length;i++){
                Node node =  branchList.item(i);
                if(node.getNodeType()!=Node.TEXT_NODE){
                   Element el = (Element)node;
                   if(branchName.equals(el.getTextContent())){
                       branchesElement.removeChild(el);
                       break;
                   }
                }
            }
            commanFunction.saveXml("config.xml", doc);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        }    
          
     }
     
     public void setDBConnecton(DBConnection conn)throws NumberFormatException{
     
           try {
            Comman commanFunction = new Comman();
            XmlReader read  = new XmlReader();
            Document doc =  read.getXmlDocument("config.xml");   
            Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
            Element dbConnectionElement = (Element)rootElement.getElementsByTagName("dbConnection").item(0);
            Element passwordElement = (Element)dbConnectionElement.getElementsByTagName("password").item(0);
            Element userNameElement = (Element)dbConnectionElement.getElementsByTagName("username").item(0);
            Element ipElement = (Element)dbConnectionElement.getElementsByTagName("ip").item(0);
            Element portElement = (Element)dbConnectionElement.getElementsByTagName("port").item(0);
            Element schemaElement = (Element)dbConnectionElement.getElementsByTagName("schema").item(0);
            passwordElement.setTextContent(conn.getDbPassword());
            userNameElement.setTextContent(conn.getDbUserName());
            ipElement.setTextContent(conn.getDbIP());
            schemaElement.setTextContent(conn.getDbSchema());
            try{
            portElement.setTextContent(Integer.toString(conn.getDbPort()));
            }catch(NumberFormatException ex){
              throw new NumberFormatException("Port Should be Integer");
              
            }
//            dbConnectionElement.appendChild(passwordElement);
            commanFunction.saveXml("config.xml", doc);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        }  
     
     }
     
     public DBConnection getDBConnection(){
       DBConnection conn = new DBConnection();
              try {
            Comman commanFunction = new Comman();
            XmlReader read  = new XmlReader();
            Document doc =  read.getXmlDocument("config.xml");   
            Element rootElement  = (Element)doc.getElementsByTagName("jpath-config").item(0);
            Element dbConnectionElement = (Element)rootElement.getElementsByTagName("dbConnection").item(0);
            Element passwordElement = (Element)dbConnectionElement.getElementsByTagName("password").item(0);
            Element userNameElement = (Element)dbConnectionElement.getElementsByTagName("username").item(0);
            Element ipElement = (Element)dbConnectionElement.getElementsByTagName("ip").item(0);
            Element portElement = (Element)dbConnectionElement.getElementsByTagName("port").item(0);
            Element schemaElement = (Element)dbConnectionElement.getElementsByTagName("schema").item(0);
            String dbPassowrd=passwordElement.getTextContent();
            String dbUserName=userNameElement.getTextContent();
            String dbIp=ipElement.getTextContent();
            String dbschema = schemaElement.getTextContent();
            int dbPort = 0;
            try{
             dbPort= Integer.parseInt(portElement.getTextContent());
            }catch(NumberFormatException ex){
              throw new NumberFormatException("Port Should be Integer");
              
            }
            conn.setDbPassword(dbPassowrd);
            conn.setDbUserName(dbUserName);
            conn.setDbIP(dbIp);
            conn.setDbPort(dbPort);
            conn.setDbSchema(dbschema);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JConfig.class.getName()).log(Level.SEVERE, null, ex);
        }  
       
       
       return conn;
     
     }
}
