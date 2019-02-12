/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.event;


import com.tcs.regrato.gui.About;
import com.tcs.regrato.gui.BranchEditor;
import com.tcs.regrato.gui.BranchRenderer;
import com.tcs.regrato.gui.CommandEditor;
import com.tcs.regrato.gui.Config;
import com.tcs.regrato.gui.ExtractMapper;
import com.tcs.regrato.gui.region.Mflags;
import com.tcs.regrato.gui.ProgressBar;
import com.tcs.regrato.gui.ReportMapper;
import com.tcs.regrato.gui.RunningStatus;
import com.tcs.regrato.gui.region.CardDialog;
import com.tcs.regrato.gui.region.CardOpener;
import com.tcs.regrato.gui.region.DRO;
import com.tcs.regrato.gui.thread.CommandEditorThread;
import com.tcs.regrato.gui.thread.CommandSaverThread;
import com.tcs.regrato.gui.thread.ExtractMapperOpenThread;
import com.tcs.regrato.gui.thread.ExtractMapperThread;
import com.tcs.regrato.gui.thread.RunCommandThread;
import com.tcs.regrato.gui.thread.StartNewTraceThread;
import com.tcs.regrato.gui.thread.StopNewTraceThread;
import com.tcs.regrato.modal.CommandEditorModal;
import com.tcs.regrato.modal.CommandEditorTableModel;
import com.tcs.regrato.modal.ExtractMapperModal;
import com.tcs.regrato.modal.GuiModal;
import com.tcs.regrato.modal.RunningStatusModal;
import com.tcs.regrato.resources.AppIcon;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.resources.SSHSessionFactory;
import com.tcs.regrato.util.Comman;
import com.tcs.regrato.util.ErrorChecker;
import com.tcs.regrato.util.InvalidRowLineException;
import com.tcs.regrato.util.MessageConsole;
import com.tcs.regrato.util.NoTableFoundException;
import com.tcs.shellsch.ShellOutputStream;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author 1299792
 */
