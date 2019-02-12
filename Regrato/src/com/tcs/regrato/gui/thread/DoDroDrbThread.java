/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.tcs.jpath.modal.JConfig;
import com.tcs.regrato.modal.GuiModal;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.resources.SSHSessionFactory;
import com.tcs.regrato.util.Comman;
import com.tcs.regrato.util.MessageConsole;
import com.tcs.shellsch.ShellOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author 1299792
 */
public class DoDroDrbThread extends SwingWorker<Integer, Void>{
    private String DD;
    private String MM;
    private String YYYY;
    private ChannelExec exec =  null;
    private String inst;
    private JButton button;
    private org.apache.log4j.Logger log;
    public DoDroDrbThread(String DD,String MM,String YYYY,String inst,JButton button) {
      this.DD=DD;
      this.MM=MM;
      this.YYYY=YYYY;
      this.inst=inst;
      this.button=button;
      this.log = new Comman().getLogger();
    }

    
    
    @Override
    protected Integer doInBackground() throws Exception {
        Session session = SSHSessionFactory.getSSHSession(new JConfig().getBranches().getBranches().get(0),"C");
      
        String date = "'" +DD+"/"+MM+"/"+YYYY +"'";
        String inst = "'" +this.inst + "'";
        try{
          exec=(ChannelExec)session.openChannel("exec");
//          exec.setPty(true);
           exec.setCommand("bash --login -s");
        }catch(JSchException ex){
            JOptionPane.showMessageDialog(null,ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            this.log.error(ex);
            return null;
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
          File droScript = new File(Path.SCRIPT+File.separator+"dro");    
          RandomAccessFile droFile = new RandomAccessFile(droScript,"rw");
          droFile.seek(156);
          droFile.write(inst.getBytes());
          droFile.seek(217);
          droFile.write(date.getBytes());
          droFile.close();
          
          
           try(FileInputStream droFileInputStream = new FileInputStream(droScript);){
               OutputStream serverStream = exec.getOutputStream();
               ShellOutputStream s = new ShellOutputStream();
               s.writeFile(droFileInputStream, serverStream);
               StringBuffer buffer=s.readFile(exec.getInputStream());
               System.out.println(buffer.toString());
           }
            
        }
        
        return 0;
    }

    @Override
    protected void done() {
        GuiModal guiModal = GuiModal.getGuiModal();
        MessageConsole console = new MessageConsole(guiModal.getOutputLogsPane(), true);
        console.redirectOut();  
        button.setEnabled(true);
        if(exec!=null){
         exec.disconnect();
        }
    }
    
    
    
}
