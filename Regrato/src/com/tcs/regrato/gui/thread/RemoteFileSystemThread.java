/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.tcs.jpath.modal.JConfig;
import com.tcs.regrato.modal.RemoteFileSystemModal;
import com.tcs.regrato.resources.AppIcon;
//import com.tcs.regrato.modal.TableFileSystemModel;
import com.tcs.regrato.resources.Path;
//import com.tcs.regrato.modal.Host;
import com.tcs.regrato.resources.SSHSessionFactory;
import com.tcs.regrato.util.Comman;
import com.tcs.shellsch.ShellOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 1299792
 */
public class RemoteFileSystemThread extends SwingWorker<String, Object[]>{

    private String mode;
    private RemoteFileSystemModal mo;
    private Comman comman;
    private String path;
    private JTable table;
    private DefaultTableModel tableModel;
    private org.apache.log4j.Logger log;
//    private String pathToBrowse;
    public RemoteFileSystemThread() {
      mo = RemoteFileSystemModal.getRemoteFileSystemModal();
      comman = new Comman();
      this.log = comman.getLogger();
      path = null;
    }
    
    
    
    public RemoteFileSystemThread(String path){
        mo = RemoteFileSystemModal.getRemoteFileSystemModal();
        comman = new Comman();
        this.path = path;
    }
    
    
    @Override
    protected String doInBackground() throws Exception {
        mo.getProgress().setVisible(true);
        mo.getConnectButton().setEnabled(false);
        ShellOutputStream outputStream = new ShellOutputStream();
        JConfig con = new JConfig();
        String branch = con.getBranches().getBranches().get(0);
        String retValue = "0:Done";
        Path path  = new Path();
        String temp= (String)mo.getMode().getSelectedItem();
        mode = temp.substring(0, 1);
        table = mo.getRemoteFileSystemView();
        tableModel = (DefaultTableModel)table.getModel();
         
        int col = tableModel.getColumnCount();
//        System.out.println(col);
        Object []tableDetails = new Object[col];
      try{
        Session fact = SSHSessionFactory.getSSHSession(branch, mode);
        ChannelExec exec =   (ChannelExec)fact.openChannel("exec");
//        path.getPath(fact, temp)
        String pwd="pwd;";
        String homePath="";
//        mo.getCurrentPath().setText(homePath);
        String loginCommand = "bash --login -c ";
        String getDirListCommand = "ls -lrt;";
        String changeDir="cd;";
        
        String totalCommand=null;
        if(this.path==null){
           totalCommand=loginCommand+ "'"+pwd+"'"+  "'" +changeDir + "'"+ "'"+ getDirListCommand + "'";
        }else{
//            this.path = this.path + ";";
          changeDir = "cd " + this.path + ";";
          totalCommand = loginCommand +   "'"  +changeDir + "'"  + "'"+pwd+"'"+   "'"+ getDirListCommand  + "'";
     
        }
        exec.setCommand(totalCommand);      
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

//           exec.connect();
           InputStream stream =  exec.getInputStream();
           String data = outputStream.execOutputStream(stream, true);
           
//           System.out.println(data);
           StringBuilder build = new StringBuilder(data);
           
            build.delete(0,39); 
//           if(this.path==null){
          
           homePath=build.substring(0, build.indexOf(System.lineSeparator()));
           mo.getCurrentPath().setText(homePath);
           build.delete(0,(build.indexOf(System.lineSeparator())+1));
           build.delete(0,(build.indexOf(System.lineSeparator())+1));
//           }else{
               build.delete(0,(build.indexOf(System.lineSeparator())+1));
//           }
           String allDir[] =   build.toString().split(System.lineSeparator());
           // 7 col
           for(String p:allDir){
               
//               Icon ico = null;
               if(p.equals("")==false){
                 p=p.replaceAll("[\\s]{2,}", " ");
                 String details[] = p.split(" ");
                  int len =details.length;
                  tableDetails =new  Object[col];
                  String modifiedDate = "";
                  for(int index=0;index<len;index++){
                      
                     switch(index){
                         case 0:
                            tableDetails[4] =  details[index]; 
                         break;
                         case 2:
                              tableDetails[5] =  details[index];
                         break;
                         case 4:
                             tableDetails[1] =  details[index];
                         break;
                         case 5:
                             modifiedDate = modifiedDate +" "+ details[index];
                             break;
                         case 6:
                             modifiedDate = modifiedDate +" "+ details[index];
                             break;
                         case 7:
                             modifiedDate = modifiedDate +" "+ details[index];
                             tableDetails[3] =  modifiedDate;
                             break;
                         case 8:
                             Icon ico =null;
                             JLabel fileLabel = null;
                             tableDetails[0] =  details[index];
                             String temp1 = details[index];
                             String fileType="File";
                             int in = temp1.lastIndexOf(".");
                             if(in>0){
                               fileType = temp1.substring(in+1, temp1.length());
                               
                             }
                             if(fileType.equals("File")){
                               if(details[0].charAt(0)=='d'){
                                fileType = "directory";
                                ico=new ImageIcon(Path.ICON+File.separator+"folderIcon.png");
                                
                               }else{
                                  ico= new ImageIcon(Path.ICON+File.separator+"fileIcon.png");
                               }
                             
                             }else{
                                 
                              
                                ico= new ImageIcon(Path.ICON+File.separator+"fileIcon.png");
                                  
                             }
                             fileLabel = new JLabel(details[index],ico,JLabel.LEFT);
                             fileType=fileType.toUpperCase();
//                             tableDetails[0]=ico;
                              tableDetails[0]=fileLabel;
                             tableDetails[2]=fileType;
                            break;
                     
                     }
                      
                  }
                   publish(tableDetails);
               }
               
           
           }
           
           
//           System.out.println(build.toString());
           exec.disconnect();

             
         }
      }catch(JSchException ex){
          retValue = "1:"+ex.getLocalizedMessage();
      
      }
        return retValue;
    }


    @Override
    protected void process(List<Object[]> chunks) {
      
        int len = chunks.size();
        for(int i=0;i<len;i++){
          tableModel.addRow(chunks.get(i));
        }
        
    }
    
    
    @Override
    protected void done() {
        String errorMessage = null;
        try {
            String retValue =   get();
            String []arr = retValue.split(":");
            if(arr[0].equals("1")){
            
                JOptionPane.showMessageDialog(null,arr[1],"Error",JOptionPane.ERROR_MESSAGE);
            }else{
              table.revalidate();
              table.repaint();
            }
        } catch (InterruptedException ex) {
            this.log.error(RemoteFileSystemThread.class.getName() + ex);
            errorMessage = ex.getLocalizedMessage();
            Logger.getLogger(RemoteFileSystemThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            errorMessage=ex.getLocalizedMessage();
            this.log.error(RemoteFileSystemThread.class.getName() + ex);
            Logger.getLogger(RemoteFileSystemThread.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          mo.getConnectButton().setEnabled(true);
          mo.getProgress().setVisible(false);
        }
        
        if(errorMessage!=null){
            
          JOptionPane.showMessageDialog(null,errorMessage,AppIcon.APP_NAME,JOptionPane.ERROR_MESSAGE);
        
        }
    }
    
    
}
