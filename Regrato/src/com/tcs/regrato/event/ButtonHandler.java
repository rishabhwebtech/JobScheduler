/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.event;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.tcs.jpath.modal.DBConnection;
import com.tcs.jpath.modal.DriverProperty;
import com.tcs.jpath.modal.Host;
import com.tcs.jpath.modal.JConfig;
import com.tcs.jpath.modal.Login;
import com.tcs.regrato.gui.ExtractMapper;
import com.tcs.regrato.gui.RemoteFileSystem;
import com.tcs.regrato.gui.RunningStatus;
import com.tcs.regrato.gui.region.CardOpener;
import com.tcs.regrato.gui.region.ExtractReader;
import com.tcs.regrato.gui.thread.DoDroDrbThread;
import com.tcs.regrato.gui.thread.DownloadSSHFile;
import com.tcs.regrato.gui.thread.ExtractMapperOpenThread;
import com.tcs.regrato.gui.thread.ExtractMappingThread;
import com.tcs.regrato.gui.thread.RemoteFileSystemThread;
import com.tcs.regrato.modal.BranchListModel;
import com.tcs.regrato.modal.ConfigModal;
import com.tcs.regrato.modal.ExtractMapperModal;
import com.tcs.regrato.modal.RemoteFileSystemModal;
import com.tcs.regrato.modal.RunningStatusModal;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.resources.SSHSessionFactory;
import com.tcs.regrato.util.Comman;
import com.tcs.regrato.util.MessageConsole;
import com.tcs.shellsch.ShellOutputStream;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author 1299792
 */
