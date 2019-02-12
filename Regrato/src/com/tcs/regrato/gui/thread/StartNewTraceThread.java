/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.tcs.jpath.Jpath;
import com.tcs.regrato.modal.GuiModal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

/**
 *
 * @author 1299792
 */
public class StartNewTraceThread extends SwingWorker<Object, Object>{

    String fileName;
     GuiModal guiModal;
    public StartNewTraceThread(String fileName){
      this.fileName = fileName;
      this.guiModal = GuiModal.getGuiModal();
    }
    
    
    @Override
    protected Object doInBackground() throws Exception {
        Jpath path = new Jpath(this.fileName);
//        JMenuItem stopTrace = guiModal.getStopTraceMenuItem();
//        JProgressBar bar = this.guiModal.getProgresBar();
//        JLabel workLable = this.guiModal.getShowOutput();
//        JLabel progressBarLabel = this.guiModal.getProgramBarLabel();
//        workLable.setVisible(true);
//        progressBarLabel.setVisible(true);
//        bar.setIndeterminate(true);
//        bar.setVisible(true);
//        workLable.setText("Tracing ...");
//        JOptionPane.showMessageDialog(null, "Started", "Info", JOptionPane.INFORMATION_MESSAGE);
//        stopTrace.setVisible(true);
        publish(new ArrayList<Object>().add("update"));
        path.traceEvents();
        return null;
    }

    @Override
    protected void process(List<Object> chunks) {
        JMenuItem stopTrace = guiModal.getStopTraceMenuItem();
        JProgressBar bar = this.guiModal.getProgresBar();
        JLabel workLable = this.guiModal.getShowOutput();
        JLabel progressBarLabel = this.guiModal.getProgramBarLabel();
        workLable.setVisible(true);
        progressBarLabel.setVisible(true);
        bar.setIndeterminate(true);
        bar.setVisible(true);
        workLable.setText("Tracing ...");
        JOptionPane.showMessageDialog(null, "Started", "Info", JOptionPane.INFORMATION_MESSAGE);
        stopTrace.setVisible(true);
        JScrollPane pane = guiModal.getOutputScrollPane();
        pane.setVisible(true);
        
        
    }

    @Override
    protected void done() {
        
//       JScrollPane pane = guiModal.getOutputScrollPane();
//       pane.setVisible(false);
        
        
    }
    
    
    
}
