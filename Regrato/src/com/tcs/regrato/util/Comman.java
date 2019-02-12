/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.util;

import com.tcs.jpath.modal.DriverProperty;
import com.tcs.regrato.event.MenuHandler;
import com.tcs.regrato.gui.region.CardOpener;
import com.tcs.regrato.modal.CommandEditorTableModel;
import com.tcs.regrato.resources.InvalidSeqType;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.resources.SeqType;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.omg.CORBA.DynAnyPackage.InvalidSeq;

/**
 *
 * @author 1299792
 */
public class Comman {
    
    public JList getJListFromButtonEvent(ActionEvent e){
      JList list = null;
    
              JButton button  = (JButton)e.getSource();
          JPanel dialog =          (JPanel)button.getParent();
            Component children[] = dialog.getComponents();
            for(Component c:children){
               if(c instanceof JScrollPane){
                  JScrollPane allCardPane = (JScrollPane)c;
                  JList cardList =  (JList)allCardPane.getViewport().getComponent(0);
//                  BranchListModel model =  (BranchListModel)cardList.getModel();
                  list = cardList;
                  break;

               }
      
     
    }
             return list;
    }
    
    public JFrame getJFrameFromEventForMenu(ActionEvent e){
      
       
      JMenuItem component = (JMenuItem)e.getSource();
      JPopupMenu menu = (JPopupMenu)component.getParent();
      Component invoker =  menu.getInvoker();
      JComponent invokerAsJComponent = (JComponent) invoker;
      Container topLevel = invokerAsJComponent.getTopLevelAncestor();
      JFrame frame = (JFrame)topLevel;
      JRootPane root = frame.getRootPane();
      Component comp[] = root.getComponents();
      JTabbedPane pane=null;
      
                  topLevel:for(Component c : comp){
               
                if(c instanceof JLayeredPane){
                  JLayeredPane p = (JLayeredPane)c;
                  Component []co = p.getComponents();
                  for(Component c1 : co){
                    if(c1 instanceof JPanel){
                      JPanel p1 = (JPanel)c1;
                      Component []c2 = p1.getComponents();
                      for(Component c3 : c2){
                        if(c3 instanceof JTabbedPane){
                        
                          pane = (JTabbedPane)c3;
                          break topLevel;
                        }
                      }
                    }
                  }
                }
            
            }
   
       
       
       return frame;
    }
    
    
    
        public JFrame getJFrameFromButtonEvent(ActionEvent e){
      
           JFrame frame = null;
           JButton component = (JButton)e.getSource();
           JPanel panel=(JPanel)component.getParent();
           frame = (JFrame)panel.getTopLevelAncestor();
       
       
          return frame;
    }
    
    
    public  JTabbedPane getJTabbedPaneFromEventForMenu(ActionEvent e){
       JTabbedPane pane=null;
       JMenuItem component = (JMenuItem)e.getSource();
       JPopupMenu menu = (JPopupMenu)component.getParent();
       Component invoker =  menu.getInvoker();
       JComponent invokerAsJComponent = (JComponent) invoker;
       Container topLevel = invokerAsJComponent.getTopLevelAncestor();
       JFrame frame = (JFrame)topLevel;
       JRootPane root = frame.getRootPane();
       Component comp[] = root.getComponents(); 
                         topLevel:for(Component c : comp){
               
                if(c instanceof JLayeredPane){
                  JLayeredPane p = (JLayeredPane)c;
                  Component []co = p.getComponents();
                  for(Component c1 : co){
                    if(c1 instanceof JPanel){
                      JPanel p1 = (JPanel)c1;
                      Component []c2 = p1.getComponents();
                      for(Component c3 : c2){
                        if(c3 instanceof JTabbedPane){
                        
                          pane = (JTabbedPane)c3;
                          break topLevel;
                        }
                      }
                    }
                  }
                }
            
            }
       
       return pane;
    }
    public List<JTable> getJTableFromEventForMenu(ActionEvent e) throws NoTableFoundException{
      JTable table = null;
      List<JTable> tableHolder = new ArrayList<>();
      JMenuItem component = (JMenuItem)e.getSource();
      JPopupMenu menu = (JPopupMenu)component.getParent();
      Component invoker =  menu.getInvoker();
      JComponent invokerAsJComponent = (JComponent) invoker;
      Container topLevel = invokerAsJComponent.getTopLevelAncestor();
      JFrame frame = (JFrame)topLevel;
      JRootPane root = frame.getRootPane();
      Component comp[] = root.getComponents();
      JTabbedPane pane=null;
      
                  topLevel:for(Component c : comp){
               
                if(c instanceof JLayeredPane){
                  JLayeredPane p = (JLayeredPane)c;
                  Component []co = p.getComponents();
                  for(Component c1 : co){
                    if(c1 instanceof JPanel){
                      JPanel p1 = (JPanel)c1;
                      Component []c2 = p1.getComponents();
                      for(Component c3 : c2){
                        if(c3 instanceof JTabbedPane){
                        
                          pane = (JTabbedPane)c3;
                          break topLevel;
                        }
                      }
                    }
                  }
                }
            
            }
      Component []paneComponents = pane.getComponents();
      int length = paneComponents.length;
      if(length == 0){
          throw new NoTableFoundException("No Table Found");
      }else{
          
           int tabCount =  pane.getTabCount();
           for(int i=0;i<tabCount;i++){
             JScrollPane scrolPane =  (JScrollPane)paneComponents[i];
             
             table = (JTable)scrolPane.getViewport().getComponent(0);
             tableHolder.add(table);
           }
//         int selectedIndex =   pane.getSelectedIndex();
//         JScrollPane scrolPane =  (JScrollPane)paneComponents[selectedIndex];
//         table = (JTable)scrolPane.getViewport().getComponent(0);
      }
      
      return tableHolder;
    
    }
    
