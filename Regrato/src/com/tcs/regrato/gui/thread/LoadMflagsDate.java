/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;
import com.tcs.jpath.modal.JConfig;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.resources.SSHSessionFactory;
import com.tcs.regrato.util.Comman;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author 1299792
 */
public class LoadMflagsDate extends SwingWorker<Integer, String>{

    private JLabel mfLagsDate;
    private String inst;
    private JComboBox<String> allInst;
    private org.apache.log4j.Logger log;
    public LoadMflagsDate(JLabel mf,String inst,JComboBox allInst) {
      this.mfLagsDate=mf;
      this.inst=inst;
      this.allInst=allInst;
      this.log = new Comman().getLogger();
    }

    
    
    @Override
    protected Integer doInBackground() throws Exception {
        allInst.setEnabled(false);
        Session session =   SSHSessionFactory.getSSHSession(new JConfig().getBranches().getBranches().get(0), "C");
        String controlMflagsDate = null;
        ChannelExec exec = (ChannelExec)session.openChannel("exec");
        exec.setPty(true);
        Path p = new Path();
        String con= p.getContent(session,"MFLAGS", inst);
        controlMflagsDate=con;
        if(controlMflagsDate!=null){
         String DD = controlMflagsDate.substring(14, 16);
         String MM = controlMflagsDate.substring(12, 14);
         String YYYY = controlMflagsDate.substring(8, 12);
         String date = DD+"/"+MM+"/"+YYYY;
         publish(date);
        }
        
        
        return 0;
    }

    @Override
    protected void process(List<String> chunks) {
        mfLagsDate.setText(chunks.get(0));
    }

    @Override
    protected void done() {
      allInst.setEnabled(true);
    }
    
}