public class MenuHandler implements ActionListener{
     private GuiModal guiModal;
     private CommandEditorModal coModal ;
     private RunningStatusModal runModal;
     private Comman comman;
     private org.apache.log4j.Logger log;
    public MenuHandler() {
      this.comman = new Comman();
      guiModal = GuiModal.getGuiModal();
      this.coModal = CommandEditorModal.getCommandEditorModal();
      this.runModal = RunningStatusModal.getRunningStatus();
      this.log = this.comman.getLogger();
    }
     
     
     
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        
        if(action.equals("New Trace")){
        
          String result = (String)JOptionPane.showInputDialog(null, "Enter Trace name:");
          if(result==null || result.equals("") ){
            return;
          }else{
              JLabel traceName = guiModal.getTraceNameValue();
              
              traceName.setText(result);
              StartNewTraceThread startThread = new StartNewTraceThread(result);
              startThread.execute();
             
          }
        }else if(action.equals("Stop Trace")){
            StopNewTraceThread thread = new StopNewTraceThread();
            thread.execute();
        }else if(action.equals("openTrace") || action.equals("newCommandEdit")){
            JTabbedPane pane = this.coModal.getMainTabedPaneOfCommandEditor();
            JLabel label = this.coModal.getCommandFileLabel();
            JMenuItem a1 =               this.coModal.getAppendRowMenuItem();
            JMenuItem a2 =               this.coModal.getAppendRowAtTopMenuItem();
            JMenuItem a3 =               this.coModal.getAppendRowAtMenuItem();
            a1.setVisible(true);
            a2.setVisible(true);
            a3.setVisible(true);
             int tab = pane.getTabCount();
             System.out.println("TAB "+tab);
             if(tab!=0){
               JOptionPane.showMessageDialog(null,"Please Close All Command ", "Warning",JOptionPane.WARNING_MESSAGE);
                return;
             }
             this.coModal.setAppendRowMenuItem(a1);
             this.coModal.setAppendRowAtTopMenuItem(a1);
             this.coModal.setAppendRowAtMenuItem(a1);
            
             if(action.equals("newCommandEdit")){
               label.setText("New Command");
             }
            CommandEditorThread thread = new CommandEditorThread(pane,label);             
            thread.execute();
           

           
        }else if(action.equals("commandEditor")){
           
               
                java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                 public void run() {
                 CommandEditor editor = new CommandEditor();
                 editor.setTitle("Command Editor");
                 editor.setLocationRelativeTo(null);
                 editor.setVisible(true);
               }
             });
        }else if(action.equals("saveCommand") || action.equals("saveAsCommand")){
            ProgressBar bar = new ProgressBar(new javax.swing.JFrame(), true);
            HashMap<String,String[][]> allTestCase = new HashMap();
            bar.setLocationRelativeTo(null);
            JTabbedPane pane = this.comman.getJTabbedPaneFromEventForMenu(e);
            bar.setVisible(true);
            String message = this.comman.validateTabPane(pane);
            if(message!=null){
              JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
              bar.setVisible(false);
              bar.dispose();
              return;
            }
            int row = 0;
            int col = 0;
            String rowData[][]=null;
            File file = null;
               JTable table=null;
              List<JTable> allCommandTable  = null;
              try{
                allCommandTable = this.comman.getJTableFromEventForMenu(e);
              }catch(NoTableFoundException ex){
                  JOptionPane.showMessageDialog(null,"Nothing To Save!","Error",JOptionPane.ERROR_MESSAGE);
                  bar.dispose();
                  bar.setVisible(false);
                    this.log.error(ex);
                  return;
              }
              
              int totalLength = allCommandTable.size();
              for(int tableIndex=0;tableIndex<totalLength;tableIndex++){
              table = allCommandTable.get(tableIndex);

               CommandEditorTableModel model = (CommandEditorTableModel)table.getModel();
               int hashCode = model.hashCode();
//               System.out.println(hashCode);
               row = model.getRowCount();
               col = model.getColumnCount();
               col = col - 1;
               rowData = new String[row][col];
               for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                  String value = (String)table.getValueAt(i, j);
                  
                  rowData[i][j]=value;
                   
                }
               
               }
               String testCaseStringName = pane.getTitleAt(tableIndex);
               allTestCase.put(testCaseStringName, rowData);
               String variable[] = new String[row];
               for(int i=0;i<row;i++){
                variable[i] =  (String)table.getValueAt(i,0);
               }
                int isError = new ErrorChecker().isError(hashCode,variable);
               
                if(isError!=0){
                    bar.setVisible(false);
                   JOptionPane.showMessageDialog(null,"Jobs Command contain error at variable "+isError,"Error",JOptionPane.ERROR_MESSAGE);
                  return;
                }               
                
               
               if(action.equals("saveAsCommand")){
                  String fileName =  JOptionPane.showInputDialog(null,"Enter Trace File Name");
                  fileName = fileName+".xls";
                  file = new File(Path.TEMP_PATH+File.separator+fileName);
               }else{
                  String filePath= this.coModal.getFilePath();
                  File temp = new File(filePath);
                  String name=temp.getName();
                  file=new File(Path.TEMP_PATH+File.separator+name);
               }
//               CommandSaverThread thread = new CommandSaverThread(rowData, row,col, file, bar);

               
               
               }
                 String desc = "";
                 CommandSaverThread thread = new CommandSaverThread(file, bar, allTestCase);
                 JTextField f = this.comman.getTextAreaFromActionEvent(e);
                 if(f!=null){
                   desc = f.getText();
                 }
//                 System.err.println(desc);
                 thread.setName(desc);
                 thread.execute();                
