/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.modal;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;

/**
 *
 * @author 1299792
 */
public class CommandEditorModal {
    private static volatile CommandEditorModal instance = null;
    private static Object mutex = new Object();
    
    private CommandEditorModal(){}
    
    public  static CommandEditorModal getCommandEditorModal(){
        CommandEditorModal result=instance;
        if(instance==null){
         synchronized(mutex){
          result=instance;
          if(result==null){
            instance = result = new CommandEditorModal();
          }
         }
            
//         instance = new CommandEditorModal();
                
         }
        
    return instance;
    }
    
    
    private JTable commandEditorTable ;
    private JTabbedPane mainTabedPaneOfCommandEditor;
    private JFrame commandEditorJFrame;
    private JLabel commandFileNamedLabel;
    private JLabel commandFileLabel;
    private JMenuItem appendRowMenuItem;
    private JMenuItem appendRowAtTopMenuItem;
    private JMenuItem appendRowAtMenuItem;
    private JTextPane outputStatPane;
    private JScrollPane outputStatPaneScrollPane;
    private JToggleButton hostOnly;
    private String filePath;
    private JTextField description;
    public JFrame getCommandEditorJFrame() {
        return commandEditorJFrame;
    }

    public  void setCommandEditorJFrame(JFrame commandEditorJFrame) {
        this.commandEditorJFrame = commandEditorJFrame;
    }
    public JTabbedPane getMainTabedPaneOfCommandEditor() {
        return mainTabedPaneOfCommandEditor;
    }

    public void setMainTabedPaneOfCommandEditor(JTabbedPane mainTabedPaneOfCommandEditor) {
        this.mainTabedPaneOfCommandEditor = mainTabedPaneOfCommandEditor;
    }
    
    
    
    public JTable getCommandEditorTable() {
        return commandEditorTable;
    }

    public void setCommandEditorTable(JTable commandEditorTable) {
        this.commandEditorTable = commandEditorTable;
    }

    public JLabel getCommandFileNamedLabel() {
        return commandFileNamedLabel;
    }

    public void setCommandFileNamedLabel(JLabel commandFileNamedLabel) {
        this.commandFileNamedLabel = commandFileNamedLabel;
    }

    public JLabel getCommandFileLabel() {
        return commandFileLabel;
    }

    public void setCommandFileLabel(JLabel commandFileLabel) {
        this.commandFileLabel = commandFileLabel;
    }

    public JMenuItem getAppendRowMenuItem() {
        return appendRowMenuItem;
    }

    public void setAppendRowMenuItem(JMenuItem appendRowMenuItem) {
        this.appendRowMenuItem = appendRowMenuItem;
    }

    public JMenuItem getAppendRowAtTopMenuItem() {
        return appendRowAtTopMenuItem;
    }

    public void setAppendRowAtTopMenuItem(JMenuItem appendRowAtTopMenuItem) {
        this.appendRowAtTopMenuItem = appendRowAtTopMenuItem;
    }

    public JMenuItem getAppendRowAtMenuItem() {
        return appendRowAtMenuItem;
    }

    public void setAppendRowAtMenuItem(JMenuItem appendRowAtMenuItem) {
        this.appendRowAtMenuItem = appendRowAtMenuItem;
    }

    public JTextPane getOutputStatPane() {
        return outputStatPane;
    }

    public void setOutputStatPane(JTextPane outputStatPane) {
        this.outputStatPane = outputStatPane;
    }

    public JScrollPane getOutputStatPaneScrollPane() {
        return outputStatPaneScrollPane;
    }

    public void setOutputStatPaneScrollPane(JScrollPane outputStatPaneScrollPane) {
        this.outputStatPaneScrollPane = outputStatPaneScrollPane;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public JToggleButton getHostOnly() {
        return hostOnly;
    }

    public void setHostOnly(JToggleButton hostOnly) {
        this.hostOnly = hostOnly;
    }

    public JTextField getDescription() {
        return description;
    }

    public void setDescription(JTextField description) {
        this.description = description;
    }
    
 
}
