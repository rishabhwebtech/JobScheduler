/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.tcl.parser;
 
import com.tcs.rtestingframework.util.Comman;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author 1299792
 */
public class ExcelToTCL {
    String filePath;
    public ExcelToTCL(String filePath){
       this.filePath = filePath;
    }
    public String rootElementNameAttr = "";
    public String rootElementPathAttr = "";

    public String getRootElementNameAttr() {
        return rootElementNameAttr;
    }

    public void setRootElementNameAttr(String rootElementNameAttr) {
        this.rootElementNameAttr = rootElementNameAttr;
    }

    public String getRootElementPathAttr() {
        return rootElementPathAttr;
    }

    public void setRootElementPathAttr(String rootElementPathAttr) {
        this.rootElementPathAttr = rootElementPathAttr;
    }

  
    
    public Document convertExcelToXml() throws IOException{
       File file = new File(this.filePath);
       TCLParser parser = new TCLParser();
       Comman commanFunction = new Comman();
       boolean byId=false;
       boolean byClass = false;
       Document doc=null;
       Element root = null;
       Element declareElement = null;
       Element casesElement   = null;
       String testCaseText = "";
         
            
           try {
            doc = parser.getNewDocument();
           } catch (ParserConfigurationException ex) {
            Logger.getLogger(ExcelToTCL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
           }
            root = doc.createElement("tcl:TestCase");
            root.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:tcl", "urn:tcs:tcl");
            root.setAttribute("name", this.rootElementNameAttr);
            root.setAttribute("path", this.rootElementPathAttr);
           // Declare Section 
             declareElement = doc.createElement("tcl:declare");
             casesElement   = doc.createElement("tcl:cases");
            
//              Element caseElement = doc.createElement("tcl:case");
//              caseElement.setAttribute("name", testCaseText);
            
            Workbook traceBook=null;
             try {
                 traceBook = Workbook.getWorkbook(file);
             } catch (BiffException ex) {
              Logger.getLogger(ExcelToTCL.class.getName()).log(Level.SEVERE, null, ex);
              return null;
             }
            Sheet sheet  = null;
             Sheet []allSheet=traceBook.getSheets();
             int length = allSheet.length;
               
           for(int sheetIndex=0;sheetIndex<length;sheetIndex++){
            Element caseElement = doc.createElement("tcl:case");
            Element declareScopeElement = doc.createElement("tcl:declareScope");
            
            sheet=allSheet[sheetIndex];
            String sheetName = sheet.getName();
            declareScopeElement.setAttribute("name",sheetName) ;
            declareElement.appendChild(declareScopeElement);
            caseElement.setAttribute("name", sheetName);
            int col =  sheet.getColumns();
            int row =  sheet.getRows();
            System.out.println(col+" "+row);
          
            for(int i=1;i<row;i++){
                byId=false;
                byClass = false;
                Element fieldElement = doc.createElement("tcl:field");

                fieldElement.setAttribute("type", "String");
                Element insideCaseElement = null;
                
              for ( int j=1;j<col;j++){
              try{
                String content = sheet.getCell(j, i).getContents();
                
                switch(j){
                    case 1:
                   
                   if(content.equals("")==false && content.equals(" ")==false){
//                     if(content.contains("user:")){
//                         fieldElement.setAttribute("by", content);
//                     }else{
                         if(content.equals("D") || content.equals("N") || content.equals("C")){
                            fieldElement.setAttribute("by", "mode");
                         }else{
                          fieldElement.setAttribute("by", "id");
                         }
                          fieldElement.setAttribute("map", content);
//                     }
                     byId = true;
                     }else{
                      byId = false;
                    }
                    break;
                    case 2:
                      if(content.equals("") ==false && content.equals(" ")==false){
                          if(content.equals("fieldinput_mandatory")){
                            fieldElement.setAttribute("mandatory", "true");
                          }else{
                            fieldElement.setAttribute("mandatory", "false");
                          }
                          
                      switch (content) {
                        case "fieldinput":
                            String temp = sheet.getCell(3, i).getContents(); 
                            if(temp.equals("SELECT")){
                              insideCaseElement = doc.createElement("tcl:select");
                            }else{
                            insideCaseElement = doc.createElement("tcl:put");
                            }
                            break;
                        case "ThumbButton":
                            insideCaseElement = doc.createElement("tcl:click");
                            break;
                        case "btn-close":
                            insideCaseElement = doc.createElement("tcl:click");
                            break;
                        default:
                            break;
                         }
                          if(byId==false){
                             fieldElement.setAttribute("by", "className");
                             fieldElement.setAttribute("map", content);
                          } 
//                          else {
//                            if(content.contains("pass:")){
//                              fieldElement.setAttribute("map", content);
//                            }
//                          }
                        }
                        
                    break;
                    case 3:
                        if(content.equals("SELECT")){
                          if(insideCaseElement == null){
                            insideCaseElement = doc.createElement("tcl:select");
                          }
                        }else if(content.equals("PUT")){
                           if(insideCaseElement == null){
                            insideCaseElement = doc.createElement("tcl:put");
                          }                        
                        }else if(content.equals("CLICK")){
                          if(insideCaseElement == null){
                            insideCaseElement = doc.createElement("tcl:click");
                          }                      
                        }else if(content.equals("RUN")){
                           if(insideCaseElement == null){
                              insideCaseElement = doc.createElement("tcl:run");
                           }
                          
                        }
                        break;
                    case 4:
                        if(insideCaseElement!=null){
                         insideCaseElement.setAttribute("location",commanFunction.rearrangeBranch(content));
                        }
                    break;
                    case 5:
                        if(insideCaseElement!=null){
                        insideCaseElement.setAttribute("value", content);
                        }
                        break;
                    case 6:
                     if(insideCaseElement!=null){
                       if(content.equals("") || content.equals(" ")){  
                          insideCaseElement.setAttribute("var",Integer.toString(i));
                       }else{
                          insideCaseElement.setAttribute("var",content);
                       }
                      }
                     
                  
                       if(content.equals("") || content.equals(" ")){
                       
                       fieldElement.setAttribute("name",Integer.toString(i));
                       }else{
                        fieldElement.setAttribute("name",content);
             
                     
                     }
                     
                     break;
                    case 7:
                        if(content.equals("")==false && content.equals(" ")==false){
                             fieldElement.setAttribute("by", "cssSelector");
                             fieldElement.setAttribute("map", content);        
                        }
                }
//                if(insideCaseElement!=null){
//                    insideCaseElement.setAttribute("var",Integer.toString(i));
//                }
              }catch(java.lang.ArrayIndexOutOfBoundsException ex){
                System.out.println("out");
              }
              }
//              declareElement.appendChild(fieldElement);
                declareScopeElement.appendChild(fieldElement);
                caseElement.appendChild(insideCaseElement);
//                System.out.println();
            }
            
          
            
            casesElement.appendChild(caseElement);
            root.appendChild(declareElement);
            root.appendChild(casesElement);
            
        }
           doc.appendChild(root);
        return doc;
    }
    
    
    
    
    
}
