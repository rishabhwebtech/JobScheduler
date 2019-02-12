/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.tcs.jpath.modal.JConfig;
import com.tcs.mapper.exception.SizeLengthDifferenceException;
import com.tcs.mapper.modal.Body;
import com.tcs.mapper.modal.Field;
import com.tcs.mapper.modal.Footer;
import com.tcs.mapper.modal.Header;
import com.tcs.mapper.text.ExtractReader;
import com.tcs.mapper.xml.MapperReader;
import com.tcs.regrato.modal.ExtractMapperModal;
import com.tcs.regrato.resources.AppIcon;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.resources.SSHSessionFactory;
import com.tcs.regrato.util.Comman;
import com.tcs.shellsch.ShellOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author 1299792
 */
public class ExtractMappingThread extends SwingWorker<String, Vector>{

    private String filePath;
    private String mapPath;
    private JTable  table;
    private long setIndex;
    private long totalLine;
//    private int cursor;
    private int rowLength;
    private org.apache.log4j.Logger log;
    private ExtractMapperModal mo ;
     
    private DefaultTableModel model ;
    public ExtractMappingThread(String filePath,String mapPath) {
      this.filePath=filePath;
      this.mapPath=mapPath;
      log = new Comman().getLogger();
      mo=ExtractMapperModal.getExtractMapperModal();
      
    }
    
    public void setIndex(long index){
         this.setIndex = index;
         
    }
    public void totalLine(long line){
      this.totalLine=line;
    }
    
    
    @Override
    protected String doInBackground() throws Exception {
        mo.getProgressBar().setVisible(true);
//        String mode = ((String)mo.getIsDelCompobox().getSelectedItem()).substring(0, 1);
//        Session session = SSHSessionFactory.getSSHSession(new JConfig().getBranches().getBranches().get(0),mode);
//        ChannelSftp  sftp =     (ChannelSftp)session.openChannel("sftp");
//        sftp.connect();
//        ShellOutputStream output = new ShellOutputStream();
//        String textContent =  output.execOutputStream(sftp.get(this.filePath), true);
//        
        File saveToTempFile = new File(Path.TEMP_PATH+File.separator+"temp.txt");
////        FileOutputStream saveToTemp = new FileOutputStream(saveToTempFile);
//        output.writeFile(textContent, saveToTempFile, true);
        MapperReader  mapperReader = new MapperReader(new File(this.mapPath));
//        ExtractReader extractReader = new ExtractReader(new File(this.filePath));
           ExtractReader extractReader = new ExtractReader(saveToTempFile);
//        File filePathFile = new File(this.filePath);
//        File mapPathFile  = new File(this.mapPath);
        int []sizeArray =null;
        String []nameArray = null;
        String retValue = "0:Success";
        String extractLine = extractReader.getLine(setIndex);
        if(extractLine==null){
           retValue = "1:No Line Found In Extract";
        }
        mo.getOneLineAtATime().setText(extractLine);
//        int i=0;  
        if(setIndex==1){
        // For Header
        
            Header header = mapperReader.getHeader();
            Body   body   = null;
            ArrayList<Field> field = null;
            if(header.isEmpty()){
              body=mapperReader.getBody();
              field = body.getField();
            }else{
              field = header.getField();
            }
            int len = field.size();
            sizeArray = new int[len];
            nameArray = new String[len];
            for(int i=0;i<len;i++){
               Field temp =   field.get(i);
               String name = temp.getName();
               int size = temp.getSize();
               nameArray[i]=name;
               sizeArray[i]=size;
                  
            }
        
            
        }else if(setIndex==totalLine){
         // Footer
            ArrayList<Field> field = null;
            Footer foot = mapperReader.getFooter();
            if(foot.isEmpty()){
              Body   body   = mapperReader.getBody();
              field = body.getField();
            }else{
              field = foot.getField();
            }
            int len = field.size();
             nameArray = new String[len];
                  sizeArray = new int[len];
            for(int i=0;i<len;i++){
             Field temp = field.get(i);
             String name=temp.getName();
             sizeArray[i] = temp.getSize();
             nameArray[i]=name;
           }
             
        }else{
         // For Body
            ArrayList<Field> field = null;
            Body   body   = mapperReader.getBody();
            field = body.getField();
            int len = field.size();
             nameArray = new String[len];
                  sizeArray = new int[len];
            for(int i=0;i<len;i++){
             Field temp = field.get(i);
             String name=temp.getName();
             sizeArray[i] = temp.getSize();
             nameArray[i]=name;
           }       
        }
        
        ArrayList<String>    lineIntoList =   null;
        try{
        lineIntoList=extractReader.convertIntoList(extractLine, sizeArray);
        }catch(SizeLengthDifferenceException ex){
          retValue = "1:"+ex.getLocalizedMessage();
          return retValue;
        }
        rowLength = sizeArray.length;
        table = new JTable(rowLength,2);
        
        model = (DefaultTableModel)table.getModel();
//        for(int i=0;i<rowLength;i++){
//          model.removeRow(i);
//         }
        int finalLength=0;
        int len = lineIntoList.size();
        if(rowLength>len){
           finalLength=len;
        }else{
          finalLength = rowLength;
        }
        
        for(int i=0;i<finalLength;i++){
//          cursor=i;
          Vector<String> vec = new Vector<>();
          vec.add(nameArray[i]);
          vec.add(lineIntoList.get(i));
         
          publish(vec);
         }
//        publish(table);
        return retValue;
    }

    @Override
    protected void process(List<Vector> chunks) {
        try{
        for(int i=0;i<chunks.size();i++){    
        Vector vec = chunks.get(i);
        for(int j=0;j<vec.size();j++){
           model.setValueAt(vec.get(j), i, j);
        }
//        model.addRow(vec);
        
//        model.addRow(rowData);
        }
        }catch(java.lang.IndexOutOfBoundsException ex){
           log.error(ExtractMappingThread.class.getName() + ex.getLocalizedMessage());
        }
        
    }

    
    
    
    
    @Override
    protected void done() {
        String []arrayMesString = null;
        String exErrorMessage = null;
        try {
            String value =    get();
            arrayMesString = value.split(":");
            if(arrayMesString[0].equals("1")){
                JOptionPane.showMessageDialog(null,arrayMesString[1],"Error",JOptionPane.ERROR_MESSAGE);
            }else if(arrayMesString[0].equals("0")){
             table.getColumn("A").setHeaderValue("Field");
             table.getColumn("B").setHeaderValue("Value");
//             ((DefaultTableModel)table.getModel()).removeRow(0);
             table.getTableHeader().setReorderingAllowed(false);
             table.setRowHeight(25);     
             table.revalidate();
             table.repaint();
             JScrollPane pane = new JScrollPane(table);
            
             JTabbedPane jTabPane =  mo.getTabbedPane();
             try{
               jTabPane.removeTabAt(0);
             }catch(Exception ex){
             
             }
             jTabPane.addTab("Extract Mapping", pane);
             jTabPane.revalidate();
             jTabPane.repaint();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ExtractMappingThread.class.getName()).log(Level.SEVERE, null, ex);
            exErrorMessage=ex.getLocalizedMessage();
        } catch (ExecutionException ex) {
            exErrorMessage=ex.getLocalizedMessage();
            Logger.getLogger(ExtractMappingThread.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
         mo.getProgressBar().setVisible(false);
        }
        if(exErrorMessage!=null){
            log.error(ExtractMappingThread.class.getName() + exErrorMessage);
            JOptionPane.showMessageDialog(null,exErrorMessage,AppIcon.APP_NAME,JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
}
