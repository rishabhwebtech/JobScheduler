/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.tcs.jpath.connect.SeleniumConnect;
import com.tcs.jpath.exception.BrowserNotSupported;
import com.tcs.regrato.JobChecker;
import com.tcs.regrato.RunCommand;
import com.tcs.regrato.gui.RunningStatus;
import com.tcs.regrato.modal.CommandEditorModal;
import com.tcs.regrato.modal.CommandEditorTableModel;
import com.tcs.regrato.modal.GuiModal;
import com.tcs.regrato.resources.SeqType;
import com.tcs.regrato.util.Comman;
import com.tcs.regrato.util.ErrorChecker;
import com.tcs.regrato.util.MessageConsole;
import com.tcs.rtestingframework.tcl.tag.Click;
import com.tcs.rtestingframework.tcl.tag.Field;
import com.tcs.rtestingframework.tcl.tag.Put;
import com.tcs.rtestingframework.tcl.tag.Run;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author 1299792
 */
public class RunCommandThread  extends SwingWorker<Object, String> implements JobChecker{
   
    public   List<JTable> tableClass;
    public volatile JTable table; 
    private JTextPane outputPane;
    private SeleniumConnect connect;
    private boolean hostOnly;
    public AtomicInteger noOfJobsDone;
    private org.apache.log4j.Logger log;
//     RunningStatus stat;
  
    public RunCommandThread(List<JTable> table,JTextPane pane,boolean isHost) {
//      SharedTable.table = table;
 
      this.tableClass = table;
      this.outputPane = pane;
      this.hostOnly=isHost;
      this.log = new Comman().getLogger();
      
    }

    
    
