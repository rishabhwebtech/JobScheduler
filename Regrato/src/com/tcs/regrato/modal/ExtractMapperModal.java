/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.modal;

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
public class ExtractMapperModal {
 
    private volatile static ExtractMapperModal instance = null;
    private static Object mutext = new Object();
    
    public static ExtractMapperModal getExtractMapperModal(){
        
        synchronized(mutext){
          
          if(instance==null){
           instance =  new ExtractMapperModal();
          }
        }
      return instance;
    }
    private JComboBox<String> isDelCompobox;
    private JTextField        extractFile;
    private JTextField        mapperFile;
    private JTabbedPane       tabbedPane;
    private JTextField        currentLine;
    private JTextArea         oneLineAtATime;
    private JTextField        totalLineNumber;
    private JProgressBar      progressBar;
    public JComboBox<String> getIsDelCompobox() {
        return isDelCompobox;
    }

    public void setIsDelCompobox(JComboBox<String> isDelCompobox) {
        this.isDelCompobox = isDelCompobox;
    }

    public JTextField getExtractFile() {
        return extractFile;
    }

    public void setExtractFile(JTextField extractFile) {
        this.extractFile = extractFile;
    }

    public JTextField getMapperFile() {
        return mapperFile;
    }

    public void setMapperFile(JTextField mapperFile) {
        this.mapperFile = mapperFile;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public JTextField getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(JTextField currentLine) {
        this.currentLine = currentLine;
    }

    public JTextArea getOneLineAtATime() {
        return oneLineAtATime;
    }

    public void setOneLineAtATime(JTextArea oneLineAtATime) {
        this.oneLineAtATime = oneLineAtATime;
    }

    public JTextField getTotalLineNumber() {
        return totalLineNumber;
    }

    public void setTotalLineNumber(JTextField totalLineNumber) {
        this.totalLineNumber = totalLineNumber;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    
    
    
}
