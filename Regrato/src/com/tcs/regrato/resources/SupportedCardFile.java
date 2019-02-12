/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.resources;

import com.tcs.regrato.util.Comman;
import com.tcs.shellsch.ShellOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author 1299792
 */
public class SupportedCardFile {

    
    
  public  boolean isSupportedCardFile(String cardFile) throws FileNotFoundException, IOException{
      File param = new File(Path.PARAM_PATH+File.separator+"SuppoertedCardFile.dat");
      
      ShellOutputStream output = new ShellOutputStream();
      String fileData =  output.readFile(new FileInputStream(param)).toString();
      String fileDataArray[] = fileData.split("\r\n");
      boolean isSup=false;
      Arrays.sort(fileDataArray);
      int ret = Arrays.binarySearch(fileDataArray, cardFile);
      if(ret>=0){
       isSup = true;
      }
      return isSup;
    }
    public String getCardLayout(String layout) throws CardFileNotSupportedException{
       String str=null;
       layout=layout.replace(".card","CARD.txt");
       str=layout;
       File layoutFile = new File(Path.LAYOUT+File.separator+layout);
       boolean isE=layoutFile.exists();
       if(isE==false){
         throw new CardFileNotSupportedException("Card File Not Supported");
       }
       return str;
    }
}
