/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.shellsch;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1299792
 */
public class ShellOutputStream {
    
    
    
    
    public String execOutputStream(InputStream stream) throws IOException{
      StringBuffer msg=new StringBuffer();
      String output = "";
      Scanner in = new Scanner(stream);
      while(in.hasNext()){
       
        msg.append(in.nextLine());
       
      }
      
      
      return output=clearOutput(msg.toString());
    }
 
    
    public String execOutputStream(InputStream stream,boolean isNewLine) throws IOException{
      StringBuffer msg=new StringBuffer();
      String output = "";
      Scanner in = new Scanner(stream);
      while(in.hasNext()){
       if(isNewLine==false){
        msg.append(in.nextLine());
       }else{
         msg.append(in.nextLine()+"\r\n");
       }
       
      }
      
      
      return output=clearOutput(msg.toString());
    }
    
    
    public StringBuffer readFile(InputStream stream) throws IOException{
       StringBuffer buffer = new StringBuffer();
        
       try(ReadableByteChannel channel = Channels.newChannel(stream)){
          ByteBuffer buf = ByteBuffer.allocate(1024);
          byte []content = null;
          int read = 0;
       
          while((read=channel.read(buf))!=-1){
              buf.flip();
                 for(int i=0;i<buf.limit();i++){
                   buffer.append((char)buf.get());
                 }
             buf.clear();
          }
             
          //channel.close();
         
       }

       return buffer;
    
    }
    
    private String clearOutput(String output){
      String str="";
      String lastString = "DB2";
      StringBuffer buffer = new  StringBuffer(output);
      int indexOfLastString=output.indexOf(lastString);
      if(indexOfLastString>=0){
        int lastLength = (indexOfLastString+lastString.length()+3);
        buffer.delete(0, lastLength);
      }
      return str=buffer.toString();
    }
    public void writeFile(InputStream inputStream,OutputStream outputStream) throws IOException{
        
        try(WritableByteChannel wchannel = Channels.newChannel(outputStream);
        ReadableByteChannel rchannel = Channels.newChannel(inputStream);){
        ByteBuffer buf = ByteBuffer.allocate(1024);
        
        while(rchannel.read(buf)!=-1){
           buf.flip();
           wchannel.write(buf);
           buf.compact();
        }
        } 
        
        
    }
    public void writeFile(String text,OutputStream stream) throws IOException{
            try(WritableByteChannel wchannel = Channels.newChannel(stream)){
                ByteBuffer buf = ByteBuffer.wrap(text.getBytes());
//                buf.flip();
                wchannel.write(buf);
                buf.compact();
        }
    
    
    
    }
    
    
    public void writeFile(String text,File file,boolean isAppend) throws IOException{
       try(RandomAccessFile randomAcc = new RandomAccessFile(file, "rw");
           WritableByteChannel fileChannel = (WritableByteChannel)randomAcc.getChannel();){
           if(isAppend){
            randomAcc.seek(randomAcc.length());
           }
           ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());
           fileChannel.write(buffer);
      }catch(IOException ex){
        
      }  
    
    
    
    }
    
    
}