    public int[] getVariablesFromTable(DefaultTableModel m){
      int row =   m.getRowCount();
      int []variables = new int[row];
      for(int i =0;i<row;i++){
          String var =  (String)m.getValueAt(i,0);
          variables[i]=Integer.parseInt(var);
      }
      
      return variables;
    }
    public String getNewVariable(int []a){
      String var = "";
      int size = a.length;
      Arrays.sort(a);
      
      var = Integer.toString((a[size-1] + 1));
      return var;
    
    }
   public JTextField getTextAreaFromActionEvent(ActionEvent e){
      JTextField field = null;
      
      JMenuItem component = (JMenuItem)e.getSource();
       JPopupMenu menu = (JPopupMenu)component.getParent();
       Component invoker =  menu.getInvoker();
       JComponent invokerAsJComponent = (JComponent) invoker;
       Container topLevel = invokerAsJComponent.getTopLevelAncestor();
       JFrame frame = (JFrame)topLevel;
       JRootPane root = frame.getRootPane();
       Component comp[] = root.getComponents(); 
       topLevel:for(Component c : comp){
               
                if(c instanceof JLayeredPane){
                  JLayeredPane p = (JLayeredPane)c;
                  Component []co = p.getComponents();
                  for(Component c1 : co){
                    if(c1 instanceof JPanel){
                      JPanel p1 = (JPanel)c1;
                      Component []c2 = p1.getComponents();
                      for(Component c3 : c2){
                        if(c3 instanceof JTextField){
                        
                          field = (JTextField)c3;
                          break topLevel;
                        }
                      }
                    }
                  }
                }
            
            }
      
      return field;
   }
    public void insertRowAtLine(JTable table , int atLine) throws InvalidRowLineException{
                if(atLine <=0){
                   
                   throw new InvalidRowLineException("Invalid Row Line "+atLine);
                }
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                int col = model.getColumnCount();
                int row = model.getRowCount();
                String []a = new String[col];
                for(int i =0 ;i<col-1;i++){
                    if(i==0){
                     int []var =  getVariablesFromTable(model);
                     String nextVar = getNewVariable(var);
                     a[i] = nextVar;
                    }else{
                       a[i]= "";
                    }
                }
                a[col-1] = "No Action";
                try{
                model.insertRow((atLine-1), a);
                }catch(ArrayIndexOutOfBoundsException ex){
                   throw new InvalidRowLineException("Invalid Row Line "+atLine);
                }
    }
    public void addingRightClickMenu(JTable table){
       final JPopupMenu menu = new JPopupMenu("Options");
        JMenuItem addRuleItem = new JMenuItem("Delete Row");
//        JMenuItem mapperItem = new JMenuItem("Add Map");
        addRuleItem.setActionCommand("deleteRow");
//        mapperItem.setActionCommand("mapRow");
        MenuHandler handle = new MenuHandler();
        addRuleItem.addActionListener(handle);
//        mapperItem.addActionListener(handle);
        menu.add(addRuleItem);
//        menu.add(mapperItem);
        table.setComponentPopupMenu(menu);
        
        
    }
    
    
    
