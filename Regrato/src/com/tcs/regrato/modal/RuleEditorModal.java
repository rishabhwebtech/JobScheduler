/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.modal;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author 1299792
 */
public class RuleEditorModal {
    private static RuleEditorModal instance = null;
    private RuleEditorModal(){}
    public static RuleEditorModal getRuleEditorModal(){
        if(instance==null){
           instance = new RuleEditorModal();
        } 
        return instance;   
    }
    
    
    
    private JTextArea codeEditor;
    private JLabel row;
    private JLabel col;
    private JLabel fileNamePath;

    public JLabel getRow() {
        return row;
    }

    public void setRow(JLabel row) {
        this.row = row;
    }

    public JLabel getCol() {
        return col;
    }

    public void setCol(JLabel col) {
        this.col = col;
    }

    public JLabel getFileNamePath() {
        return fileNamePath;
    }

    public void setFileNamePath(JLabel fileNamePath) {
        this.fileNamePath = fileNamePath;
    }

   

    public JTextArea getCodeEditor() {
        return codeEditor;
    }

    public void setCodeEditor(JTextArea codeEditor) {
        this.codeEditor = codeEditor;
    }
    
    
    
    
}
