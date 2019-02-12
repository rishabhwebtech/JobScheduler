/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.tcl.tag.defination;

import com.tcs.rtestingframework.tcl.action.PerformAction;
import com.tcs.rtestingframework.tcl.exception.InvalidSyntaxException;
import org.w3c.dom.Element;

/**
 *
 * @author 1299792
 */
public interface TagDefination {
    
    
    public void putDefination(Element element);
    public void clickDefination(Element element);
    public void screenshortDefination(Element element);
    public void pageWaitDefination(Element element);
    public void ifDefination(Element element,PerformAction action) throws InvalidSyntaxException;
    public void printDefination(Element element);
//    public void elseDefination(Element element,PerformAction action);
    
}