    @Override
    protected Object  doInBackground() throws Exception {
        Date runningDate = new Date();
        Comman comman = new Comman();
        int totalNumberOfBranch=0;
//        List<Run> jobToRun = new ArrayList<>();
//        Set<String> uniqueBranch = new HashSet<>();
          List<Run> jobToRun=null;
          Set<String> uniqueBranch = null;
//        List<Thread> allThreadToRun = new ArrayList<>();
        System.out.println("Start Time "+comman.toStringDate(runningDate));
//        boolean hostOnly = CommandEditorModal.getCommandEditorModal().getHostOnly().isSelected();
        WebDriver driver = null;
        if(this.hostOnly==false){
          connect = SeleniumConnect.getInstance();
          driver = connect.getDriver();
        }
        
        String outputMessage = "";
        RunCommand command = new RunCommand(driver,CommandEditorModal.getCommandEditorModal().getFilePath());
        
        String traceFilePath = CommandEditorModal.getCommandEditorModal().getFilePath();
        command.setTraceFilePath(traceFilePath);
        int lengthOfListTable = this.tableClass.size();
        int seqNo =new Comman().getSeqNumber(SeqType.LOGGER_TYPE);
       for(int j=0;j<lengthOfListTable;j++){
         table = this.tableClass.get(j);
         DefaultTableModel model =(DefaultTableModel)table.getModel();
         int row = table.getRowCount();
         noOfJobsDone = new AtomicInteger(0);
         jobToRun = new ArrayList<>();
         uniqueBranch = new HashSet<>();
         for(int i=0;i<row;i++){
           table.setRowSelectionInterval(i, 0);
           String variable = (String)model.getValueAt(i, 0);
           String commandToRun  = (String)model.getValueAt(i, 1);
           String value    = (String)model.getValueAt(i, 2);
           
           String location = (String)model.getValueAt(i,3);
//           System.out.println(location);
           String by  = (String)model.getValueAt(i, 4);
           String map = (String)model.getValueAt(i, 5);
           Put put = null;
           Click click = null;
           Run run = null;
           Field f = new Field();
           f.setBy(by);
           f.setLocation(location);
           f.setMap(map);
           f.setName(variable);
           switch(commandToRun){
               case "put":
                 put = new Put();
                 put.setLocation(location);
                 put.setValue(value);
                 put.setVariable(variable);

               break;
               case "click":
                   click = new Click();
                   click.setLocation(location);
                   click.setVariable(variable);
               break;
               case "run":
                   run = new Run();
                   run.setValue(value);
               break;
           }
           if(put!=null){
             command.setCommand(put,f);
             outputMessage = "Running put command";
           }else if(click!=null){
              command.setCommand(click,f);
              outputMessage = "Running click command";
           }else if(run!=null){
              command.setCommand(run);
              outputMessage = "Running run command";
           }
           System.out.println(outputMessage);
//           publish(outputMessage);
           if(hostOnly==true && commandToRun.equals("run")==false){
             continue;
           }
           if(hostOnly==true && commandToRun.equals("run")==true){
               Run temp =  new Run();
               temp.setLocation(location);
               temp.setMode(map);
               temp.setValue(value);
               temp.setVar(variable);
               jobToRun.add(temp);
               uniqueBranch.add(location);
               continue;
           }
            CommandEditorTableModel m = (CommandEditorTableModel)table.getModel();
             int ret=0;
            
             ret=command.run();
          
             
             if(ret!=0){
//             this.table.setSelectionBackground(Color.RED);
             
             m.setRowColor(i, Color.RED);
             System.err.println(new ErrorChecker().getErrorMessage(ret));
             return null;
           }else{
//            this.table.setSelectionBackground(Color.GREEN);
            m.setRowColor(i, Color.GREEN);
           }
        }
        if(this.hostOnly==true){
         totalNumberOfBranch=uniqueBranch.size();
         Iterator it = uniqueBranch.iterator();
         Map<String,List<Run>> jobs = new HashMap<>(0);
        while(it.hasNext()){
           String branchName =  (String)it.next();
           List<Run> l = new ArrayList<>();
           int len=jobToRun.size();
           for(int i=0;i<len;i++){
            Run run = jobToRun.get(i);
             boolean isBranch=run.getLocation().equals(branchName);
             if(isBranch==true){
                l.add(run);
//                jobToRun.remove(i);
             }
                
           }
           jobs.put(branchName, l);
        }

         it = uniqueBranch.iterator();
         int count = 1;
         
         while(it.hasNext()){
           
            String branchName = (String)it.next();
            List<Run> run=jobs.get(branchName);
            String tabName = this.table.getName();
            Calendar cal = Calendar.getInstance();
            int DD=cal.get(Calendar.DATE);
            int MM=cal.get(Calendar.MONTH)+1;
            int YEAR = cal.get(Calendar.YEAR);
            String dateCon=DD+"_"+MM+"_"+YEAR;
            
            
//            String initial = (new File(traceFilePath).getName().replace("xml",String.format("%02d",count)))+"_"+branchName;
            String initial =  "HOST_OUTPUT_"+String.format("%02d", seqNo)+"_"+(new File(traceFilePath).getName().replace("xml",""))+"_"+tabName+"_"+dateCon+".txt";
            NewBranchThread branchThread = new NewBranchThread(initial,run,table,noOfJobsDone);
            Thread thread = new Thread(branchThread);
            thread.setName(branchName);
            
            thread.start();            
            System.out.println("Running Branch "+branchName);
            System.out.println("Output will be logged in host_run_output folder "+initial);
            count = count + 1;
         }
        }
              //   
            
              check(row);
       }
        return null;

    }
 
    @Override
    protected void done() {
        try {
             
         Date endDate = new Date();
         System.out.println("End Time "+new Comman().toStringDate(endDate));            
            
        GuiModal guiModal = GuiModal.getGuiModal();
        MessageConsole console = new MessageConsole(guiModal.getOutputLogsPane(), true);
        console.redirectOut();        

            connect.closeDriver();
//        stat.setVisible(false);
//        stat.dispose();
        } catch (BrowserNotSupported ex) {
            Logger.getLogger(RunCommandThread.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        } catch(NullPointerException ex){
            this.log.error(ex);
        }
    }
    
    @Override
    public void check(int i){
        
        while(noOfJobsDone.get()!=i){
            try {
               
                Thread.sleep(1000);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(RunCommandThread.class.getName()).log(Level.SEVERE, null, ex);
                  this.log.error(ex);
            }
        }
    }
    
}
