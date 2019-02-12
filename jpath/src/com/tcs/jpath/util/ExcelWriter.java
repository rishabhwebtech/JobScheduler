/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.jpath.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tcs.jpath.info.GetInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author 1299792
 */
public class ExcelWriter {
   
   String fileName ; 
   public ExcelWriter(String fileName){
       this.fileName = fileName;
   }
    
   
  public void writeAllElementsDetailsInExcel() throws FileNotFoundException, IOException, WriteException{
      Comman commanFunction = new Comman();
      if(this.fileName.lastIndexOf(".xls")<0){
         this.fileName = this.fileName + ".xls";
      }else if(this.fileName.lastIndexOf(".xlsx")>0){
         
      }
      
      FileOutputStream stream = new FileOutputStream(new File(fileName));
      WritableWorkbook workbook =    Workbook.createWorkbook(stream);
      WritableSheet sheet  =  workbook.createSheet("Case", 0);
      sheet = addingJPathHeader(sheet);
      WritableCellFormat format = new WritableCellFormat();
      format.setWrap(true);
      JsonArray data   = commanFunction.getTracedDataFromTemp();
      int size = data.size();
      for(int i =0;i<size;i++){
        JsonObject object =  (JsonObject)data.get(i);
        String id         =  object.get("id").getAsString();
        String xpath      = object.get("xpath").getAsString();
        String tagName    =  object.get("tagName").getAsString();
        String className  =  object.get("className").getAsString();
        String elementIframeLocation = object.get("iframe").getAsString();
        String valueOfElement       = object.get("value").getAsString();
        String variableValue = object.get("variable").getAsString();
        String cssSelector = object.get("cssSelector").getAsString();
        int objectSize = object.size();
        for(int j=1;j<=objectSize;j++){
            String value =   sheet.getCell((j-1), 0).getContents();
            Label mainData = null;
            switch (value){
                case "Xpath":
                    mainData = new Label((j-1), (i+1), xpath,format);
                  
                    break;
                case "Id": 
                    mainData = new Label((j-1), (i+1), id,format);
                    break;
                case "Tag Name":
                    mainData = new Label((j-1), (i+1), tagName,format);
                    break;
                case "Class Name":
                    mainData = new Label((j-1), (i+1), className,format);
                    break;
                case "Iframe":
                    mainData = new Label((j-1), (i+1), elementIframeLocation,format);
                    break;
                case "Value":
                    mainData = new Label((j-1), (i+1), valueOfElement,format);
                    break;
                case "Variable" :
                     mainData = new Label((j-1), (i+1), variableValue,format);
                     break;
                case "CSS Selector":
                    mainData = new Label((j-1), (i+1), cssSelector,format);
            }
        try{    
            sheet.addCell(mainData);
        }catch(NullPointerException ex){}
        }
        
      }
      
      workbook.write();
      workbook.close();
      stream.flush();
      stream.close();
  } 
  public WritableSheet addingJPathHeader(WritableSheet sheet) throws WriteException{
       WritableSheet sheet1=null;
       GetInfo info  = new GetInfo();
       JsonObject object = info.getHeaderInfo();
       WritableCellFormat format = new WritableCellFormat();
       format.setBackground(Colour.GRAY_50);
       format.setBorder(Border.ALL, BorderLineStyle.THIN);
       int length = object.size();
       for(int i =0;i<length;i++){
          Label label = new Label(i,0,object.get(Integer.toString(i)).getAsString());
         
          label.setCellFormat(format);
          sheet.addCell(label);
       }
       
       sheet1 = sheet;
       
       return sheet1;
  }
    
}