    public void writeTextInFile(File file,String text) throws FileNotFoundException, IOException{
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
        
        byte data[] = text.getBytes();
        output.write(data);
        output.flush();
        output.close();
    }
    public String toStringDate(Date date){
       String stringDate="";
       SimpleDateFormat format = new  SimpleDateFormat("DD-MM-YYY:HH:MM:SS");
       stringDate = format.format(date);
       return stringDate;
    }

    public int getRowNumByVar(int var,JTable table){
      int rowNum=-1;
      CommandEditorTableModel m =(CommandEditorTableModel)table.getModel();
      int rowCount = table.getRowCount();
      for(int i=0;i<rowCount;i++){
        int index=Integer.parseInt((String)table.getValueAt(i,0));
        if(index==var){
          rowNum=index;
          break;
        }
      }
      
      return rowNum;
    }
    
    public void rearrangeVariableInJTable(JTable table){
      int row =   table.getRowCount();
      for(int i=0;i<row;i++){
         
         table.setValueAt(Integer.toString((i+1)), i, 0);
      }
    }
    public void rearrangeVariableInJTable(TableModel table){
      int row =   table.getRowCount();
      for(int i=0;i<row;i++){
         
         table.setValueAt(Integer.toString((i+1)), i, 0);
      }
    }
    public void writeSemaphore(String key,String value) throws IOException{
        File semaphoreFile = new File(Path.SEMA_PATH);
        OutputStream write = null;
        FileInputStream load = new FileInputStream(semaphoreFile);
        Properties prop = new Properties();
        prop.load(load);
        prop.setProperty(key, value);
        write=new FileOutputStream(semaphoreFile);
        prop.store(write,"Semaphore");
        write.flush();
        write.close();
    }
    public synchronized String readSemaphore(String key) throws FileNotFoundException, IOException{
        File semaphoreFile = new File(Path.SEMA_PATH);
        String p = null;
        InputStream read = new FileInputStream(semaphoreFile);
        Properties prop = new Properties();
        prop.load(read);
        p=prop.getProperty(key);
        read.close();
        return p;
    }
    public int getSeqNumber(int type) throws InvalidSeqType{
       int seq=0;
       if(type==SeqType.LOGGER_TYPE){
        File hostOutput = new File(Path.HOST_OUT);
        File []file = hostOutput.listFiles();
        File temp=null;
        for(File f : file){
          if(f.isFile()){    
          temp=f;
          }
        }
        if(temp==null){
          seq=0; 
        }else{
          String []ts = temp.getName().split("_");
          seq = Integer.parseInt(ts[2]);
          seq=seq+1;
        }
       }else {
         throw  new InvalidSeqType("Invalid Sequence Generator Type");
       }
       
       return seq;
    }
    
