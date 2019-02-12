/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.xml;

import com.tcs.rtestingframework.tcl.tag.Click;
import com.tcs.rtestingframework.tcl.tag.Field;
import com.tcs.rtestingframework.tcl.tag.Put;
import com.tcs.rtestingframework.tcl.tag.Run;
import com.tcs.rtestingframework.tcl.tag.Select;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.collections.map.MultiKeyMap;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author 1299792
 */
public class TraceReader {
    
    Element rootElement;
//    HashMap<String,Field> fieldVariables ;
    MultiKeyMap fieldVariables;
    
    public TraceReader(Element rootElement){
       this.rootElement = rootElement;
//       this.fieldVariables = new HashMap<>();
       this.fieldVariables = new MultiKeyMap();
       loadField();

    }
    public Field getField(String scope,String variableName){
      Field field = null;
       
       field  = (Field)this.fieldVariables.get(scope,variableName);
      
      return field;
     
    }
//    public HashMap<String,Field> getLoadField(){
    public MultiKeyMap getLoadField(){
        return this.fieldVariables;
    }
    private void loadField(){
        Element declareElement =  (Element)this.rootElement.getElementsByTagName("tcl:declare").item(0);
        NodeList scopeOfVar    =   declareElement.getElementsByTagName("tcl:declareScope");
        int lenscopeOfVar = scopeOfVar.getLength();
       for(int indexOfScope = 0;indexOfScope<lenscopeOfVar;indexOfScope++){
          Element scopeOfVarElement = (Element)scopeOfVar.item(indexOfScope);
          String scopeName = scopeOfVarElement.getAttribute("name");
          NodeList list = scopeOfVarElement.getElementsByTagName("tcl:field");
//         NodeList list = declareElement.getElementsByTagName("tcl:field");
         int length = list.getLength();
         for(int i =0;i<length;i++){
            Element el =   (Element)list.item(i);
            Field field = new Field();
            String by = el.getAttribute("by");
            String mandatory = el.getAttribute("mandatory");
            String map = el.getAttribute("map");
            String name = el.getAttribute("name");
            String type = el.getAttribute("type");
           
            field.setBy(by);
            field.setMandatory(Boolean.valueOf(mandatory));
            field.setMap(map);
            field.setName(name);
            field.setType(type);
            this.fieldVariables.put(scopeName,name, field);
         }
        }
    }
    
    public String[] getTestCasesName(){
       String testCases[] = null;
       
       
        NodeList testCasesNodeList =   rootElement.getElementsByTagName("tcl:cases");
        Element testCasesElement = (Element)testCasesNodeList.item(0);
        NodeList el = testCasesElement.getElementsByTagName("tcl:case");
       int length = el.getLength();
       testCases = new String[length];
       for(int i =0;i<length;i++){
       
            Element element = (Element)el.item(i);
            String name = element.getAttribute("name");
            testCases[i]=name;
       }
        
       return testCases;
    }
    
    
    
    public HashMap<String,List<Object>> getTraceData(){
        
        HashMap<String,List<Object>> map = new HashMap<>();
        if(this.rootElement!=null){
       
        NodeList testCasesNodeList =   rootElement.getElementsByTagName("tcl:cases");
        Element testCasesElement = (Element)testCasesNodeList.item(0);
        NodeList el = testCasesElement.getElementsByTagName("tcl:case");
         int length = el.getLength();
 
           for(int i =0;i<length;i++){
              List<Object> tags = new ArrayList<>();
              Element element = (Element)el.item(i);
              String name = element.getAttribute("name");
               Node child=null;
//              while((child=element.get)!=null){
              NodeList childLists = element.getChildNodes();
              int childLength = childLists.getLength();
              for(int j =0;j<childLength;j++){
                  child = childLists.item(j);
                 if(child.getNodeType() == Node.TEXT_NODE){
                   continue;
                 }
                 Element childElement = (Element)child;
                 
                 String tagName = childElement.getTagName();
                 String var = "";
                 String location = "";
                 String value    = "";
                  var = childElement.getAttribute("var");
                  location = childElement.getAttribute("location");
                  value    = childElement.getAttribute("value");               
                 switch(tagName){
                 
                     case "tcl:click":

                         Click click = new Click();
                         click.setLocation(location);
                         click.setVariable(var);
                         tags.add(click);
                         break;
                     case "tcl:put" : 
                         Put put = new Put();
                         put.setValue(value);
                         put.setVariable(var);
                         put.setLocation(location);
                         tags.add(put);
                         break;
                     case "tcl:select" : 
                         Select select = new Select();
                         select.setValue(value);
                         select.setLocation(location);
                         select.setVariable(var);
                          tags.add(select);
                         break;
                     case "tcl:run":
                         Run run = new Run();
                         run.setValue(value);
                         run.setLocation(location);
                         run.setVar(var);
                         tags.add(run);
                      break;
                 }
                    
              }
              
              map.put(name,tags);
            }
            
        
        }
        
        return map;
    
    }
    
}
