/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.region;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.tcs.jpath.modal.Host;
import com.tcs.jpath.modal.JConfig;
import com.tcs.regrato.resources.AppIcon;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.resources.SSHSessionFactory;
import com.tcs.regrato.util.Comman;
import com.tcs.shellsch.ShellOutputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 1299792
 */
public class Mflags extends javax.swing.JDialog {

    /**
     * Creates new form Mflags
     */
    JConfig c;
    Path path;
    Session control;
    Session day;
    Session night;
    Comman commanFunction;
    public Mflags(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.commanFunction= new Comman();
        c = new JConfig();
        path = new Path();
        control=null;
        day=null;
        night=null;
        try{
         onLoad("001"); 
         setProperty();
         setEvent();
        }catch(JSchException ex){
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldConDD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldConMM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldConYYYY = new javax.swing.JTextField();
        jTextFieldDayYYYY = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldDayMM = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldDayDD = new javax.swing.JTextField();
        jTextFieldNSYYYY = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldNSMM = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldNSDD = new javax.swing.JTextField();
        jCheckBoxSyncDate = new javax.swing.JCheckBox();
        jButtonChangeDate = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxInst = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mflags");

        jLabel1.setText("Current Mflags Date In Control Region :");

        jLabel2.setText("Current Mflags Date In Day Region      :");

        jLabel3.setText("Current Mflags Date In Night Region    :");

        jLabel4.setText("/");

        jLabel5.setText("/");

        jTextFieldConYYYY.setText(" ");

        jTextFieldDayYYYY.setText(" ");

        jLabel6.setText("/");

        jLabel7.setText("/");

        jTextFieldNSYYYY.setText(" ");

        jLabel8.setText("/");

        jLabel9.setText("/");

        jCheckBoxSyncDate.setText("Sync Date");

        jButtonChangeDate.setText("Change Date");

        jLabel10.setText("DD");

        jLabel11.setText("DD");

        jLabel12.setText("DD");

        jLabel13.setText("MM");

        jLabel14.setText("MM");

        jLabel15.setText("MM");

        jLabel16.setText("YYYY");

        jLabel17.setText("YYYY");

        jLabel18.setText("YYYY");

        jLabel19.setText("Institution");

        jComboBoxInst.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "001", "002" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBoxSyncDate)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldNSDD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldNSMM, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel18)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldNSYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldDayDD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldDayMM, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel17)
                            .addGap(10, 10, 10)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldDayYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldConDD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(159, 159, 159)
                                .addComponent(jComboBoxInst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldConMM, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldConYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonChangeDate)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBoxInst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldConDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldConMM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldConYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldDayDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jTextFieldDayMM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTextFieldDayYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel14)
                        .addComponent(jLabel17)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextFieldNSDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jTextFieldNSMM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jTextFieldNSYYYY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel15))
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxSyncDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonChangeDate)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonChangeDate;
    private javax.swing.JCheckBox jCheckBoxSyncDate;
    private javax.swing.JComboBox<String> jComboBoxInst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextFieldConDD;
    private javax.swing.JTextField jTextFieldConMM;
    private javax.swing.JTextField jTextFieldConYYYY;
    private javax.swing.JTextField jTextFieldDayDD;
    private javax.swing.JTextField jTextFieldDayMM;
    private javax.swing.JTextField jTextFieldDayYYYY;
    private javax.swing.JTextField jTextFieldNSDD;
    private javax.swing.JTextField jTextFieldNSMM;
    private javax.swing.JTextField jTextFieldNSYYYY;
    // End of variables declaration//GEN-END:variables

  public void setProperty(){
//    jCheckBoxSyncDate.setSelected(true);
//    setSyncUnSunc(1);
      setIconImage(new AppIcon().getAppIcon());
      setResizable(false);
  }
  
  public void onLoad(String inst) throws JSchException{
      boolean isSync=false;
      Host host = c.getHost();
            
            String cIp=host.getHostIp();
//            String cUserName=host.getHostUserName();
//            String cPassword=host.getHostPassword();
            
            
            String dayIp=host.getDayHostIp();
//            String dayUserName=host.getDayHostUserName();
//            String dayPassword=host.getDayHostPassword();
            
            
            String nIp=host.getNightHostIp();
//            String nUserName=host.getNightHostUserName();
//            String nPassword=host.getNightHostPassword();
            String firstBranch = c.getBranches().getBranches().get(0);
            if(cIp.equals("")==false){
              // Control Region 
               control =  SSHSessionFactory.getSSHSession(firstBranch,"C");
            }
            if(dayIp.equals("")==false){
              // Day Region
               day =  SSHSessionFactory.getSSHSession(firstBranch, "D");
            }
            if(nIp.equals("")==false){
              // Night
             night =   SSHSessionFactory.getSSHSession(firstBranch, "N");
            }   
        String controlMflagsDate = null;
        String dayMflagsDate =null;
        String nightMflagsDate = null;
        
        String DD=null;
        String MM=null;
        String YYYY=null;
        
        if(control!=null){
          controlMflagsDate =    path.getContent(control,"MFLAGS",inst);

       }
       
       if(day!=null){
         dayMflagsDate =  path.getContent(day,"MFLAGS",inst);
       }
       
       if(night!=null){
         nightMflagsDate= path.getContent(night,"MFLAGS",inst);
       }
       if(day!=null && night!=null){
       if(controlMflagsDate.equals(dayMflagsDate)){
           if(controlMflagsDate.equals(nightMflagsDate)){
              isSync=true;
           }
       
       }  
       }else {
         isSync=false;
       }
       
       if(isSync==false){
          jCheckBoxSyncDate.doClick();
          DD = controlMflagsDate.substring(14, 16);
          MM = controlMflagsDate.substring(12, 14);
          YYYY = controlMflagsDate.substring(8, 12);
          jTextFieldConDD.setText(DD);
          jTextFieldConMM.setText(MM);
          jTextFieldConYYYY.setText(YYYY);
          
          // Day
          if(dayMflagsDate!=null){
           DD = dayMflagsDate.substring(14, 16);
           MM = dayMflagsDate.substring(12, 14);
           YYYY = dayMflagsDate.substring(8, 12);
           jTextFieldDayDD.setText(DD);
           jTextFieldDayMM.setText(MM);
           jTextFieldDayYYYY.setText(YYYY);
          }
          // Night
          if(nightMflagsDate!=null){
           DD = nightMflagsDate.substring(14, 16);
           MM = nightMflagsDate.substring(12, 14);
           YYYY = nightMflagsDate.substring(8, 12);
           jTextFieldNSDD.setText(DD);
           jTextFieldNSMM.setText(MM);
           jTextFieldNSYYYY.setText(YYYY); 
          }
          
       }else{
          
          DD = controlMflagsDate.substring(14, 16);
          MM = controlMflagsDate.substring(12, 14);
          YYYY = controlMflagsDate.substring(8, 12);
          jTextFieldConDD.setText(DD);
          jTextFieldConMM.setText(MM);
          jTextFieldConYYYY.setText(YYYY);     
       
       }
       
       DD=null;
       MM=null;
       YYYY=null;
       controlMflagsDate=null;
       nightMflagsDate=null;
       dayMflagsDate=null;
       
  }
  
  public void setEvent(){
      
      jCheckBoxSyncDate.addItemListener(new ItemListener() {
          @Override
          public void itemStateChanged(ItemEvent e) {
//              System.out.println("adssad");
             if(e.getStateChange()==ItemEvent.SELECTED){
                setSyncUnSunc(1);
             }else{
                 setSyncUnSunc(0);
             }

          }
      });
      jButtonChangeDate.setActionCommand("changeMflags");
      jButtonChangeDate.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             String inst = (String)jComboBoxInst.getSelectedItem();
             actionCommand(inst);
          }
      });
      
      jComboBoxInst.addItemListener(new ItemListener() {
          @Override
          public void itemStateChanged(ItemEvent e) {
              
              if(ItemEvent.COMPONENT_EVENT_MASK==e.getStateChange()){
                 String inst =      (String)e.getItem();
//                 System.out.println(inst);
               try{
                 onLoad(inst);
                }catch(JSchException ex){
                   JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
//             if(e.getStateChange()==ItemEvent.){
//               String inst =      (String)e.getItem();
//               System.out.println(inst);
//             }
          }
      });
      
  }
  
  
  private void actionCommand(String inst){
  
//               Comman comman = new Comman();
              // Control Region
            String conDD=jTextFieldConDD.getText();
            String conMM=jTextFieldConMM.getText();
            String conYYYY=jTextFieldConYYYY.getText();
            
            // Day Region
            String dayDD=jTextFieldDayDD.getText();
            String dayMM=jTextFieldDayMM.getText();
            String dayYYYY=jTextFieldDayYYYY.getText();
            
            // Night Region
            String nsDD=jTextFieldNSDD.getText();
            String nsMM=jTextFieldNSMM.getText();
            String nsYYYY=jTextFieldNSYYYY.getText();
            
            if(jCheckBoxSyncDate.isSelected()){
              boolean isValid =  commanFunction.isValidDate(conDD, conMM, conYYYY);
              if(isValid==false){
                  JOptionPane.showMessageDialog(null, "Invalid Date ","Error",JOptionPane.ERROR_MESSAGE);
                  return;
              }
            }

            
            if(control!=null){
                  if(commanFunction.isValidDate(conDD,conMM,conYYYY)==false){
                   JOptionPane.showMessageDialog(null, "Invalid Date in Control","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                  }

                boolean retValue = processMflagsDate(control, conDD, conMM, conYYYY,inst);
                if(retValue==true){
                  JOptionPane.showMessageDialog(null,"MFLAGS Date changed in Control Region","Done",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(day!=null){
                if(commanFunction.isValidDate(dayDD,dayMM,dayYYYY)==false){
                   JOptionPane.showMessageDialog(null, "Invalid Date in Day","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
               boolean retValue = processMflagsDate(day, dayDD, dayMM, dayYYYY,inst);
                   if(retValue==true){
                  JOptionPane.showMessageDialog(null,"MFLAGS Date changed in Day Region","Done",JOptionPane.INFORMATION_MESSAGE);
                } 
            }
            
            
            if(night!=null){
              if(commanFunction.isValidDate(nsDD,nsMM,nsYYYY)==false){
                   JOptionPane.showMessageDialog(null, "Invalid Date in Night","Error",JOptionPane.ERROR_MESSAGE);
                    return;
              }          
                 boolean retValue =   processMflagsDate(night, nsDD, nsMM, nsYYYY,inst);
                       if(retValue==true){
                  JOptionPane.showMessageDialog(null,"MFLAGS Date changed in Control Region","Done",JOptionPane.INFORMATION_MESSAGE);
                }
            }
  
  }
  
//  private boolean isValidDate(String DD,String MM,String YYYY){
//    boolean isValid = false;
//    if(DD.compareTo("31")<=0 && MM.compareTo("12")<=0){
//       isValid=true;
//    }
//    
//    return isValid;
//  }
  private String changeDateInMflags(String DD,String MM,String YYYY,String mflagsDate){
     String dc="" ;
     StringBuffer buffer = new StringBuffer(mflagsDate);
     // Set YYYY;
     char []yyyy = YYYY.toCharArray();
     char []mm = MM.toCharArray();
     char []dd = DD.toCharArray();
     int i=0;
     for(int y=8;y<12;y++){
         
       buffer.setCharAt(y, yyyy[i++]);
     
     }
     // Set Month
     i=0;
     for(int m=12;m<14;m++){
        buffer.setCharAt(m, mm[i++]);
     }
     // Set Day
     i=0;
     for(int d=14;d<16;d++){
        buffer.setCharAt(d, dd[i++]);
     } 
     
     return dc=buffer.toString();
  }
  public void setSyncUnSunc(int sync){
    if(sync==1){
    JTextFieldLimit limitDD2 = new JTextFieldLimit(2);
    JTextFieldLimit limitMM2 = new JTextFieldLimit(2);
    JTextFieldLimit limit4 = new JTextFieldLimit(4);
    
    jTextFieldConDD.setDocument(limitDD2);
    jTextFieldConMM.setDocument(limitMM2);
    jTextFieldConYYYY.setDocument(limit4);
    
    jTextFieldDayDD.setDocument(limitDD2);
    jTextFieldDayMM.setDocument(limitMM2);
    jTextFieldDayYYYY.setDocument(limit4);
    
    jTextFieldNSDD.setDocument(limitDD2);
    jTextFieldNSMM.setDocument(limitMM2);
    jTextFieldNSYYYY.setDocument(limit4);
    }else{
    
      jTextFieldConDD.setDocument(new JTextFieldLimit(2));
      jTextFieldConMM.setDocument(new JTextFieldLimit(2));
      jTextFieldConYYYY.setDocument(new JTextFieldLimit(4));
    
    jTextFieldDayDD.setDocument(new JTextFieldLimit(2));
    jTextFieldDayMM.setDocument(new JTextFieldLimit(2));
    jTextFieldDayYYYY.setDocument(new JTextFieldLimit(4));
    
    jTextFieldNSDD.setDocument(new JTextFieldLimit(2));
    jTextFieldNSMM.setDocument(new JTextFieldLimit(2));
    jTextFieldNSYYYY.setDocument(new JTextFieldLimit(4));  
    
    
    
    }
  
  
  }
  
  
  
    private boolean processMflagsDate(Session session,String DD,String MM,String YYYY,String inst){
      boolean flag=true;
     try {
                    ChannelSftp sftp =   (ChannelSftp)session.openChannel("sftp");
                    ChannelExec exec = (ChannelExec)session.openChannel("exec");
                    exec.setPty(true);
                    if(inst.equals("001")){
                     exec.setCommand("bash --login -c '. setinst 001;echo $data/file'");
                    }else if(inst.equals("002")){
                     exec.setCommand("bash --login -c '. setinst 002;echo $data/file'");
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
                     try {
                      ShellOutputStream out = new ShellOutputStream();
                      String path =   out.execOutputStream(exec.getInputStream());
                      path = path + "/" + "MFLAGS";
                         try {
                           sftp.connect();
                           String date =     out.execOutputStream(sftp.get(path));
                           String changeDate = changeDateInMflags(DD,MM,YYYY,date);
                           OutputStream stream = sftp.put(path);
                           BufferedOutputStream ou = new BufferedOutputStream(stream);
                           ou.write(changeDate.getBytes());
                           ou.flush();
                           ou.close();
//                           System.out.println(changeDate);
                         } catch (SftpException ex) {
                             Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                         }
                    } catch (IOException ex) {
                        flag=false;
                        JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                    }                   
                    }else{
                        JOptionPane.showMessageDialog(null,"Unable to connect to region" , "Error", JOptionPane.ERROR_MESSAGE);
                    }
 
                } catch (JSchException ex) {
                     flag=false;
                      JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                }
     return flag;
  }
  
  
  private boolean processMflagsDate(Session session,String DD,String MM,String YYYY){
      boolean flag=true;
     try {
                    ChannelSftp sftp =   (ChannelSftp)session.openChannel("sftp");
                    ChannelExec exec = (ChannelExec)session.openChannel("exec");
                    exec.setPty(true);
                    exec.setCommand("bash --login -c 'echo $data/file'");
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
                     try {
                      ShellOutputStream out = new ShellOutputStream();
                      String path =   out.execOutputStream(exec.getInputStream());
                      path = path + "/" + "MFLAGS";
                         try {
                           sftp.connect();
                           String date =     out.execOutputStream(sftp.get(path));
                           String changeDate = changeDateInMflags(DD,MM,YYYY,date);
                           OutputStream stream = sftp.put(path);
                           BufferedOutputStream ou = new BufferedOutputStream(stream);
                           ou.write(changeDate.getBytes());
                           ou.flush();
                           ou.close();
                           System.out.println(changeDate);
                         } catch (SftpException ex) {
                             Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                         }
                    } catch (IOException ex) {
                        flag=false;
                        JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                    }                   
                    }else{
                        JOptionPane.showMessageDialog(null,"Unable to connect to region" , "Error", JOptionPane.ERROR_MESSAGE);
                    }
 
                } catch (JSchException ex) {
                     flag=false;
                      JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Mflags.class.getName()).log(Level.SEVERE, null, ex);
                }
     return flag;
  }
  
  

  
  
  
}
