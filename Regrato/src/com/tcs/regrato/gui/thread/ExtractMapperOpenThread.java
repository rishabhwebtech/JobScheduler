/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.tcs.regrato.gui.BranchEditor;
import com.tcs.regrato.gui.BranchRenderer;
import com.tcs.regrato.modal.ExtractMapperModal;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.util.Comman;
import com.tcs.shellsch.ShellOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author 1299792
 */
public class ExtractMapperOpenThread extends SwingWorker<Void, JTable>{

    private volatile JTable table;
    private boolean isDel;
    private File file;
    private String [][]rowData;
    private JTabbedPane pane;
    private ExtractMapperModal model;
    private org.apache.log4j.Logger log;
    public ExtractMapperOpenThread(File file,JTabbedPane pane) {
        this.file = file;
        model = ExtractMapperModal.getExtractMapperModal();
        this.pane = pane;
        this.log = new Comman().getLogger();
    }
    
    

    
    
    @Override
    protected Void doInBackground() throws Exception {
//         publish(table); 
         
         Comman comman = new Comman();
         
         int rowLength = 0;
         int colLength = 0;
         DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
         DocumentBuilder build =  fac.newDocumentBuilder();
         Document doc =      build.parse(file);
         Element mapperElement =   doc.getDocumentElement();
         isDel =  Boolean.valueOf(mapperElement.getAttribute("del"));
         String delValue=null;
         JComboBox box =  model.getIsDelCompobox();
         if(isDel){
           comman.writeSemaphore("isDel", "true");
          box.setSelectedItem("1:Yes");
          delValue = mapperElement.getAttribute("value");
         }else{
             comman.writeSemaphore("isDel", "false"); 
             box.setSelectedItem("2:No");
         }
         
         rowLength=Integer.parseInt(mapperElement.getAttribute("rowLength"));
         colLength=Integer.parseInt(mapperElement.getAttribute("colLength"));
         rowData = new String[rowLength][colLength];
         
         Element headerElement  = (Element)mapperElement.getElementsByTagName("header").item(0);
         Element bodyElement    = (Element)mapperElement.getElementsByTagName("body").item(0);
         Element footerElement  = (Element)mapperElement.getElementsByTagName("footer").item(0);
         
         // For Header 
         
        int row=-1;
        int col=0; 
         String typeOfTag      = "Header";
         NodeList headerNodeList =   headerElement.getChildNodes();
         int headerNodeListSize = headerNodeList.getLength();
         for(int i=0;i<headerNodeListSize;i++){
             Node node = headerNodeList.item(i);
             short type = node.getNodeType();
             if(type==Node.ELEMENT_NODE){
               row = row + 1;
               col = 0;
               Element fieldElement = (Element)node;
               String fieldName = fieldElement.getTextContent();
               String size      = fieldElement.getAttribute("size");
               
               rowData[row][col]=fieldName;
               col = col + 1;
               rowData[row][col]=typeOfTag;
               col = col + 1;
               rowData[row][col]=size;
             }
         
         }
         
         
         // For Body
         
         
//        row=-1;
//        col=0; 
        typeOfTag      = "Body";
        headerNodeList =   bodyElement.getChildNodes();
        headerNodeListSize = headerNodeList.getLength();
         for(int i=0;i<headerNodeListSize;i++){
             Node node = headerNodeList.item(i);
             short type = node.getNodeType();
             if(type==Node.ELEMENT_NODE){
               row = row + 1;
               col = 0;
               Element fieldElement = (Element)node;
               String fieldName = fieldElement.getTextContent();
               String size      = fieldElement.getAttribute("size");
               
               rowData[row][col]=fieldName;
               col = col + 1;
               rowData[row][col]=typeOfTag;
               col = col + 1;
               rowData[row][col]=size;
             }
         
         }      
         
         
         
         
         
         
         
         
         
         // For Footer
         
         
         typeOfTag      = "Footer";
         headerNodeList =   footerElement.getChildNodes();
         headerNodeListSize = headerNodeList.getLength();
         for(int i=0;i<headerNodeListSize;i++){
             Node node = headerNodeList.item(i);
             short type = node.getNodeType();
             if(type==Node.ELEMENT_NODE){
               row = row + 1;
               col = 0;
               Element fieldElement = (Element)node;
               String fieldName = fieldElement.getTextContent();
               String size      = fieldElement.getAttribute("size");
               
               rowData[row][col]=fieldName;
               col = col + 1;
               rowData[row][col]=typeOfTag;
               col = col + 1;
               rowData[row][col]=size;
             }
         
         }  
         
         
//           NodeList list =   mapperElement.getChildNodes();
//           int listLength = list.getLength();
//           int row=-1;
//           int col=0;
//           for(int i=0;i<listLength;i++){
//               Node node   = list.item(i);
//               
//               short type = node.getNodeType();
//               if(type==Node.ELEMENT_NODE){
//                  row = row + 1;
//                  col = 0;
//                 Element fieldElement = (Element)node;
//                 String value = fieldElement.getTextContent();
//                 String size  = fieldElement.getAttribute("size");
//                 
//                 rowData[row][col]=value;
//                 col=col+1;
//                 rowData[row][col]=size;
//                
//               }
//           }
        
         
         
        return null;
    }

    @Override
    protected void done() {
        ShellOutputStream output  = new ShellOutputStream();
      String colName="";
      if(isDel==false){
         colName= "Size";
      }else{
        colName= "Delimiter";
      
      }
          
           
          
           int row =  rowData.length;
           int col = rowData[0].length;
           JTable table = new JTable(row, col);
           
           DefaultTableModel model = (DefaultTableModel)table.getModel();
           table.setRowHeight(30);
           // Create Header Table
           table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Field");
           TableColumn typeCol=table.getTableHeader().getColumnModel().getColumn(1);
           typeCol.setHeaderValue("Type");
           JComboBox<String> s = new JComboBox<String>();
           FileInputStream stream=null;
           String []colTable = null;
        try {
            stream = new FileInputStream(new File(Path.COL_LAYOUT+File.separator+"MapperTypeValue.txt"));
            StringBuffer buf=output.readFile(stream);
            colTable=buf.toString().split("\r\n");
        } catch (FileNotFoundException ex) {
            
            this.log.error(ex);
            Logger.getLogger(ExtractMapperOpenThread.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Runtime Error, Application dependency not found", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            this.log.error(ex);
            JOptionPane.showMessageDialog(null,"Runtime Error, Application dependency not found", "Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ExtractMapperOpenThread.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
           for(String s1:colTable){
              s.addItem(s1);
           }
           
           BranchEditor ed = new BranchEditor(s);
           BranchRenderer rd = new BranchRenderer(colTable);
           typeCol.setCellEditor(ed);
           typeCol.setCellRenderer(rd);
           table.getTableHeader().getColumnModel().getColumn(2).setHeaderValue(colName);
         
           String val="";
           String del="";
           String type ="";
           for(int i=0;i<row;i++){
           
             for(int j=0;j<col;j++){
                 switch (j){
                     case 0:
                           val = rowData[i][j];
                           model.setValueAt(val, i, j);
                           break;
                     case 1:
                           type = rowData[i][j];
                           model.setValueAt(type, i, j);
                           break;
                     case 2:
                           del = rowData[i][j];
                           model.setValueAt(del, i, j);
                           break;  
                 }
                 
             }
//             model.setValueAt(new Object[]{val,del},i,j);
           }
        
        JScrollPane scrol = new JScrollPane(table);
        pane.addTab("Extract Mapper",scrol);
        
    }

    
    
    
    
   
 
    
    
}