     public void writeStringInFile(File file,String text){
      try(RandomAccessFile randomAcc = new RandomAccessFile(file, "rw");
           FileChannel fileChannel = randomAcc.getChannel();){
           randomAcc.seek(randomAcc.length());
          
           ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());
           fileChannel.write(buffer);
      }catch(IOException ex){
       
      }
     }
     
      public void writeStringInFile(File file,String text,boolean isAppend){
      try(RandomAccessFile randomAcc = new RandomAccessFile(file, "rw");
           WritableByteChannel fileChannel = (WritableByteChannel)randomAcc.getChannel();){
           if(isAppend){
            randomAcc.seek(randomAcc.length());
           }
           ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());
           fileChannel.write(buffer);
      }catch(IOException ex){
       
      }
     }
      public void deleteTemp(){
        File files = new File(Path.TEMP_PATH);
        File allFile[] = files.listFiles();
        for(File file : allFile){
          file.delete();
        }
      
      }
    public String validateTabPane(JTabbedPane pane){
        String str = null;
        Set<String>   tabName = new HashSet<String>();
        int tabCount =     pane.getTabCount();
        for(int i =0;i<tabCount;i++){
          tabName.add(pane.getTitleAt(i));
        }
        int sizeOfSet = tabName.size();
        if(sizeOfSet!=tabCount){
          str = "Tab name cannot be same";
        }
        return str;
    }
 public boolean isValidDate(String DD,String MM,String YYYY){
    boolean isValid = false;
    if(DD.compareTo("31")<=0 && MM.compareTo("12")<=0){
       isValid=true;
    }
    if(DD.equals("")){
      isValid=false;
    }
    if(MM.equals("")){
     isValid=false;
    }
    if(YYYY.equals("")){
     isValid=false;
    }
    
    
    return isValid;
  }
 
     public org.apache.log4j.Logger getLogger(){
              String log4jPath = Path.LOG4J_PATH;
        System.setProperty("log4j.configurationFile", log4jPath);
      org.apache.log4j.Logger log = null;
      log = org.apache.log4j.Logger.getLogger("EXCEPTION");
      return log;
    
    }
     
      public List<JTable> getJTableFromEventForMenu(JMenuItem e) throws NoTableFoundException{
      JTable table = null;
      List<JTable> tableHolder = new ArrayList<>();
      JMenuItem component = e;
      JPopupMenu menu = (JPopupMenu)component.getParent();
      Component invoker =  menu.getInvoker();
      JComponent invokerAsJComponent = (JComponent) invoker;
      Container topLevel = invokerAsJComponent.getTopLevelAncestor();
      JFrame frame = (JFrame)topLevel;
      JRootPane root = frame.getRootPane();
      Component comp[] = root.getComponents();
      JTabbedPane pane=null;
      
                  topLevel:for(Component c : comp){
               
                if(c instanceof JLayeredPane){
                  JLayeredPane p = (JLayeredPane)c;
                  Component []co = p.getComponents();
                  for(Component c1 : co){
                    if(c1 instanceof JPanel){
                      JPanel p1 = (JPanel)c1;
                      Component []c2 = p1.getComponents();
                      for(Component c3 : c2){
                        if(c3 instanceof JTabbedPane){
                        
                          pane = (JTabbedPane)c3;
                          break topLevel;
                        }
                      }
                    }
                  }
                }
            
            }
      Component []paneComponents = pane.getComponents();
      int length = paneComponents.length;
      if(length == 0){
          throw new NoTableFoundException("No Table Found");
      }else{
          
           int tabCount =  pane.getTabCount();
           for(int i=0;i<tabCount;i++){
             JScrollPane scrolPane =  (JScrollPane)paneComponents[i];
             
             table = (JTable)scrolPane.getViewport().getComponent(0);
             tableHolder.add(table);
           }
//         int selectedIndex =   pane.getSelectedIndex();
//         JScrollPane scrolPane =  (JScrollPane)paneComponents[selectedIndex];
//         table = (JTable)scrolPane.getViewport().getComponent(0);
      }
      
      return tableHolder;
    
    }   
         public  JTabbedPane getJTabbedPaneFromEventForMenu(JMenuItem e){
       JTabbedPane pane=null;
       JMenuItem component = e;
       JPopupMenu menu = (JPopupMenu)component.getParent();
       Component invoker =  menu.getInvoker();
       JComponent invokerAsJComponent = (JComponent) invoker;
       Container topLevel = invokerAsJComponent.getTopLevelAncestor();
       JFrame frame = (JFrame)topLevel;
       JRootPane root = frame.getRootPane();
       Component comp[] = root.getComponents(); 
                         topLevel:for(Component c : comp){
               
                if(c instanceof JLayeredPane){
                  JLayeredPane p = (JLayeredPane)c;
                  Component []co = p.getComponents();
                  for(Component c1 : co){
                    if(c1 instanceof JPanel){
                      JPanel p1 = (JPanel)c1;
                      Component []c2 = p1.getComponents();
                      for(Component c3 : c2){
                        if(c3 instanceof JTabbedPane){
                        
                          pane = (JTabbedPane)c3;
                          break topLevel;
                        }
                      }
                    }
                  }
                }
            
            }
       
       return pane;
    }
     
}
