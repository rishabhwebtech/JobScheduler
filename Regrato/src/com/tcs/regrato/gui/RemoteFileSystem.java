/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui;

import com.tcs.jpath.modal.Host;
import com.tcs.jpath.modal.JConfig;
import com.tcs.regrato.event.ButtonHandler;
import com.tcs.regrato.gui.thread.RemoteFileSystemThread;
import com.tcs.regrato.modal.RemoteFileSystemModal;
import com.tcs.regrato.resources.Path;
import javax.swing.JLabel;
//import com.tcs.regrato.modal.TableFileSystemModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
/**
 *
 * @author 1299792
 */
public class RemoteFileSystem extends javax.swing.JDialog {
     
     
    /**
     * Creates new form RemoteFileSystem
     */
    private RemoteFileSystemModal mo;
    private   JMenuItem  download ;
    private   JMenuItem  open     ;
    public RemoteFileSystem(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        setModalityType(ModalityType.TOOLKIT_MODAL);
        mo = RemoteFileSystemModal.getRemoteFileSystemModal();
        setProperty();
        setEvent();
        onLoad();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRemoteFileSystem = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldRemotePath = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxSSHProfile = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldIP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldUserName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jTextField1KnownHost = new javax.swing.JTextField();
        jButtonConnect = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Remote FileSystem");
        setResizable(false);

        jTableRemoteFileSystem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {new JLabel("Empty",new ImageIcon(),JLabel.LEFT),"","","","",""}
            },
            new String [] {
                "FileName", "File Size", "File Type", "Last Modified", "Permissions", "Owner/Group"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableRemoteFileSystem);

        jLabel1.setText("Remote Site :");

        jLabel2.setText("Regrato Profile :");

        jComboBoxSSHProfile.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Control", "Day", "Night" }));

        jLabel3.setText("Host :");

        jTextFieldIP.setEditable(false);
        jTextFieldIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIPActionPerformed(evt);
            }
        });

        jLabel4.setText("User Name :");

        jTextFieldUserName.setEditable(false);

        jLabel5.setText("Port :");

        jTextFieldPort.setEditable(false);

        jLabel6.setText("Password :");

        jPasswordFieldPassword.setEditable(false);

        jButtonConnect.setText("Connect");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldIP, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxSSHProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1KnownHost, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonConnect)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldRemotePath))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSSHProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1KnownHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldRemotePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIPActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JComboBox<String> jComboBoxSSHProfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRemoteFileSystem;
    private javax.swing.JTextField jTextField1KnownHost;
    private javax.swing.JTextField jTextFieldIP;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldRemotePath;
    private javax.swing.JTextField jTextFieldUserName;
    // End of variables declaration//GEN-END:variables

private void setProperty(){
  jTableRemoteFileSystem.getTableHeader().setReorderingAllowed(false);
  jTextField1KnownHost.setVisible(false);
//    TableFileSystemModel model = new TableFileSystemModel();
//    jTableRemoteFileSystem.setModel(model);
  TableColumn col=jTableRemoteFileSystem.getColumn("FileName");
  JLabelEditor ed = new JLabelEditor(new JTextField());
  JLabelRenderer rd = new JLabelRenderer("Empty",new ImageIcon(Path.ICON+File.separator+"folderIcon.png"), JLabel.LEFT);
  col.setCellEditor(ed);
  col.setCellRenderer(rd);
  jTableRemoteFileSystem.setShowGrid(false);
   JPopupMenu remoteFileSystemPopUpMenu = new JPopupMenu("Remote Options");
   download = new JMenuItem("Download");
   open     = new JMenuItem("Open");
//  JMenuItem  
 remoteFileSystemPopUpMenu.add(download);
 remoteFileSystemPopUpMenu.add(open);
 jTableRemoteFileSystem.setComponentPopupMenu(remoteFileSystemPopUpMenu);
  jProgressBar1.setBorderPainted(true);
  jProgressBar1.setIndeterminate(true);
  jProgressBar1.setStringPainted(true);
  jProgressBar1.setString("Downloading ....");
  jProgressBar1.setVisible(false);
  mo.setProgress(jProgressBar1);
  mo.setRemoteFileSystemView(jTableRemoteFileSystem);
  mo.setCurrentPath(jTextFieldRemotePath);
  mo.setMode(jComboBoxSSHProfile);
  mo.setConnectButton(jButtonConnect);
}
private void setEvent(){
    ButtonHandler handler = new ButtonHandler();
    jButtonConnect.setActionCommand("connectToHostFileSystemView");
    jButtonConnect.addActionListener(handler);
    download.setActionCommand("downloadSSHFile");
    open.setActionCommand("openSSHFileOrFolder");
    download.addActionListener(handler);
    open.addActionListener(handler);
    jTextFieldRemotePath.setActionCommand("loadRemoteDir");
    jTextFieldRemotePath.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if(code==KeyEvent.VK_ENTER){
               JTextField currentPathField  =  (JTextField)e.getSource();
               String currentPath = currentPathField.getText();
                DefaultTableModel model =  (DefaultTableModel)jTableRemoteFileSystem.getModel();
//                  int totalRow = model.getRowCount();
                  
                  for( int i = model.getRowCount() - 1; i >= 0; i-- ){
                      model.removeRow(i);
                   }
                     
//                 mo.fireTableRowsDeleted(0, totalRow);
                 
               
                RemoteFileSystemThread thread = new RemoteFileSystemThread(currentPath);
                thread.execute();
            }
        }

    
    });
    
   jComboBoxSSHProfile.addItemListener(new ItemListener() {
       @Override
       public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange()==ItemEvent.SELECTED) {
                String action = (String)e.getItem();
                 loadRegionProfille(action);
              }
  
       }
   });
}
private void onLoad(){
  String action = (String)jComboBoxSSHProfile.getSelectedItem();
  loadRegionProfille(action);

}


private void loadRegionProfille(String action){
   JConfig config = new JConfig();
   Host host = config.getHost();
   String ip="";
   String userName="";
   String password="";
    String port ="22";
   String knownHost="" ;
    switch (action){
        case "Control":
          ip = host.getHostIp();
          userName = host.getHostUserName();
          password = host.getHostPassword();
//          port = 22;
          knownHost = host.getHostKnownHost();
          break;
        case "Day":
            ip = host.getDayHostIp();
            userName = host.getDayHostUserName();
            password = host.getDayHostPassword();
//            port = 22;
            knownHost = host.getDayHostKnonwHost();
            
         break;
         case "Night":
             ip = host.getNightHostIp();
//             port = 22;
             userName = host.getNightHostUserName();
             password = host.getNightHostPassword();
             knownHost = host.getNightHostKnownHost();
         break;
      
   }
    jTextField1KnownHost.setText(knownHost);
    jTextFieldIP.setText(ip);
    jTextFieldPort.setText(port);
    jTextFieldUserName.setText(userName);
    jPasswordFieldPassword.setText(password);
}
}