//        }
//            }

        }else if(action.equals("appendRow")){
            try {
//                JTable table = this.comman.getJTableFromEventForMenu(e);
              List<JTable> allCommandTable  = this.comman.getJTableFromEventForMenu(e);
              int selectedTable = this.comman.getJTabbedPaneFromEventForMenu(e).getSelectedIndex();
               JTable table = allCommandTable.get(selectedTable);
                CommandEditorTableModel model = (CommandEditorTableModel)table.getModel();
                int col = model.getColumnCount();
                int row = model.getRowCount();
                String []a = new String[col];
                for(int i =0 ;i<col-1;i++){
                    if(i==0){
                     int []var =  this.comman.getVariablesFromTable(model);
                     String nextVar = this.comman.getNewVariable(var);
                     a[i] = nextVar;
                    }else{
                       a[i]= "";
                    }
                }
                a[col-1] = "No Action";
               
                model.addRow(a);
//                 this.comman.rearrangeVariableInJTable(table);
            } catch (NoTableFoundException ex) {
                  this.log.error(ex);
                JOptionPane.showMessageDialog(null,ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else if(action.equals("appendRowTop")){
            try {  
//                JTable table =  this.comman.getJTableFromEventForMenu(e);
              List<JTable> allCommandTable  = this.comman.getJTableFromEventForMenu(e);
              int selectedTable = this.comman.getJTabbedPaneFromEventForMenu(e).getSelectedIndex();
              JTable table = allCommandTable.get(selectedTable);
                this.comman.insertRowAtLine(table, 1);
//                 this.comman.rearrangeVariableInJTable(table);
            } catch (NoTableFoundException ex) {
                  this.log.error(ex);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidRowLineException ex) {
                  this.log.error(ex);
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }else if(action.equals("closeCommandEditor")){
              try{
                 JTabbedPane pane =  this.comman.getJTabbedPaneFromEventForMenu(e);
                 JMenuItem appendRow1= this.coModal.getAppendRowAtMenuItem();
                 JMenuItem appendRow2= this.coModal.getAppendRowAtTopMenuItem();
                 JMenuItem appendRow3= this.coModal.getAppendRowMenuItem();
                 pane.removeAll();
                 JLabel label = this.coModal.getCommandFileLabel();
                 label.setText("");
                 pane.revalidate();
                 pane.repaint();        
                 JTextField desc=this.coModal.getDescription();
                 desc.setText("");
                 appendRow1.setVisible(false);
                 appendRow2.setVisible(false);
                 appendRow3.setVisible(false);

//                 this.coModal.setAppendRowAtMenuItem(appendRow1);
//                 this.coModal.setAppendRowAtTopMenuItem(appendRow2);
//                 this.coModal.setAppendRowMenuItem(appendRow3);
                 this.coModal.setDescription(desc);
                 this.coModal.setMainTabedPaneOfCommandEditor(pane);
              }catch(java.lang.IndexOutOfBoundsException ex){
                   this.log.error(ex);
              }finally{
                 SSHSessionFactory.releaseAllSHHSession();
              }
        }else if(action.equals("appendAt")){
            try{
            int rowLine = Integer.parseInt(JOptionPane.showInputDialog(null,"Input Row Number"));
//            JTable table = this.comman.getJTableFromEventForMenu(e);
              List<JTable> allCommandTable  = this.comman.getJTableFromEventForMenu(e);
              int selectedTable = this.comman.getJTabbedPaneFromEventForMenu(e).getSelectedIndex();
              JTable table = allCommandTable.get(selectedTable);
              this.comman.insertRowAtLine(table, rowLine);
//              this.comman.rearrangeVariableInJTable(table);
            }catch(NumberFormatException ex){
               JOptionPane.showMessageDialog(null,"Invalid Row Number","Error",JOptionPane.ERROR_MESSAGE);
            } catch (InvalidRowLineException ex) {
                  this.log.error(ex);
                JOptionPane.showMessageDialog(null,ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoTableFoundException ex) {
                  this.log.error(ex);
                JOptionPane.showMessageDialog(null,ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }else if(action.equals("option")){
            
                   java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                    Config dialog = new Config(new javax.swing.JFrame(), true);
 
                     dialog.setVisible(true);
                 }
               }); 
            
            }else if(action.equals("about")){
                       java.awt.EventQueue.invokeLater(new Runnable() {
                  public void run() {
                  About about = new About(new javax.swing.JFrame(), true);
                  about.setVisible(true);
                           
                  }
                });
              
            }
            
//            else if(action.equals("addRule")){
//              JMenuItem menuItem = (JMenuItem)e.getSource();
//              JPopupMenu menu = (JPopupMenu)menuItem.getParent();
//            try {
//               JTable table = this.comman.getJTableFromEventForMenu(e);
//               int row = table.getSelectedRow();
//               int col = table.getSelectedColumn();
//               if(row>-1 && col>-1){
//                 String variable = (String)table.getModel().getValueAt(row, 0);
//                 java.awt.EventQueue.invokeLater(new Runnable() {
//                  public void run() {
//                   RuleEditor editor =  new RuleEditor();
//                   editor.setLocationRelativeTo(null);
//                   editor.setVisible(true);
//                   editor.setName(variable);
//                   RuleEditorModal ruleModal = RuleEditorModal.getRuleEditorModal();
//                   ruleModal.getFileNamePath().setText(variable);
//                  }
//                });
//                                           
//               }
//            } catch (NoTableFoundException ex) {
//                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            }
            else if(action.equals("runCommand")){
                
         
                RunningStatus s = new RunningStatus(new javax.swing.JFrame(), true);
                s.setModalityType(Dialog.ModalityType.MODELESS);
                s.setVisible(true);
                  
                JTextPane textPane = this.runModal.getOutputScreen();
                MessageConsole console = new MessageConsole(textPane, true);
                console.redirectOut(); 
                boolean isHost=false;
                JTabbedPane pane = this.comman.getJTabbedPaneFromEventForMenu(e);
                String message = this.comman.validateTabPane(pane);
                if(message!=null){
                  JOptionPane.showMessageDialog(null, message,"Error",JOptionPane.ERROR_MESSAGE);
                  console=null;
                  return;
                }
                 
                try {
                  isHost = Boolean.valueOf(this.comman.readSemaphore("isHost"));
                } catch (IOException ex) {
                   JOptionPane.showMessageDialog(null,"Unable to proceed, Property file reading failed","Error",JOptionPane.ERROR_MESSAGE);
                   Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
                   
                   return; 
                }
                 List<JTable > allCommandsTable = null;
                 try{
                   allCommandsTable = this.comman.getJTableFromEventForMenu(e);
                 }catch(NoTableFoundException ex){
                    return;
                 }
//                 JTabbedPane pane = this.comman.getJTabbedPaneFromEventForMenu(e);
                 int tabCount=pane.getTabCount();
                 for(int i=0;i<tabCount;i++){
                    // Validation
                    JTable table = allCommandsTable.get(i);
                    CommandEditorTableModel model = (CommandEditorTableModel)table.getModel();
                    int hashCode = model.hashCode();
                    table.setName(pane.getTitleAt(i));
                    int row = table.getRowCount();
                    String []variable = new String[row];
                    for(int j=0;j<row;j++){
                        String var = (String)table.getValueAt(j,0);
                        variable[i] =  var;
                        model.setRowNumByVar(Integer.parseInt(var), j);
                     }
                      
                     int isError = new ErrorChecker().isError(hashCode,variable);
                     if (isError!=0){
                       JOptionPane.showMessageDialog(null,"Jobs Command contain error at variable "+isError,"Error",JOptionPane.ERROR_MESSAGE);
                       return;
                     }
//                    allCommandsTable.get(i).setName(pane.getTitleAt(i));
                    
                 }
           
                RunCommandThread thread = new RunCommandThread(allCommandsTable,textPane,isHost);
                thread.execute();

            }else if(action.equals("openMflags")){
              SwingUtilities.invokeLater(new Runnable() {
                  @Override
                  public void run() {
                     ProgressBar bar = new ProgressBar(new javax.swing.JFrame(), true);
                     bar.setVisible(true);
                     Mflags mflags = new Mflags(new javax.swing.JFrame(), true);
                     bar.setVisible(false);
                     mflags.setVisible(true);       
                     
                  }
              });
              

               
            
            }else if(action.equals("openCardFilePanel")){
            
//              JMenuItem cardMenu = (JMenuItem)e.getSource();
//              String cardName = cardMenu.getText();
//              System.out.println(cardName);
              java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                 
                   CardDialog log = new CardDialog(new javax.swing.JFrame(), true);
                   log.setVisible(true);
//                 ProgressBar bar = new ProgressBar(new javax.swing.JFrame(), true);
//                 bar.setVisible(true);               
//                 CardOpener dialog = new CardOpener(new javax.swing.JFrame(), true,cardName);
//                 dialog.setTitle(cardName+" Card File");
//                 bar.setVisible(false);
//                 dialog.setVisible(true);
            }
        });
//            
            
            
            }else if(action.equals("openDro")){
                    java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                     DRO dialog = new DRO(new javax.swing.JFrame(), true);
                     dialog.setVisible(true);
                   
            }
        });
            
            }else if(action.equals("openRunLogs")){
            try {
                Desktop.getDesktop().open(new File(new Path().getPath(Path.HOST_OUT)));
            } catch (IOException ex) {
                log.error(ex);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
               
            }else if(action.equals("deleteRow")){
             JTabbedPane pane =   this.comman.getJTabbedPaneFromEventForMenu(e);
             int selectedTab = pane.getSelectedIndex();
            try {
                List<JTable> allTabsTable = this.comman.getJTableFromEventForMenu(e);
                JTable table =   allTabsTable.get(selectedTab);
//                CommandEditorTableModel m = (CommandEditorTableModel)table.getModel();
                DefaultTableModel m = (DefaultTableModel)table.getModel();
                
                int rowLength = m.getRowCount();
                if(rowLength==1){
                  JOptionPane.showMessageDialog(null,"Last Row Cannot be deleted","Regrato", JOptionPane.WARNING_MESSAGE);
                  return ;
                }  
                
                int c=table.getSelectedColumn();
                int r=table.getSelectedRow();
                if(c>-1 && r>-1){
                m.removeRow(r);
//                this.comman.rearrangeVariableInJTable(table);
                }
            } catch (NoTableFoundException ex) {
                log.error(ex);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }else if(action.equals("mapRow")){
//               JTabbedPane pane =   this.comman.getJTabbedPaneFromEventForMenu(e);
//               int selectedTab = pane.getSelectedIndex();
//               String fileName = this.coModal.getFilePath();

                  
                  SwingUtilities.invokeLater(new Runnable() {
                      @Override
                      public void run() {
                          ReportMapper mapper = new ReportMapper();
                          mapper.setTitle("Extract Editor");
                          mapper.setVisible(true);
                      }
                  });
                  
          
            }else if(action.equals("appendRowOfMapperAtBottom")){
            try {
                JTable table = this.comman.getJTableFromEventForMenu(e).get(0);
                DefaultTableModel model=(DefaultTableModel)table.getModel();
                int colLength  = model.getColumnCount();
              
                Object []row = new Object[colLength];
                boolean isDel=false;
                try {
                    // Delemiter Column;
                  isDel =   Boolean.valueOf(comman.readSemaphore("isDel"));
                } catch (IOException ex) {
                    this.log.error(ex);
                    Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(isDel){
                 int delCol = colLength-1;
                 String del = (String)table.getValueAt(0, 1);
                 row[delCol]=del;
                }
                model.addRow(row);
                 
            } catch (NoTableFoundException ex) {
                this.log.error(ex);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
               
            }else if(action.equals("appendRowOfMapperAtTop")){
            
               try {
                JTable table = this.comman.getJTableFromEventForMenu(e).get(0);
                DefaultTableModel model=(DefaultTableModel)table.getModel();
//                Enumeration<TableColumn> enum1 =    table.getColumnModel().getColumns();
                int colLength  = model.getColumnCount();
                Object []row = new Object[colLength];
                boolean isDel=false;
                try {
                    // Delemiter Column;
                  isDel =   Boolean.valueOf(comman.readSemaphore("isDel"));
                } catch (IOException ex) {
                    this.log.error(ex);
                    Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(isDel){
                 int delCol = colLength-1;
                 String del = (String)table.getValueAt(0, 1);
                 row[delCol]=del;
                }
                model.insertRow(0, row);
                 
            } catch (NoTableFoundException ex) {
                this.log.error(MenuHandler.class.getName() + ex);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }           
            }else if(action.equals("saveMapper")){
               JFileChooser choose = new JFileChooser();

               JTable table = null;
               try{
                   table  = this.comman.getJTableFromEventForMenu(e).get(0);
                   choose.setFileFilter(new FileNameExtensionFilter("Mapper File","map"));
                   choose.setCurrentDirectory(new File(Path.MAPPER));
                   int open=choose.showSaveDialog(null);
                
                   if(open==JFileChooser.OPEN_DIALOG){
                    File file = choose.getSelectedFile();
                    
                    boolean isDel=false;
                    try {
                        isDel = Boolean.valueOf(comman.readSemaphore("isDel"));
                        
                        ExtractMapperThread thread = new ExtractMapperThread(file,table , isDel);
                        thread.execute();
                        
                    } catch (IOException ex) {
                        this.log.error(ex);
                        JOptionPane.showMessageDialog(null, "Semaphore Read Error","Read Error",JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
                        return;
                    }
                    
                    
                 
                }
               }catch(NoTableFoundException ex){
                 JOptionPane.showMessageDialog(null, "Nothing To Save","Regrato",JOptionPane.WARNING_MESSAGE);
                 this.log.error(ex);
                 return;
               }
//               choose.setVisible(true);
             
            }else if(action.equals("openMapper")){
               
              JTabbedPane pane = null;
 
                pane = comman.getJTabbedPaneFromEventForMenu(e);
 
               JFileChooser choose = new JFileChooser();
               choose.setFileFilter(new FileNameExtensionFilter("Mapper File","map"));
               choose.setCurrentDirectory(new File(Path.MAPPER));
               int open=choose.showOpenDialog(null);
               if(open==JFileChooser.OPEN_DIALOG){
                    File file =       choose.getSelectedFile();
               
                      pane.removeTabAt(0);
                    ExtractMapperOpenThread thread = new ExtractMapperOpenThread(file,pane);
                    thread.execute();
                    
               }
            
            }else if(action.equals("closeMapper")){
               JTabbedPane pane = this.comman.getJTabbedPaneFromEventForMenu(e);
               if(pane!=null){
                pane.removeTabAt(0);
                pane.revalidate();
                pane.repaint();
               }
               
            }else if(action.equals("newMapper")){
               JTabbedPane pane = this.comman.getJTabbedPaneFromEventForMenu(e);
               JTable   removeTable = null;
               try{
               pane.removeTabAt(0);
               }catch( java.lang.IndexOutOfBoundsException ex){}
               try {
                removeTable=this.comman.getJTableFromEventForMenu(e).get(0);
                } catch (NoTableFoundException ex) {
                 this.log.error(MenuHandler.class.getName() + ex);
                 Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
                 
               }
               
               boolean isDel=false;
            try {
                isDel = Boolean.valueOf(comman.readSemaphore("isDel"));
                   if(pane!=null){
                String colName="";
                  if(isDel==false){
                   colName= "Size";
                  }else{
                   colName= "Delimiter";
                 } 
                JTable table = new JTable(1, 3);
                JComboBox b = ExtractMapperModal.getExtractMapperModal().getIsDelCompobox();
               if(removeTable!=null){    
                   b.removeItemListener(new ColumnChangeEvent(removeTable));
                }
                b.addItemListener(new ColumnChangeEvent(table));
                table.setRowHeight(30);
                table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("Field");
                TableColumn typeCol = table.getTableHeader().getColumnModel().getColumn(1);
                table.getTableHeader().getColumnModel().getColumn(2).setHeaderValue(colName);
                typeCol.setHeaderValue("Type");
                
                       String col[]=null;
    
        FileInputStream stream=null;
//        String []colTable = null;
        try {
            ShellOutputStream output = new ShellOutputStream();
            stream = new FileInputStream(new File(Path.COL_LAYOUT+File.separator+"MapperTypeValue.txt"));
            StringBuffer buf=output.readFile(stream);
            col=buf.toString().split("\r\n");
        } catch (FileNotFoundException ex) {
            
            this.log.error(ExtractMapper.class.getName() + ex);
            Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Runtime Error, Application dependency not found", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            this.log.error(MenuHandler.class.getName() + ex);
            JOptionPane.showMessageDialog(null,"Runtime Error, Application dependency not found", "Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ExtractMapperOpenThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    
                       JComboBox<String> s = new JComboBox<>();
               try{
                  for(String c:col){
                   s.addItem(c);
                  }
                    s.setSelectedItem((String)col[0]);
    
               }catch(NullPointerException ex){
                 this.log.error(MenuHandler.class.getName()+ex);
                  JOptionPane.showMessageDialog(null,"Runtime Error, Application dependency not found", "Error",JOptionPane.ERROR_MESSAGE);
                }
              BranchEditor ed = new BranchEditor(s);
              BranchRenderer rd = new BranchRenderer(col);
             rd.setSelectedItem((String)col[0]);
    
              typeCol.setCellEditor(ed);
              typeCol.setCellRenderer(rd);
                  
                
                JScrollPane sPane = new JScrollPane(table);
                pane.addTab("Extract Mapper", sPane);
                pane.revalidate();
                pane.repaint();
               }            
                
                
            } catch (IOException ex) {
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            
            }else if(action.equals("openExtractPath")){
               try {
                Desktop.getDesktop().open(new File(new Path().getPath(Path.EXTRACT_OUTPUT)));
            } catch (IOException ex) {
                log.error(MenuHandler.class.getName() + ex);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }           
            
            }else if(action.equals("appendRowAtNthIndexOfMapper")){
  
               try {
                JTable table = this.comman.getJTableFromEventForMenu(e).get(0);
                DefaultTableModel model=(DefaultTableModel)table.getModel();
//                Enumeration<TableColumn> enum1 =    table.getColumnModel().getColumns();
                int colLength  = model.getColumnCount();
                Object []row = new Object[colLength];
                boolean isDel=false;
                try {
                    // Delemiter Column;
                  isDel =   Boolean.valueOf(comman.readSemaphore("isDel"));
                } catch (IOException ex) {
                    this.log.error(ex);
                    Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(isDel){
                 int delCol = colLength-1;
                 String del = (String)table.getValueAt(0, 1);
                 row[delCol]=del;
                }
                String index=null;
                index=JOptionPane.showInputDialog(null, "Enter Row Number");
                if(index!=null){
                    int in = Integer.parseInt(index);
                    if(in<=0){
                        JOptionPane.showMessageDialog(null,"Invalid Row Number",AppIcon.APP_NAME, JOptionPane.WARNING_MESSAGE);
                        return ;
                    }
                      model.insertRow(in-1, row);
                }
            } catch (NoTableFoundException ex) {
                this.log.error(MenuHandler.class.getName() + ex);
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NumberFormatException ex){
                this.log.error(MenuHandler.class.getName() + ex);
                JOptionPane.showMessageDialog(null,"Invalid Row Number",AppIcon.APP_NAME, JOptionPane.WARNING_MESSAGE);         
            }    catch(java.lang.ArrayIndexOutOfBoundsException ex){
                this.log.error(MenuHandler.class.getName() + ex);
               JOptionPane.showMessageDialog(null,"Row not found",AppIcon.APP_NAME, JOptionPane.WARNING_MESSAGE);          
            }                   
            
            
            }
        
            }
        
    }