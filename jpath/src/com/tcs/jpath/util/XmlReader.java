/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.jpath.util;

import java.io.IOException;
import java.sql.DriverPropertyInfo;
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
public class XmlReader {
    
    
    public Document getXmlDocument(String path) throws ParserConfigurationException, SAXException, IOException{
         
        Document doc = null;
        Element rootNode = null; 
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build  =    fact.newDocumentBuilder();  
        doc=build.parse(path);
        doc.normalize();
        
        return doc;
    }
    
    
    
    
}