public class ButtonHandler implements ActionListener{
    private Comman commanFunction; 
    private org.apache.log4j.Logger log;
//    private long currentLine;
    public ButtonHandler() {
       this.commanFunction = new Comman();
       this.log= this.commanFunction.getLogger();
    }
      
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        String action = e.getActionCommand();
        if(action.equals("closeConfig")){
           ConfigModal config = ConfigModal.getConfigModal();
           config.getMainDialog().dispose();
        }else if(action.equals("saveConfig")){
            boolean flag=false;
            ConfigModal config = ConfigModal.getConfigModal();
            String url = config.getWebpage().getText();
            String driverPath = config.getDriver().getText();
            String type = (String)config.getType().getSelectedItem();
            String userName = config.getUserName().getText();
            String password = config.getPassword().getText();
            String inst = config.getInst().getText();
            String branch = config.getBranch().getText();
            String hostPassword = config.getHostPassword().getText();
            String hostUserName = config.getUserNameHost().getText();
            String ip = config.getIpAddressHost().getText();
            String knownHost = config.getKnownHost().getText();
            

            
            Host host = new Host();
            host.setHostIp(ip);
            host.setHostKnownHost(knownHost);
            host.setHostUserName(hostUserName);
            host.setHostPassword(hostPassword);
            DriverProperty prop = new DriverProperty();
            Login login = new Login();
            login.setBranch(branch);
            login.setPassword(password);
            login.setInst(inst);
            login.setUserName(userName);
            prop.setLoginPage(url);
            prop.setPath(driverPath);
            prop.setType(type);
            // Database Connection
            
            
            
            
            DBConnection conn = new DBConnection();
            conn.setDbIP(config.getDbIP().getText());
            conn.setDbPassword(config.getDbPassword().getText());
            conn.setDbPort(Integer.parseInt(config.getDbPort().getText()));
            conn.setDbUserName(config.getDbUserName().getText());
            conn.setDbSchema(config.getDbSchema().getText());
            JConfig jConfig = new JConfig();
            
            try {
                jConfig.setTypeOfDrivetSet(prop);
                jConfig.setLoginDetails(login);
                jConfig.setHost(host);
                jConfig.setDBConnecton(conn);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                flag = true;
                  this.log.error(ex);
            } catch (SAXException ex) {
                Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                flag = true;
            } catch (IOException ex) {
                Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                flag = true;
                  this.log.error(ex);
            } catch (TransformerException ex) {
                Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                flag = true;
                  this.log.error(ex);
            } catch(NumberFormatException ex){
               this.log.error(ex);
               flag = true;
            }
            
            
            if(flag){
               JOptionPane.showMessageDialog(null, "Error","Failed To Save",JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Success","Saved!",JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(action.equals("saveCommandOuput")){
            Comman comman  = new Comman();
            JButton button = (JButton)e.getSource();
            JPanel d = (JPanel)button.getParent();
            JScrollPane p = (JScrollPane)d.getComponents()[2];
            JViewport viewport =p.getViewport();
            JTextPane a = (JTextPane)viewport.getComponent(0);
            String text = a.getText();
            JDialog dialog = (JDialog)button.getRootPane().getParent();
//            StyledDocument doc = a.getStyledDocument();
//            int length=doc.getLength();
//            for(int i=0;i<length;i++){
//               
////                System.out.println(el.getName());
//               System.out.println ( el.getAttributes());
//            }
            String fileName=dialog.getName() ;
//
                  File savedFile = new File("output"+File.separator+fileName);
                try {
                    comman.writeTextInFile(savedFile, text);
                    JOptionPane.showMessageDialog(null,"Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                      this.log.error(ex);
                }

        }else if(action.equals("addSeamIsHost")){
             JToggleButton button = (JToggleButton)e.getSource();
             boolean isHost = button.isSelected();
             String isHostInString = Boolean.toString(isHost);
            try {
                this.commanFunction.writeSemaphore("isHost",isHostInString);
            } catch (IOException ex) {
                Logger.getLogger(ButtonHandler.class.getName()).log(Level.SEVERE, null, ex);
                  this.log.error(ex);
            }
        }else if(action.equals("addBranch")){
            ConfigModal c  = ConfigModal.getConfigModal();
            JTextField  nameToSave = c.getBranchNameToSave();
            JList       allBranches = c.getAllBranches();
            String branchName = nameToSave.getText();
            if(branchName.equals("") || branchName.matches("[\\s]+")){
               JOptionPane.showMessageDialog(null,"Invalid Branch Name ","Error",JOptionPane.ERROR_MESSAGE);
            }else{
               
                DefaultListModel m =  (DefaultListModel)allBranches.getModel();
                boolean isContain = m.contains(branchName);
                
                 if(isContain==false){
                   m.addElement(branchName);
                  new JConfig().setBranch(branchName);
                  c.setAllBranches(allBranches);
                 }
            }
          
        }else if(action.equals("removeBranch")){
          ConfigModal c  = ConfigModal.getConfigModal();
          JList list = c.getAllBranches();
          int index = list.getSelectedIndex();
          BranchListModel m = (BranchListModel)list.getModel();
           new JConfig().deleteBranch(m.getElementAt(index));
          m.remove(index);
         
        }else if(action.equals("openCardFile")) {
                  JButton b = (JButton)e.getSource();
                  b.setEnabled(false);
                  JList cardList =  null;
                  cardList = this.commanFunction.getJListFromButtonEvent(e);
                  if(cardList==null){
                      JOptionPane.showMessageDialog(null,"Card File List Not Found","Error",JOptionPane.ERROR_MESSAGE);
                      return ;
                  }
                  String cardFileName =    (String)cardList.getSelectedValue();
                   SwingUtilities.invokeLater(new Runnable() {
                      @Override
                      public void run() {   

                        CardOpener opener = new CardOpener(new javax.swing.JFrame(), true,cardFileName);
                        opener.setTitle(cardFileName+" Card File");
                        opener.setVisible(true); 
                        b.setEnabled(true);
                      }
                  });

               }else if(action.equals("doDroDrb")){
 
                 JButton button = (JButton)e.getSource();
                 JPanel panel = (JPanel)button.getParent();
                 Component []child  = panel.getComponents();
                 String []date = new String[3];
                 String inst = "001";    
                     for(Component c:child){
                       if(c instanceof JTextField){
                          JTextField f = (JTextField)c;
                          String n = c.getName();
                          if(n.equals("DD")){
                            date[0]=f.getText();
                          }
                          if(n.equals("MM")){
                           date[1]=f.getText();
                          }
                          if(n.equals("YYYY")){
                           date[2]=f.getText();
                          }
                       }
                       if(c instanceof JComboBox){
                            JComboBox b = (JComboBox)c;
                            inst=(String)b.getSelectedItem();
                       }
                     }
                  
                   boolean isValid =  this.commanFunction.isValidDate(date[0],date[1],date[2]);
                  if(isValid==false){
                    JOptionPane.showMessageDialog(null,"Invalid Date","Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                  }
                  RunningStatus s = new RunningStatus(new javax.swing.JFrame(), true);
                  s.setModalityType(Dialog.ModalityType.MODELESS);
                  s.setVisible(true);
                  JTextPane textPane = RunningStatusModal.getRunningStatus().getOutputScreen();
                  MessageConsole console = new MessageConsole(textPane, true);
                  console.redirectOut();
                  System.out.println("Processing......... Log will be display after min..");
                  button.setEnabled(false);
                  DoDroDrbThread thread = new DoDroDrbThread(date[0],date[1],date[2],inst,button);
                  thread.execute();
                      
               }else if(action.equals("closeReportMapper")){
                  // JButton button = (JButton)e.getSource();
                   
                   JFrame frame = this.commanFunction.getJFrameFromButtonEvent(e);
                    
                    Component []comp= frame.getRootPane().getComponents();
                    String reportType = "";
                   topLevel:for(Component c : comp){
                  
                  if(c instanceof JLayeredPane){
                   JLayeredPane p = (JLayeredPane)c;
                   Component []co = p.getComponents();
                   for(Component c1 : co){
                    if(c1 instanceof JPanel){
                      JPanel p1 = (JPanel)c1;
                      Component []c2 = p1.getComponents();
                      for(Component c3 : c2){
                        if(c3 instanceof JComboBox){
                        
                          reportType = (String)((JComboBox)c3).getSelectedItem();
                          break topLevel;
                        }
                      }
                    }
                  }
                  }
            
               }
                  
                   if(reportType.equals("") || reportType.equals("Select")){
                     ExtractMapperModal mo = ExtractMapperModal.getExtractMapperModal();
                     String filePath = mo.getExtractFile().getText();
                     String mapPath  = mo.getMapperFile().getText();
                     
                     if(filePath.equals("") && mapPath.equals("")){
                        JOptionPane.showMessageDialog(null,"Extract or Mapper File Not Selected","Regrato",JOptionPane.WARNING_MESSAGE);
                         return;
                     }
                     
                     // JOptionPane.showMessageDialog(null, "Please Select Report Type","Report",JOptionPane.WARNING_MESSAGE);
                         java.awt.EventQueue.invokeLater(new Runnable() {
                         public void run() {
                          ExtractReader re = new ExtractReader(filePath,mapPath);

                          re.setTitle("Extract Checker");
                          re.setVisible(true);
                         }
                     });
                     return ;
                   
                   
                   }
                   if(reportType.equals("Extract")){
                   SwingUtilities.invokeLater(new Runnable() {
                       @Override
                       public void run() {
                           ExtractMapper map  = new ExtractMapper();
                           map.setTitle(frame.getTitle());
                           map.setVisible(true);
                       }
                   });
                   }
                   frame.dispose();
               }else if(action.equals("browseExtractFile")){
                  ExtractMapperModal m = ExtractMapperModal.getExtractMapperModal();
                  JTextField extractPath = m.getExtractFile();
                  
                  
                  
                  
                   RemoteFileSystem dialog = new RemoteFileSystem(new javax.swing.JFrame(), true);
                   dialog.setModalityType(Dialog.ModalityType.MODELESS);
                   dialog.setLocationRelativeTo(null);
//                   dialog.setAlwaysOnTop(true);
                   dialog.setVisible(true);
//                  JFileChooser chooser = new JFileChooser();
//                  int open=chooser.showOpenDialog(null);
//                  if(open==JFileChooser.OPEN_DIALOG){
//                    File file = chooser.getSelectedFile();
//                    extractPath.setText(file.getPath());
//                    
//                  }
               
               }else if(action.equals("browseMapperFile")){
                 
                  ExtractMapperModal m = ExtractMapperModal.getExtractMapperModal();
                  JTextField mapperPath = m.getMapperFile();
                  
                  JFileChooser chooser = new JFileChooser();
                  chooser.setCurrentDirectory(new File(Path.MAPPER));
                  chooser.setFileFilter(new FileNameExtensionFilter("Mapper File","map"));
                  int open=chooser.showOpenDialog(null);
                  if(open==JFileChooser.OPEN_DIALOG){
                    File file = chooser.getSelectedFile();
                    mapperPath.setText(file.getPath());
                    
                  }   
               }else if(action.equals("mapFileWithMap")){
                  ExtractMapperModal mo = ExtractMapperModal.getExtractMapperModal();
                  String file=mo.getExtractFile().getText();
                  String map=mo.getMapperFile().getText();
                  String totalLine =    mo.getTotalLineNumber().getText();
                  long totalLineLong  = 0;
                  boolean wrongLine = false;
                  try{
                    totalLineLong = Long.parseLong(totalLine);
                     
                  }catch(NumberFormatException ex){
                    this.log.error(ButtonHandler.class.getName()+ex);
                    JOptionPane.showMessageDialog(null,"Runtime Exception, Error to get total line","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                  }
                  long currentLine = 0;
                  try{
                   currentLine=Long.parseLong(mo.getCurrentLine().getText());
                  }catch(NumberFormatException ex){
                     this.log.error(ButtonHandler.class.getName()+ex);
                    JOptionPane.showMessageDialog(null,"Runtime Exception, Error to get total line","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                  }
                  if(currentLine>totalLineLong){
                    wrongLine = true;
                  }else if(currentLine<=0){
                    wrongLine = true;
                  }
                  
                  if(wrongLine){
                        JOptionPane.showMessageDialog(null,"Invalid Line Number","Error",JOptionPane.ERROR_MESSAGE);
                      return ;
                  }
                  ExtractMappingThread thread = new ExtractMappingThread(file, map);
                  thread.setIndex(currentLine);
                  thread.execute();
                    
                
               }else if(action.equals("connectToHostFileSystemView")){
                  JTable table = RemoteFileSystemModal.getRemoteFileSystemModal().getRemoteFileSystemView();
                  DefaultTableModel model =  (DefaultTableModel)table.getModel();
//                  int totalRow = model.getRowCount();
                  
//                  for(int i=0;i<totalRow;i++){
//                    mo.removeRow(i);
//                  }

                   for( int i = model.getRowCount() - 1; i >= 0; i-- ){
                      model.removeRow(i);
                   }

//                  table.revalidate();
//                  table.repaint();
                  String path = null;
                  RemoteFileSystemThread thread = new RemoteFileSystemThread();
                  thread.execute();
                  
               }else if(action.equals("downloadSSHFile")){
                  RemoteFileSystemModal mo = RemoteFileSystemModal.getRemoteFileSystemModal();
                  mo.getProgress().setVisible(true);
                  JTable removeTableView =  mo.getRemoteFileSystemView();
                  String currentPath = mo.getCurrentPath().getText();
//                  removeTableView.getSelectedColumn();
                  int selectedRow=removeTableView.getSelectedRow();
                  if(selectedRow>-1){
                      TableColumnModel mo1 = removeTableView.getColumnModel();
                      int fileNameCol = mo1.getColumnIndex("FileName");
                      int fileTypeCol = mo1.getColumnIndex("File Type");
                      JLabel label = (JLabel)removeTableView.getValueAt(selectedRow, fileNameCol);
                      
                      String fileName = label.getText();
                      String typeOfFile = (String)removeTableView.getValueAt(selectedRow, fileTypeCol);
                      
                      if(typeOfFile.equals("DIRECTORY")){
                         JOptionPane.showMessageDialog(null,"Directory cannot be downloaded","Regrato",JOptionPane.WARNING_MESSAGE);
                         mo.getProgress().setVisible(false);
                         return;
                      }else{
                            String fullPath = currentPath + "/" + fileName;
                            DownloadSSHFile fileDownloadThread = new DownloadSSHFile(fullPath,fileName);
                            fileDownloadThread.execute();
                      }
                  }
               }else if(action.equals("openSSHFileOrFolder")){
                  RemoteFileSystemModal mo = RemoteFileSystemModal.getRemoteFileSystemModal();
                  JTable removeTableView=  mo.getRemoteFileSystemView();
                  int selectedRow = removeTableView.getSelectedRow();
                  String currentPath = mo.getCurrentPath().getText();
                  if(selectedRow>-1){
                       TableColumnModel mo1 = removeTableView.getColumnModel();
                      int fileNameCol = mo1.getColumnIndex("FileName");
                      int fileTypeCol = mo1.getColumnIndex("File Type");
                      JLabel label = (JLabel)removeTableView.getValueAt(selectedRow, fileNameCol);
                      
                      String fileName = label.getText();
                      String typeOfFile = (String)removeTableView.getValueAt(selectedRow, fileTypeCol);
                      
                      if(typeOfFile.equals("DIRECTORY")){
                                  DefaultTableModel model =  (DefaultTableModel)removeTableView.getModel();
//                  int totalRow = model.getRowCount();
                  
                         for( int i = model.getRowCount() - 1; i >= 0; i-- ){
                              model.removeRow(i);
                           }        
                          
                         String fullPath = currentPath + "/" + fileName;
                         mo.getCurrentPath().setText(fullPath);
                         RemoteFileSystemThread thread = new RemoteFileSystemThread(fullPath);
                         thread.execute();
                      }else{
                            String fullPath = currentPath + "/" + fileName;
                            ExtractMapperModal.getExtractMapperModal().getExtractFile().setText(fullPath);
                      }                    
                  
                  
                  }
               }
            }
          
        }
    
