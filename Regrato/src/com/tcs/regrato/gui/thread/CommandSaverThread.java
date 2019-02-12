/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;


import com.tcs.jpath.util.ExcelWriter;
import com.tcs.regrato.modal.CommandEditorModal;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.util.Comman;
import com.tcs.rtestingframework.tcl.parser.ExcelToTCL;
import com.tcs.rtestingframework.tcl.parser.TCLParser;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.xml.transform.TransformerException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.w3c.dom.Document;

/**
 *
 * @author 1299792
 */
public class CommandSaverThread extends SwingWorker<Integer, Void>{
    
    
    private String rowData[][];
    private int rowSize;
    private int colSize;
    private JDialog progressBar;
    private File savePath;
    private HashMap<String,String[][]> multipleRowData;
    private  String desc;
    private org.apache.log4j.Logger log;

    public CommandSaverThread(File savePath,JDialog progressBar,HashMap multipleRowData){
        this.savePath= savePath;
        this.progressBar = progressBar;
        this.multipleRowData = multipleRowData;
        
        this.log= new Comman().getLogger();
    }
    public void setName(String desc){
        this.desc = desc;
    }
   
    
    @Override
    protected Integer doInBackground() throws Exception {

         ExcelWriter writer = new ExcelWriter(savePath.getPath());
         Set<String>  testCaseName = null;
         WritableSheet sheet  = null;
         int retValue = 0;
         WritableWorkbook workbook  = Workbook.createWorkbook(savePath);
         testCaseName = this.multipleRowData.keySet();
         Iterator it = testCaseName.iterator();
         int sheetIndex = 0;
         while(it.hasNext()){
           String sheetName = (String)it.next();
           sheet  =  workbook.createSheet(sheetName, sheetIndex);
           this.rowData = this.multipleRowData.get(sheetName);
           this.rowSize = this.rowData.length;
           this.colSize = this.rowData[0].length;
           sheet=writer.addingJPathHeader(sheet);
           WritableCellFormat format = new WritableCellFormat();
           format.setWrap(true);  
           int excelRow = 0;
           int excelCol = 0;
           for(int row =0;row<this.rowSize;row++){
           for(int col=0;col<this.colSize;col++){
//             if(row==0 ){
//                 continue;
//              }
                if(row == 0 ){
                   excelRow = 1;    
                }else{
                   excelRow = row + 1;
                }
               String value = rowData[row][col];
              
               Label wc = null;
               switch(col){
                   case 0:
                     excelCol = 6;
                       break;
                   case 1:
                       excelCol = 3;

                       value = value.toUpperCase();
                       break;
                   case 2:
                       excelCol = 5;
//                       excelRow = row;
                       break;
                   case 3:
                       excelCol = 4;
//                       excelRow = row;
                       break;
                   case 4:
                       if(value.equals("id")){
                         excelCol = 1;
                         value = rowData[row][col+1];
//                         excelRow = row;
                       }else if(value.equals("className")){
                         excelCol = 2;
                         value = rowData[row][col+1];
//                         excelRow = row;
                       }else if(value.contains("mode")){
                         excelCol = 1;
                         value = rowData[row][col+1];
                       }else if(value.equals("cssSelector")){
                         excelCol = 7;
                         value = rowData[row][col+1];
                       }else if(value.equals("card")){
                         excelCol = 1;
                         value = rowData[row][col+1];
                       }
                       
                       break;
                   case 5:
                      if(value.contains("pass:")){
                         excelCol = 2;
                        
                      }
                      break;
               
               }
//               System.out.println("excelRow "+ excelRow +"excelCol "+excelCol);
                wc = new Label(excelCol, excelRow,value, format);
               sheet.addCell(wc);
              
           }

         }
          sheetIndex=sheetIndex+1;
         }
               workbook.write();
               workbook.close();
            
               return retValue;
    }

    @Override
    protected void done() {
        try {
        
            int retValue =   get();
            
            if(retValue==0){
                File file =   new File(this.savePath.getPath());
                TCLParser par = new TCLParser();
                ExcelToTCL tcl = new ExcelToTCL(file.getPath());
                Document doc = tcl.convertExcelToXml();
//                String savePath = Path.TRACED_DATA+File.separator+file.getName()+".xml";
                String fileName = file.getName();
                if(fileName.lastIndexOf("xml")<0){
                    fileName=fileName.replace(".xls", "");
                    fileName=fileName+".xml";
                }
                String savePath = Path.TRACED_DATA+File.separator+fileName;
                System.out.println("Saved Path is "+savePath);
                String description = this.desc;
                System.out.println("Description is "+description);
                doc.getDocumentElement().setAttribute("name", description);
                par.saveXml(savePath, doc);
                
            JOptionPane.showMessageDialog(null, "Saved!", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CommandSaverThread.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        } catch (ExecutionException ex) {
            JOptionPane.showMessageDialog(null,ex.getLocalizedMessage(), "Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CommandSaverThread.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getLocalizedMessage(), "Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CommandSaverThread.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CommandSaverThread.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        }finally{
        
        this.progressBar.setVisible(false);
        }
    }
    
}
