/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.gui.thread;

import com.tcs.jpath.Jpath;
import com.tcs.jpath.exception.BrowserNotSupported;
import com.tcs.regrato.event.MenuHandler;
import com.tcs.regrato.modal.GuiModal;
import com.tcs.rtestingframework.tcl.parser.ExcelToTCL;
import com.tcs.rtestingframework.tcl.parser.TCLParser;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.xml.transform.TransformerException;
import jxl.write.WriteException;

/**
 *
 * @author 1299792
 */
public class StopNewTraceThread extends SwingWorker<String, Object>{

    GuiModal guiModal;
    public StopNewTraceThread(){
      this.guiModal = GuiModal.getGuiModal();
    }
    
    @Override
    protected String doInBackground() throws Exception {
        
           
            JLabel    traceName = guiModal.getTraceNameValue();

            Jpath path = new Jpath(traceName.getText());
            
            try {
                path.stopTraceEvents();
            } catch (IOException ex) {
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BrowserNotSupported ex) {
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriteException ex) {
                Logger.getLogger(MenuHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        return traceName.getText();
    }

    @Override
    protected void done() {
        
        try {
             JMenuItem stopTrace = guiModal.getStopTraceMenuItem();
            JLabel    showOutput = guiModal.getShowOutput();
            JLabel    progressLabel = guiModal.getProgramBarLabel();
            JProgressBar progress = guiModal.getProgresBar();
            
            
            showOutput.setText(" ");
            progressLabel.setVisible(false);
            progress.setIndeterminate(false);
            progress.setVisible(false);
            stopTrace.setVisible(false);      
            
            
            JLabel    traceName = guiModal.getTraceNameValue();
            String fileName = (String)get();
            fileName = fileName + ".xls";
            ExcelToTCL convertToXml = new ExcelToTCL(fileName);
            TCLParser parser = new TCLParser();
            org.w3c.dom.Document doc = convertToXml.convertExcelToXml();
            convertToXml.setRootElementNameAttr(fileName);
            parser.saveXml(fileName+".xml", doc);
            traceName.setText(" ");
        } catch (InterruptedException ex) {
            Logger.getLogger(StopNewTraceThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(StopNewTraceThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StopNewTraceThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(StopNewTraceThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
       
}
