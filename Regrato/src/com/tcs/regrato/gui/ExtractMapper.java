/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui;

import com.tcs.regrato.event.ButtonHandler;
import com.tcs.regrato.event.ColumnChangeEvent;
import com.tcs.regrato.event.MapperTableEvent;
import com.tcs.regrato.event.MenuHandler;
import com.tcs.regrato.gui.thread.ExtractMapperOpenThread;
import com.tcs.regrato.modal.BranchListModel;
import com.tcs.regrato.modal.ExtractMapperModal;
import com.tcs.regrato.resources.AppIcon;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.util.Comman;
import com.tcs.shellsch.ShellOutputStream;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author 1299792
 */
public class ExtractMapper extends javax.swing.JFrame {

    /**
     * Creates new form ExtractMapper
     */
    private JComboBox<String> s;
    private Comman comman;
    private org.apache.log4j.Logger log;
    private ExtractMapperModal mapper;
    public ExtractMapper() {
        comman=new Comman();
        this.log = comman.getLogger();
        mapper = ExtractMapperModal.getExtractMapperModal();
        initComponents();
        setProperty();
        setEvent();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxDelYesNo = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1Save = new javax.swing.JMenu();
        jMenuItemNewMapper = new javax.swing.JMenuItem();
        jMenuItemOpenMapper = new javax.swing.JMenuItem();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemCloseMapper = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemAppendRowAtBottom = new javax.swing.JMenuItem();
        jMenuItemAppendRowAtTop = new javax.swing.JMenuItem();
        jMenuItemAtNthIndex = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Field", "Type", "Delimiter"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTabbedPane1.addTab("Extract Mapper", jScrollPane1);

        jLabel1.setText("Delimiter :");

        jComboBoxDelYesNo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1:Yes", "2:No" }));

        jMenu1Save.setText("File");

        jMenuItemNewMapper.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNewMapper.setText("New");
        jMenu1Save.add(jMenuItemNewMapper);

        jMenuItemOpenMapper.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemOpenMapper.setText("Open");
        jMenu1Save.add(jMenuItemOpenMapper);

        jMenuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSave.setText("Save");
        jMenu1Save.add(jMenuItemSave);

        jMenuItemCloseMapper.setText("Close");
        jMenu1Save.add(jMenuItemCloseMapper);

        jMenuBar1.add(jMenu1Save);

        jMenu2.setText("Edit");

        jMenuItemAppendRowAtBottom.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAppendRowAtBottom.setText("Append Row At Bottom");
        jMenu2.add(jMenuItemAppendRowAtBottom);

        jMenuItemAppendRowAtTop.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAppendRowAtTop.setText("Append Row At Top");
        jMenu2.add(jMenuItemAppendRowAtTop);

        jMenuItemAtNthIndex.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAtNthIndex.setText("Append Row At nth Index");
        jMenu2.add(jMenuItemAtNthIndex);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addGap(68, 68, 68)
                        .addComponent(jComboBoxDelYesNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jComboBoxDelYesNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addGap(97, 97, 97))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxDelYesNo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1Save;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemAppendRowAtBottom;
    private javax.swing.JMenuItem jMenuItemAppendRowAtTop;
    private javax.swing.JMenuItem jMenuItemAtNthIndex;
    private javax.swing.JMenuItem jMenuItemCloseMapper;
    private javax.swing.JMenuItem jMenuItemNewMapper;
    private javax.swing.JMenuItem jMenuItemOpenMapper;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

private void setProperty(){
    s = new JComboBox<>();
    mapper.setIsDelCompobox(jComboBoxDelYesNo);
        try {

           comman.writeSemaphore("isDel", Boolean.toString(true)); 
        } catch (IOException ex) {
            Logger.getLogger(ExtractMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
     setIconImage(new AppIcon().getAppIcon());
    jTable1.setRowHeight(30);
    comman.addingRightClickMenu(jTable1);
//    String col[]={"Header","Body","Footer"};
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
            Logger.getLogger(ExtractMapperOpenThread.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Runtime Error, Application dependency not found", "Error",JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException ex) {
            this.log.error(ExtractMapper.class.getName() + ex);
            JOptionPane.showMessageDialog(null,"Runtime Error, Application dependency not found", "Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ExtractMapperOpenThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    try{
    for(String c:col){
       s.addItem(c);
    }
    s.setSelectedItem((String)col[0]);
    
    }catch(NullPointerException ex){
       this.log.error(ex);
       JOptionPane.showMessageDialog(null,"Runtime Error, Application dependency not found", "Error",JOptionPane.ERROR_MESSAGE);
    }
    BranchEditor ed = new BranchEditor(s);
    
    BranchRenderer rd = new BranchRenderer(col);
    rd.setSelectedItem((String)col[0]);
    TableColumn typeCol=jTable1.getColumn("Type");
    typeCol.setCellEditor(ed);
    typeCol.setCellRenderer(rd);
    
}
private void setEvent(){
    ButtonHandler handle = new ButtonHandler();
    MenuHandler   menuHandler = new MenuHandler();
    DefaultTableModel model =   (DefaultTableModel)jTable1.getModel();
    model.addTableModelListener(new MapperTableEvent());
    jMenuItemSave.setActionCommand("saveMapper");
    jMenuItemSave.addActionListener(menuHandler);
    jMenuItemOpenMapper.setActionCommand("openMapper");
    jMenuItemOpenMapper.addActionListener(menuHandler);
    jMenuItemAppendRowAtBottom.setActionCommand("appendRowOfMapperAtBottom");
    jMenuItemAppendRowAtTop.setActionCommand("appendRowOfMapperAtTop");
    jMenuItemCloseMapper.setActionCommand("closeMapper");
    jMenuItemNewMapper.setActionCommand("newMapper");
    jMenuItemNewMapper.addActionListener(menuHandler);
    jMenuItemCloseMapper.addActionListener(menuHandler);
    jMenuItemAppendRowAtBottom.addActionListener(menuHandler);
    jMenuItemAppendRowAtTop.addActionListener(menuHandler);
    jMenuItemAtNthIndex.setActionCommand("appendRowAtNthIndexOfMapper");
    jMenuItemAtNthIndex.addActionListener(menuHandler);
    this.jComboBoxDelYesNo.addItemListener(new ColumnChangeEvent(jTable1));
    
// this.jComboBoxDelYesNo.addItemListener(new ItemListener() {
//     @Override
//     public void itemStateChanged(ItemEvent e) {
//       if(e.getStateChange()==ItemEvent.SELECTED){
//           
//           
//            String type =  (String)e.getItem();
//              boolean isDel=false;
////              jTable1.setAutoCreateColumnsFromModel(false);
//              jComboBoxDelYesNo.setSelectedItem(type);
//            switch(type){
//                case "1:Yes":
//                    isDel=true;
//                  try{
//                    jTable1.getColumn("Delimiter").setHeaderValue("Delimiter");
//                  }catch(IllegalArgumentException ex){
//                     jTable1.getColumn("Size").setHeaderValue("Delimiter");
//                  }
//                  break;
//                case "2:No":
//                    isDel=false;
//                    try{
//                    jTable1.getColumn("Delimiter").setHeaderValue("Size");
//                    
//                    }catch(IllegalArgumentException ex){
//                      
//                    }
//                    break;  
//            
//            }
//           try{
//             
//               
//               comman.writeSemaphore("isDel", Boolean.toString(isDel));          
//           } catch (IOException ex) {
//               Logger.getLogger(ExtractMapper.class.getName()).log(Level.SEVERE, null, ex);
//           }
//            jTable1.revalidate();
//            jTable1.repaint();
//       }
//     }
// });
}

}