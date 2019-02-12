/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.mapper.text;

import com.tcs.mapper.exception.SizeLengthDifferenceException;
import com.tcs.mapper.util.Comman;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1299792
 */
public class ExtractReader {

    File file;
    Comman comman;
    public ExtractReader(File file ) {
      this.file=file;
      this.comman = new Comman();
    }
       
    
    
   public ArrayList<String> convertIntoList(String lineString,int []sizeArray) throws SizeLengthDifferenceException{
       ArrayList<String> line = new ArrayList<>();
     
       int lengthOfString  = lineString.length();
       int lenOfSizeArray = sizeArray.length;
//       int sum=this.comman.sumOfArray(sizeArray);
//       if(lengthOfString!=sum){
//          throw new SizeLengthDifferenceException("Extract Line and Mapper Defination not matched");
//       }
       int firstIndex=0;
       int lastIndex = 0;
        lastIndex = sizeArray[0]; 
       for(int i=0;i<lenOfSizeArray;i++){
           String temp ="";
         try{
            temp= lineString.substring(firstIndex, lastIndex);
          
          firstIndex = lastIndex;
          try{
          lastIndex = lastIndex + sizeArray[(i+1)];
          if(lastIndex>lengthOfString){
            lastIndex = lengthOfString;
          }
          }catch(ArrayIndexOutOfBoundsException ex){
              if(temp.length()==0){
                  temp = "";
              }
            
          }
         }catch(StringIndexOutOfBoundsException ex){
            temp = "";
         }
         
         line.add(temp);
       }
       
       
       return line;
   }
    
    
    
    public String getLine(long lineNumber){
       String line=null;
           
       BufferedReader reader = null;
        try {
             reader = new BufferedReader(new FileReader(file));
             for(int i=1;i<=lineNumber;i++){
                 
                 line =  reader.readLine();
             }
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExtractReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
        
          if(reader!=null){
              try {
                  reader.close();
              } catch (IOException ex) {
                  Logger.getLogger(ExtractReader.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        }
        
        
        
       return line;
    }
    
}
