/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.resources;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.tcs.regrato.gui.region.Mflags;
import com.tcs.shellsch.ShellOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 1299792
 */
public class Path {
   final public static String TEMP_PATH = "temp";
   final public static String CONFIG_PATH = "config";
   final public static String TRACED_DATA  = "trace";
   final public static String DATA = "data";
   final public static String SEMA_FILE_NAME = "sema.config";
   final public static String SEMA_PATH = "data" +File.separator +"param" + File.separator + SEMA_FILE_NAME;
   final public static String HOST_OUT = "host_run_output";
   final public static String JOB_OUT  = HOST_OUT+File.separator +"job_output";
   final public static String LOG4J_PATH = "data" + File.separator + "param" + File.separator + "log4j2.xml";
   final public static String LAYOUT = "data" + File.separator + "layout";
   final public static String APP_ICON = "ico";
   final public static String PARAM_PATH  = "data" + File.separator +"param";
   final public static String PATH = PARAM_PATH + File.separator + "path.properties";
   final public static String SCRIPT = DATA + File.separator + "scripts";
   final public static String MAPPER = TRACED_DATA + File.separator + "mapper";
   final public static String COL_LAYOUT = LAYOUT + File.separator + "col";
   final public static String ICON = DATA + File.separator + "icon";
   final public static String EXTRACT_OUTPUT = "output" + File.separator + "extract";
   public String findPath(String folderName){
     String path=null;
     File file = new File(folderName);
     path=file.getAbsolutePath();
     return path;
   }
   
   public void setPath(String name,String path)throws IOException{
       String file = Path.PARAM_PATH+File.separator+"path.properties";
       FileOutputStream   stream = null;
      try( 
         FileInputStream load = new FileInputStream(file);){
         Properties prop = new Properties();
         prop.load(load);
         stream =  new FileOutputStream(file);
         prop.setProperty(name,path);
         prop.store(stream,"All Path Constant");
       
      }finally{
        stream.flush();
        stream.close();
      }
       
   }
   public String getPath(String key) throws FileNotFoundException, IOException{
     String path = null;
     try(FileInputStream stream = new FileInputStream(PATH);){
        Properties prop = new Properties();
        prop.load(stream);
        path=prop.getProperty(key);
     }
     
     return path;
   }
   public String getPath(Session session,String fileName){
   
        String retPath = null;
     try {
                   
                    ChannelExec exec = (ChannelExec)session.openChannel("exec");
                    exec.setPty(true);
                    if(fileName.lastIndexOf(".card")>0){
                      exec.setCommand("bash --login -c 'echo $card'");
                    }else{
                      exec.setCommand("bash --login -c 'echo $data/file'");
                    }
                    int attempt=5;
                    int i=0;
             
                    while(i<=attempt){
                     exec.connect();
                    if(exec.isConnected()){
                     break;
                    }
                    if(i>=attempt){
                      break;
                    }
                    i=i+1;
                    }
                    if(exec.isConnected()){
                     try {
                      ShellOutputStream out = new ShellOutputStream();
                      String path =   out.execOutputStream(exec.getInputStream());
                      path = path + "/" + fileName;
                      retPath = path;
                    } catch (IOException ex) {
                        
                        JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                    }                   
                    }else{
                        JOptionPane.showMessageDialog(null,"Unable to connect to region" , "Error", JOptionPane.ERROR_MESSAGE);
                    }
 
                } catch (JSchException ex) {
                    
                      JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                }
     return retPath; 
   
   }
      public String getPath(Session session,String fileName,String inst) throws JSchException{
   
        String retPath = null;
//     try {
                   
                    ChannelExec exec = (ChannelExec)session.openChannel("exec");
                    exec.setPty(true);
                  if(inst.equals("001")){
                    if(fileName.lastIndexOf(".card")>0){
                      exec.setCommand("bash --login -c 'echo $card'");
                    }else{
                      exec.setCommand("bash --login -c 'echo $data/file'");
                    }
                  }else if(inst.equals("002")){
                     if(fileName.lastIndexOf(".card")>0){
                      exec.setCommand("bash --login -c '. setinst 002;echo $card'");
                    }else{
                      exec.setCommand("bash --login -c '. setinst 002;echo $data/file'");
                    }                 
                  
                  }
                    int attempt=5;
                    int i=0;
             
                    while(i<=attempt){
                     exec.connect();
                    if(exec.isConnected()){
                     break;
                    }
                    if(i>=attempt){
                      break;
                    }
                    i=i+1;
                    }
                    if(exec.isConnected()){
                     try {
                      ShellOutputStream out = new ShellOutputStream();
                      String path =   out.execOutputStream(exec.getInputStream());
                      path = path + "/" + fileName;
                      retPath = path;
                    } catch (IOException ex) {
                        
                        JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                    }                   
                    }else{
                        JOptionPane.showMessageDialog(null,"Unable to connect to region" , "Error", JOptionPane.ERROR_MESSAGE);
                    }
 
//                } 
     return retPath; 
   
   }
      
