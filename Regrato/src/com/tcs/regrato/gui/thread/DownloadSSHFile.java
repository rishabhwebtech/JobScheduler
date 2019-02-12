/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.tcs.jpath.modal.JConfig;
import com.tcs.regrato.event.ButtonHandler;
import com.tcs.regrato.modal.RemoteFileSystemModal;
import com.tcs.regrato.resources.AppIcon;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.resources.SSHSessionFactory;
import com.tcs.regrato.util.Comman;
import com.tcs.shellsch.ShellOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.plaf.FileChooserUI;

/**
 *
 * @author 1299792
 */
public class DownloadSSHFile extends SwingWorker<String, Void>{

    private org.apache.log4j.Logger log;
    private Comman comman;
    private RemoteFileSystemModal mo;
    private String fullPathWithFile ;
    private String fileName;
    public DownloadSSHFile(String fullPathWithFile,String fileName) {
       this.fullPathWithFile = fullPathWithFile;
       this.fileName = fileName;
       this.mo = RemoteFileSystemModal.getRemoteFileSystemModal();
       this.comman = new Comman();
       this.log = this.comman.getLogger();
      
    }

    
    
    @Override
    protected String doInBackground() throws Exception {
         
         String retValue = "0:Done";
         String errorMessage = null;
         Path path = new Path();

                        String mode= (String)mo.getMode().getSelectedItem();
                        Session session  = SSHSessionFactory.getSSHSession(new JConfig().getBranches().getBranches().get(0),mode.substring(0,1));
                          try {
                            String textContent =   path.getContent(fullPathWithFile, session);
                            if(textContent==null){
                              errorMessage = "1:File Not Found "+this.fullPathWithFile;
                            }
                            JFileChooser chooser = new JFileChooser();
                               File suggestion = new File(path.findPath(Path.EXTRACT_OUTPUT) + File.separator + this.fileName);
//                            File defaultPath = new File(Path.EXTRACT_OUTPUT);
//                            chooser.setCurrentDirectory(defaultPath);
                            chooser.setSelectedFile(suggestion);
                            int open=chooser.showSaveDialog(null);
                            if(open==JFileChooser.OPEN_DIALOG){
                            
//                               chooser.setSelectedFile(suggestion);
                              File saveFile =   chooser.getSelectedFile();
                              ShellOutputStream s = new ShellOutputStream();
                              
                              s.writeFile(textContent, saveFile, true);
                            
                            }else{
                              errorMessage = "-1:No Action";
                            }
                          } catch (JSchException ex) {
                              this.log.error(ex);
                              errorMessage = "1:"+ex.getLocalizedMessage();
                              Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                          } catch (SftpException ex) {
                              this.log.error(ex);
                               errorMessage = "1:"+ex.getLocalizedMessage();
                              Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                          } catch (IOException ex) {
                              this.log.error(ex);
                              errorMessage = "1:"+ex.getLocalizedMessage();
                              Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                          }

        if(errorMessage!=null){   
          retValue = errorMessage;
        }
        return retValue;
    }

    @Override
    protected void done() {
        try {
            String retValue = get();
            String []msg = retValue.split(":");
            String errorCode  = msg[0];
            String errorMessage = msg[1];
            if(errorCode.equals("0")){
                JOptionPane.showMessageDialog(null,errorMessage,AppIcon.APP_NAME,JOptionPane.INFORMATION_MESSAGE);
                
            }else if(errorCode.equals("1")) {
                JOptionPane.showMessageDialog(null,errorMessage,AppIcon.APP_NAME,JOptionPane.ERROR_MESSAGE);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DownloadSSHFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(DownloadSSHFile.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          mo.getProgress().setVisible(false);
        }
    }
    
    
    
}
