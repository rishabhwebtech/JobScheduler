/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.tcl.parser;

import org.w3c.dom.Element;

/**
 *
 * @author 1299792
 */
public abstract class TCLRules {
    
    
    public static final String COMMAND[]={"click","put","field","select",
                                             "run","wait","screenshot","if",
                                             "else","alert"};
    
    
    
    public static final String MODE[]={"C","D","N"};
    
    
    
    public static final String ANNOTAION[]={"@alert","@retValue"};
    
}
