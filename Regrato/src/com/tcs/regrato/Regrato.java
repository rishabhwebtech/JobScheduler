/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato;


import com.tcs.regrato.gui.MainWindow;
import com.tcs.regrato.modal.GuiModal;
import com.tcs.regrato.resources.Path;
import com.tcs.regrato.util.Comman;
import com.tcs.regrato.util.MessageConsole;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 1299792
 */
public class Regrato {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MainWindow window = new MainWindow();
        String log4jPath = Path.LOG4J_PATH;
        System.setProperty("log4j.configurationFile", log4jPath);
        Comman comman  = new Comman();
        org.apache.log4j.Logger log = comman.getLogger();
        try {
           Path p  = new Path();
           String fullPath=p.findPath(Path.TRACED_DATA);
           String runLogsPath = p.findPath(Path.HOST_OUT);
           String extractPath = p.findPath(Path.EXTRACT_OUTPUT);
           String s=   comman.readSemaphore("isFirstTime");
           if(s.equals("true")){
            p.setPath(Path.TRACED_DATA, fullPath);
            p.setPath(Path.HOST_OUT, runLogsPath);
            p.setPath(Path.EXTRACT_OUTPUT,extractPath);
            comman.writeSemaphore("isFirstTime", "false");
           }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Failed to create Semaphore","Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Regrato.class.getName()).log(Level.SEVERE, null, ex);
            log.error(ex);
            System.exit(1);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                window.setTitle("Regrato");
                window.setLocationRelativeTo(null);
                window.setVisible(true);
        
            }
        });
        GuiModal guiModal = GuiModal.getGuiModal();
        MessageConsole console = new MessageConsole(guiModal.getOutputLogsPane(), true);
        console.redirectOut();
      

    }
    
}
