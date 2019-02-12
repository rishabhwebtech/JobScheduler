/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;


import com.tcs.rtestingframework.tcl.parser.TCLParser;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author 1299792
 */
public class ExtractMapperThread extends SwingWorker<String, Void>{

    private File mapperSaveFile ;
    private JTable data;
    private Document doc;
    private boolean isDel;
    public ExtractMapperThread(File mapperSaveFile,JTable data,boolean isDel) {
       this.mapperSaveFile = mapperSaveFile;
       this.data= data;
       this.isDel=isDel;
    }

    
    
    @Override
    protected String doInBackground() throws Exception {
        DefaultTableModel model = (DefaultTableModel)data.getModel();
        String [][]rowData;
        String retValueS = "0:Success";
        int rowLength = data.getRowCount();
        int colLength = data.getColumnCount();
        rowData = new String[rowLength][colLength];
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build =  fact.newDocumentBuilder();
        this.doc = build.newDocument();
        this.doc.normalizeDocument();
        Element mapperElement =   this.doc.createElement("mapper");
        Element headerElement = this.doc.createElement("header");
        Element footerElement = this.doc.createElement("footer");
        Element bodyElement   = this.doc.createElement("body");
        mapperElement.setAttribute("del",Boolean.toString(isDel));
        mapperElement.setAttribute("rowLength",Integer.toString(rowLength));
        mapperElement.setAttribute("colLength",Integer.toString(colLength));
        if(isDel==true){
          int delCol = model.findColumn("Delimiter");
          mapperElement.setAttribute("value", (String)data.getValueAt(0, delCol));
        }
        for(int i =0;i<rowLength;i++){
          
           if(isDel==false){
           for(int j=0;j<colLength;j++){
                 
              String value = (String)data.getValueAt(i, j);
              rowData[i][j]=value;
             
              
           }
           }else {
             String value = (String)data.getValueAt(i, 0);
             rowData[i][0]=value;        
           
           }
           
        }
        
        boolean retValue = true;
        int i=0;
        for(i=0;i<rowLength;i++){
            Element field = doc.createElement("field");
            String fieldName = rowData[i][0];
            String type      = rowData[i][1];
            try{
            retValue=validateMapperData(fieldName,(short)1);
            if(retValue==false){
               
                break;
            }
            retValue=validateMapperData(type,(short)2);
            if(retValue==false){
              break;
            }
            }catch(NullPointerException ex){
                  retValueS="1:Invalid Data At Row "+(i+1);
                  return retValueS;
            }
            String del  =  "";
            if(isDel==false){
                del      = rowData[i][2];
                field.setAttribute("size", del);
                retValue=validateMapperData(del,(short)3);
                if(retValue==false){
                  break;
                }
            }
             
           
           field.setTextContent(fieldName);
           switch(type){
               case "Header":
                 headerElement.appendChild(field);
               break;
               case "Body":
                   bodyElement.appendChild(field);
               break;
               case "Footer":
                   footerElement.appendChild(field);
               break;
           
           }

        }
        if(retValue==false){
         retValueS="1:Invalid Data At Row "+(i+1);
         return retValueS;
        }
        
        mapperElement.appendChild(headerElement);
        mapperElement.appendChild(bodyElement);
        mapperElement.appendChild(footerElement);
        doc.appendChild(mapperElement);
        TCLParser par = new TCLParser();
        String savePath = mapperSaveFile.getPath();
        if(savePath.lastIndexOf(".map")<0){
          savePath = savePath + ".map";
        }
        par.saveXml(savePath, doc);
        return retValueS;
      
    

    }

    @Override
    protected void done() {
        try {
            String ret =  get();
            String array[] = ret.split(":");
            
           if(array[0].equals("0")){
                 JOptionPane.showMessageDialog(null,"Done!", "Success",JOptionPane.INFORMATION_MESSAGE); 
           }else if(array[0].equals("1")){
                 JOptionPane.showMessageDialog(null,array[1], "Regrato",JOptionPane.ERROR_MESSAGE);
           }
        } catch (InterruptedException ex) {
            Logger.getLogger(ExtractMapperThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ExtractMapperThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private boolean validateMapperData(String value,short type){
//      int retValue =0;
      boolean flag = false;
      switch (type){
          case 1: // For Field Name
            flag =  value.matches("^(\\w+ ?)+$");
          break;
          case 2: // For Type
             flag = value.matches("[\\w]+");
          break;
          case 3: // For Delimiter
             flag  = value.matches("[0-9]+");
          break;
      }
      
      
      
      return flag;
      
    }
    
     
    
}
