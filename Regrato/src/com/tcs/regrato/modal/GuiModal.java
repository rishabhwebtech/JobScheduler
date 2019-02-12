/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.modal;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 *
 * @author 1299792
 */
public class GuiModal {
  private static GuiModal instance = null;
  private GuiModal(){}
  public static GuiModal getGuiModal(){
     if(instance ==null){
       instance=new GuiModal();
     }
     return instance;
  }
  private JMenuItem stopTraceMenuItem;
  private JLabel traceNameValue;
  private JLabel traceNameLabel;
  private JLabel showOutput;
  private JLabel programBarLabel;
  private JProgressBar progresBar;
  private JTextPane  outputLogsPane;
  private JScrollPane outputScrollPane;
    public JMenuItem getStopTraceMenuItem() {
        return stopTraceMenuItem;
    }

    public void setStopTraceMenuItem(JMenuItem stopTraceMenuItem) {
        this.stopTraceMenuItem = stopTraceMenuItem;
    }

    public JLabel getTraceNameValue() {
        return traceNameValue;
    }

    public void setTraceNameValue(JLabel traceNameValue) {
        this.traceNameValue = traceNameValue;
    }

    public JLabel getTraceNameLabel() {
        return traceNameLabel;
    }

    public void setTraceNameLabel(JLabel traceNameLabel) {
        this.traceNameLabel = traceNameLabel;
    }

    public JLabel getShowOutput() {
        return showOutput;
    }

    public void setShowOutput(JLabel showOutput) {
        this.showOutput = showOutput;
    }

    public JLabel getProgramBarLabel() {
        return programBarLabel;
    }

    public void setProgramBarLabel(JLabel programBarLabel) {
        this.programBarLabel = programBarLabel;
    }

    public JProgressBar getProgresBar() {
        return progresBar;
    }

    public void setProgresBar(JProgressBar progresBar) {
        this.progresBar = progresBar;
    }

    public JTextPane getOutputLogsPane() {
        return outputLogsPane;
    }

    public void setOutputLogsPane(JTextPane outputLogsPane) {
        this.outputLogsPane = outputLogsPane;
    }

    public JScrollPane getOutputScrollPane() {
        return outputScrollPane;
    }

    public void setOutputScrollPane(JScrollPane outputScrollPane) {
        this.outputScrollPane = outputScrollPane;
    }
  
  
}
