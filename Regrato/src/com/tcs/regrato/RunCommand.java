/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.tcs.jpath.modal.Host;
import com.tcs.jpath.modal.JConfig;
import com.tcs.regrato.modal.Variables;
import com.tcs.rtestingframework.tcl.action.TCLAction;
import com.tcs.rtestingframework.tcl.parser.TCLParser;
import com.tcs.rtestingframework.tcl.tag.Click;
import com.tcs.rtestingframework.tcl.tag.Field;
import com.tcs.rtestingframework.tcl.tag.Put;
import com.tcs.rtestingframework.tcl.tag.Run;
import com.tcs.rtestingframework.tcl.util.TCLUtil;
import com.tcs.shellsch.Config;
import com.tcs.shellsch.ShellChannel;
import com.tcs.shellsch.ShellOutputStream;
import com.tcs.shellsch.ShellSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author 1299792
 */
final public class RunCommand {
    
    private WebDriver driver;    
    private Object command;
    private TCLAction action;
    private TCLUtil util;
    private String path;
    private Variables var;
    private Field field = null;
    public RunCommand(WebDriver driver,String path) {
       this.driver = driver;
       this.path = path;
       this.action = new TCLAction(this.driver);
        TCLParser p = new TCLParser(this.path);
        this.util = new TCLUtil();
        try {
            Element rootElement = p.getRootElemement();
            this.var = Variables.getVariable(rootElement);
         
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(RunCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(RunCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RunCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
    public int run(){
     int retValue=0;
     String className = this.command.getClass().getSimpleName();
     String location = "";
     String value = "";
     
     WebElement element  =null;
     switch (className){
         case "Put":
             Put put = (Put)this.command;
//             field = this.var.getVariable(put.getVariable());
             location = put.getLocation();
             value    = put.getValue();
             field.setLocation(location);
             try{
               element = this.util.findElement(driver, field);
               this.action.put(element, value);
               this.util.switchOutsideFrame(driver);
             }catch(NoSuchElementException ex){
               retValue = 404;
             }
             
         break;
         case "Click":
             Click click = (Click)this.command;
//             field = this.var.getVariable(click.getVariable());
             location = click.getLocation();
             field.setLocation(location);
             try{
              element = this.util.findElement(driver, field);
              this.action.click(element);
              this.util.switchOutsideFrame(driver); 
             }catch(NoSuchElementException ex){
               retValue = 404;
             }
 
         break;
         case "Run":
         
              Run run = (Run)this.command;
              String jobName = run.getValue();
              String mode = run.getMode();
              JConfig config = new JConfig();
              Host host = config.getHost();
              String ip = "";
              String hostKnown = "";
              String hostPass = "";
              String userName = "";
              Config configShell = new Config();
              switch (mode){
                  case "D":
                      ip=host.getDayHostIp();
                      hostKnown=host.getDayHostKnonwHost();
                      userName=host.getDayHostUserName();
                      hostPass = host.getDayHostPassword();
                  break;
                  case "N":
                      ip=host.getNightHostIp();
                      hostKnown=host.getNightHostKnownHost();
                      userName=host.getNightHostUserName();
                      hostPass = host.getNightHostPassword();
                  break;
                  case "C":
                  default:
                   ip=host.getHostIp();
                   hostKnown=host.getHostKnownHost();
                   hostPass=host.getHostPassword();
                   userName=host.getHostUserName();                  
              
              }

              
              configShell.setIp(ip);
              configShell.setPassword(hostPass);
              configShell.setPort(22);
              configShell.setUserName(userName);
              configShell.setKnownHost(hostKnown);
              
              ShellSession session = new ShellSession();
//              ShellChannel channel = new ShellChannel();
              ShellOutputStream outputFromShell = new ShellOutputStream();
              Session sessionShell=null;
              ChannelExec exec=null;
         try {
             sessionShell= session.getSession(configShell);
             if(sessionShell.isConnected()){
                System.out.println("Connected to "+ip);
                exec=(ChannelExec)sessionShell.openChannel("exec");
                exec.setPty(true);
             }else{
                System.out.println("Unable to connect to "+ip);
                return 404;
             }
             
//             exec =   channel.openExecChannel(sessionShell);
             System.out.println("Running Command  "+jobName);
             String strCommand = "";
             if(jobName.lastIndexOf(".")>=0){
               strCommand="bash --login -c "+ '"'+jobName+'"';
             }else {
                   strCommand="bash --login -c "+ '"'+jobName+'"' + "<<< $'YES'";
             }
             exec.setCommand(strCommand);
             
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
             boolean isConnect = exec.isConnected();
             if(isConnect){
                 try {
                  exec.getExitStatus();
                  String ret =    outputFromShell.execOutputStream(exec.getInputStream());
                  int ret1 = exec.getExitStatus();
                  System.out.println("Output of "+jobName +" "+ret);
                  if(ret1==0){
                   System.out.println(jobName+" Success"); 
                  }else {
                    System.out.println();
                    System.err.println(jobName+" Aborted with return code "+ret1); 
                    System.err.println("Aborting all process");
                    return ret1;
                  }
                 } catch (IOException ex) {
                     Logger.getLogger(RunCommand.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
             }
         } catch (FileNotFoundException ex) {
             Logger.getLogger(RunCommand.class.getName()).log(Level.SEVERE, null, ex);
         } catch (JSchException ex) {
             Logger.getLogger(RunCommand.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
             try{
               sessionShell.disconnect();
               exec.disconnect();
             }catch(NullPointerException ex){
                 
             } 
         }
     
         break;
     
     }
     return retValue;
    }
    
    
    
    public void setCommand(Object command){
       this.command = command;
    }   
    public void setCommand(Object command,Field field){
      this.command=command;
      this.field = field;
    }
    public void setTraceFilePath(String path){
      this.path = path;
    }
}
