/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.tcs.jpath.modal.JConfig;
import com.tcs.regrato.gui.ProgressBar;
import com.tcs.regrato.gui.region.CardOpener;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.resources.SSHSessionFactory;
import com.tcs.regrato.util.Comman;
import com.tcs.regrato.util.RegExLine;
import com.tcs.shellsch.ShellOutputStream;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/**
 *
 * @author 1299792
 */
public class CardFileRWThread extends SwingWorker<Object, JScrollPane>{
    private String inst; 
    private Session controlSession;
    String cardFile;
    String cardFileLayout;
    String prevIN0800Line;
    int []lengthOfCard;
    Comman comman;
    JTabbedPane pane;
    org.apache.log4j.Logger log;
    public CardFileRWThread(String inst,Session controlSession,String cardFile,String cardFileLayout,String prevIN0800Line,int lengthOfCard[],JTabbedPane pane){
       this.inst = inst;
       this.controlSession=controlSession;
       this.cardFile=cardFile;
       this.cardFileLayout=cardFileLayout;
       this.prevIN0800Line=prevIN0800Line;
       this.lengthOfCard=lengthOfCard;
       this.comman = new Comman();
       this.pane=pane;
       this.log=this.comman.getLogger();
     }
    @Override
    protected Object doInBackground() throws Exception {
        ProgressBar bar = new ProgressBar(new javax.swing.JFrame(), true);
        bar.setLocationRelativeTo(null);
//        bar.setAlwaysOnTop(true);
        bar.setVisible(true);
        bar.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancel(true);
            }
          
        
        });
        JScrollPane pane = null;
        try{
            
         pane=onLoad(inst);
        
        }catch(IOException ex){
          bar.setAlwaysOnTop(false);
          bar.setVisible(false);
          bar.dispose();  
          this.log.error(ex);
        }catch(NullPointerException ex){
          bar.setAlwaysOnTop(false);
          bar.setVisible(false);
          bar.dispose();   
            this.log.error(ex);
        }catch(JSchException ex){
            bar.setAlwaysOnTop(false);
            bar.setVisible(false);
            bar.dispose();    
              this.log.error(ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }finally{
          bar.setAlwaysOnTop(false);
          bar.setVisible(false);
          bar.dispose();
        }
        return pane;
    }
    
    
     private JScrollPane onLoad(String inst) throws IOException,JSchException{
     JConfig c = new JConfig();
     Comman comman = new Comman();
      JScrollPane scrolPane = null;
     Path path = new Path();
     comman.deleteTemp();
     ShellOutputStream inputRead = new ShellOutputStream();
     RegExLine reg = new RegExLine();
     String branch=c.getBranches().getBranches().get(0);
//     this.defaultBranch=branch;
     controlSession = SSHSessionFactory.getSSHSession(branch, "C");
     
     String filePath =null;
     
     filePath=path.getPath(controlSession,this.cardFile,inst);
     
     if(filePath==null){
       throw new NullPointerException("Session Connection Failed");
     }
//     this.in0800CardFilePath=filePath;
     ShellOutputStream s = new ShellOutputStream();
        try {
            ChannelSftp sftp =  (ChannelSftp)controlSession.openChannel("sftp");
            sftp.connect();
            InputStream stream = sftp.get(filePath);
            StringBuffer buffer =  s.readFile(stream);
            File in0800cardTemp = new File(Path.TEMP_PATH+File.separator+this.cardFile);
//            new Comman().writeStringInFile(in0800cardTemp, buffer.toString(),false);
            s.writeFile(buffer.toString(), new FileOutputStream(in0800cardTemp));
            String line = null;
            
            line=reg.forAccountCard(in0800cardTemp.getPath());
            
            String []details = line.split(" ");
            this.prevIN0800Line=line;
            File tableLayoutFile = new File(Path.LAYOUT+File.separator+this.cardFileLayout);
           
            InputStream readStream = new FileInputStream(tableLayoutFile);
            String []tableCol=inputRead.readFile(readStream).toString().split("\r\n");
            int length = 0;
            int actualLength = tableCol.length;
            length = actualLength / 2;
//            for(String s1:tableCol){
//               if(s1.matches("[0-9]")==false){
//                   length = length + 1;
//               }else{
//                break;
//               }
//            }
            this.lengthOfCard = new int[length];
            int len=0;
            for(int j=length;j<actualLength;j++){
                this.lengthOfCard[len]=Integer.parseInt(tableCol[j]);
                len = len + 1;
            }
            Object [][]rowData = new Object[1][length];
            Object []colData = new Object[length];
            int i=0;
            for(String row:details){
              if(row.equals("")==false){
                 rowData[0][i]=row;
                   i=i+1;
              }
              
            }
            i=0;
            for(i=0;i<length;i++){
               colData[i]=tableCol[i];
            }
//            for(String col:tableCol){
//               colData[i]=col;
//
//               i=i+1;
//            }
            JTable table = new JTable(rowData, colData);
            table.getTableHeader().setReorderingAllowed(false);
            scrolPane  = new JScrollPane(table);
         
//            this.jTabbedPane1.addTab(this.cardFile,scrolPane);

        } catch (JSchException ex) {
            Logger.getLogger(CardOpener.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        } catch (SftpException ex) {
            Logger.getLogger(CardOpener.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        }
//        for(Integer i : this.lengthOfCard){
//          System.out.println(i);
//        }
      return scrolPane;
 }

    @Override
    protected void done() {
        try {
            JScrollPane sc = ( JScrollPane)get();
            pane.addTab(cardFile, sc);
            pane.setName(prevIN0800Line);
        } catch (InterruptedException ex) {
            Logger.getLogger(CardFileRWThread.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(CardFileRWThread.class.getName()).log(Level.SEVERE, null, ex);
              this.log.error(ex);
        }
    }
    
    
}
