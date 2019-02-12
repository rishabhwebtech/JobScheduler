/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.modal;

import javax.swing.JTextPane;

/**
 *
 * @author 1299792
 */
public class RunningStatusModal {
    private static RunningStatusModal instance;
    private RunningStatusModal(){}
    public static RunningStatusModal getRunningStatus(){
      if(instance==null){
        instance = new RunningStatusModal();
      }
      return instance;
    }
    
    
    private JTextPane outputScreen;

    public JTextPane getOutputScreen() {
        return outputScreen;
    }

    public void setOutputScreen(JTextPane outputScreen) {
        this.outputScreen = outputScreen;
    }
    
    
    
    
}
