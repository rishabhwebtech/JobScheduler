/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.modal;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 1299792
 */
public class RemoteFileSystemModal {
 
    private volatile static RemoteFileSystemModal instance = null;
    private static Object mutext = new Object();
    
    public static RemoteFileSystemModal getRemoteFileSystemModal(){
        
        synchronized(mutext){
          
          if(instance==null){
           instance =  new RemoteFileSystemModal();
          }
        }
      return instance;
    }
     private JTable remoteFileSystemView;
     private JTextField currentPath;
     private JComboBox<String> mode;
     private JButton connectButton;
     private JProgressBar progress;
    public JTable getRemoteFileSystemView() {
        return remoteFileSystemView;
    }

    public void setRemoteFileSystemView(JTable remoteFileSystemView) {
        this.remoteFileSystemView = remoteFileSystemView;
    }

    public JTextField getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(JTextField currentPath) {
        this.currentPath = currentPath;
    }

    public JComboBox<String> getMode() {
        return mode;
    }

    public void setMode(JComboBox<String> mode) {
        this.mode = mode;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public void setConnectButton(JButton connectButton) {
        this.connectButton = connectButton;
    }

    public JProgressBar getProgress() {
        return progress;
    }

    public void setProgress(JProgressBar progress) {
        this.progress = progress;
    }
     

    
    
    
}
