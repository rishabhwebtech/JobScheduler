/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcs.regrato.resources;

/**
 *
 * @author 1299792
 */
public class CardFileNotSupportedException extends Exception{
    public CardFileNotSupportedException(String msg){
       super(msg);
    }
}
