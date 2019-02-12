/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tcs.rtestingframework.exception;

/**
 *
 * @author 1299792
 */
public class InvalidAttributeValueException extends Exception{
    
    public InvalidAttributeValueException(String errorMessage){
       super(errorMessage);
    }
    
}
