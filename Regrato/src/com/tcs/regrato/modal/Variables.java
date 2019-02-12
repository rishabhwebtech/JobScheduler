/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.modal;

import com.tcs.regrato.xml.TraceReader;
import com.tcs.rtestingframework.tcl.tag.Field;
//import java.util.HashMap;
import org.apache.commons.collections.map.MultiKeyMap;
import org.w3c.dom.Element;

/**
 *
 * @author 1299792
 */
public class Variables {
    
    private static Variables getVariables=null;
//    private  HashMap<String,Field> variable;  
    private  MultiKeyMap variable;  
    private Variables(){}
    public static Variables getVariable(Element rootElement){
      if(getVariables==null){
        getVariables  = new Variables();
        getVariables.variable = getVariables.loadVariables(rootElement);
      }
      return getVariables;
    }
    
//    private HashMap<String,Field> loadVariables(Element rootElement){
     private MultiKeyMap loadVariables(Element rootElement){
        TraceReader reader = new TraceReader(rootElement);
        return reader.getLoadField();
    }

    public Field getVariable(String scope,String var) {
        return (Field)this.variable.get(scope,var);
    }

//    public void setVariable(HashMap<String, Field> variable) {
    public void setVariable(MultiKeyMap variable) {
        this.variable = variable;
    }
    
    
    
}
