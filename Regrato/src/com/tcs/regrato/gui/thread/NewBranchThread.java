/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.tcs.regrato.RunCommand;
import com.tcs.regrato.modal.CommandEditorTableModel;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.resources.SSHSessionFactory;
import com.tcs.regrato.util.Comman;
import com.tcs.regrato.util.ErrorChecker;
import com.tcs.rtestingframework.tcl.tag.Run;
import com.tcs.shellsch.ShellOutputStream;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import javax.swing.JTable;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

/**
 *
 * @author 1299792
 */
public class NewBranchThread implements Runnable{

     private List<Run> run;
     private String commandFileName;
     private Comman commanFunction;
//     private  CommandEditorTableModel tableModel;
     private volatile  JTable table;
     private AtomicInteger noOfJobDone;
     private final Logger log;
    public NewBranchThread(String commandFileName,List<Run> run,JTable table,AtomicInteger noOfJobDone) {
        this.run=run;
        this.commandFileName=commandFileName;
        this.commanFunction=new Comman();
        this.table=table;
        this.noOfJobDone=noOfJobDone;
        this.log = getLogger();
    }

    
    private   synchronized void setColor(int variable,Color c,String text){
            CommandEditorTableModel m =(CommandEditorTableModel)table.getModel();
            int rowNum=0;
            
            rowNum = m.getRowNumByVar(variable);
//            System.err.println(rowNum);
            m.setRowColor(rowNum, c);
            m.setValueAt(text, rowNum, 6);
            table.revalidate();
            table.repaint();
    }
  
//    private   synchronized void setText(int rowNum,String text){
//            CommandEditorTableModel m=(CommandEditorTableModel)table.getModel();
//            m.setValueAt(text, rowNum, 6);
//            table.revalidate();
//            table.repaint();
//    }
    
    
    
    @Override
    public void run() {
       

       
       ThreadContext.put("threadId", this.commandFileName);
       int sizeOfJobs = run.size();
       for(int i=0;i<sizeOfJobs;i++){
         Run tempRun = (Run)run.get(i);
         int rowNum = Integer.parseInt(tempRun.getVar());
         noOfJobDone.getAndIncrement();
//           setColor(rowNum, Color.BLUE);
           setColor(rowNum, Color.ORANGE,"Running");
//           setText(rowNum, "Running");
         int retValue =  processJob(tempRun);
         if(retValue==0){
      
              setColor(rowNum, Color.GREEN,"Success");
             log.info(tempRun.getValue()+" Success");
//             setText(rowNum, "Success");
         }else {
   
             setColor(rowNum, Color.RED,"Abort");
//              setText(i, "Abort");
             String errorMessage = ErrorChecker.getErrorMessage(retValue);
             if(errorMessage!=null){
               log.error(retValue+" : "+errorMessage);
             }
             log.error(tempRun.getValue()+" Aborted with return code "+retValue);
             log.error("Aborting all session and process");
//             log=null;
             ThreadContext.remove("threadId");
             return;
         }  
       
       
       }
         ThreadContext.remove("threadId");
//         log=null;
    }
    
    private Session getCurrentSSHSession(String branch,String mode) throws FileNotFoundException, JSchException{
             Session s=null;
             s=SSHSessionFactory.getSSHSession(branch,mode);
             return s;
    
    }
    private int processJob(Run run){
//        Logger log = getLogger(commandFileName);
        String jobOutpuFileName = Path.JOB_OUT+File.separator+this.commandFileName;
        File file = new File(jobOutpuFileName);
        int ret1=0;
        String jobName = run.getValue();
        String mode = run.getMode();
        String branch = run.getLocation();
        ShellOutputStream outputFromShell = new ShellOutputStream();
        Session sessionShell=null;
        ChannelExec exec=null;
         try {
             sessionShell= getCurrentSSHSession(branch,mode);
             if(sessionShell==null){
                return 500;
             }
             if(sessionShell.isConnected()){
                log.info("Connected to "+sessionShell.getHost());
                exec=(ChannelExec)sessionShell.openChannel("exec");
                exec.setPty(true);
             }else{
                log.info("Unable to connect to "+sessionShell.getHost());
                return 404;
             }

             log.info("Running Command "+jobName);
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
                  String ret =    outputFromShell.execOutputStream(exec.getInputStream(),true);
                  ret1 = exec.getExitStatus();
//                  log.info("Output of "+jobName+" "+ret);
//                  this.commanFunction.writeStringInFile(file, "Output Of "+jobName+": \r\n"+ret+"\r\n");
                  outputFromShell.writeFile("============================================================= \r\n", file,true);
                  outputFromShell.writeFile("Output Of "+jobName+": \r\n"+ret+"\r\n", file,true);
                  outputFromShell.writeFile("============================================================= \r\n", file,true);
                 } catch (IOException ex) {
                     java.util.logging.Logger.getLogger(RunCommand.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
             }
         } catch (FileNotFoundException ex) {
             log.error(ex);
             java.util.logging.Logger.getLogger(RunCommand.class.getName()).log(Level.SEVERE, null, ex);
         } catch (JSchException ex) {
             log.error(ex);
             java.util.logging.Logger.getLogger(RunCommand.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
             try{
               
//               sessionShell.disconnect();
               exec.disconnect();
             }catch(NullPointerException ex){
                 log.error(ex);
             } 
         }   
    
    
          return ret1;
    
    }
    
    
    
    private Logger getLogger(){
      Logger log = null;
      log = Logger.getLogger("OUTPUT");
      return log;
    
    }
    
    
   @Deprecated
    private Logger getLogger(String fileName){
        Logger log = null;
        String savePath = "host_run_output"+File.separator+fileName;
        ConfigurationBuilder<BuiltConfiguration> config = ConfigurationBuilderFactory.newConfigurationBuilder();
        AppenderComponentBuilder fileAppender= config.newAppender("log", "File");
 
        fileAppender.addAttribute("fileName", savePath);
      
        LayoutComponentBuilder standard = config.newLayout("PatternLayout");
        standard.addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable");
        fileAppender.add(standard);
        
        RootLoggerComponentBuilder rootLogger = config.newRootLogger(org.apache.logging.log4j.Level.ALL);
        rootLogger.add(config.newAppenderRef(fileAppender.getName()));
        
        config.add(rootLogger);
        config.add(fileAppender);
        
        Configurator.initialize(config.build());
//         Logger.getLogger("HOST OUPUT");
       
        log=Logger.getLogger(NewBranchThread.class.getName());
       
        return log;
    }
    
}