   public String getContent(String path,Session session) throws JSchException, SftpException, IOException{
      String data = "";
      ShellOutputStream out = new ShellOutputStream();
      ChannelSftp sftp =   (ChannelSftp)session.openChannel("sftp");
      sftp.connect();
      String temp =     out.execOutputStream(sftp.get(path),true);
      
       if(sftp!=null){
         sftp.disconnect();
       }
       
      return data=temp;
   
   }   
      
   
   // Only search in $data/file
   public String getContent(Session session,String fileName){
      String mflagsDate = null;
     try {
                    ChannelSftp sftp =   (ChannelSftp)session.openChannel("sftp");
                    ChannelExec exec = (ChannelExec)session.openChannel("exec");
                    exec.setPty(true);
                    if(fileName.lastIndexOf(".card")>0){
                      exec.setCommand("bash --login -c 'echo $card'");
                    }else{
                      exec.setCommand("bash --login -c 'echo $data/file'");
                    }
                    int attempt=5;
                    int i=0;
             
                    while(i<=attempt){
                     exec.connect();
                    if(exec.isConnected()){
                     break;
                    }
                    if(i>=attempt){
                      break;
                    }
                    i=i+1;
                    }
                    if(exec.isConnected()){
                     try {
                      ShellOutputStream out = new ShellOutputStream();
                      String path =   out.execOutputStream(exec.getInputStream());
                      path = path + "/" + fileName;
                         try {
                           sftp.connect();
                           String date =     out.execOutputStream(sftp.get(path));
                           mflagsDate=date;
                         } catch (SftpException ex) {
                             Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                         }
                    } catch (IOException ex) {
                        
                        JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                    }                   
                    }else{
                        JOptionPane.showMessageDialog(null,"Unable to connect to region" , "Error", JOptionPane.ERROR_MESSAGE);
                    }
 
                } catch (JSchException ex) {
                    
                      JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                }
     return mflagsDate;
  }
   
   // Only search in $data/file
   public String getContent(Session session,String fileName,String inst) throws JSchException{
      String mflagsDate = null;
//     try {
                    ChannelSftp sftp =   (ChannelSftp)session.openChannel("sftp");
                    ChannelExec exec = (ChannelExec)session.openChannel("exec");
                    exec.setPty(true);
                   if(inst.equals("001")){
                    if(fileName.lastIndexOf(".card")>0){
                      exec.setCommand("bash --login -c 'echo $card'");
                    }else{
                      exec.setCommand("bash --login -c 'echo $data/file'");
                    }
                   }else if(inst.equals("002")){
                      if(fileName.lastIndexOf(".card")>0){
                      exec.setCommand("bash --login -c '. setinst 002;echo $card'");
                    }else{
                      exec.setCommand("bash --login -c '. setinst 002;echo $data/file'");
                    }                    
                   
                   }
                    int attempt=5;
                    int i=0;
             
                    while(i<=attempt){
                     exec.connect();
                    if(exec.isConnected()){
                     break;
                    }
                    if(i>=attempt){
                      break;
                    }
                    i=i+1;
                    }
                    if(exec.isConnected()){
                     try {
                      ShellOutputStream out = new ShellOutputStream();
                      String path =   out.execOutputStream(exec.getInputStream());
                      path = path + "/" + fileName;
                         try {
                           sftp.connect();
                           String date =     out.execOutputStream(sftp.get(path));
                           mflagsDate=date;
                         } catch (SftpException ex) {
                             Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                         }
                    } catch (IOException ex) {
                        
                        JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                    }                   
                    }else{
                        JOptionPane.showMessageDialog(null,"Unable to connect to region" , "Error", JOptionPane.ERROR_MESSAGE);
                    }
 
//                } 
//     catch (JSchException ex) {
                    
//                      JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
//                    Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
//                }
     return mflagsDate;
  }
}
